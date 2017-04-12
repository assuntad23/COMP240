package chapter1;

import java.awt.Graphics2D;


public class Alien2 extends Entity implements ConstantValues {

	private Sprite sprite; 
	private final float MAX_VELOCITY = 0.2f;
	
	public Alien2(String resid){
		sprite = new Sprite(resid, FRAME_COUNT);
	}
	
	@Override
	public void move(long tm) {
		rx = rx + vx * tm;
		ry = ry + vy * tm;
	}

	@Override
	public void draw(Graphics2D gc) {
		sprite.drawImage(gc, (int)rx, (int)ry, (int)rx+dimension.width, (int)ry+dimension.height, active);
	}
	
	@Override
	public void processKeys(byte keys) {
		if ( (keys & UP_KEY) != 0){
			vy = -Math.abs(vy) - 0.01f;
			if ( (keys & LEFT_KEY | RIGHT_KEY) == 0)
				vx = 0;
		}
		if ( (keys & DOWN_KEY) != 0){
			vy = +Math.abs(vy) + 0.01f;
			if ( (keys & LEFT_KEY | RIGHT_KEY) == 0)
				vx = 0;
		}
		if ( (keys & LEFT_KEY) != 0){
			vx = -Math.abs(vx) - 0.01f;
			if ( (keys & UP_KEY | DOWN_KEY) == 0)
				vy = 0;
		}
		if ( (keys & RIGHT_KEY) != 0){
			vx = +Math.abs(vx) + 0.01f;
			if ( (keys & UP_KEY | DOWN_KEY) == 0)
				vy = 0;
		}  
		if ( (keys & 0x0F) == 0){
			vx = 0;
			vy = 0;
			active = false;
		} else 
			active = true;
		
		if (vy < -MAX_VELOCITY)
			vy = - MAX_VELOCITY;
		if (vy > MAX_VELOCITY)
			vy = MAX_VELOCITY;
		if (vx < -MAX_VELOCITY)
			vx = - MAX_VELOCITY;
		if (vx > MAX_VELOCITY)
			vx = MAX_VELOCITY;
		
	}

}
