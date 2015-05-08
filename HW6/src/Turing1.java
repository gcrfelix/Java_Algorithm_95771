/**
 * Turing machine to simulate part1
 * 
 * @author zhaoru
 *
 */
public class Turing1 {
	public static void main(String args[]) {

		Turing machine = new Turing(1); // This machine will have one state.
										// Note: There is an additional halt
										// state.
										// The values on the input tape are set
										// to all B’s.

		Transition one = new Transition('0', Transition.RIGHT, 0);
		Transition two = new Transition('1', Transition.RIGHT, 0);
		Transition three = new Transition('B', Transition.LEFT, 1);

		machine.addTransition(0, '0', two);
		machine.addTransition(0, '1', one);
		machine.addTransition(0, 'B', three);

		String inTape = "11111100010101"; // The leftmost value of inTape will
											// be placed under the read write
											// head.

		System.out.println(inTape);

		String outTape = machine.execute(inTape);
		System.out.println(outTape);
	}

}