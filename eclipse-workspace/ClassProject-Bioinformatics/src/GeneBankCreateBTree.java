/**
 * Creates a BTree from the gene bank files. 
 * @author Jake Andrews and Devan Craig
 *
 */
public class GeneBankCreateBTree {
	static int cacheYN, degree, seqLength;
	static String file;

	
	/**
	 * Main method for creating the BTree from the user's specifications. 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		cacheYN = Integer.parseInt(args[1]);
			if (!(cacheYN == 0 || cacheYN == 1))
			{
				Usage();
			}
		degree = Integer.parseInt(args[2]);
		file = args[3];
		seqLength = Integer.parseInt(args[4]);
			if (seqLength > 31)
			{
				System.err.println("Maximum Sequence Size is 31");
				Usage();
			}
		} catch (Exception e)
		{
			Usage();
		}
		Parser parse = new Parser(file, seqLength);

		
	}
	
	/**
	 * Errors out with the correct usage of the java file. 
	 */
	public static void Usage()
	{
		System.err.println("Usage: java GeneBankCreateBTree <0/1(no/with Cache)> <degree> <gbk file> <sequence" + 
			"length> [<cache size>] [<debug level>]");
	}

}
