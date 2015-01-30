/**
 * The class DoubleNode holds two pointers and a character. It is used to
 * represent a single node on a double linked list.
 * 
 * @author Ru Zhao
 *
 */
public class DoubleNode {
	private DoubleNode p, n;
	private char ch;

	/**
	 * Constructor with no arguments. Assign null values to previous, next and
	 * the null character to c.
	 */
	public DoubleNode() {
		this.p = null;
		this.n = null;
		this.ch = '\u0000';
	}

	/**
	 * Constructor
	 * 
	 * @param p
	 *            p - is a pointer to a previous node.
	 * @param ch
	 *            ch - is a character for this node.
	 * @param n
	 *            n - is a pointer to a next node.
	 */
	public DoubleNode(DoubleNode p, char ch, DoubleNode n) {
		this.p = p;
		this.n = n;
		this.ch = ch;
	}

	/**
	 * Postcondition: the pointer to the previous node is returned
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: 1
	 * 
	 * @return a pointer to the previous node or null if none exists
	 */
	public DoubleNode getPrev() {
		return this.p;
	}

	/**
	 * Precondition: the node prev exists Postcondition: the pointer p is
	 * pointed to the node prev
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: 1
	 * 
	 * @param prev
	 *            a pointer to the previous node
	 */
	public void setPrev(DoubleNode prev) {
		this.p = prev;
	}

	/**
	 * Postcondition: the pointer to the next node is returned
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: 1
	 * 
	 * @return a pointer to the next node or null if none exists
	 */
	public DoubleNode getNext() {
		return this.n;
	}

	/**
	 * Precondition: the node next exists Postcondition: the pointer n is
	 * pointed to the node next
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: 1
	 * 
	 * @param next
	 *            a pointer to the next node
	 */
	public void setNext(DoubleNode next) {
		this.n = next;
	}

	/**
	 * Postcondition: the character of this node is returned
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: 1
	 * 
	 * @return the character of this node
	 */
	public char getC() {
		return this.ch;
	}

	/**
	 * Precondition: char c exists Postcondition: char c is assigned to this
	 * node
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: 1
	 * 
	 * @param c
	 *            char c to assigned to this node
	 */
	public void setC(char c) {
		this.ch = c;
	}

	/**
	 * Override the toString in class java.lang.Object
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: 1
	 * 
	 * Postcondition: the String value of this node's character is returned
	 */
	@Override
	public java.lang.String toString() {
		return String.valueOf(ch);
	}

	/**
	 * Test driver for DoubleNode
	 * 
	 * @param args
	 *            Arguments input by users
	 */
	public static void main(java.lang.String[] args) {
		DoubleNode pre = new DoubleNode();
		DoubleNode nex = new DoubleNode();
		pre.setC('a');
		pre.setPrev(null);
		pre.setNext(nex);
		System.out.println(pre.toString());
	}
}
