import java.math.BigInteger;
import java.util.Random;

/**
 * A class to generate key values for Merkle-Hellman Knapsack Cryptosystem
 * 
 * @author Ru Zhao
 *
 */
public class MerkleHellman {
	private SinglyLinkedList sll;

	/**
	 * Constructor
	 */
	public MerkleHellman() {
		sll = new SinglyLinkedList();
	}

	/**
	 * 
	 * @return the SinglyLinkedList of this Cryptosystem, which is "w"
	 */
	public SinglyLinkedList getSll() {
		return this.sll;
	}

	/**
	 * Generate private key list, which is "w"
	 * 
	 * @param n
	 *            number of nodes in the list
	 * @return sum of the list "w"
	 */
	public BigInteger generatePrivateKeyList(int n) {
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
			sll.addBigIntEnd(temp);
			sum = sum.add(temp);
		}
		return sum;
	}

	/**
	 * Generate "q"
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
	 * @param q
	 *            q of this Cryptosystem
	 * @param r
	 *            r of this Cryptosystem
	 * @return public key list
	 */
	public SinglyLinkedList calculatePublicKey(BigInteger q, BigInteger r) {
		SinglyLinkedList pk = new SinglyLinkedList();
		int num = sll.countNodes();
		for (int i = 1; i < num + 1; i++) {
			pk.addBigIntEnd(sll.getNth(i).multiply(r).mod(q));
		}
		return pk;
	}
}
