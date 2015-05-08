import java.io.*;
import java.util.*;

/**
 * This class handles whole possibility and the main method
 * 
 * @author zhaoru
 *
 */
public class FinalSchedule {
	private RedBlackTree rbt;
	private AdjacencyMatrix am;
	private int len;

	/**
	 * Constructor initialize the length of the matrix as zero at first, but
	 * will be updated once the rbt is well built
	 */
	public FinalSchedule() {
		rbt = new RedBlackTree();
		am = new AdjacencyMatrix();
		len = 0;
	}

	/**
	 * set the length of the matrix (valid part), as the size of rbt
	 */
	public void setLen() {
		this.len = rbt.getSize();
	}

	/**
	 * insert a node of string value v to the rbt Pre-condition: none, because
	 * checking if v is contained in rbt has been handled in the insert method.
	 * Post-condition: v is inserted to rbt, or not inserted with a console
	 * window notification
	 * 
	 * @param v
	 *            string value to insert
	 */
	public void insert(String v) {
		rbt.insert(v);
	}

	/**
	 * add the edge of two node, undirectly Pre-condition: x and y are within
	 * the range of the matrix, detailedly should be within the size of rbt
	 * Post-condition: the edge has been added two direction
	 * 
	 * @param x
	 *            first node
	 * @param y
	 *            second node
	 */
	public void addEdge(int x, int y) {
		am.addEdge(x, y);
	}

	/**
	 * using the method from rbt, to find a index by name
	 * 
	 * @param name
	 *            name to search
	 * @return the index of that string name, or -1 if not found
	 */
	public int findIndexByName(String name) {
		return rbt.findIndexByName(name);
	}

	/**
	 * display the courses using in-order traversal from rbt Post-condition: the
	 * content of the tree from the root is displayed by inOrderTraversal
	 */
	public void displayCourses() {
		rbt.inOrderTraversal();
	}

	/**
	 * display the matrix Pre-condition: len is the size of the rbt, and should
	 * be less than the range of the matrix, which is 40. The rbt and matrix are
	 * well built Post-condition: the matrix is desplayed
	 */
	public void displayMatrix() {
		am.display(len);
	}

	/**
	 * color the matrix using greedy algorithm, and print out the colored nodes
	 * when building Pre-condition: the rbt and matrix are well built
	 * post-condition: the matrix is colored and final schedule is printed out
	 */
	public void color() {
		DoublyLinkedList[] lists = new DoublyLinkedList[40];
		int count = 0;
		int colorFlag = 1;
		boolean found;
		int v, w;
		while (!am.allColored(len)) {
			DoublyLinkedList newclr = new DoublyLinkedList();
			v = am.getFirstUncolored(len);
			while (v != -1) {
				found = false;
				DoubleNode first = newclr.getFirst();
				while (first != null) {
					if (first.getNext() != null) {
						w = first.getC();
						if (am.isEdge(v, w))
							found = true;
					}
					first = first.getNext();
				}
				if (found == false) {
					am.setColor(v, colorFlag);
					newclr.addCharAtFront(v);
				}
				v = am.getNextUncolored(v, len);
			}
			lists[count] = newclr;
			count++;
			colorFlag++;

			System.out.print("Final Exam Period " + count + " => ");
			DoubleNode x = newclr.getFirst();
			while (x.getNext() != null) {
				System.out.print(rbt.findNameByIndex(x.getC()) + " ");
				x = x.getNext();
			}
			System.out.println();
		}
	}

	/**
	 * main method to read file, build rbt and matrix, and color the matrix and
	 * print outputs.
	 * 
	 * @param args
	 *            the filename to read. if executed in eclipse, "src/" is
	 *            needed. If executed in Unix, "src/" should be deleted. See
	 *            below
	 */
	public static void main(String[] args) {
		FinalSchedule finalSchedule = new FinalSchedule();
		System.out.println("Building R-B Tree...");
		try {
			// Be careful, if executed in eclipse, "src/" is needed. If executed
			// in Unix, "src/" should be deleted

			// BufferedReader in = new BufferedReader(new FileReader("src/" + args[0]));
			BufferedReader in = new BufferedReader(new FileReader(args[0]));

			String line;
			line = in.readLine();

			while (line != null) {
				processLine(line, finalSchedule);
				line = in.readLine();
			}
			in.close();
			finalSchedule.setLen();
		} catch (IOException e) {
			System.out.println("IO Exception");
			return;
		}

		System.out.println();
		System.out.println("Displaying courses in alphabetical order:");
		finalSchedule.displayCourses();
		System.out.println();
		System.out.println("Displaying Adjacent Matrix:");
		finalSchedule.displayMatrix();
		System.out
				.println("RECOMMENDED SCHEDULE OF FINAL EXAMS (NOT NECESSARILY OPTIMAL)");
		finalSchedule.color();
	}

	/**
	 * helper method to build the rbt and matrix at the same time using the
	 * input file, which detailedly is one step of a line input
	 * 
	 * @param line
	 *            the line input
	 * @param finalSchedule
	 *            the finalSchedule instance parsed in
	 */
	public static void processLine(String line, FinalSchedule finalSchedule) {

		StringTokenizer st;
		String[] element = new String[8];
		int numElms = 0;

		// use comma, space, and tab for delimiters
		st = new StringTokenizer(line, " \t");

		while (st.hasMoreTokens()) {
			element[numElms] = st.nextToken();
			numElms++;
		}

		for (int i = 2; i < numElms; i++) {
			finalSchedule.insert(element[i]);
		}

		for (int j = 2; j < numElms - 1; j++) {
			for (int k = j + 1; k < numElms; k++) {
				finalSchedule.addEdge(
						finalSchedule.findIndexByName(element[j]),
						finalSchedule.findIndexByName(element[k]));
			}
		}
	}
}
