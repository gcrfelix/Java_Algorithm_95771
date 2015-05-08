/**
 * This class stores a node of a Red Black Tree.
 * 
 * @author zhaoru
 *
 */
public class RedBlackNode {
	public static final int RED = 1;
	public static final int BLACK = 0;
	private String data;
	private int index;
	private int color;
	private RedBlackNode p, lc, rc;

	/**
	 * Constructor. Construct a RedBlackNode with data, color, parent pointer,
	 * left child pointer and right child pointer.
	 * 
	 * @param data
	 *            data - a simple value held in the tree
	 * @param index
	 *            the integer or index of the node
	 * @param color
	 *            color - either RED or BLACK
	 * @param p
	 *            p - the parent pointer
	 * @param lc
	 *            lc - the pointer to the left child (will be null only for the
	 *            node that represents all external nulls.
	 * @param rc
	 *            rc - the pointer to the right child (will be null only for the
	 *            node that represents all external nulls.
	 */
	public RedBlackNode(String data, int index, int color, RedBlackNode p,
			RedBlackNode lc, RedBlackNode rc) {
		this.data = data;
		this.index = index;
		this.color = color;
		this.p = p;
		this.lc = lc;
		this.rc = rc;
	}

	/**
	 * The getColor() method returns RED or BLACK. Post-condition: the color
	 * value is returned BigTheta: 1
	 * 
	 * @return the color value (RED or BLACK)
	 */
	public int getColor() {
		return this.color;
	}

	/**
	 * getIndex() method returns index of this node. Post-condition: the index
	 * value is returned
	 * 
	 * @return index of this node
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * The getData() method returns the data in the node. Post-condition: the
	 * data value is returned BigTheta: 1
	 * 
	 * @return the data value in the node
	 */
	public String getData() {
		return this.data;
	}

	/**
	 * The getLc() method returns the left child of the RedBlackNode.
	 * Post-condition: the lc value is returned BigTheta: 1
	 * 
	 * @return the left child field
	 */
	public RedBlackNode getLc() {
		return this.lc;
	}

	/**
	 * The getP() method returns the parent of the RedBlackNode. Post-condition:
	 * the p value is returned BigTheta: 1
	 * 
	 * @return the parent field
	 */
	public RedBlackNode getP() {
		return this.p;
	}

	/**
	 * The getRc() method returns the right child of the RedBlackNode.
	 * Post-condition: the rc value is returned BigTheta: 1
	 * 
	 * @return the right child field
	 */
	public RedBlackNode getRc() {
		return this.rc;
	}

	/**
	 * The setColor() method sets the color of the RedBlackNode. Pre-condition:
	 * color is either 0 or 1 Post-condition: the color value is set BigTheta: 1
	 * 
	 * @param color
	 *            color - is either RED or BLACK
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * The setData() method sets the data or key of the RedBlackNode.
	 * Post-condition: the data value is set BigTheta: 1
	 * 
	 * @param data
	 *            data - is an int holding a node's data value
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * The setLc() method sets the left child of the RedBlackNode.
	 * Post-condition: the lc value is set BigTheta: 1
	 * 
	 * @param lc
	 *            lc - establishes a left child for this node
	 */
	public void setLc(RedBlackNode lc) {
		this.lc = lc;
	}

	/**
	 * The setP() method sets the parent of the RedBlackNode. Post-condition:
	 * the p value is set BigTheta: 1
	 * 
	 * @param p
	 *            p - establishes a parent pointer for this node
	 */
	public void setP(RedBlackNode p) {
		this.p = p;
	}

	/**
	 * The setRc() method sets the right child of the RedBlackNode.
	 * Post-condition: the rc value is set BigTheta: 1
	 * 
	 * @param rc
	 *            rc - establishes a right child for this node.
	 */
	public void setRc(RedBlackNode rc) {
		this.rc = rc;
	}

	/**
	 * The toString() methods returns a string representation of the
	 * RedBlackNode. Overrides: toString in class java.lang.Object Returns: the
	 * string representation of a RedBlackNode Post-condition: the string
	 * representation of the RedBlackNode is returned BigTheta: 1
	 */
	@Override
	public String toString() {
		/*
		 * If a node's nearby is a nil, that is, this node's p, lc, or rc is
		 * nil. The nil's lc and rc should both be null, but nil's p should not
		 * be null.
		 */
		String colorString, pString, lcString, rcString;
		if (color == RED)
			colorString = "Red";
		else
			colorString = "Black";
		if ((p.getLc() == null) && (p.getRc() == null))
			pString = "-1";
		else
			pString = String.valueOf(p.getData());
		if ((lc.getLc() == null) && (lc.getRc() == null))
			lcString = "-1";
		else
			lcString = String.valueOf(lc.getData());
		if ((rc.getLc() == null) && (rc.getRc() == null))
			rcString = "-1";
		else
			rcString = String.valueOf(rc.getData());
		String line = "[data = " + data + ":Color = " + colorString
				+ ":Parent = " + pString + ": LC = " + lcString + ": RC = "
				+ rcString + "]";
		return line;
	}
}
