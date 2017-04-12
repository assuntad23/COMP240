package chapter2;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import chapter1.ConstantValues;

public class MarchingAlien extends Entity implements ConstantValues {

	private Sprite sprite;

	
	public MarchingAlien(GameEventListener controller, Dimension dim) {
		super(controller, dim);
		sprite = new Sprite("sprites/alienSpriteSheet.png",FRAME_COUNT);
		vx = -0.08f;
	}

	@Override
	public void move(long tm) {
		active = true;
		rx += vx * tm;
	}

	@Override
	public void draw(Graphics2D gc) {
	 	sprite.drawImage(gc, (int)rx, (int)ry, (int)rx+dimension.width, (int)ry+dimension.height, active);

	}

	@Override
	public void processKeys(byte keys) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean inCollision(Entity e) {
		/*if (e instanceof Wall){
			int left = 0;
			int right = ((Wall) e).getBounds().width;
			if (rx <= left){
				rx = 0;
				ry = ry + dimension.height;
				vx = -vx;
			} else if (rx >= right-dimension.width){
				rx = right-dimension.width;
				vx = 0;
				ry = ry + dimension.height;
				vx = -vx;
			}
		}
		*/
		if (e instanceof Missile){
			Missile m = (Missile) e;
			Rectangle r1 = new Rectangle((int)rx, (int)ry, dimension.width, dimension.height);
			Rectangle r2 = new Rectangle((int)m.rx, (int)m.ry, m.dimension.width, m.dimension.height);
			if (r1.intersects(r2)){
				controller.onEndOfLife(this);
				return true;
			}
		}
		return false;
	}
}