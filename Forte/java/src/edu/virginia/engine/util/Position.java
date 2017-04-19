package edu.virginia.engine.util;

public class Position {

	private int x, y;
	
	public Position() {
		x = y = 0;
	}
	
	public Position(int x, int y) {
		setX(x);
		setY(y);
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public int setY(int y) {
		this.y = y;
		return y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	
}
