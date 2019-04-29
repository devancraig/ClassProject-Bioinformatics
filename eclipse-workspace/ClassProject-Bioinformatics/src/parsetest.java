/** 
 * A class to test the parser, we can delete from the final project when we submit. 
 * @author Jake Andrews and Devan Craig
 *
 */
public class parsetest {
	static Parser parse = new Parser("test", 10);
	
	public static void main(String[] args)
	{
		for (int i = 0; i < 10; i++)
		{
			String q = Long.toBinaryString(parse.nextSubSeq());
			System.out.print(q + " ");
		}
	} 

}
