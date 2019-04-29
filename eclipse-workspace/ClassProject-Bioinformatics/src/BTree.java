import java.io.File;

/**
 * Class to represent a BTree
 * @author Jake Andrews and Devan Craig
 *
 */
public class BTree {
	private int degree, numNodes, suqLength, height;
	private BTreeNode current, root, next;
	private File file;
	
	public BTree(int degree, int length, File file) {
		
		this.degree = degree;
		suqLength = length;
		this.file = file;
		
		root = new BTreeNode(this.degree, true, true, 0);
		numNodes =1;
	}
	/**
	 * Returns the degree of the BTree.
	 * @return
	 */
	public int getDegree()
	{
		return this.degree;
	}
	/**
	 * Returns the number of nodes in the BTree.
	 * @return
	 */
	public int getNumNodes() {
		return numNodes;
	}
	/**
	 * Returns the sequence length?? I think?? @Devan help
	 * @return
	 */
	public int getSugLength()
	{
		return suqLength;
	}
	/**
	 * Returns the height of the BTree.
	 * @return
	 */
	public int getHeight()
	{
		return height;
	}
	/**
	 * Returns the value of the node associated with the specified key. 
	 * @param key
	 * @return
	 */
	public long get(long key) {
		return key;				//should return the value at the key location
		}
	
	/**
	 * Inserts a value into a specified location. 
	 * @param node
	 * @param key
	 */
	public void insert(BTreeNode node, long key, long sequence) {
		
	}
	/**
	 * Returns a string representation of the BTree. //We might not need this idk
	 */
	public String toString()
	{
		return "";
	}
	
	

}