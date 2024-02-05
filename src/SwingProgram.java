import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Point;

public class SwingProgram extends JFrame {
	public static SwingProgram frame;

	public static void main(String[] args) {
		String className = "BallGame";
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				String mainClassName = className;
				
				try {
					Class mainClass = Class.forName(mainClassName);
					Object obj = mainClass.newInstance();
					frame = (SwingProgram)obj;
					frame.run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SwingProgram() {
		setVisible(true);
		setBounds((GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth() / 2) - 400, (GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight() / 2) - 250, 800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void run() {
		//Overridden by Subclass
	}

}
