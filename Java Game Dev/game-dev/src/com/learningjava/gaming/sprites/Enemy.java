package com.learningjava.gaming.sprites;

import javax.swing.ImageIcon;

public class Enemy extends Sprite{
    
	public Enemy(int x, int speed) {
		// constructor.
	    y = 30 ;
		this.x = x;
		this.speed = speed;
		w = 300;
		h = 300;
		image = new ImageIcon(Enemy.class.getResource("spider-enemy.gif"));
		
	}
    public void move() {
    	if(y>820) {
    		y = 0;
    	}
		y = y + speed;
	}
	
}
