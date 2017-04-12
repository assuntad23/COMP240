package chapter1;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Alien extends Entity implements ConstantValues {

	private Image image; 
	private int imageWidth, imageHeight;
	private final float MAX_VELOCITY = 0.2f;
	
	public Alien(String resid){
		try {
			URL url = this.getClass().getClassLoader().getResource(resid);
			if (url == null){
				System.err.println("Alien Sprite not found");
			}
			image = ImageIO.read(url);
		} catch(IOException e){
			System.err.println("Failed to load sprite!");
		}
		
		imageWidth = image.getWidth(null);
		imageHeight = image.getHeight(null);
	}
	
	@Override
	public void move(long tm) {
		rx = rx + vx * tm;
		ry = ry + vy * tm;
	}

	@Override
	public void draw(Graphics2D gc) {
		gc.drawImage(image, (int) rx, (int) ry , (int) rx + dimension.width, (int) ry + dimension.height, 0, 0, imageWidth, imageHeight, null);
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
			vx = -Math.abs(vy) - 0.01f;
			if ( (keys & UP_KEY | DOWN_KEY) == 0)
				vy = 0;
		}
		if ( (keys & RIGHT_KEY) != 0){
			vx = +Math.abs(vy) + 0.01f;
			if ( (keys & UP_KEY | DOWN_KEY) == 0)
				vy = 0;
		}  
		if ( (keys & 0x0F) == 0){
			vx = 0;
			vy = 0;
		}
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
