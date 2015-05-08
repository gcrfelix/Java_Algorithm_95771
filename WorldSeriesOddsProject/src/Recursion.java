/**
 * The class to use recursion to calculate possibility for a team to win the
 * game.
 * 
 * @author zhaoru
 *
 */
public class Recursion {

	/**
	 * method to calculate possibility to win with i and j by recursion
	 * Pre-condition: i and j are within the index of the table Post-condition:
	 * the value of certain cell is returned, if i or j is negative or equals to
	 * 0 at the same time, return -1 BigTheta: 2^n
	 * 
	 * @param i
	 *            A needs i games to win
	 * @param j
	 *            B needs j games to win
	 * @return probability that if A needs i games to win and B needs j games to
	 *         win that A will eventually win the series
	 */
	public double p(int i, int j) {
		if ((i == 0) && (j > 0))
			return 1;
		else if ((i > 0) && (j == 0))
			return 0;
		else if ((i > 0) && (j > 0))
			return (p(i - 1, j) + p(i, j - 1)) / 2;
		else
			return -1;
	}

	/**
	 * main method to calculate time difference by recursion
	 * 
	 * @param args arguments
	 */
	public static void main(String[] args) {
		Recursion r = new Recursion();

		long start = System.currentTimeMillis();
		double result = r.p(20, 23);
		long elapsedTime = System.currentTimeMillis() - start;

		System.out.println("Result: " + result);
		System.out.println("Time: " + (double) elapsedTime / 1000.0);
	}
}
