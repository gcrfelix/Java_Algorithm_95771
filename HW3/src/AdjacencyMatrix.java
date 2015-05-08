/**
 * This class handles the functions of the adjacency matrix
 * 
 * @author zhaoru
 *
 */
public class AdjacencyMatrix {
	private int[][] matrix;
	private int[] color;

	/**
	 * Constructor which initialized the matrix and color array as all zeros as
	 * default. color 0 means not colored. matrix 0 means no edge
	 */
	public AdjacencyMatrix() {
		matrix = new int[40][40];
		color = new int[40];
		for (int i = 0; i < 40; i++) {
			color[i] = 0;
			for (int j = 0; j < 40; j++) {
				matrix[i][j] = 0;
			}
		}
	}

	/**
	 * add and edge of two nodes, undirectly Pre-condition: x and y are within
	 * the range of the color array, and should be within the rbt size
	 * Post-condition: an edge is added
	 * 
	 * @param x
	 *            first node to add
	 * @param y
	 *            second node to add
	 */
	public void addEdge(int x, int y) {
		matrix[x][y] = 1;
		matrix[y][x] = 1;
	}

	/**
	 * get the first uncolored node of the matrix Pre-condition: len is within
	 * the range of the matrix, and should be the size of the rbt
	 * Post-condition: an integer number is returned
	 * 
	 * @param len
	 *            length of the valid length of the matrix
	 * @return the node number of the first uncolored node, or -1 if all nodes
	 *         are colored
	 */
	public int getFirstUncolored(int len) {
		for (int i = 0; i < len; i++) {
			if (color[i] == 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * get next uncolored node of v Pre-condition: len is within the range of
	 * the matrix, and should be the size of the rbt. v should be within the
	 * range of the matrix and the size of the rbt Post-condition: an integer
	 * number is returned
	 * 
	 * @param v
	 *            node of which to find the next uncolored node
	 * @param len
	 *            length of the valid length of the matrix
	 * @return the node number of the next uncolored node of v, or -1 if all
	 *         nodes are colored
	 */
	public int getNextUncolored(int v, int len) {
		for (int i = v + 1; i < len; i++) {
			if (color[i] == 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * check if all nodes are colored Pre-condition: len is within the range of
	 * the matrix, and should be the size of the rbt. Post-condition: an boolean
	 * value is returned
	 * 
	 * @param len
	 *            length of the valid length of the matrix
	 * @return a boolean value to find if all nodes are colored
	 */
	public boolean allColored(int len) {
		for (int i = 0; i < len; i++) {
			if (color[i] == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * set the color of a group of nodes Pre-condition: the node v is within the
	 * range of the color array, and should be within the rbt size
	 * Post-condition: the color of node v is set
	 * 
	 * @param v
	 *            the node to set color
	 * @param colorFlag
	 *            the flag of the color to set
	 */
	public void setColor(int v, int colorFlag) {
		color[v] = colorFlag;
	}

	/**
	 * find if there is an edge of x and y Pre-condition: x and y are within the
	 * range of the color array, and should be within the rbt size
	 * Post-condition: a boolean value is returned
	 * 
	 * @param x
	 *            first node
	 * @param y
	 *            second node
	 * @return a boolean value if there is edge between the two nodes
	 */
	public boolean isEdge(int x, int y) {
		if (matrix[x][y] == 1 && matrix[y][x] == 1)
			return true;
		return false;
	}

	/**
	 * display the matrix Pre-condition: len is within the range of the matrix,
	 * and should be the size of the rbt. Post-condition: an matrix is displayed
	 * 
	 * @param len
	 *            length of the valid length of the matrix
	 */
	public void display(int len) {
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				System.out.print(matrix[i][j] + "	");
			}
			System.out.println("\n");
		}
	}
}
