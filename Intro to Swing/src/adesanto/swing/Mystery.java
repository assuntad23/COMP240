package adesanto.swing;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;

public class Mystery extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyCanvas canvas;
	private class MyCanvas extends Canvas{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyCanvas(){
			super();
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			draw(g);

		}
	}
	
	public Mystery(){
		super("Mystery Pattern");
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		canvas = new MyCanvas();
		add(canvas);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	private Point getMidPoint(Point a, Point b){
		
		int midX = (a.x+b.x)/2;
		int midY = (a.y+b.y)/2;
		
		return new Point(midX, midY);
	}
	
	private void draw(Graphics gc) {
		Random rand = new Random();
		
		gc.setColor(Color.RED);
		
		Rectangle bounds = canvas.getBounds();
		
		Point a = new Point(bounds.width/2, 0);
		Point b = new Point(0, bounds.height);
		Point c = new Point(bounds.width, bounds.height);
		
		Point[] points = {a,b,c};
		
		Point current = a;
		
		for (int i =0; i < 1000000; i ++){
			Point target = points[rand.nextInt(3)];
			current = getMidPoint(target, current);
			gc.fillRect(current.x, current.y, 1, 1);
		}
		
	}
	
	public static void main(String[] args) {
		Mystery gui = new Mystery();
		gui.setVisible(true);
	}
}