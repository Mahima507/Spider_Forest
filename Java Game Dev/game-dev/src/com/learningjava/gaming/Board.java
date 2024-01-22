package com.learningjava.gaming;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.learningjava.gaming.sprites.Enemy;
import com.learningjava.gaming.sprites.Player;

public class Board extends JPanel{ // JPanel is an whole soul responsible class for painting things. 
	
	Timer timer;
	
	BufferedImage backgroundImage; // for storing the image.
	
	Player player;
	
	Enemy enemies[] = new Enemy[3];
	
	public Board(){
		//constructor for initialization 
	   
	  setSize(1500,820);
      loadBackgroungImage();  
      player = new Player();
      loadEnemies();
      gameLoop();
      bindEvents(); 
      setFocusable(true); // means 1st focus comes on board then on bindEvents.
	}
	
	private void gameOver(Graphics pen) {
		if(player.outOfScreen()) {
			pen.setFont(new Font("times",Font.BOLD, 30));
			pen.setColor(Color.RED);
			pen.drawString("Game Win",1500/2,820/2);
			timer.stop();
			return;
		}
        for(Enemy enemy: enemies) {
			if(isCollide(enemy)) {
				pen.setFont(new Font("times",Font.BOLD, 30));
				pen.setColor(Color.RED);
				pen.drawString("Game Over",1500/2,820/2);
				timer.stop();
			}
		}
	}
	
	private boolean isCollide(Enemy enemy) {
		int xDistance = Math.abs(player.x - enemy.x);
		int yDistance = Math.abs(player.y - enemy.y);
		int maxH = Math.max(player.h, enemy.h);
		int maxW = Math.max(player.w, enemy.w);
		return xDistance <= maxW-200 && yDistance <= maxH-100;
	}
	
	private void bindEvents() {
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
				player.speed = 10;
				}
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.speed = -10;
					
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				player.speed = 0;
			}
			
			
		});
	}
	
	private void loadEnemies() {
		int x = 400;
		int gap =400;
		int speed =5;
		for(int i=0 ; i< enemies.length; i++) {
			enemies[i] = new Enemy(x,speed);
			x = x + gap;
			speed = speed + 5; 
			
		}
	}
	
	private void gameLoop() {
		timer = new Timer(50,(e)->repaint());  // lambda expression. repaint() will call paintComponent()
	    timer.start();
	}
	
	
	private void loadBackgroungImage(){
	// created function for loading the background image into the memory by using ImageIO class.	
	  try { 
	     backgroundImage =	ImageIO.read(Board.class.getResource("game-bg.jpeg"));
	   }   
	  catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("Background Image Not Found... ");
		System.exit(0);// so that application will get closed.
		e.printStackTrace();
     }
  }
	
	
	private void printEnemies(Graphics pen) {
	  for(Enemy enemy : enemies) {
		  enemy.draw(pen);
		  enemy.move();
	  }
	}
	
	
    //for painting we have an special function called paintComponent() 
    @Override 
	public void paintComponent(Graphics pen){
    	// paintComponent is call only one time so that's why we created gameLoop method by Timer class
    	//all printing logic will be here
     // for painting we need a pen and for that we have Graphics class from awt. 
    	 super.paintComponent(pen); //clean up 
    	 pen.drawImage(backgroundImage,0,0,1500,820,null);
         player.draw(pen);
         printEnemies(pen);
         player.move();
         gameOver(pen);
    }
}
