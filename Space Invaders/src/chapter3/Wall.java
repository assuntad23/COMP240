package chapter3;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Wall extends Entity implements ConstantValues {

	private Rectangle bounds;
	
	public Wall(int x, int y, Dimension dim){
		super(null, dim);
		bounds = new Rectangle (x, y, dim.width, dim.height);
	}
	
	@Override
	public void move(long tm) {
	}

	@Override
	public void draw(Graphics2D gc) {
	}

	@Override
	public void processKeys(byte keys) {
	}

	@Override
	public boolean inCollision(Entity e) {
		//return !bounds.contains(new Point((int)e.getRx(), (int)e.getRy()));
		Dimension d = e.dimension;
		if (e.getRx() >= bounds.width - d.width)
			return true;
		if (e.getRx() <= bounds.x)
			return true;
		if (e.getRy() >= bounds.height- d.height)
			return true;
		if (e.getRy() <= bounds.y)
			return true;
		return false;
		
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(bounds);
	}

}
