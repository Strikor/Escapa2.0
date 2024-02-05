import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;

public class GameArea extends JPanel{
	private static int WIDTH;
	private static int HEIGHT;
	public static int tick = 0;
	
	public Rectangle Bound;
	public aUnit Ship;
	private Point mouseToShip;
	public Enemy[] Enemies;
	protected Bomb[] bombs;
	
	protected static byte gameState = 0; //0 Game hasn't started, 1 Game in progress, 2 Game over
	
	public GameArea(int w, int h) {
		WIDTH = w - (w / 61);
		HEIGHT = h - (h / 61);
		
		this.setFocusable(true);
		this.setBackground(Color.black);
		Motion m = new Motion();
		this.addMouseMotionListener(m);
		this.addMouseListener(m);
		this.addKeyListener(m);
		
		defineRectangles();

	}
	
	public void startGame() {
		//Overridden by Subclass
	}
	
	//height = 813  - 13 = 800
	//width  = 1084 - 18 = 1066
	protected void defineRectangles() {
		Bound = new Rectangle(WIDTH / 16, HEIGHT / 12, WIDTH - (WIDTH / 8), HEIGHT - (HEIGHT / 6)); // Variable size achieved in 4:3 screen ratio
		Ship = new aUnit(new Rectangle(Bound.width / 2 + (75 / 2), Bound.height / 2 + (75 / 2), 50, 50));
		Enemies = new Enemy[4];
		bombs = new Bomb[1];
		bombs[0] = new Bomb(new Point(Bound.width / 2 + (75 / 2) + 50, Bound.height / 2 + (75 / 2) + 50), 25);
		
		Enemies[0] = new Enemy(new Rectangle((Bound.width / 6) - 45 + (75 / 2), (Bound.height - 45) / 6 + (75 / 2), 90, 90), new Point (5, 3)); //Top Left
		Enemies[1] = new Enemy(new Rectangle(Bound.width - Bound.width / 6 + (75 / 2), Bound.height / 6 + (75 / 2), 120,120), new Point (-2, 3)); //Top Right
		Enemies[2] = new Enemy(new Rectangle((Bound.width / 6) - 105 + (75 / 2), (Bound.height - 105) - Bound.height / 6 + (75 / 2), 210, 210), new Point (2, -4)); //Lower Left
		Enemies[3] = new Enemy(new Rectangle((Bound.width - Bound.width / 6) - 40 + (75 / 2), Bound.height - Bound.height / 3 + (75 / 2), 155, 155), new Point (-2, -2)); //Lower Right
		
	}
	
	protected void updateCenter() {
		Ship.setCenter(Ship.getX() + Ship.getWidth() / 2, Ship.getY() + Ship.getHeight() / 2);
		
		for(int i = 0; i < Enemies.length; i++) {
			Enemies[i].setCenter(Enemies[i].getX() + Enemies[i].getWidth() / 2, Enemies[i].getY() + Enemies[i].getHeight() / 2);
		}
	}
	
	//Paints Rectangles
	public void paintComponentRect(Graphics g) {
		super.paintComponent(g);  
		
		g.setColor(Color.WHITE);
		g.fillRect(Bound.x, Bound.y, Bound.width, Bound.height);
		
		g.setColor(Color.RED);  
		g.fillRect(Ship.getX(), Ship.getY(), Ship.getWidth(), Ship.getHeight()); //(Start X, Start Y, Width, Length)
		
		g.setColor(Color.CYAN);
		for(int i = 0; i < 4; i++) {
			g.fillRect(Enemies[i].getX(), Enemies[i].getY(), Enemies[i].getWidth(), Enemies[i].getHeight());
		}
		
		
		
	}
	
