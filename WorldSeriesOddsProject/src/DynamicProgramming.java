/**
 * The class to use dynamic programming to calculate possibility for a team to
 * win the game.
 * 
 * @author zhaoru
 *
 */
public class DynamicProgramming {
	private double[][] t;
	private int size;

	/**
	 * Constructor to initialize the size
	 * 
	 * @param n
	 *            size of the table
	 */
	public DynamicProgramming(int n) {
		size = n;
		t = new double[n][n];
	}

	/**
	 * method to calculate possibility table for i and j from 0 to 50
	 * Post-condition: the table is computed with a short time BigTheta: n^2
	 * (n+n^2, no case)
	 */
	public void computeTable() {
		for (int n = 0; n < size; n++) {
			t[n][0] = 0.0;
			t[0][n] = 1.0;
		}
		for (int i = 1; i < size; i++) {
			for (int j = 1; j < size; j++) {
				t[i][j] = (t[i - 1][j] + t[i][j - 1]) / 2.0;
			}
		}
	}

	/**
	 * get the result from the table Pre-condition: i and j are within the index
	 * of the table Post-condition: the value of certain cell is returned
	 * BigTheta: 1 (no case)
	 * 
	 * @param i
	 *            A needs i games to win
	 * @param j
	 *            B needs j games to win
	 * @return probability that if A needs i games to win and B needs j games to
	 *         win that A will eventually win the series
	 */
	public double getResult(int i, int j) {
		return t[i][j];
	}

	/**
	 * main method to calculate time difference by dynamic programming
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args) {
		DynamicProgramming dp = new DynamicProgramming(51);
		dp.computeTable();

		long start = System.currentTimeMillis();
		double result = dp.getResult(50, 40);
		long elapsedTime = System.currentTimeMillis() - start;

		System.out.println("Result: " + result);
		System.out.println("Time: " + (double) elapsedTime / 1000.0);
	}
}
