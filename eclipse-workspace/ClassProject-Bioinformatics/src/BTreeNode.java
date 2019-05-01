import java.util.LinkedList;

/**
 * Class to represent a node in the BTree.
 * @author Jake Andrews and Devan Craig
 *
 */

public class BTreeNode implements Comparable<BTreeNode> {

	LinkedList<TreeObject> keys;
	LinkedList<Integer> child;
	private int parent;
	private int offset;
	private boolean isLeaf;
	public int numKeys;
	
	/**
	 * Constructor of the BTreeNode
	 * @param height - height of the node being created
	 * @param isRoot - is the node a root node?
	 * @param isLeaf - or is it a leaf?
	 * @param nodeCount - number of nodes in the tree
	 */
	public BTreeNode() {
		keys = new LinkedList<TreeObject>();
		child = new LinkedList<Integer>();
		
		numKeys = 0;
		parent = -1;
		
	}
	
	/**
	 * Returns the key give my param from the BTree
	 * @param key
	 * @return
	 */
	public TreeObject getKey(int key) {
		TreeObject object = keys.get(key);
		return object;
	}
	
	/**
	 * Adds a key using our TreeObject
	 * @param object
	 */
	public void addKey(TreeObject object) {
		keys.add(object);
	}
	
	/**
	 * Adds a key using our TreeObject and a integer
	 * @param object
	 * @param key
	 */
	public void addKey2(TreeObject object, int key) {
		keys.add(key, object);
	}
	
	/**
	 * removes a key from out TreeObject using a key 
	 * @param key
	 * @return
	 */
	public TreeObject removeKey(int key) {
		return keys.remove(key);
	}
	
	/**
	 * returns the amount of keys in the BTree
	 * @return
	 */
	public LinkedList<TreeObject> getKeys(){
		return keys;
	}
	
	/**
	 * Returns the child specified in the BTree
	 * @param key
	 * @return
	 */
	public int getChild(int key) {
		return child.get(key);
	}
	
	/**
	 * Adds a child into our BTree
	 * @param key
	 */
	public void addChild(int key) {
		child.add(key);
	}
	
	/**
	 * Adds a child into our BTree using two parameters
	 * @param c
	 * @param key
	 */
	public void addChild2(Integer c, int key) {
		child.add(key, c);
	}
	
	/**
	 * returns the removed child from the BTree
	 * @param key
	 * @return
	 */
	public int removeChild(int key) {
		return child.remove(key);
	}
	
	/**
	 * Returns the amount of childs in the BTree
	 * @return
	 */
	public LinkedList<Integer> getChild(){
		return child;
	}
	/**
	 * Sets the offset of the given node. 
	 * @param index - the index to set the node to. 
	 */
	public void setOffset(int index) {
		offset = index;
	}
	/**
	 * Returns if the node is a leaf or not. 
	 * @return
	 */
	public boolean isLeaf() {
		return isLeaf;
	}
	/**
	 * Returns the offset of the BTree.
	 * @return
	 */
	public int getOffset() {
		return offset;
	}
	/**
	 * Returns the number of key value for the BTree
	 * @return
	 */
	public int getNumKeys() {
		return numKeys;
	}
	
	/**
	 * Sets the number of keys in the BTree
	 * @param numKeys
	 */
	public void setNumKeys(int numKeys) {
		this.numKeys = numKeys;
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
		this.parent = parent;
	}
	/**
	 * Returns the amount of parents a node has. 
	 * @return
	 */
	public int getParent() {
		return parent;
	}
	/**
	 * Makes a readable string explaining a node in the tree. 
	 */
	public String toString() {						//not used anymore, can delete
		StringBuilder s = new StringBuilder();
		s.append("Keys: ");
	//	int counter = 0;
//		for (int i = 0; i < numKeys; i++) {
//			if (counter % 4 == 0) {
//				s.append("\n");
//				String str = keys.get(i).toString();
//				s.append(str);
//			}
//			counter++;
		//}
		return s.toString();
	}
	
	@Override
	public int compareTo(BTreeNode o) {
		return offset - o.offset;
	}

	

}