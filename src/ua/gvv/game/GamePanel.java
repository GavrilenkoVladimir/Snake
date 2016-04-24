package ua.gvv.game;

import java.awt.Color;
import java.awt.Graphics;
import java.nio.channels.Pipe.SinkChannel;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class GamePanel extends JPanel implements Runnable{
	
	protected boolean rightSide = false;
	protected boolean leftSide = false;
	protected boolean topSide = false;
	protected boolean bottomSide = false;
	private ArrayList<int[]> coordinatesList;
	private int[] startCoord ={0,0};
	private int foodCoordX, foodCoordY;
	private int score = 0;
	private int x = 0, y = 0;
	GamePanel(){
		super();
		setSize(360, 520);// 18/26 squares
		setBorder(BorderFactory.createLineBorder(Color.YELLOW,2));
		coordinatesList = new ArrayList<int[]>();
		coordinatesList.add(startCoord);
		foodCoordX = (int)(Math.random()*(17))*20;
		foodCoordY = (int) (Math.random()*(25))*20;
			
	}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
/*check*/	System.out.println(coordinatesList.get(0)[0] +" and " + coordinatesList.get(0)[1]);
			
			for(int [] cord:coordinatesList){
				
				g.setColor(Color.DARK_GRAY);
				g.fillRect(cord[0], cord[1], 20, 20);
				g.setColor(Color.WHITE);
				g.drawRect(cord[0], cord[1], 20, 20);
				g.setColor(Color.GREEN);
				g.fillRect(foodCoordX, foodCoordY, 20, 20);
				}
			
		}
		
		public synchronized void anime(int x, int y){
			System.out.println("Doesn't call" + " " + x + y);
			int [] move = {x,y};
			if (foodCoordX == x && foodCoordY == y){
				score++;
				foodCoordX = (int)(Math.random()*(17))*20;
				foodCoordY = (int) (Math.random()*(25))*20;
				coordinatesList.add(move);
/*check*/		System.out.println(coordinatesList.size());
				}

			if (coordinatesList.size()>=2){
				for(int i = coordinatesList.size()-1; i > 0 ; i--){
					coordinatesList.set(i,coordinatesList.get(i-1));	
				}
			}coordinatesList.set(0,move);
			
			repaint();
		}
		
		
		public void run() {
			System.out.println("run total");

			while(true){
			if (rightSide){
				while(rightSide){
					System.out.println("right");
					if (y <= 340){ y-=20;
					anime(x,y);}
					}
			}
			if (leftSide){
				
				while(leftSide){
					if(x>=20){ x-=20;
					anime(x,y);}
					}
			}
			if (topSide){
				
				while(topSide){
					if (y >= 20){ y-=20;
					anime(x,y);}
					}
			}
			if (bottomSide){
				
				while(bottomSide){
					if(y <= 480) {y+=20;
					anime(x,y);}
			
					}
			}
		}
			}
}
