package chapter3;

public interface GameEventListener {

	void onEndOfLife(Entity e);
	void onFire(Entity e);
	void requestLogic(Entity e);
	void youLost();
	
}
