import java.math.BigInteger;

/**
 * The class SingleNode holds one pointer and a biginteger. It is used to
 * represent a single node on a singly linked list.
 * 
 * @author Ru Zhao
 *
 */
public class SingleNode {
	private SingleNode next;
	private BigInteger bi;

	/**
	 * Constructor to initialize a null node
	 */
	public SingleNode() {
		next = null;
		bi = null;
	}

	/**
	 * Constructor to initialize a non-null node
	 * 
	 * @param next
	 *            a pointer to next node
	 * @param bi
	 *            the biginteger of this node
	 */
	public SingleNode(SingleNode next, BigInteger bi) {
		this.next = next;
		this.bi = bi;
	}

	/**
	 * Get next node.
	 * 
	 * Postcondition: pointer to next node is returned
	 * 
	 * Best case Big Theta: 1 Worst case Big Theta: 1
	 * 
	 * @return pointer to next node
	 */
	public SingleNode getNext() {
		return this.next;
	}

	/**
	 * Get next node.
	 * 
	 * Precondition: next node exists Postcondition: pointer to next node is set
	 * 
	 * Best case Big Theta: 1 Worst case Big Theta: 1
	 * 
	 * @param next
	 *            pointer to set next node
	 */
	public void setNext(SingleNode next) {
		this.next = next;
	}

	/**
	 * Get biginteger of this node.
	 * 
	 * Precondition: biginteger if this node exists Postcondition: biginteger of
	 * this node is returned
	 * 
	 * Best case Big Theta: 1 Worst case Big Theta: 1
	 * 
	 * @return biginteger value of this node
	 */
	public BigInteger getBigInt() {
		return this.bi;
	}

	/**
	 * Set biginteger of this node.
	 * 
	 * Precondition: bi exists Postcondition: biginteger of this node is set
	 * 
	 * Best case Big Theta: 1 Worst case Big Theta: 1
	 * 
	 * @param bi
	 *            biginteger value to set to this node
	 */
	public void setBigInt(BigInteger bi) {
		this.bi = bi;
	}

	/**
	 * Override the toString in class java.lang.Object
	 * 
	 * Best case Big Theta: 1 Worst case Big Theta: 1
	 */
	@Override
	public String toString() {
		return bi.toString();
	}
}
