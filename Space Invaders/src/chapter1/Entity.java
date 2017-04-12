package chapter1;

import java.awt.Dimension;
import java.awt.Graphics2D;

public abstract class Entity {
	
	protected float rx, ry; // positions
	protected float vx, vy; //velocity
	protected Dimension dimension = new Dimension();
	protected boolean active = false;
	
	public float getRx() {
		return rx;
	}
	
	public float getRy() {
		return ry;
	}
	
	public float getVx() {
		return vx;
	}
	
	public float getVy() {
		return vy;
	}
	
	public void setPosition(float x, float y){
		rx = x;
		ry = y;
	}
	
	public void setVelocity(float u, float v){
		vx = u;
		vy = v;
	}
	
	public void setDimension (int width, int height){
		dimension.width = width;
		dimension.height = height;
	}
	

	public void activate(){
		active = true;
	}
	
	public void deactivate(){
		active = false;
	}

	public abstract void move(long tm);
	public abstract void draw(Graphics2D gc);
	public abstract void processKeys(byte keys);
}