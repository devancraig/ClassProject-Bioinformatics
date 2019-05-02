import java.util.Scanner;

/**
 * A class to search the BTree for sequences of given length.
 * 
 * @author Jake Andrews and Devan Craig
 *
 */
public class GeneBankSearch {
	private static int cacheYN, debugLevel;
	private static String file, bTreeFile;
	private static boolean debug = false;
	private static Scanner scan;

	/**
	 * Main method for the GeneBankSearch. See usage() for usage.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if (args.length < 3 || args.length > 4) {
				Usage();
				System.exit(-1);
			}
			if (args.length == 4) {
				debug = true;
				debugLevel = Integer.parseInt(args[3]);
			}
			cacheYN = Integer.parseInt(args[0]);
			if (!(cacheYN == 0 || cacheYN == 1)) {
				Usage();
				System.exit(-1);
			}
			bTreeFile = args[1];
			file = args[2];
		} catch (Exception e) {
			Usage();
			System.exit(-1);
		}
		scan = new Scanner(file);
		if (!debug || debugLevel == 0) {
			String bTreeArgs[] = bTreeFile.split("\\.");
			System.out.println(bTreeFile);
			System.out.println(bTreeArgs.length);
			String gbkFile = bTreeArgs[0] + ".gbk";
			int seqLength = Integer.parseInt(bTreeArgs[4]);
			int degree = Integer.parseInt(bTreeArgs[5]);
			Parser parse = new Parser(gbkFile, seqLength);
			BTree tree = new BTree(degree, bTreeFile, seqLength);
			while (parse.hasNext()) {
				Long seq = parse.nextSubSeq();
				tree.insert(seq);
			}
			while (scan.hasNextLine()) {
			String line = scan.nextLine();
			System.out.println(line + ": " + tree.search(tree.getRoot(), queryScanNextLine(line)).getFreq());
			}

		} else if (debugLevel == 1) {
			Usage();
			System.exit(-1);
		}

	}

	/**
	 * Errors out with the correct usage of the java file.
	 */
	public static void Usage() {
		System.err.println(
				"Usage: java GeneBankSearch <0/1(no/with Cache)> <btree file> <query file> [<debug-level must be 0>]");
	}
	
	public static long queryScanNextLine(String line)
	{

			String sequence = "";
			int charPos = 0;
			while (charPos < line.length()) {
				char c = line.charAt(charPos++);

				if (c == 'a') {
					sequence += "00";
				}
				if (c == 't') {
					sequence += "11";
				}
				if (c == 'c') {
					sequence += "01";
				}
				if (c == 'g') {
					sequence += "10";
				}
			}
			charPos = 0;
			return Long.parseLong(sequence, 2);
	}
}
