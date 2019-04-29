import java.util.ArrayList;

/**
 * Class to represent a node in the BTree.
 * @author Jake Andrews and Devan Craig
 *
 */

public class BTreeNode implements Comparable<BTreeNode> {

	ArrayList<TreeObject> keys;
	ArrayList<Integer> child;
	private int nodeCount, parentCount;
	private int height, maxKey;
	private Boolean isRoot, isLeaf;
	
	/**
	 * Constructor of the BTreeNode
	 * @param height - height of the node being created
	 * @param isRoot - is the node a root node?
	 * @param isLeaf - or is it a leaf?
	 * @param nodeCount - number of nodes in the tree
	 */
	public BTreeNode(int height, boolean isRoot, boolean isLeaf, int nodeCount) {
		keys = new ArrayList<TreeObject>(maxKey);
		child = new ArrayList<Integer>(maxKey + 1);
		
		this.nodeCount = nodeCount;
		this.height = height;
		this.isLeaf = isLeaf;
		this.isRoot = isRoot;
		
		if (isRoot == true) {
			parentCount = -1;
		}
		
		maxKey = (2 * height) - 1; // [2t - 1] max number of keys for a non root key
	}
	/**
	 * Returns if the node is a root or not. 
	 * @return
	 */
	public boolean isRoot() {
		return isRoot;
	}
	/**
	 * Returns if the BTree is full or not. 
	 * @return
	 */
	public boolean isFull() {
		return keys.size() == maxKey;
	}
	/**
	 * Sets the index of the given node. 
	 * @param index - the index to set the node to. 
	 */
	public void setIndex(int index) {
		nodeCount = index;
	}
	/**
	 * Returns if the node is a leaf or not. 
	 * @return
	 */
	public boolean isLeaf() {
		return isLeaf;
	}
	/**
	 * Returns the number of nodes in the BTree.
	 * @return
	 */
	public int getNodeCount() {
		return nodeCount;
	}
	/**
	 * Returns the maximum key value for the BTree
	 * @return
	 */
	public int getMaxKey() {
		return maxKey;
	}
	/**
	 * Sets the given node as a root node. 
	 * @param isRoot
	 */
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	/**
	 * Sets the given node as a leaf node. 
	 * @param isLeaf
	 */
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	/**
	 * Sets the amount of parents a node has. 
	 * @param parent - number of parents
	 */
	public void setParent(int parent) {
		parentCount = parent;
	}
	/**
	 * Returns the amount of parents a node has. 
	 * @return
	 */
	public int getParentCount() {
		return parentCount;
	}
	/**
	 * Makes a readable string explaining a node in the tree. 
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Keys: \n");
		int counter = 0;
		for (int i = 0; i < keys.size(); i++) {
			if (counter % 4 == 0) {
				s.append("\n");
				s.append(keys.get(i));
			}
		}
		return s.toString();
	}
	
	@Override
	public int compareTo(BTreeNode o) {
		// TODO Auto-generated method stub
		return nodeCount - o.nodeCount;
	}

	

}