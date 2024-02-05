import java.awt.Point;
import java.awt.Rectangle;

public class aUnit {
	private Rectangle b;
	private Point Center;
	
	public aUnit(Rectangle b) {
		this.setB(b);
		Center = new Point(getX() + getWidth() / 2, getY() + getHeight() / 2);
	}
	
	public Rectangle getB() {
		return b;
	}
	public void setB(Rectangle b) {
		this.b = b;
	}
	public int getX() {
		return b.x;
	}
	public void setX(int x) {
		b.x = x;
	}
	public int getY() {
		return b.y;
	}
	public void setY(int y) {
		b.y = y;
	}
	public void addPos(Point pos) {
		b.x += pos.x;
		b.y += pos.y;
	}
	public void addPos(Point pos, double multi) {
		b.x += pos.x * multi;
		b.y += pos.y * multi;
	}
	public int getWidth() {
		return b.width;
	}
	public void setWidth(int width) {
		b.width = width;
	}
	public int getHeight() {
		return b.height;
	}
	public void setHeight(int height) {
		b.height = height;
	}

	public Point getCenter() {
		return Center;
	}
	
	public int getCenterX() {
		return Center.x;
	}
	
	public int getCenterY() {
		return Center.y;
	}

	public void setCenter(Point center) {
		Center = center;
	}
	
	public void setCenter(int x, int y) {
		Center = new Point(x, y);
	}
}