	//Paints Circles
	public void paintComponent(Graphics g) {
		super.paintComponent(g);  
		
		g.setColor(Color.WHITE);
		g.fillRect(Bound.x, Bound.y, Bound.width, Bound.height);
		
		g.setColor(Color.RED);  
		g.fillOval(Ship.getX(), Ship.getY(), Ship.getWidth(), Ship.getHeight()); //(Start X, Start Y, Width, Length)
		
		g.setColor(Color.CYAN);
		for(int i = 0; i < 4; i++) {
			g.fillOval(Enemies[i].getX(), Enemies[i].getY(), Enemies[i].getWidth(), Enemies[i].getHeight());
		}
		
		g.setColor(Color.darkGray);
		for(int i = 0; i < bombs.length; i++) {
			g.fillOval(bombs[i].getX(), bombs[i].getY(), bombs[i].getWidth(), bombs[i].getHeight());
			g.fillRect(bombs[i].getX() + bombs[i].getRad() - bombs[i].getRad() / 3, bombs[i].getY() - bombs[i].getRad() / 6, (int)(bombs[i].getRad() / 1.5), bombs[i].getRad());
			
			g.setColor(new Color(255, 0, 0, bombs[i].getOpp()));
			g.fillOval(bombs[i].getX() - bombs[i].getRad() * 2, bombs[i].getY() - bombs[i].getRad() * 2, bombs[i].getWidth() * 3, bombs[i].getHeight() * 3);
		}
		
	}
	
	public class Motion implements MouseMotionListener, MouseListener, KeyListener {
		private boolean showMouse = true;
		Cursor hand = new Cursor(Cursor.HAND_CURSOR);
		Cursor point = new Cursor(Cursor.DEFAULT_CURSOR);
		
		// Create a new blank cursor.
		Cursor blankCursor = getToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor");
		
		//MouseMotion
		public void mouseMoved(MouseEvent e) { // Active when in window
			if(showMouse) {
				if(getCursor() != hand && e.getPoint().getX() >= Ship.getX() && e.getPoint().getY() >= Ship.getY() && 
						e.getPoint().getX() <= Ship.getX() + Ship.getWidth() && e.getPoint().getY() <= Ship.getY() + Ship.getHeight()) {
					setCursor(hand);
					
				} else if(getCursor() != point && (e.getPoint().getX() < Ship.getX() || e.getPoint().getY() < Ship.getY() || 
						e.getPoint().getX() > Ship.getX() + Ship.getWidth() || e.getPoint().getY() > Ship.getY() + Ship.getHeight())) {
					setCursor(point);
				}
				
			}
		    
		}

		public void mouseDragged(MouseEvent e) { // Makes more sense for this program
			if(gameState == 1 && !showMouse) {//Checks that mouse is in bounds for ship
				
			Ship.setX(e.getX() - (int)mouseToShip.getX());
			Ship.setY(e.getY() - (int)mouseToShip.getY());
			
			}
		}

		//MouseListener
		@Override
		public void mouseClicked(MouseEvent e) { //Only if reads fill press and release
//			mousePos = e.getPoint();
//			repaint();
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) { // I think where the mouse enters the window
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {// I think where the mouse exits the window
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) { // I assume when the mouse is pushed down (Most likely one position)
			if(gameState != 2 && e.getPoint().getX() >= Ship.getX() && e.getPoint().getY() >= Ship.getY() && 
						e.getPoint().getX() <= Ship.getX() + Ship.getWidth() && e.getPoint().getY() <= Ship.getY() + Ship.getHeight()) {//Checks that mouse is in bounds for ship
				showMouse = false;
				setCursor(blankCursor);
				
				mouseToShip = new Point(e.getX() - Ship.getX(), e.getY() - Ship.getY());
				
				if(gameState == 0) { // Prevents the starting of multiple timers
					startGame();
					gameState = 1;
				}
				
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) { // Same as above but opposite
			showMouse = true;
			mouseMoved(e); //I'm calling this to simplify showing the mouse
			
		}

		//Key Listener
		@Override
		public void keyPressed(KeyEvent e) {// Key down
			
//			System.out.println(e.getKeyChar());
//			System.out.println(e.getKeyCode());
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) { // Key up
//			System.out.println(e.getKeyChar());
//			System.out.println(e.getKeyCode());
		}

		@Override
		public void keyTyped(KeyEvent e) { //Works with held keys, e.getKeyCode does not work, always returns 0
//			System.out.println(e.getKeyChar());
//			System.out.println(e.getKeyCode());
			
		}
		

	}
}
