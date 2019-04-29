import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
	String line = null;
	BufferedReader in;
	int position;
	int seqLength;
	String sequence;

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
