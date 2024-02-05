import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;

public class GameLogic extends GameArea{
	private static final int FRAMERATE = 60;
	
	private Timer timer = new Timer();
	
	private int score = 0;
	private double moveMulti = 1;
	private static final int FOLLOWSPEED = 25;
	private static int difficulty = 1;
	
	private JButton b;
	private JLabel lblScore;
	
	public GameLogic(int w, int h) {
		super(w, h);
		
		b = new JButton("Replay");  
		b.setBounds(w / 2 - 72, h / 3 - 15, 96, 30);
		b.setVisible(false);
		add(b); 
		
		lblScore = new JLabel("Score: 00");
		lblScore.setBounds(w - 150, 0, 100, 25);
		lblScore.setFont(new Font(lblScore.getFont().getName(), Font.BOLD, 20)); 
		lblScore.setForeground(Color.WHITE);
		lblScore.setBackground(Color.BLACK);
		add(lblScore);
		
		b.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)	{
		        restartGame();
		        b.revalidate();
		        b.setVisible(false);
		        repaint();
		        
		        gameState = 0;
		        
		    }
		}); 
	}
	
	public void startGame() {
		updateCenter();
		lblScore.setText("Score: 00");
		timer.scheduleAtFixedRate(new Task(), 0, 1000/FRAMERATE);
	}
	
	public void restartGame() {
		defineRectangles();
		startGame();
	}
	
	private class Task extends TimerTask {
		public void run() {
			if (gameState == 1) {
				tick++;
				
				
				if(tick % 60 == 0) {
					score++;
					if(score < 10) {
						lblScore.setText("Score: 0" + score);
					} else {
						lblScore.setText("Score: " + score);
					}
				}
				
				if(tick % (FRAMERATE*5) == 0) {//Speed up every 5 seconds
					moveMulti += 0.25;
				}
				
				Enemies[0].addPos(new Point(-(Enemies[0].getCenterX() - Ship.getCenterX()) / (int)(FOLLOWSPEED / (moveMulti / 2)), -(Enemies[0].getCenterY() - Ship.getCenterY()) / (int)(FOLLOWSPEED / (moveMulti / 2))));
				for(int i = 1; i < Enemies.length; i++) {
					Enemies[i].addPos(Enemies[i].getVel(), moveMulti);
				}
				
				for(int i = 0; i < bombs.length; i++) {
					if(bombs[i].getExp()) {
						bombs[i].explode();
					}
				}
				
				updateCenter();
				checkCollisions();
				checkEnemyBounce();
				repaint();
				
			} else {
				tick = 0;
				score = 0;
				moveMulti = 1;
				b.setVisible(true);
				
				cancel();
			}
		}
	}
	
	
	
	//check Circle Collisions
	private void checkCollisions() {
		//Check if ship goes out of bounds
		if(Ship.getX() <= Bound.getX() || Ship.getY() <= Bound.getY()
				|| Ship.getX() + Ship.getWidth() >= Bound.getMaxX() || Ship.getY() + Ship.getHeight() >= Bound.getMaxY()) {
			gameState = 2;
			
		//Check if ship intersects with other shapes
		} else {
			for(int i = 0; i < Enemies.length; i++) {
				if(Ship.getWidth() / 2 + Enemies[i].getWidth() / 2 >= Math.sqrt(Math.pow(Enemies[i].getCenterX() - Ship.getCenterX(), 2) + Math.pow(Enemies[i].getCenterY() - Ship.getCenterY(), 2))) {
					gameState = 2;
					return;
				}
			}
		}
	}
	
	//check Rectangle Collisions
	private void checkCollisionsRect() {
		//Check if ship goes out of bounds
		if(Ship.getX() <= Bound.getX() || Ship.getY() <= Bound.getY()
				|| Ship.getX() + Ship.getWidth() >= Bound.getMaxX() || Ship.getY() + Ship.getHeight() >= Bound.getMaxY()) {
			gameState = 2;
			
		//Check if ship intersects with other shapes
		} else {
			for(int i = 0; i < Enemies.length; i++) {
				if(Ship.getX() <= Enemies[i].getX() + Enemies[i].getWidth() && Ship.getX() + Ship.getWidth() >= Enemies[i].getX() && 
						Ship.getY() <= Enemies[i].getY() + Enemies[i].getHeight() && Ship.getY() + Ship.getHeight() >= Enemies[i].getY()) {
					gameState = 2;
					return;
				}
			}
		}
	}
	
	private void checkEnemyBounce() {
		for(int i = 0; i < Enemies.length; i++) {
			if(Enemies[i].getX() <= 0 || Enemies[i].getX() + Enemies[i].getWidth() >= getWidth()) { //If enemy hits either wall
				//flip x vel
				Enemies[i].setVel(new Point((int)Enemies[i].getVel().getX() * -1, (int)Enemies[i].getVel().getY()));
				
			}
			if (Enemies[i].getY() + Enemies[i].getHeight() >= getHeight() || Enemies[i].getY() <= 0) { //If enemy hits top or bottom
				//flip y vel
				Enemies[i].setVel(new Point((int)Enemies[i].getVel().getX(), (int)Enemies[i].getVel().getY() * -1));
				
			}
		}
	}
	
	public static void setDifficulty(int dif) {
		difficulty = dif;
		gameState = 0;
	}
}