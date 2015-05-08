import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * This class is DeCompressor tester used for LZW compression
 * 
 * @author zhaoru
 *
 */
public class DeCompressor {
	public static void main(String args[]) throws IOException {
		DataInputStream in = new DataInputStream(new BufferedInputStream(
				new FileInputStream("src/compressor.txt")));

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

		// write de-compressed content into text file
		PrintWriter outPrinter = new PrintWriter("src/deCompressor.txt");
		outPrinter.println(temp);
		outPrinter.close();

	}
}