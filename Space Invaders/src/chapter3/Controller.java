package chapter3;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	private Entity ship;	
	private Missile missile;
	private boolean collided = false;
	private Canvas canvas;
	private boolean start = false;
	private int num2Kill = XNUM_OF_ALIENS*YNUM_OF_ALIENS;
	
	private ArrayList<Entity> entities = new ArrayList<>();
	private ArrayList<Entity> removeList = new ArrayList<>();
	private ArrayList<MarchingAlien> army = new ArrayList<>();
	private GameState state = GameState.START;
	
	private enum GameState {
		UNSTART,
		START,
		PLAY,
		WON,
		LOST
	}
	
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
		
		addAliens(XNUM_OF_ALIENS,YNUM_OF_ALIENS);
	}


	public void addAliens(int rows, int cols){
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < cols; j++){
				MarchingAlien a = new MarchingAlien(this, new Dimension(SCREEN_WIDTH/20, SCREEN_HEIGHT/20));
				a.setPosition((j* SCREEN_WIDTH/20 + ((SCREEN_WIDTH/20) * cols/2)), (i* SCREEN_HEIGHT/20) + 1);
				a.setVelocity(0.1f, 0);                                                
				army.add(a);
				a.activate();
			}
		}
		entities.addAll(army);
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
	
	private void onStart(){
		BufferStrategy strategy = canvas.getBufferStrategy();
		Graphics2D gc = (Graphics2D) strategy.getDrawGraphics();
		
		gc.setColor(Color.BLACK);
		gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		displayMessage ("Uh-Oh Aliens!  Save the World!", gc, Color.RED, 0);
		displayMessage ("Press [ENTER] to play", gc, Color.GREEN, 20);
		
		for (int i = 0; i <entities.size(); i++){
			Entity e = entities.get(i);
			e.processKeys(keys);
			
			for (int j = i+1; j<entities.size(); j++){
				Entity a = entities.get(j);
				if (e.inCollision(a)){
					if (e instanceof Wall && a instanceof MarchingAlien){
						a.inCollision(e);
						collided = true;
					} else
						a.inCollision(e);
				}
				}
			e.draw(gc);	
		}
			
		gc.dispose();
		strategy.show();
		timeStamp = System.currentTimeMillis();
		entities.removeAll(removeList);
		removeList.clear();
		collided = false;
		
		if ( (keys & ENTER_KEY) != 0){
			SoundFX.STARTUP.play();
	 		start = true;
	 		state = GameState.PLAY;
		}
	}
	
	private void onPlay(){
		BufferStrategy strategy = canvas.getBufferStrategy();
		Graphics2D gc = (Graphics2D) strategy.getDrawGraphics();

		long elapsedTime = System.currentTimeMillis() - timeStamp;
		//clears Screen
		gc.setColor(Color.BLACK);
		gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		
		for (int i = 0; i <entities.size(); i++){
			Entity e = entities.get(i);
			e.processKeys(keys);
			e.move(elapsedTime);
			
			for (int j = i+1; j<entities.size(); j++){
				Entity a = entities.get(j);
				if (e.inCollision(a)){
					if ((e instanceof SpaceShip && a instanceof MarchingAlien)){
						a.inCollision(e);
						collided = true;
						state = GameState.LOST;
					}
					if (e instanceof Wall && a instanceof MarchingAlien){
						a.inCollision(e);
						collided = true;
					} else
						a.inCollision(e);
				}
			}
			
			e.draw(gc);	
			
		}
			
		gc.dispose();
		strategy.show();
		timeStamp = System.currentTimeMillis();
		entities.removeAll(removeList);
		removeList.clear();
		collided = false;
		
		if (num2Kill == 0)
			state = GameState.WON;
		
	}
	
	private void youWon(){
		BufferStrategy strategy = canvas.getBufferStrategy();
		Graphics2D gc = (Graphics2D) strategy.getDrawGraphics();
		
		SoundFX.END.play();
		
		SoundFX.WIN.play();
		displayMessage ("Game Over!", gc, Color.WHITE, 0);
		displayMessage ("Yay! You saved planet earth!", gc, Color.GREEN, 20); 
		gc.dispose();
		strategy.show();
		timeStamp = System.currentTimeMillis();
		
	}
	
	public void youLost(){
		//will the aliens automatically stop moving? or do I have to stop them? if so, how?
		BufferStrategy strategy = canvas.getBufferStrategy();
		Graphics2D gc = (Graphics2D) strategy.getDrawGraphics();
		
		SoundFX.END.play();
		
		SoundFX.LOSE.play();
		displayMessage ("Game Over!", gc, Color.WHITE, 0);
		displayMessage ("Uh-Oh! Everyone died because of you!", gc, Color.RED, 20); 
		gc.dispose();
		strategy.show();
		timeStamp = System.currentTimeMillis();
	}
	
	private void playAgain(){
		//TODO
	}
	
	private void render(){
		
		switch(state){
			case START:
				onStart();
				break;
			case PLAY:
				onPlay();
				break;
			case WON:
				youWon();
				state = GameState.UNSTART;
				break;
			case LOST:
				youLost();
				state = GameState.UNSTART;
				break;
			case UNSTART:
				if ( (keys & ENTER_KEY) != 0){
					playAgain();
				}
				break;
		}
		
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_SPACE:
			keys |= SPACE_KEY;
			break;
		case KeyEvent.VK_ENTER:
			keys |= ENTER_KEY;
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
		case KeyEvent.VK_ENTER:
			keys &= ~ENTER_KEY;
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
		SoundFX.SHOOT.play();
	}

	@Override
	public void onEndOfLife(Entity e) {
		if (e instanceof MarchingAlien)
			num2Kill--;
		removeList.add(e);
	}



	@Override
	public void requestLogic(Entity e) {
		if (e instanceof MarchingAlien && collided == false){
			for (MarchingAlien a : army){
				a.setPosition(a.getRx(), a.getRy()+20);
				a.setVelocity(-a.getVx(), 0);
			}
		}
	}
	
	private void displayMessage(String message, Graphics2D gc, Color color, int voffSet){
		Color oldColor = gc.getColor();
		Font oldFont = gc.getFont();
		
		Font font = new Font("Courier New", Font.PLAIN, 16);
		gc.setFont(font);
		gc.setColor(color);
		
		int stringWidth = gc.getFontMetrics().stringWidth(message);
		
		gc.drawString(message, (SCREEN_WIDTH- stringWidth)/2, (SCREEN_HEIGHT/2) + voffSet);
		
		gc.setColor(oldColor);
		gc.setFont(oldFont);
		
	}
	
}