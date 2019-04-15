import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser //change to method later, but for now its a standalone class to test it out
{

	public static void main(String[] args) {
		
		File file = new File("C:\\Users\\JakeA\\git\\ClassProject-Bioinformatics\\test1.gbk");	//change to wherever your file is
		FileReader reader = null;
		int charPos = 0;
		String sequence = "";
		String line = null;
		try {
			reader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader in = new BufferedReader(reader);
		try {			
			while ((line = in.readLine()) != null)
			{
				if (line.startsWith("ORIGIN")) 
				{
					while (!(line = in.readLine().toLowerCase().trim()).startsWith("//"))
					{
						while (charPos < line.length()) 
						{
						char c = line.charAt(charPos++);
						
							if (c == 'a')
							{
								sequence += "00";
							}
							if (c == 't')
							{
								sequence += "11";
							}
							if (c == 'c')
							{
								sequence += "01";
							}
							if (c == 'q')
							{
								sequence += "10";
							}
						}
						charPos=0;
					}
				}

			}
			System.out.println(sequence.substring(0, 2000));
			System.out.println(sequence.substring(2000,4000));
			System.out.println(sequence.substring(4000,6000));
			System.out.println(sequence.substring(6000,8000));
			System.out.println(sequence.substring(8000));

		} catch (IOException e) {
			System.err.println("Error in file parsing: characters to bytes");
			e.printStackTrace();
		}

	}
		
		
		
		

	}


