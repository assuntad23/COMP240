package basic;

import java.awt.Canvas;

public abstract class Entity {
	
	protected float rx, ry; //position
	protected float vx, vy; //velocity
	
	public void setPosition(float x, float y){
		rx = x;
		ry = y;
	}
	
	public void setVelocity(float x, float y){
		vx = x;
		vy = y;
	} 
	
	public abstract void move(long elapsedTime);
	public abstract void draw(Canvas canvas);
}
