import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Test driver for Merkle-Hellman Knapsack Cryptosystem
 * 
 * @author Ru Zhao
 *
 */
public class Main {
	public static void main(String args[]) throws IOException {
		/*
		 * Let user input
		 */
		System.out
				.println("Enter a string and I will encrypt it as single large integer.");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String name = br.readLine();
		int n = name.length();
		while (n > 80) {
			System.out.println("The string entered is too long, try again!");
			name = br.readLine();
			n = name.length();
		}
		System.out.println("Clear text:");
		System.out.println(name);
		System.out.println("Number of clear text bytes = " + n);

		/*
		 * Generate public and private key
		 */
		MerkleHellman mh = new MerkleHellman();
		BigInteger sum = mh.generatePrivateKeyList(n * 8);
		BigInteger q = mh.generateQ(sum);
		BigInteger r = mh.generateR(q);
		SinglyLinkedList pk = mh.calculatePublicKey(q, r);

		/*
		 * Encrypt input to a long binary string
		 */
		StringBuilder sb = new StringBuilder();
		byte[] bytes = name.getBytes();
		for (byte b : bytes) {
			String bs = Integer.toBinaryString(0x100 | b).substring(1);
			sb.append(bs);
		}

		/*
		 * Generate the sum of encrypted number
		 */
		BigInteger encrypt = new BigInteger("0");
		for (int i = 0; i < n * 8; i++) {
			BigInteger x = BigInteger.valueOf(Character.getNumericValue((sb
					.charAt(i))));
			BigInteger y = pk.getNth(i + 1);
			encrypt = encrypt.add(x.multiply(y));
		}
		System.out.println(name + " is encrypted as " + encrypt);

		/*
		 * Calculate the decrypted number, and generate decrypted binary string
		 */
		BigInteger decryptNum = encrypt.multiply(r.modInverse(q)).mod(q);
		StringBuilder decryptBin = new StringBuilder();
		for (int j = 0; j < n * 8; j++)
			decryptBin.append(0);
		SinglyLinkedList w = mh.getSll();
		int nNodes = w.countNodes();
		while (decryptNum.compareTo(BigInteger.valueOf(0)) != 0) {
			BigInteger temp = w.getNth(nNodes);
			if (decryptNum.compareTo(temp) != -1) {
				decryptNum = decryptNum.subtract(temp);
				decryptBin.setCharAt(nNodes - 1, '1');
			}
			nNodes--;
		}

		/*
		 * Convert decrypted binary string to an array of integer, and then to
		 * the decrypted string
		 */
		int binNum = decryptBin.length();
		int[] decode = new int[binNum / 8];
		for (int k = 0; k < binNum; k += 8) {
			decode[k / 8] = Integer.parseInt(decryptBin.substring(k, k + 8), 2);
		}

		StringBuilder decrypted = new StringBuilder();
		for (int charNum : decode) {
			decrypted.append((char) charNum);
		}
		System.out.println("Result of decryption: " + decrypted.toString());
	}
}
