/**
 * This class simulates the transition function
 * 
 * @author zhaoru
 *
 */
public class Transition {
	public static final char LEFT = 'l';
	public static final char RIGHT = 'r';
	private char writeDown;
	private char direction;
	private int nextState;

	/**
	 * Constructor
	 * @param writeDown replaced char to write down
	 * @param direction direction of next step
	 * @param nextState next state
	 */
	public Transition(char writeDown, char direction, int nextState) {
		this.writeDown = writeDown;
		this.direction = direction;
		this.nextState = nextState;
	}

	public char getWriteDown() {
		return writeDown;
	}

	public char getDirection() {
		return direction;
	}

	public int getNextState() {
		return nextState;
	}
}
