import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

public class menuBar extends JMenuBar{
	JMenu menu;
	JMenuItem menuItem;
	JCheckBoxMenuItem cbMenuItem;
	JRadioButtonMenuItem esy, med, hrd;
	JButton direct;
	
	private int difficulty = 1;
	private boolean isTimer = false;
	
	public menuBar(JFrame frame) {
		menu = new JMenu("Options");
		add(menu);
		
			//Beginner
			esy = new JRadioButtonMenuItem("Beginner");
			esy.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	esy.setSelected(true);
			    	med.setSelected(false);
			    	hrd.setSelected(false);
			    	
			    	if(difficulty != 0) {
			    		difficulty = 0;
			    		GameLogic.setDifficulty(0);
			    	}
			    	
			    	
			    	
			    }
			});
			menu.add(esy);
			
			//Intermediate
			med = new JRadioButtonMenuItem("Intermediate");
			med.setSelected(true);
			med.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	esy.setSelected(false);
			    	med.setSelected(true);
			    	hrd.setSelected(false);
			    	
			    	if(difficulty != 1) {
			    		difficulty = 1;
			    		GameLogic.setDifficulty(1);
			    	}
			    }
			});
			menu.add(med);
			
			//Expert
			hrd = new JRadioButtonMenuItem("Expert");
			hrd.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	esy.setSelected(false);
			    	med.setSelected(false);
			    	hrd.setSelected(true);
			    	
			    	if(difficulty != 2) {
			    		difficulty = 2;
			    		GameLogic.setDifficulty(2);
			    	}
			    }
			});
			
			menu.add(hrd);
			menu.addSeparator();
			
			//BestTimes
			menuItem = new JMenuItem("Best Times...");
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Highscores high = new Highscores();
					
				}
			});
			menu.add(menuItem);
			
			menu.addSeparator();
			
			menuItem = new JMenuItem("Exit");
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			menu.add(menuItem);
			
		direct = new JButton("Directions");
		direct.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4)); //Used to create padding from other objects
		direct.setContentAreaFilled(false); 
		add(direct);
		
		direct.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)	{
		    	JOptionPane.showMessageDialog(frame, "Escapa is played in a small white box, in which there are five\nrectangles: four are blue, and move automatically; and the\nother—a square—is red. The object of the game is to navigate\nthe red square by dragging it with the mouse within the small\nwhite playing field for as long as possible, avoiding both the\nblue blocks, and the black border surrounding the playing field.");
		        
		    }
		}); 
		
	}
	
	private class Highscores extends JFrame{
		private JFrame frame;
		private static final int WIDTH = 300;
		private static final int HEIGHT = 200;
		
		public Highscores() {
			frame = new JFrame();
			
			frame.setTitle("Highscores");
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Escapa.ico"));
			frame.setBounds((GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth() - WIDTH) / 2, (GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
			frame.setResizable(false);
			
			frame.setVisible(true);
		}
		
		
	}
	
}
