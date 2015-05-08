/**
 * The class DoubleNode holds two pointers and a integer. It is used to
 * represent a single node on a double linked list.
 * 
 * @author Ru Zhao
 *
 */
public class DoubleNode {
	private DoubleNode p, n;
	private int ch;

	/**
	 * Constructor with no arguments. Assign null values to previous, next.
	 */
	public DoubleNode() {
		this.p = null;
		this.n = null;
	}

	/**
	 * Constructor
	 * 
	 * @param p
	 *            p - is a pointer to a previous node.
	 * @param ch
	 *            ch - is an integer for this node.
	 * @param n
	 *            n - is a pointer to a next node.
	 */
	public DoubleNode(DoubleNode p, int ch, DoubleNode n) {
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
	 * Postcondition: the integer of this node is returned
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: 1
	 * 
	 * @return the integer of this node
	 */
	public int getC() {
		return this.ch;
	}

	/**
	 * Precondition: integer c exists Postcondition: integer c is assigned to
	 * this node
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: 1
	 * 
	 * @param c
	 *            integer c to assigned to this node
	 */
	public void setC(int c) {
		this.ch = c;
	}

	/**
	 * Override the toString in class java.lang.Object
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: 1
	 * 
	 * Postcondition: the String value of this node's integer is returned
	 */
	@Override
	public java.lang.String toString() {
		return String.valueOf(ch);
	}
}
