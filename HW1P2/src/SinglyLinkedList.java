import java.math.BigInteger;

/**
 * This class implements a singly linked list of characters in Java. The
 * instance variables head and tail are initially null. As elements are added
 * head points to the first element on the list and tail points to the last
 * element. Each node on the list is of type SingleNode. Each SingleNode holds a
 * pointer to the next node in the list.
 * 
 * @author Ru Zhao
 *
 */
public class SinglyLinkedList {
	private SingleNode head, tail;

	/**
	 * Constructor Constructs a new SinglyLinkedList object with head and tail
	 * as null.
	 */
	public SinglyLinkedList() {
		head = new SingleNode();
		tail = new SingleNode();
		head.setNext(tail);
		tail.setNext(null);
	}

	/**
	 * Returns true if the list is empty false otherwise
	 * 
	 * Postcondition: a boolean value true or false is returned
	 * 
	 * Best case Big Theta: 1 Worst case Big Theta: 1
	 * 
	 * @return true if the list empty false otherwise
	 */
	public boolean isEmpty() {
		if (head.getNext() == tail)
			return true;
		return false;
	}

	/**
	 * Add a node containing the biginteger bi to the front of the linked list.
	 * No search is required.
	 * 
	 * Precondition: biginteger bi exists Postcondition: a node containing the
	 * biginteger bi is added to the front of the linked list
	 * 
	 * Best case Big Theta: 1 Worst case Big Theta: 1
	 * 
	 * @param bi
	 *            bi - -- a biginteger
	 */
	public void addBigIntFront(BigInteger bi) {
		SingleNode x = new SingleNode(head.getNext(), bi);
		head.setNext(x);
	}

	/**
	 * Add a node containing the biginteger bi to the end of the linked list. No
	 * search is required.
	 * 
	 * Precondition: biginteger bi exists Postcondition: a node containing the
	 * biginteger bi is added to the end of the linked list
	 * 
	 * Best case Big Theta: 1 Worst case Big Theta: 1
	 * 
	 * @param bi
	 *            bi - -- a biginteger
	 */
	public void addBigIntEnd(BigInteger bi) {
		SingleNode point = head;
		while (point.getNext() != tail) {
			point = point.getNext();
		}
		SingleNode x = new SingleNode(tail, bi);
		point.setNext(x);
	}

	/**
	 * Get the biginteger of the Nth node, n starts from 1. If list is empty or
	 * n is larger than the number of nodes, return null
	 * 
	 * Precondition: integer n exists, list is not empty, n is less or equal to
	 * number of nodes in the list Postcondition: the biginteger of the Nth node
	 * is returned, or null is returned
	 * 
	 * Best case Big Theta: 1 Worst case Big Theta: n
	 * 
	 * @param n
	 *            the Nth node to look for
	 * @return the biginteger of the Nth node, or null if list is empty or n is
	 *         larger than the number of nodes
	 */
	public BigInteger getNth(int n) {
		if (isEmpty())
			return null;
		if (n > countNodes())
			return null;
		SingleNode point = head;
		for (int i = 0; i < n; i++) {
			point = point.getNext();
		}
		return point.getBigInt();
	}

	/**
	 * Count the number of nodes in the list.
	 * 
	 * Postcondition: a number of the length of the list is returned
	 * 
	 * Best case Big Theta: n Worst case Big Theta: n
	 * 
	 * @return the number of nodes in the list
	 */
	public int countNodes() {
		int count = 0;
		SingleNode point = head;
		while (point.getNext() != null) {
			point = point.getNext();
			count++;
		}
		return count - 1; // tail is not null
	}
}
