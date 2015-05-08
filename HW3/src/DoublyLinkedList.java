/**
 * This class implements a doubly linked list of characters in Java. The
 * instance variables head and tail are initially null. As elements are added
 * head points to the first element on the list and tail points to the last
 * element. Each node on the list is of type DoubleNode. Each DoubleNode holds a
 * pointer to the previous node and a pointer to the next node in the list.
 * 
 * @author Ru Zhao
 *
 */
public class DoublyLinkedList {
	private DoubleNode head, tail;

	/**
	 * Constructor Constructs a new DoublyLinkedList object with head and tail
	 * as null.
	 */
	public DoublyLinkedList() {
		head = new DoubleNode();
		tail = new DoubleNode();
		head.setNext(tail);
		tail.setPrev(head);
	}

	/**
	 * Returns true if the list is empty false otherwise
	 * 
	 * Postcondition: a boolean value true or false is returned
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: 1
	 * 
	 * @return true if the list empty false otherwise
	 */
	public boolean isEmpty() {
		if (head.getNext() == tail && tail.getPrev() == head) {
			return true;
		}
		return false;
	}

	/**
	 * Add a character node containing the character c to the end of the linked
	 * list. This routine does not require a search.
	 * 
	 * Precondition: char c exists Postcondition: a character node containing
	 * the character c is added to the end of the linked list
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: 1
	 * 
	 * @param c
	 *            c - -- a single character
	 */
	public void addCharAtEnd(int c) {
		DoubleNode prev = tail.getPrev();
		DoubleNode x = new DoubleNode(prev, c, tail);
		tail.setPrev(x);
		prev.setNext(x);
	}

	/**
	 * Add a character node containing the character c to the front of the
	 * linked list. No search is required.
	 * 
	 * Precondition: char c exists Postcondition: a character node containing
	 * the character c is added to the front of the linked list
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: 1
	 * 
	 * @param c
	 *            c - -- a single character
	 */
	public void addCharAtFront(int c) {
		DoubleNode next = head.getNext();
		DoubleNode x = new DoubleNode(head, c, next);
		head.setNext(x);
		next.setPrev(x);
	}

	/**
	 * Remove and return the character at the front of the doubly linked list.
	 * 
	 * Precondition: the list is not empty Postcondition: the front character is
	 * removed
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: 1
	 * 
	 * @return the character at the front
	 */
	public int removeCharFromFront() {
		DoubleNode front = head.getNext();
		head.setNext(front.getNext());
		front.getNext().setPrev(head);
		return front.getC();
	}

	/**
	 * Remove and return the character at the end of the doubly linked list. No
	 * searching is required.
	 * 
	 * Precondition: the list is not empty Postcondition: the end character is
	 * removed
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: 1
	 * 
	 * @return the character at the end
	 */
	public int removeCharAtEnd() {
		DoubleNode end = tail.getPrev();
		tail.setPrev(end.getPrev());
		end.getPrev().setNext(tail);
		return end.getC();
	}

	/**
	 * Counts the number of nodes in the list. We are not maintaining a counter
	 * so a search is required.
	 * 
	 * Postcondition: the number of nodes in the list is returned
	 * 
	 * Best case Big Theta: n Worse case Big Theta: n
	 * 
	 * @return the number of nodes in the doubly linked list between head and
	 *         tail inclusive
	 */
	public int countNodes() {
		int count = 0;
		DoubleNode point = head;
		while (point.getNext() != null) {
			point = point.getNext();
			count++;
		}
		return count - 1; // tail is not null
	}

	/**
	 * Deletes the first occur of the character c from the list. If the
	 * character c is not in the list then no modifications are made. This
	 * method needs to search the list.
	 * 
	 * Precondition: char c exists Postcondition: the first occur of the
	 * character c is deleted, a boolean value is returned
	 * 
	 * Best case Big Theta: 1 Worse case Big Theta: n
	 * 
	 * @param c
	 *            the character to be searched for
	 * @return true if a deletion occurred and false otherwise
	 */
	public boolean deleteChar(int c) {
		DoubleNode point = head;
		while (point.getNext() != null) {
			point = point.getNext();
			if (point.getC() == c) {
				point.getPrev().setNext(point.getNext());
				point.getNext().setPrev(point.getPrev());
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the list as a String. The class DoubleNode has a toString that
	 * will be called from this toString.
	 * 
	 * Best case Big Theta: n Worse case Big Theta: n
	 * 
	 * Postcondition: a String contain the characters in the list is retuened
	 */
	@Override
	public java.lang.String toString() {
		StringBuilder sb = new StringBuilder();
		DoubleNode point = head;
		while (point.getNext() != null && point.getNext() != tail) {
			point = point.getNext();
			sb.append(point.toString());
		}
		return sb.toString();
	}

	/**
	 * Reverse the list
	 * 
	 * Best case Big Theta: n Worse case Big Theta: n
	 * 
	 * Postcondition: the list is reversed
	 */
	public void reverse() {
		DoubleNode temp = head;
		head = tail;
		tail = temp;
		DoubleNode p = head;
		while (p != null) {
			temp = p.getNext();
			p.setNext(p.getPrev());
			p.setPrev(temp);
			p = p.getNext();
		}
	}

	/**
	 * get the first element
	 * 
	 * @return the first element, or null if the list is empty
	 */
	public DoubleNode getFirst() {
		if (!isEmpty()) {
			return head.getNext();
		}
		return null;
	}
}
