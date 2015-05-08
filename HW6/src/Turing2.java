/**
 * Turing machine to simulate part2
 * 
 * @author zhaoru
 *
 */
public class Turing2 {
	public static void main(String args[]) {

		// the halt state is 6
		Turing machine = new Turing(6);

		Transition one0 = new Transition('B', Transition.RIGHT, 1);
		
		Transition one1 = new Transition('0', Transition.RIGHT, 1);
		Transition two1 = new Transition('1', Transition.RIGHT, 2);
		
		Transition one2 = new Transition('1', Transition.RIGHT, 2);
		Transition two2 = new Transition('1', Transition.LEFT, 3);
		
		Transition one3 = new Transition('0', Transition.LEFT, 3);
		Transition two3 = new Transition('1', Transition.LEFT, 3);
		Transition three3 = new Transition('B', Transition.RIGHT, 0);
		
		Transition one4 = new Transition('B', Transition.LEFT, 4);
		Transition two4 = new Transition('0', Transition.LEFT, 4);
		Transition three4 = new Transition('0', Transition.RIGHT, 6);
		
		Transition one5 = new Transition('B', Transition.RIGHT, 5);
		Transition two5 = new Transition('B', Transition.RIGHT, 6);

		machine.addTransition(0, '0', one0);
		
		machine.addTransition(1, '0', one1);
		machine.addTransition(1, '1', two1);
		
		machine.addTransition(2, '1', one2);
		machine.addTransition(2, '0', two2);
		
		machine.addTransition(3, '0', one3);
		machine.addTransition(3, '1', two3);
		machine.addTransition(3, 'B', three3);
		
		machine.addTransition(2, 'B', one4);
		machine.addTransition(4, '1', one4);
		machine.addTransition(4, '0', two4);
		machine.addTransition(4, 'B', three4);
		
		machine.addTransition(0, '1', one5);
		machine.addTransition(5, '0', one5);
		machine.addTransition(5, '1', one5);
		machine.addTransition(5, 'B', two5);

		String inTape = "00000010000"; 

		System.out.println(inTape);

		String outTape = machine.execute(inTape);
		System.out.println(outTape);
	}

}