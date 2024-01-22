package com.learningjava.gaming;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	// JFrame is an class responsible for the frame.
	public GameFrame() {
		Board board = new Board();
		
		// constructor is created to initialize the object
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //so that after closing it get remove from the memory.
        // by default the java uses this so need not to specify
		setTitle("Game Dev in Java");
        setSize(1500,820);// setting the size of the frame. 
        setLocationRelativeTo(null); //first set the size then center it.
        setResizable(false); // so that resizing of the frame get disabled.
        add(board); // added board inside the    frame.
        setVisible(true);// so that frame is visible.
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
             new GameFrame(); // object created  /calling the constructor.
        
        
	}

}   
