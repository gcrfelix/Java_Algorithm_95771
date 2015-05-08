import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * This class is a Turing machine simulator, which contains the needed method to
 * simulate the Turing machine
 * 
 * @author zhaoru
 *
 */
public class Turing {
	private static final int MAX_LEN = 40;

	private List<HashMap<Character, Transition>> listOfMaps;
	private int haltState;
	private int header;
	private int currentState;

	/**
	 * Constructor
	 * 
	 * @param n
	 *            number of states, which is also the index of halt state
	 */
	public Turing(int n) {
		haltState = n;
		currentState = 0;
		header = 19;
		listOfMaps = new ArrayList<HashMap<Character, Transition>>();

		for (int i = 0; i < n; i++) {
			listOfMaps.add(new HashMap<Character, Transition>());
		}
	}

	/**
	 * initialize an array of hashMap to store transition functions
	 * 
	 * @param state
	 *            input state
	 * @param symbol
	 *            input symbol
	 * @param transition
	 *            the transition function
	 */
	public void addTransition(int state, char symbol, Transition transition) {
		listOfMaps.get(state).put(symbol, transition);
	}

	/**
	 * execute Turing machine until it enters halt state
	 * 
	 * @param inputTape
	 *            input tape
	 * @return output tape
	 */
	public String execute(String inputTape) {
		char[] in = inputTape.toCharArray();
		char[] inTape = new char[MAX_LEN];
		Arrays.fill(inTape, 'B');
		System.arraycopy(in, 0, inTape, header, in.length);

		// index of inTape
		while (currentState != haltState) {
			Transition t = listOfMaps.get(currentState).get(inTape[header]);
			inTape[header] = t.getWriteDown();
			switch (t.getDirection()) {
			case 'l':
				header--;
				break;
			case 'r':
				header++;
				break;
			}
			currentState = t.getNextState();
		}
		return String.valueOf(inTape);
	}

}
