import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * This class is DeCompressor tester used for LZW compression
 * 
 * @author zhaoru
 *
 */
public class Compressor {
	public static void main(String[] args) throws IOException {
		DataInputStream in = new DataInputStream(new BufferedInputStream(
				new FileInputStream("src/shortwords.txt")));
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
				new FileOutputStream("src/compressor.txt")));
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

		for (int i : input) {
			// one integer for 4 bytes
			byte[] bytes = ByteBuffer.allocate(4).putInt(i).array();

			for (byte b : bytes) {
				out.writeByte(b);
			}
		}
		out.close();
	}
}