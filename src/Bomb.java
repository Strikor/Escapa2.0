import java.awt.Point;
import java.awt.Rectangle;

public class Bomb extends aUnit {
	private int rad;
	private int direction = 1; //-1 Reverse, 1 Forward
	private int blinkSpeed = 1;
	private int opp = 0;
	
	private boolean exp = false;
	
	public Bomb(Point loc, int rad) {
		super(new Rectangle(loc.x, loc.y, rad * 2, rad * 2));
		this.rad = rad;
	}
	
	public int getOpp() {
		if(opp < 204 && direction == 1) {
			opp += blinkSpeed;
			return opp;
		} else if(opp > 51 && direction == -1) {
			opp -= blinkSpeed;
			return opp;
		}
		
		opp += direction;
		direction *= -1;
		blinkSpeed++;
		
		if (blinkSpeed == 7) {
			exp = true;
		}
		
		return opp;
	}
	
	public boolean getExp() {
		return exp;
	}

	public int getRad() {
		return rad;
	}

	public void setRad(int rad) {
		this.rad = rad;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
}
