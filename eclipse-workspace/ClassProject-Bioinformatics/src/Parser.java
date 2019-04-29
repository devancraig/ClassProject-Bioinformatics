import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Defines a parser class that takes in a file and a sequence length to return. 
 * @author Jake Andrews and Devan Craig
 *
 */
public class Parser {
	String line = null;
	BufferedReader in;
	int position;
	int seqLength;
	String sequence;
	
	/**
	 * Constructor class for the parser. 
	 * @param file - the gbk file you are going to parse from. 
	 * @param k - the length of subsequences you are looking for. 
	 */
	public Parser(String file, int k) {
		File file2 = new File("C:\\Users\\JakeA\\git\\ClassProject-Bioinformatics\\test1.gbk"); // change to wherever
																								// your file is
		FileReader reader = null;
		seqLength = k;
		position = 0;

		try {
			reader = new FileReader(file2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		in = new BufferedReader(reader);
		int charPos = 0;

		sequence = "";

		try {
			while ((line = in.readLine()) != null) {
				if (line.startsWith("ORIGIN")) {
					while (!(line = in.readLine().toLowerCase().trim()).startsWith("//")) {
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
							if (c == 'q') {
								sequence += "10";
							}
						}
						charPos = 0;
					}
				}

			}

		} catch (IOException e) {
			System.err.println("Error in file parsing: characters to bytes");
			e.printStackTrace();
		}

	}
	/**
	 * Gets the next subsequence of length k in the file string. 
	 * @return - a long with the binary string of data
	 */
	public Long nextSubSeq() { 
		String sequenceString = "";
		long seq;

		for (int i = 0; i < seqLength * 2; i++) {

			sequenceString += sequence.charAt(position+i);
		}
		position += 2;

		seq = Long.parseLong(sequenceString, 2);
		return seq;
	}

}
