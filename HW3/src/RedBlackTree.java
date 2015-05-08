/**
 * This class follows algorithms from red black tree and has methods within it
 * 
 * @author zhaoru
 *
 */
public class RedBlackTree {
	private RedBlackNode root, nil;
	private String[] courses;

	/**
	 * This constructor creates an empty RedBlackTree. It creates a RedBlackNode
	 * that represents null. It sets the internal variable tree to point to this
	 * node. This node that an empty tree points to will be used as a sentinel
	 * node. That is, all pointers in the tree that would normally contain the
	 * value null, will instead point to this sentinel node.
	 */
	public RedBlackTree() {
		courses = new String[40];
		nil = new RedBlackNode(null, 0, 0, null, null, null);
		root = nil;
	}

	/**
	 * The method closeBy(v) returns a value close to v in the tree. If v is
	 * found in the tree it returns v. Pre-condition: v exists, the tree is not
	 * empty Post-condition: a string close to or equals to v is returned Worst
	 * case BigTheta: log(n) Best case BigTheta: 1
	 * 
	 * @param v
	 *            v - the value to search close by for.
	 * @return a value close to v in the tree. If v is found in the tree it
	 *         returns v.
	 */
	public String closeBy(String v) {
		RedBlackNode search = root;
		RedBlackNode prev = search;
		while (search != nil) {
			prev = search;
			if (search.getData().equals(v))
				return v;
			else if (search.getData().compareTo(v) > 0)
				search = search.getLc();
			else
				search = search.getRc();
		}
		return prev.getData();
	}

	/**
	 * The boolean contains() returns true if the String v is in the
	 * RedBlackTree and false otherwise. It counts each comparison it makes in
	 * the variable recentCompares. Pre-condition: v exists, the tree is already
	 * generated Post-condition: a boolean value is returned Worst case
	 * BigTheta: log(n) Best case BigTheta: 1
	 * 
	 * @param v
	 *            x - the value to search for
	 * @return true if v is in the tree, false otherwise
	 */
	public boolean contains(String v) {
		RedBlackNode search = root;
		while (search != nil) {
			if (search.getData().equals(v))
				return true;
			else if (search.getData().compareTo(v) > 0)
				search = search.getLc();
			else
				search = search.getRc();
		}
		return false;
	}

