package chapter3;

import java.awt.Dimension;
import java.awt.Graphics2D;



public class SpaceShip extends Entity implements ConstantValues{

	private Sprite sprite;
	private final float MAX_VELOCITY = 0.4f;
	private final float DELTA_VELOCITY = 0.2f;
	private Missile missile;
	private final long FIRING_INTERVAL = 300;
	private long lastFired = System.currentTimeMillis();
	
	
	public SpaceShip(GameEventListener controller, Dimension dim){
		super(controller, dim);
		sprite = new Sprite("sprites/shipSpriteSheet.png", FRAME_COUNT);
		}
	
	@Override
	public void move(long tm) {
		rx = rx + vx * tm;
	}

	@Override
	public void draw(Graphics2D gc) {
	 	sprite.drawImage(gc, (int)rx, (int)ry, (int)rx+dimension.width, (int)ry+dimension.height, active);
	}

	@Override
	public void processKeys(byte keys) {
		if ((keys & SPACE_KEY) != 0){
			if (System.currentTimeMillis() - lastFired < FIRING_INTERVAL)
				return;
			if (controller == null)
				throw new NullPointerException();
			controller.onFire(missile);
			lastFired = System.currentTimeMillis(); 
		}
		if ( (keys & LEFT_KEY) != 0){
			vx = -Math.abs(vx) - DELTA_VELOCITY;
		}
		if ( (keys & RIGHT_KEY) != 0){
			vx = +Math.abs(vx) + DELTA_VELOCITY;
		}  
		if ( (keys & 0x0F) == 0){
			vx = 0;
			active = false;
		} else 
			active = true;

		if (vx < -MAX_VELOCITY)
			vx = - MAX_VELOCITY;
		if (vx > MAX_VELOCITY)
			vx = MAX_VELOCITY;
		
	}

	@Override
	public boolean inCollision(Entity e) {
		if (e instanceof Wall){
			int left = 0;
			int right = ((Wall) e).getBounds().width;
			if (rx <= left){
				rx = 0;
				vx = 0;
			} else if (rx >= right-dimension.width){
				rx = right-dimension.width;
				vx = 0;
			}
		}
		return false;
	}
}