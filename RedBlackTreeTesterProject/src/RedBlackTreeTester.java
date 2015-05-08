import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * The RedBlackTreeTester is a spell checking program used to test the re black
 * tree.
 * 
 * @author zhaoru
 *
 */
public class RedBlackTreeTester {

	/**
	 * Read and generate a RedBlackTree
	 * 
	 * @param filename
	 *            the filename to inout
	 * @return a inserted RedBlackTree
	 * @throws IOException
	 *             if filenotfound or input error
	 */
	public RedBlackTree read(String filename) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader(filename));
		String line;
		RedBlackTree rbt = new RedBlackTree();
		while ((line = bf.readLine()) != null) {
			rbt.insert(line);
		}
		bf.close();
		return rbt;
	}

	/**
	 * Print command lines and required information
	 * 
	 * @param rbt
	 *            a generated RedBlackTree
	 */
	public void print(RedBlackTree rbt) {
		double logri = 2 * (double) Math.log(rbt.getSize() + 1)
				/ (double) Math.log(2);
		System.out.println("Red Black Tree is loaded with " + rbt.getSize()
				+ " words.");
		System.out.println("The height of the tree is " + rbt.height());
		System.out.println("2 * Lg( n+1) = " + logri);
		System.out.println();
		System.out.println("Legal commands are: ");
		System.out
				.println("<d>  display the entire word tree with a level order traversal.");
		System.out
				.println("<s>  print the words of the tree in sorted order (use an inorder traversal).");
		System.out
				.println("<r>  print the words of the tree in reverse sorted order.");
		System.out.println("<!> to quit.");
		System.out.println("<c> <word> to spell check this word");
		System.out.println("<a> <word> add word to tree.");
		System.out
				.println("<f> <fileName> to check a text file for spelling errors.");
	}

	/**
	 * execute the command line
	 * 
	 * @param input
	 *            user input containing a command word and a word if any
	 * @param rbt
	 *            a generated RedBlackTree
	 * @throws IOException
	 *             if filenotfound or input error
	 */
	public void run(String input, RedBlackTree rbt) throws IOException {
		String[] inputs = input.split(" ");
		if (inputs.length > 0) {
			String command = inputs[0];
			switch (command) {
			case "d":
				rbt.levelOrderTraversal();
				break;
			case "s":
				rbt.inOrderTraversal();
				break;
			case "r":
				rbt.reverseOrderTraversal();
				break;
			case "!":
				break;
			case "c":
				if (inputs.length == 2) {
					String word = inputs[1];
					if (rbt.contains(word)) {
						int numComp = rbt.getRecentCompares();
						System.out.println("Found " + word + " after "
								+ numComp + " comparisons");
					} else {
						String spellCheck = rbt.closeBy(word);
						System.out.println(word
								+ " Not in dictionary. Perhaps you mean");
						System.out.println(spellCheck);
					}
				}
				break;
			case "a":
				if (inputs.length == 2) {
					String word = inputs[1];
					if (rbt.contains(word)) {
						System.out.println("The word ‘" + word
								+ "’ is already in the dictionary");
					} else {
						rbt.insert(word);
						System.out.println(word + " was added to dictionary.");
					}
				}
				break;
			case "f":
				boolean spellError = false;
				if (inputs.length == 2) {
					BufferedReader bf = new BufferedReader(new FileReader(
							"src/" + inputs[1]));
					String line;
					while ((line = bf.readLine()) != null) {
						if (line.equals(""))
							continue;
						String[] check = line.split(" |\\.", 0);
						for (String eachWord : check) {
							if (!rbt.contains(eachWord)) {
								spellError = true;
								System.out.println("'" + eachWord
										+ "' was not found in dictionary.");
							}
						}
					}
					bf.close();
					if (spellError == false)
						System.out.println("No spelling errors found.");
				}
				break;
			default:
				System.out.println("Invalid Input!");
				break;
			}
		}
	}

	/**
	 * Main test method
	 * 
	 * @param args
	 *            args[0] - word list input file name. eg: src/words.txt
	 */
	public static void main(String[] args) {
		/*
		 * If you want to input filename at the command line, you can used the
		 * commented code below
		 */
		RedBlackTreeTester tbtTester = new RedBlackTreeTester();
		RedBlackTree rbt = new RedBlackTree();
		Scanner scanner = new Scanner(System.in);
		try {
			// String filename = "src/" + scanner.nextLine();
			rbt = tbtTester.read(args[0]); // change arg[0] to filename
			tbtTester.print(rbt);
			System.out.print(">");
			String line = scanner.nextLine();
			while (true) {
				if (line.equals("!")) {
					System.out.println("Bye");
					break;
				}
				tbtTester.run(line, rbt);
				System.out.print(">");
				line = scanner.nextLine();
			}
			scanner.close();
		} catch (IOException e) {
			System.out.println("File name not found!");
			e.printStackTrace();
		}
	}
}
