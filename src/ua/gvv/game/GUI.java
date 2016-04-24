package ua.gvv.game;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame{
	
	private JPanel bGround;
	private GamePanel gPanel;
	private boolean[] side = new boolean[4];
	private int x,y;
	private Thread followingGame;
	GUI(){
		x = 0;
		y = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(500,600);
		setVisible(true);
		
		bGround = new JPanel();
		bGround.setLayout(null);
		bGround.setBackground(Color.DARK_GRAY);
		add(bGround);
		

		gPanel = new GamePanel();
		gPanel.setBounds(25, 25, 360, 520);
		bGround.add(gPanel);
		followingGame = new Thread(gPanel,"Game");
		followingGame.start();
		try {
			followingGame.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		KeyAdapter kA = new KeyAdapter() {
		
			public void keyTyped(KeyEvent e) {
				
				super.keyTyped(e);
				setFocusable(true);
				if (e.getKeyChar()=='d'){
					System.out.println("Action");
					gPanel.rightSide = true;
					gPanel.leftSide = false;
					gPanel.topSide = false;
					gPanel.bottomSide = false;
										
				}
				if (e.getKeyChar()=='a'){
					
					gPanel.rightSide = false;
					gPanel.leftSide = true;
					gPanel.topSide = false;
					gPanel.bottomSide = false;
				}
				if (e.getKeyChar()=='w'){
					
					gPanel.rightSide = false;
					gPanel.leftSide = false;
					gPanel.topSide = true;
					gPanel.bottomSide = false;
				}
				if (e.getKeyChar()=='s'){
					
					gPanel.rightSide = false;
					gPanel.leftSide = false;
					gPanel.topSide = false;
					gPanel.bottomSide = true;
				}
					
			}
		};
		
		
		
		
	}
	
	
	
}
