package chapter1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Controller extends JFrame implements KeyListener, ConstantValues {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private byte keys = 0x0;
	private Timer timer; 
	private long timeStamp = System.currentTimeMillis();
	
	private Canvas canvas;
	
	private ArrayList<Entity> entities = new ArrayList<>();
	
	public Controller(){
		super("Space Invaders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		add(canvas);
		setIgnoreRepaint(true);
		pack();
		
		canvas.createBufferStrategy(2);
		addKeyListener(this);
		setVisible(true);
	}

	public void addEntity(Entity entity){
		entities.add(entity);
		entity.setVelocity(0, 0);
		entity.setPosition(0, 0);
		entity.setDimension(SCREEN_WIDTH/20, SCREEN_HEIGHT/20);
	}
	
	public void start(){
		timer = new Timer (1000/FPS, new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				render();
			}
		});
		timer.start();
		timeStamp =System.currentTimeMillis();
	}
	
	private void render(){
		BufferStrategy strategy = canvas.getBufferStrategy();
		Graphics2D gc = (Graphics2D) strategy.getDrawGraphics();
		
		long elapsedTime = System.currentTimeMillis() - timeStamp;
		gc.setColor(Color.BLACK);
		gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		for (Entity e: entities){
			e.processKeys(keys);
			e.move(elapsedTime);
			e.draw(gc);
		}
		gc.dispose();
		strategy.show();
		timeStamp = System.currentTimeMillis();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			keys |= UP_KEY;
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			keys |= LEFT_KEY;
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			keys |= DOWN_KEY;
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			keys |= RIGHT_KEY;
			break;
		case KeyEvent.VK_ESCAPE:
			askExit();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			keys &= ~UP_KEY;
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			keys &= ~LEFT_KEY;
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			keys &= ~DOWN_KEY;
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			keys &= ~RIGHT_KEY;
			break;
		}

	}
	
	
	private void askExit(){
		if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(this, "Do you want to exit?", "Exit", JOptionPane.OK_CANCEL_OPTION)){
			System.exit(0);
		}
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		Controller gui = new Controller();
		Entity alien = new Alien2("sprites/alienSpriteSheet.png");
		alien.activate();
		gui.addEntity(alien);
		gui.start();
	}
	
}
