import java.io.File;

/**
 * Class to represent a BTree
 * @author Jake Andrews and Devan Craig
 *
 */
public class BTree {
	private int degree, numNodes, suqLength;
	private BTreeNode current, root, next;
	private File file;
	
	public BTree(int degree, int length, File file) {
		
		this.degree = degree;
		suqLength = length;
		this.file = file;
		
		root = new BTreeNode(this.degree, true, true, 0);
		numNodes =1;
	}
	
	
	
	

}