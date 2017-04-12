package basic;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;


public class Framework extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Canvas canvas;
	private BufferStrategy strategy;
	private long timestamp;
	private float fps = 100.0f;
	private ArrayList<Entity> entities = new ArrayList<>();
	
	
	public Framework(){
		super("COMP240");
		setSize(600,600);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(600,600));
		add(canvas);
	}
	
	public void addEntity(Entity entity){
		entities.add(entity);
	}
	
	public void start(){
		
		Timer timer = new Timer((int) (1000/fps), new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				render(canvas);
				strategy.show();
			}
		});
		canvas.createBufferStrategy(2);
		strategy = canvas.getBufferStrategy();
		
	//	for (Entity e: entities){
	//		e.draw(canvas);
	//	}
		
		timestamp = System.currentTimeMillis();
		timer.start();
	}
	
	private void render(Canvas canvas) {
		long elapsedTime = System.currentTimeMillis() - timestamp;
		for (Entity e: entities){
			e.draw(canvas);
			e.move(elapsedTime);
			e.draw(canvas);
		}
		timestamp = System.currentTimeMillis();
	}
	public static void main(String[] args) {
		Framework gui = new Framework();
		gui.setVisible(true);
		Box box = new Box();
		box.setPosition(300, 300);
		gui.addEntity(box);
		gui.start();
	}

}
