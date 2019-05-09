/**
 * A BTree object class.
 * 
 * @author Jake Andrews and Devan Craig
 *
 */
public class TreeObject {

	private int frequency;
	private long key;

	/**
	 * Constructor for the BTree object.
	 * 
	 * @param key  - the key of the object.
	 * @param frequency - the frequency of the object.
	 */

	public TreeObject(long key, int frequency) {
		this.key = key;
		this.frequency = frequency;
	}

	/**
	 * Constructor for the BTree object.
	 * 
	 * @param key - the key of the object.
	 */

	public TreeObject(long key) {
		this.key = key;
		this.frequency = 1;
	}

	/**
	 * Returns the key of the BTree object.
	 * 
	 * @return
	 */
	public Long getKey() {
		return this.key;
	}

	/**
	 * Returns the frequency of the BTree object.
	 * 
	 * @return
	 */
	public int getFreq() {
		return this.frequency;
	}

	/**
	 * Increments the freqency of the BTree object.
	 */
	public void increaseFreq() {
		frequency++;
	}

	/**
	 * Compares one key to another.
	 * 
	 * @param obj
	 * @return
	 */
	public int compareTo(TreeObject obj) {
		if (key < obj.key)
			return -1;
		if (key > obj.key)
			return 1;
		else
			return 0;
	}

}
