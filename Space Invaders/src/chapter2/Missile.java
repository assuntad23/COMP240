package chapter2;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Missile extends Entity implements ConstantValues{

	private Sprite sprite;
	private final float MAX_VELOCITY = -0.03f;
	private final float ACCELERATION = -0.002f;
	
	public Missile(GameEventListener controller, Dimension dim) {
		super(controller, dim);
		sprite = new Sprite("sprites/projectileSpriteSheet.png",FRAME_COUNT);
	}

	@Override
	public void move(long tm) {
		ry = ry + vy * tm;
		vy = vy + ACCELERATION * tm;
		if (vy > MAX_VELOCITY)
			vy = MAX_VELOCITY;
	}

	@Override
	public void draw(Graphics2D gc) {
	 	sprite.drawImage(gc, (int)rx, (int)ry, (int)(rx+dimension.width), (int)(ry+dimension.height), active);
	}

	@Override
	public void processKeys(byte keys) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean inCollision(Entity e) {
		if (e instanceof Wall){
			Wall w = (Wall) e;
			if (ry <  w.ry){
				controller.onEndOfLife(this);
				return true;
			}
		}
		if (e instanceof MarchingAlien){
			MarchingAlien a = (MarchingAlien) e;
			Rectangle r1 = new Rectangle((int)rx, (int)ry, dimension.width, dimension.height);
			Rectangle r2 = new Rectangle((int)a.rx, (int)a.ry, a.dimension.width, a.dimension.height);
			if (r1.intersects(r2)){
				controller.onEndOfLife(this);
				return true;
			}
		}
		return false;
	}

}