	/**
	 * findIndexByName(String v) finds the index of a string v, return -1 if not
	 * found Pre-condition: v exists, the tree is already generated
	 * Post-condition: a int value is returned
	 * 
	 * @param v
	 *            the value to search for
	 * @return index of the string v, -1 if not found
	 */
	public int findIndexByName(String v) {
		for (int i = 0; i < courses.length; i++) {
			if (courses[i].equals(v)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * findNameByIndex(int index) finds a string with certain index, return null
	 * if the index is out of range, or there is no course with that index
	 * Pre-condition: index is equal or greater than 0, index exists, the tree
	 * is already generated Post-condition: a String value is returned
	 * 
	 * @param index
	 *            the index of the name
	 * @return the name with certain index
	 */
	public String findNameByIndex(int index) {
		if (index < courses.length) {
			return courses[index];
		}
		return null;
	}

	/**
	 * Get the number of nodes inside the tree Post-condition: a non-negative
	 * integer number of the size is returned BigTheta: n (no case)
	 * 
	 * @return number of values inserted into the tree.
	 */
	public int getSize() {
		int counter = 0;
		if (root == nil) {
			return counter;
		}
		RedBlackNode find;
		Queue queue = new Queue();
		queue.enQueue(root);
		while (!queue.isEmpty()) {
			find = (RedBlackNode) queue.deQueue();
			counter++;
			if (find.getLc() != nil) {
				queue.enQueue(find.getLc());
			}
			if (find.getRc() != nil) {
				queue.enQueue(find.getRc());
			}
		}
		return counter;
	}

	/**
	 * This a recursive routine that is used to compute the height of the red
	 * black tree. It is called by the height() method. The height() method
	 * passes the root of the tree to this method. This method would normally be
	 * private. Pre-condition: node t is contained in the tree Post-condition:
	 * the height of node t is returned Worst case BigTheta: n Best case
	 * BigTheta: 1 (t is a leaf)
	 * 
	 * @param t
	 *            t - a pointer to a node in the tree.
	 * @return the height of node t
	 */
	public int height(RedBlackNode t) {
		if (t == nil)
			return -1;
		else
			return Math.max(height(t.getLc()), height(t.getRc())) + 1;
	}

	/**
	 * This method calls the recursive method height(RedBlackNode t).
	 * Post-condition: the height of root is returned BigTheta: n
	 * 
	 * @return the height of the red black tree.
	 */
	public int height() {
		return height(root);
	}

	/**
	 * Perform an inorder traversal of the tree. The
	 * inOrderTraversal(RedBlackNode) method is recursive and displays the
	 * content of the tree. It makes calls on System.out.println(). This method
	 * would normally be private. Pre-condition: node t is contained in the tree
	 * Post-condition: the content of the tree after node t is displayed by
	 * inOrderTraversal Worst case BigTheta: n Best case BigTheta: 1 (t is a
	 * leaf)
	 * 
	 * @param t
	 *            t - the root of the tree on the first call.
	 */
	public void inOrderTraversal(RedBlackNode t) {
		if (t.getLc() != nil)
			inOrderTraversal(t.getLc());
		System.out.println(t.getData() + " -> " + t.getIndex());
		if (t.getRc() != nil)
			inOrderTraversal(t.getRc());
	}

	/**
	 * The no argument inOrderTraversal() method calls the recursive
	 * inOrderTraversal(RedBlackNode) - passing the root. Post-condition: the
	 * content of the tree from the root is displayed by inOrderTraversal
	 * BigTheta: n
	 */
	public void inOrderTraversal() {
		inOrderTraversal(root);
	}

	/**
	 * Perform a reverseOrder traversal of the tree. The
	 * reverseOrderTraversal(RedBlackNode) method is recursive and displays the
	 * content of the tree in reverse order. This method would normally be
	 * private. Pre-condition: node t is contained in the tree Post-condition:
	 * the content of the tree after node t is displayed by
	 * reverseOrderTraversal Worst case BigTheta: n Best case BigTheta: 1 (t is
	 * a leaf)
	 * 
	 * @param t
	 *            t - the root of the tree on the first call.
	 */
	public void reverseOrderTraversal(RedBlackNode t) {
		String colorString, pString, lcString, rcString;
		pString = t.getP().getData();
		lcString = t.getLc().getData();
		rcString = t.getRc().getData();
		if (t.getColor() == 1)
			colorString = "Red";
		else
			colorString = "Black";
		if (t.getP() == nil)
			pString = "-1";
		if (t.getLc() == nil)
			lcString = "-1";
		if (t.getRc() == nil)
			rcString = "-1";

		if (t.getRc() != nil)
			reverseOrderTraversal(t.getRc());
		System.out.println("[data = " + t.getData() + ":Color = " + colorString
				+ ":Parent = " + pString + ": LC = " + lcString + ": RC = "
				+ rcString + "]");
		if (t.getLc() != nil)
			reverseOrderTraversal(t.getLc());
	}

	/**
	 * The no argument reverseOrderTraversal() method calls the recursive
	 * reverseOrderTraversal(RedBlackNode) - passing the root. Post-condition:
	 * the content of the tree from the root is displayed by
	 * reverseOrderTraversal BigTheta: n
	 */
	public void reverseOrderTraversal() {
		reverseOrderTraversal(root);
	}

	/**
	 * This method displays the RedBlackTree in level order. It first displays
	 * the root. Then it displays all children of the root. Then it displays all
	 * nodes on level 3 and so on. It is not recursive. It uses a queue.
	 * Post-condition: the content of the tree from the root is displayed by
	 * levelOrderTraversal BigTheta: n
	 */
	public void levelOrderTraversal() {
		RedBlackNode find;
		Queue queue = new Queue();
		queue.enQueue(root);
		while (!queue.isEmpty()) {
			find = (RedBlackNode) queue.deQueue();
			System.out.println(find.toString());
			if (find.getLc() != nil) {
				queue.enQueue(find.getLc());
			}
			if (find.getRc() != nil) {
				queue.enQueue(find.getRc());
			}
		}
	}

	/**
	 * The insert() method places a data item into the tree Pre-condition:
	 * memory is available for insertion, the value should not be contained(if
	 * contained, a repeated value would be inserted) Post-consition: the value
	 * is inserted BigTheta: log(n) (no case)
	 * 
	 * @param value
	 *            value - is an integer to be inserted
	 */
	public void insert(String value) {
		int index = getSize();
		RedBlackNode x = root, y = nil, z = new RedBlackNode(value, index, 1,
				nil, nil, nil);
		while (x != nil) {
			y = x;
			if (value.compareTo(x.getData()) < 0)
				x = x.getLc();
			else if (value.compareTo(x.getData()) > 0)
				x = x.getRc();
			else {
				System.out.println(value
						+ " is already in dictionary, the index of which is "
						+ x.getIndex());
				return;
			}
		}
		z.setP(y);
		if (y == nil)
			root = z;
		else {
			if (value.compareTo(y.getData()) < 0)
				y.setLc(z);
			else
				y.setRc(z);
		}
		z.setLc(nil);
		z.setRc(nil);
		courses[index] = value;
		RBInsertFixup(z);
	}

	/**
	 * leftRotate() performs a single left rotation. This would normally be a
	 * private method. Pre-condition: right[x] != nil[T], root's parent is
	 * nill[T] Post-condition: some nodes are left rotated BigTheta: 1 (no case)
	 * 
	 * @param x
	 *            the axis node to rotate
	 */
	public void leftRotate(RedBlackNode x) {
		RedBlackNode y = x.getRc();
		x.setRc(y.getLc());
		y.getLc().setP(x);
		y.setP(x.getP());
		if (x.getP() == nil)
			root = y;
		else {
			if (x == x.getP().getLc())
				x.getP().setLc(y);
			else
				x.getP().setRc(y);
		}
		y.setLc(x);
		x.setP(y);
	}

	/**
	 * rightRotate() performs a single right rotation This would normally be a
	 * private method. Pre-condition: left[x] != nil[T], root's parent is
	 * nill[T] Post-condition: some nodes are right rotated BigTheta: 1 (no
	 * case)
	 * 
	 * @param x
	 *            the axis node to rotate
	 */
	public void rightRotate(RedBlackNode x) {
		RedBlackNode y = x.getLc();
		x.setLc(y.getRc());
		y.getRc().setP(x);
		y.setP(x.getP());
		if (x.getP() == nil)
			root = y;
		else {
			if (x == x.getP().getLc())
				x.getP().setLc(y);
			else
				x.getP().setRc(y);
		}
		y.setRc(x);
		x.setP(y);
	}

	/**
	 * Fixing up the tree so that Red Black Properties are preserved. This would
	 * normally be a private method. Pre-condition: node z is contained in the
	 * tree Post-condition: insertion is fixed up under certain condition
	 * BigTheta: 1
	 * 
	 * @param z
	 *            z - is the new node
	 */
	public void RBInsertFixup(RedBlackNode z) {
		RedBlackNode y; // Uncle
		while (z.getP().getColor() == 1) {
			if (z.getP() == z.getP().getP().getLc()) {
				y = z.getP().getP().getRc();
				if (y.getColor() == 1) {
					z.getP().setColor(0);
					y.setColor(0);
					z.getP().getP().setColor(1);
					z = z.getP().getP();
				} else {
					if (z == z.getP().getRc()) {
						z = z.getP();
						leftRotate(z);
					}
					z.getP().setColor(0);
					z.getP().getP().setColor(1);
					rightRotate(z.getP().getP());
				}
			} else {
				y = z.getP().getP().getLc();
				if (y.getColor() == 1) {
					z.getP().setColor(0);
					y.setColor(0);
					z.getP().getP().setColor(1);
					z = z.getP().getP();
				} else {
					if (z == z.getP().getLc()) {
						z = z.getP();
						rightRotate(z);
					}
					z.getP().setColor(0);
					z.getP().getP().setColor(1);
					leftRotate(z.getP().getP());
				}
			}
		}
		root.setColor(0);
	}
}
