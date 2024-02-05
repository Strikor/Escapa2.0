import java.awt.Point;
import java.awt.Rectangle;

public class Enemy extends aUnit{
	private Point Vel;
	
	public Enemy(Rectangle b, Point vel) {
		super(b);
		Vel = vel;
	}

	public Point getVel() {
		return Vel;
	}
	
	public int getVelX() {
		return Vel.x;
	}
	
	public int getVelY() {
		return Vel.y;
	}

	public void setVel(Point vel) {
		Vel = vel;
	}
	
	public void setVelX(int x) {
		Vel = new Point(x, Vel.y);
	}
	
	public void setVelY(int y) {
		Vel = new Point(Vel.x, y);
	}
}
