import java.math.BigInteger;
import java.util.Random;

/**
 * A class to generate key values for Merkle-Hellman Knapsack Cryptosystem
 * 
 * @author Ru Zhao
 *
 */
public class MerkleHellman {
	private BigInteger[] biArray;
	private int n;

	/**
	 * Constructor
	 * 
	 * @param n
	 *            number of nodes inside the array
	 */
	public MerkleHellman(int n) {
		this.n = n;
		biArray = new BigInteger[n];
	}

	/**
	 * Get the array of "w"
	 * 
	 * Precondition: This array is already initialized by
	 * generatePrivateKeyList() Postcondition: the array containing bigintegers
	 * is returned
	 * 
	 * Best case Big Theta: 1 Worst case Big Theta: 1
	 * 
	 * @return the array of this Cryptosystem, which is "w"
	 */
	public BigInteger[] getArray() {
		return this.biArray;
	}

	/**
	 * Generate private key list, which is "w"
	 * 
	 * Postcondition: the array containing bigintegers is generated, sum is
	 * returned
	 * 
	 * Best case Big Theta: n Worst case Big Theta: n
	 * 
	 * @param n
	 *            number of nodes in the list
	 * @return sum of the list "w"
	 */
	public BigInteger generatePrivateKeyList() {
		if (n <= 0)
			return BigInteger.valueOf(0);
		Random random = new Random();
		BigInteger temp;
		BigInteger sum = new BigInteger("0");
		for (int i = 0; i < n; i++) {
			temp = new BigInteger(String.valueOf(random.nextInt()));
			if (temp.compareTo(BigInteger.valueOf(0)) != 1)
				temp = temp.abs();
			temp = temp.add(sum);
			biArray[i] = temp;
			sum = sum.add(temp);
		}
		return sum;
	}

	/**
	 * Generate "q"
	 * 
	 * Precondition: The array is already initialized by
	 * generatePrivateKeyList(), sum is the sum of elements in "w"
	 * Postcondition: q is generated and returned
	 * 
	 * Best case Big Theta: 1 Worst case Big Theta: 1
	 * 
	 * @param sum
	 *            sum of the list "w"
	 * @return q
	 */
	public BigInteger generateQ(BigInteger sum) {
		Random random = new Random();
		BigInteger temp = new BigInteger(String.valueOf(random.nextInt()));
		if (temp.compareTo(BigInteger.valueOf(0)) != 1)
			temp = temp.abs();
		BigInteger q = sum.add(temp);
		return q;
	}

	/**
	 * generate "r"
	 * 
	 * Precondition: The array is already initialized by
	 * generatePrivateKeyList(), q which is the input is already created by
	 * generateQ() Postcondition: r is generated and returned
	 * 
	 * Best case Big Theta: 1 Worst case Big Theta: unlimited (random number
	 * generater cannot be controlled)
	 * 
	 * @param q
	 *            q of this Cryptosystem
	 * @return r
	 */
	public BigInteger generateR(BigInteger q) {
		Random random = new Random();
		BigInteger r = BigInteger.valueOf((int) (random.nextDouble() * (q
				.longValue() - 1)) + 1); // consider nextint() could yield
											// q.intValue() to negative
		if (r.compareTo(BigInteger.valueOf(0)) != 1)
			r = r.abs();
		while (!r.gcd(q).equals(BigInteger.valueOf(1))) {
			r = new BigInteger(String.valueOf((int) (random.nextDouble() * (q
					.intValue() - 1)) + 1));
			if (r.compareTo(BigInteger.valueOf(0)) != 1)
				r = r.abs();
		}
		return r;
	}

	/**
	 * calculate public key list
	 * 
	 * Precondition: The array is already initialized by
	 * generatePrivateKeyList(), q and r is already created by generateQ() and
	 * generateR(), as the inputs Postcondition: public key list is generated
	 * and returned
	 * 
	 * Best case Big Theta: n Worst case Big Theta: n
	 * 
	 * @param q
	 *            q of this Cryptosystem
	 * @param r
	 *            r of this Cryptosystem
	 * @return public key list
	 */
	public BigInteger[] calculatePublicKey(BigInteger q, BigInteger r) {
		BigInteger[] pk = new BigInteger[n];
		for (int i = 0; i < n; i++) {
			pk[i] = biArray[i].multiply(r).mod(q);
		}
		return pk;
	}
}
