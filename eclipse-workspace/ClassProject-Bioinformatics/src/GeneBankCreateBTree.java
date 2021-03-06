import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Creates a BTree from the gene bank files.
 * 
 * @author Jake Andrews and Devan Craig
 *
 */
public class GeneBankCreateBTree {
	private static int cacheYN, degree, seqLength, debugLevel = 0;
	private static String file, fileDataName;
	private static boolean debug = false;

	/**
	 * Main method for creating the BTree from the user's specifications.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			if (args.length < 3 || args.length > 5) {
				Usage();
				System.exit(-1);
			}
			if (args.length == 5) {
				debug = true;
				debugLevel = Integer.parseInt(args[4]);
			}
			cacheYN = Integer.parseInt(args[0]);
			if (!(cacheYN == 0 || cacheYN == 1)) {
				Usage();
				System.exit(-1);
			}
			degree = Integer.parseInt(args[1]);
				if (degree == 0)
				{
					degree = 15; //optimal degree
				}
			file = args[2];
			seqLength = Integer.parseInt(args[3]);
			fileDataName = file + ".btree.data." + seqLength + "." + degree;
			if (seqLength > 31 || seqLength < 1) {
				System.err.println("Sequence Size must be between 1 and 31 inclusive.");
				Usage();
				System.exit(-1);
			}
		} catch (Exception e) {
			Usage();
			System.exit(-1);
		}
		if (!debug || debugLevel == 0) {
			Parser parse = new Parser(file, seqLength);
			BTree tree = new BTree(degree, fileDataName, seqLength);
			while (parse.hasNext()) {
				Long seq = parse.nextSubSeq();
				tree.insert(seq);
			}
		} else if (debugLevel == 1) {
			Parser parse = new Parser(file, seqLength);
			BTree tree = new BTree(degree, fileDataName, seqLength);

			while (parse.hasNext()) {
				Long seq = parse.nextSubSeq();
				tree.insert(seq);
			}
			PrintStream dumpFile = null;
			try {
				String str = file + ".btree.dump." + seqLength;
				dumpFile = new PrintStream(new FileOutputStream(str));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			System.setOut(dumpFile);
			tree.inOrderPrint(tree.getRoot());
			System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		}

	}

	/**
	 * Errors out with the correct usage of the java file.
	 */
	public static void Usage() {
		System.err.println("Usage: java GeneBankCreateBTree <0/1(no/with Cache)> <degree> <gbk file> <sequence"
				+ "length> [<debug-level>]");
	}
	

	

}
