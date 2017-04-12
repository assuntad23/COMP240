package chapter2;

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

public class Controller extends JFrame implements KeyListener, ConstantValues, GameEventListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private byte keys = 0x0;
	private Timer timer; 
	private long timeStamp = System.currentTimeMillis();
	private Wall walls;
	private SpaceShip ship;	
	private Missile missile;
	private MarchingAlien alien;
	private boolean collided = false;
	
	private Canvas canvas;
	
	private ArrayList<Entity> entities = new ArrayList<>();
	private ArrayList<Entity> removeList = new ArrayList<>();
	private ArrayList<MarchingAlien> army = new ArrayList<>();
	private MarchingAlien [] moreAliens = new MarchingAlien[XNUM_OF_ALIENS * YNUM_OF_ALIENS];
	
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
		walls = new Wall(0,0, new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		walls.setPosition(0, 0);
		addEntity(walls);
		ship = new SpaceShip(this, new Dimension(SCREEN_WIDTH/20, SCREEN_HEIGHT/20));
		ship.setPosition(SCREEN_WIDTH/2 - ship.getDimension().width/2, SCREEN_HEIGHT - ship.getDimension().height-50);
		addEntity(ship);

			/*
			int alienrow = SCREEN_HEIGHT/20;
			for (int i = 0; i < YNUM_OF_ALIENS; i++){
				for (int j = 0; j < XNUM_OF_ALIENS; j++){
					moreAliens[i * XNUM_OF_ALIENS + j] = new MarchingAlien(this, new Dimension(SCREEN_WIDTH/20, SCREEN_HEIGHT/20));
					//addEntity(moreAliens[i * XNUM_OF_ALIENS+j]);
					if (j == 0)
						moreAliens[i * XNUM_OF_ALIENS + j].setPosition(1, alienrow-30);
					else
						moreAliens[i* XNUM_OF_ALIENS + j].setPosition((moreAliens[i * XNUM_OF_ALIENS + j].getDimension().width) * j, alienrow-30);
					addEntity(moreAliens[i * XNUM_OF_ALIENS + j]);
				}
			if (alienrow == 0)
				alienrow += alienrow;
			if (alienrow ==1)
				alienrow += alienrow;
			else 
				alienrow+= (alienrow* i);
			}
			*/	
	}

	
	public void addAliens(int rows, int cols){
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				MarchingAlien a = new MarchingAlien(this, new Dimension(SCREEN_WIDTH/20, SCREEN_HEIGHT/20));
				a.setPosition(j* SCREEN_WIDTH/20, i* SCREEN_HEIGHT/20);
				a.setVelocity(0.1f, 0);
				army.add(a);
				a.activate();
			}
		}
	}
	
	
	public void addEntity(Entity entity){
		entities.add(entity);
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
		
		for (int i = 0; i <entities.size(); i++){
			Entity e = entities.get(i);
			e.processKeys(keys);
			e.move(elapsedTime);
			
			for (int j = i+1; j<entities.size(); j++){
				Entity a = entities.get(j);
				if (e.inCollision(a)){
					if (e instanceof Wall && a instanceof MarchingAlien && collided == false){
						collided = true;
						for (int k = 0; k <moreAliens.length; k++){
							moreAliens[k].setPosition(moreAliens[k].getRx(), moreAliens[k].getRy()+ moreAliens[k].getDimension().height/YNUM_OF_ALIENS);
							moreAliens[k].setVelocity(-(moreAliens[k].getVx()), moreAliens[k].getVy());
						}
					}
				}
					a.inCollision(e);
			}
			e.draw(gc);
		}
			
		gc.dispose();
		strategy.show();
		timeStamp = System.currentTimeMillis();
		entities.removeAll(removeList);
		removeList.clear();
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_SPACE:
			keys |= SPACE_KEY;
			break;
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
		case KeyEvent.VK_SPACE:
			keys &= ~SPACE_KEY;
			break;
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
		gui.start();
	}
	
	@Override
	public void onFire(Entity e){
		missile = new Missile(this, new Dimension (SCREEN_WIDTH/30, SCREEN_HEIGHT/30));
		missile.setPosition(ship.getRx(), ship.getRy() - 20);
		addEntity(missile);
	}

	@Override
	public void onEndOfLife(Entity e) {
		removeList.add(e);
	}
	
}
