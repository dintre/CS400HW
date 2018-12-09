
public interface Moveable {
	enum Direction {UP, DOWN, LEFT, RIGHT, CLOSER, FARTHER}
	final int DELAY_TIME = 10000;
	
	
	// abstract method
	void move(Direction d);
	
	static void delay() {
		for (int i = 0; i < DELAY_TIME; i++);
	}
	
	default void moveRandom() {
		move(getRandomDirection());
	}
	
	static Direction getRandomDirection() {
		int random = (int) (Math.random()*Direction.values().length);
		return Direction.values()[random];
	}
	
	
} // interface Moveable
