import java.util.ArrayList;

public class BTreeNode implements Comparable<BTreeNode> {

	ArrayList<TreeObject> keys;
	ArrayList<Integer> child;
	private int nodeCount, parentCount;
	private int height, maxKey;
	private Boolean isRoot, isLeaf;
	
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
	
	public boolean isRoot() {
		return isRoot;
	}
	
	public boolean isFull() {
		return keys.size() == maxKey;
	}
	
	public void setIndex(int index) {
		nodeCount = index;
	}
	
	public boolean isLeaf() {
		return isLeaf;
	}
	
	public int getNodeCount() {
		return nodeCount;
	}
	
	public int getMaxKey() {
		return maxKey;
	}
	
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	
	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	public void setParent(int parent) {
		parentCount = parent;
	}
	
	public int getParentCount() {
		return parentCount;
	}
	
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