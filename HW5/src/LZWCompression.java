import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
 * Using the LZWCompression, the size of files would be less than the original size. 
 * By using InputStream: DataInputStream, BufferedInputStream, FileInputStream,
 * we can work on ASCII and Binary files.
 * 
 * Compress words.txt using HashMap takes around 1208ms, de-compress using 324ms;
 * Compress words.txt using TreeMap takes around 2017ms, de-compress using 318ms.
 * 
 * Compress CrimeLatLonXY1990.csv using HashMap takes around 304ms, de-compress using 82ms.
 * Compress CrimeLatLonXY1990.csv using TreeMap takes around 384ms, de-compress using 89ms.
 * 
 * We can see that when compressing using TreeMap takes longer than HashMap; 
 * no difference when de-compressing.
 * Because when building the Map, TreeMap would have the order while HashMap would not. 
 * Making this order takes more time. So under this condition, we do not need the 
 * ordered elements, the HashMap would be faster.
 * 
 * To avoid the overflow, I set the compressed size to 4 bytes, instead of 12 bits.
 */

/**
 * LZWCompression
 * 
 * @author zhaoru
 *
 */
public class LZWCompression {

	/**
	 * compressor. Note that "src/": if executed in eclipse, "src/" is needed.
	 * If executed in Unix, "src/" should be deleted
	 * 
	 * @param inputFile
	 *            filename to be compresses
	 * @param outputFile
	 *            output filename
	 * @throws IOException
	 *             io exception
	 */
	public void compressor(String inputFile, String outputFile)
			throws IOException {
		DataInputStream in = new DataInputStream(new BufferedInputStream(
				new FileInputStream("src/" + inputFile)));
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
				new FileOutputStream("src/" + outputFile)));
		ArrayList<Character> input = new ArrayList<Character>();

		byte byteIn;
		// read data from file
		try {
			while (true) {
				byteIn = in.readByte();
				input.add((char) byteIn);
			}
		} catch (EOFException e) {
			in.close();

		}

		StringBuilder builder = new StringBuilder(input.size());
		for (Character c : input) {
			builder.append(c);
		}
		
		// compress
		ArrayList<Integer> temp = compress(builder.toString());

		for (int i : temp) {
			// a integer has 4 bytes
			byte[] bytes = ByteBuffer.allocate(4).putInt(i).array();

			for (byte b : bytes) {
				out.writeByte(b);
			}

		}
		out.close();
	}

	/**
	 * de-compressor. Note that "src/": if executed in eclipse, "src/" is
	 * needed. If executed in Unix, "src/" should be deleted
	 * 
	 * @param inputFile
	 *            filename to be de-compresses
	 * @param outputFile
	 *            output file
	 * @throws IOException
	 *             io exception
	 */
	public void deCompressor(String inputFile, String outputFile)
			throws IOException {
		DataInputStream in = new DataInputStream(new BufferedInputStream(
				new FileInputStream("src/" + inputFile)));

		byte[] byteIn = new byte[4];
		int count = 0;
		ArrayList<Integer> temp = new ArrayList<Integer>();
		// read data from file
		try {
			while (true) {

				byteIn[count % 4] = in.readByte();
				count++;
				if (count > 1 && count % 4 == 0) {
					temp.add(ByteBuffer.wrap(byteIn).getInt());
				}
			}
		} catch (EOFException e) {
			in.close();

		}

		// decompress
		String decompressedWord = deCompress(temp);

		// write decompressed content into txt file
		PrintWriter outPrinter = new PrintWriter("src/" + outputFile);
		outPrinter.println(decompressedWord);
		outPrinter.close();

	}

	/**
	 * helper method to compress a string
	 * 
	 * @param rawString
	 *            string to be compressed
	 * @return array list of integers of result
	 */
	private ArrayList<Integer> compress(String rawString) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		// enter all symbols into the table
		int tableSize = 256;
		Map<String, Integer> table = new HashMap<String, Integer>();

		// change to TreeMap to compare the time consuming
		// Map<String, Integer> table = new TreeMap<String, Integer>();

		for (int i = 0; i < 256; i++) {
			table.put(Character.toString((char) i), i);
		}
		String s = "";

		for (char c : rawString.toCharArray()) {

			if (table.containsKey(s + c))
				s = s + c;
			else {
				result.add(table.get(s));
				table.put(s + c, tableSize);
				tableSize++;
				s = Character.toString(c);
			}
		}

		// add the last char chunk to the result
		if (!s.equals(""))
			result.add(table.get(s));
		return result;
	}

	/**
	 * helper method to de-compress a string
	 * 
	 * @param compressedCode
	 *            array list of integers of result
	 * @return de-compressed string
	 */
	private String deCompress(ArrayList<Integer> compressedCode) {
		// set return buffer
		StringBuffer result = new StringBuffer();
		String priorcodeword = "" + (char) (int) compressedCode.remove(0);
		result.append(priorcodeword);

		// enter all symbols into the table
		int tableSize = 256;
		// make the size dynamic
		String[] table = new String[compressedCode.size() + 256];

		for (int i = 0; i < 256; i++)
			table[i] = "" + (char) i;

		for (int codeword : compressedCode) {
			String getString;
			if (table[codeword] == null) {
				getString = priorcodeword + priorcodeword.charAt(0);
				table[tableSize] = priorcodeword + getString.charAt(0);
				tableSize++;
			} else {
				getString = table[codeword];
				table[tableSize] = priorcodeword + getString.charAt(0);
				tableSize++;
			}
			priorcodeword = getString;
			result.append(getString);

		}
		return result.toString();
	}

	/**
	 * main method
	 * 
	 * @param args
	 *            input, format should be c/d + input filename + output filename
	 */
	public static void main(String[] args) {
		LZWCompression lzw = new LZWCompression();
		if (args.length == 3) {
			try {
				if (args[0].equals("c")) {
					long start = System.currentTimeMillis();

					lzw.compressor(args[1], args[2]);

					long diff = System.currentTimeMillis() - start;
					System.out.println("Time used (compress): " + diff);
				} else if (args[0].equals("d")) {
					long start = System.currentTimeMillis();

					lzw.deCompressor(args[1], args[2]);

					long diff = System.currentTimeMillis() - start;
					System.out.println("Time used (decompress): " + diff);
				} else {
					throw new IllegalArgumentException(
							"Input format should be c/d + input filename + output filename.");
				}
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		} else {
			throw new IllegalArgumentException(
					"Input format should be c/d + input filename + output filename.");
		}
	}
}