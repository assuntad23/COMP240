package basic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Box extends Entity {
	private Color color = Color.PINK;
	private Dimension dimension = new Dimension(30,30);
	
	@Override
	public void move(long elapsedTime) {
		Random rand = new Random();
		
		int power = rand.nextBoolean() ? 1:2;
		
		rx += 2*Math.pow(-1, power);
		power = rand.nextBoolean() ? 1:2;
		ry += 2*Math.pow(-1, power);
	}

	@Override
	public void draw(Canvas canvas) {
		BufferStrategy strategy = canvas.getBufferStrategy();
		Graphics2D gc = (Graphics2D) strategy.getDrawGraphics();
		gc.setXORMode(gc.getBackground());
		gc.setColor(color);
		gc.fillRect((int)rx, (int)ry, (int)dimension.width, (int)dimension.height);
		gc.dispose();
		strategy.show();
		
	}

}
