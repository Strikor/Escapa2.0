/*
 * TODO
 * Improve readability
 * 	aShip and Enemy are well made and would work well as concept teachers
 * 	GameArea and GameLogic should most definitely be combined as it is completely redundant and unnecessary
 * 	Swing Program needs to be larger and contain more important things
 * 		Specifically...
 * 		-method for adding an image
 * 		-method for easily grabbing the current form size
 * 
 *  Setup button proper
 *  Add variable size for board
 */

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JLabel;

public class BallGame extends SwingProgram{
	//must be proper 4:3 ratio
	protected static final int Width = 1084;
	protected static final int Height = 810;
	
	protected int difficulty = 1; //0 = Easy, 1 = Medium, 2 = Hard

	public void run() {
		frame.setTitle("Escapa");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Escapa.ico"));
		frame.setBounds((GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth() - Width) / 2, (GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight() - Height - 75) / 2, Width, Height + 50);
		frame.setResizable(false);
		
		GameLogic game = new GameLogic(Width, Height);
		game.setLayout(null);
		frame.add(game);
		
		menuBar bar = new menuBar(frame);
		frame.setJMenuBar(bar);
	}
}
