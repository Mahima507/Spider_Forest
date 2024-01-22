package com.learningjava.gaming.sprites;

import javax.swing.ImageIcon;

public class Player extends Sprite {
     
	//image is not fixed so ImageIcon is used.
     public Player(){
    	  w = 200;
    	  h =200;
    	  x=50;
    	  y=500;
    	  image = new ImageIcon(Player.class.getResource("player.gif"));
    	  // for gif the ImageIcon method is well suited.
      }
     
     public void move() {
    	 x = x + speed;
     }
     
     public boolean outOfScreen() {
    	
    	 return x > 1500;  
     }
}
