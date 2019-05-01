
import java.io.File;
import java.util.Scanner;

/**
 * A class to search the BTree for sequences of given length. 
 * 
 * @author Jake Andrews and Devan Craig
 *
 */
public class GeneBankSearch {
	private static int cacheYN, seqLength, debugLevel;
	private static String file, bTreeFile;
	private static int cacheSize = 0;
	private static boolean debug = false;

	public static void main(String[] args) {
		try {
			if (args.length < 3 || args.length > 4) {
				Usage();
			}
			if (args.length == 4) {
				debug = true;
				debugLevel = Integer.parseInt(args[3]);
			}
			cacheYN = Integer.parseInt(args[0]);
			if (!(cacheYN == 0 || cacheYN == 1)) {
				Usage();
			}
			bTreeFile = args[1];
			file = args[2];
		} catch (Exception e) {
			Usage();
		}
		if (!debug || debugLevel == 0) {

			int degree = Integer.parseInt(bTreeFile.substring(bTreeFile.length() - 1));
			int seqLength = Integer.parseInt(bTreeFile.substring(bTreeFile.length() - 3, bTreeFile.length() - 2));
			Parser parse = new Parser(file, seqLength);
			BTree tree = new BTree(degree, bTreeFile, seqLength);

			tree.search(tree.getRoot(), parse.nextSubSeq());

		} else if (debugLevel == 1) {

		}

	}

	/**
	 * Errors out with the correct usage of the java file.
	 */
	public static void Usage() {
		System.err.println("Usage: java GeneBankSearch <0/1(no/with Cache)> <btree file> <query file> [<debug-level>]");
	}
}
