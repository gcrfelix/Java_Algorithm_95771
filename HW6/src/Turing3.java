/**
 * Turing machine to simulate part3
 * 
 * @author zhaoru
 *
 */
public class Turing3 {
	public static void main(String args[]) {

		Turing machine = new Turing(5);

		Transition one = new Transition('B', Transition.LEFT, 5);
		Transition two = new Transition('B', Transition.RIGHT, 1);
		
		Transition three = new Transition('B', Transition.RIGHT, 2);
		Transition four = new Transition('1', Transition.RIGHT, 1);
		
		Transition five = new Transition('1', Transition.LEFT, 3);
		Transition six = new Transition('1', Transition.RIGHT, 2);
		
		Transition seven = new Transition('B', Transition.LEFT, 4);
		Transition eight = new Transition('1', Transition.LEFT, 3);
		
		Transition nine = new Transition('1', Transition.RIGHT, 0);
		Transition ten = new Transition('1', Transition.LEFT, 4);

		machine.addTransition(0, 'B', one);
		machine.addTransition(0, '1', two);
		
		machine.addTransition(1, 'B', three);
		machine.addTransition(1, '1', four);
		
		machine.addTransition(2, 'B', five);
		machine.addTransition(2, '1', six);
		
		machine.addTransition(3, 'B', seven);
		machine.addTransition(3, '1', eight);
		
		machine.addTransition(4, 'B', nine);
		machine.addTransition(4, '1', ten);

		String inTape = "1111111";

		System.out.println(inTape);

		String outTape = machine.execute(inTape);
		System.out.println(outTape);
	}

}