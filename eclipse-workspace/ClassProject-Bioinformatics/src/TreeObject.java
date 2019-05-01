/**
 * A BTree object class. 
 * @author Jake Andrews and Devan Craig
 *
 */
public class TreeObject {

	private int freq;
	private long key;

	
	public TreeObject(long key, int freq) {
		this.key = key;
		this.freq = freq;
	}

	/**
	 * Constructor for the BTree object. 
	 * @param key - the key of the object. 
	 */
	
	
	public TreeObject(long key) {
		this.key = key;
		this.freq = 1;
	}

	/**
	 * Returns the key of the BTree object. 
	 * @return
	 */
	public Long getKey() {
		return this.key;
	}
	
	/**
	 * Returns the frequency of the BTree object. 
	 * @return
	 */
	public int getFreq() {
		return this.freq;
	}

	/**
	 * Increments the frequency of the BTree object. 
	 */
	public void increaseFreq() {
		freq++;
	}
	
	public int compareTo(TreeObject obj) {
		if (key < obj.key)
			return -1;
		if (key > obj.key)
			return 1;
		else
			return 0;
}

	/**
	 * Creates a readable string giving the frequency of each key. 
	 */
	public String toString() {
		return "Frequency: " + freq + "Key: " + key;

	}

}