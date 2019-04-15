public class TreeObject {

	private int freq;
	private long key;

	public TreeObject(long key) {
		this.key = key;
		this.freq = 1;
	}

	public Long getKey() {
		return this.key;
	}

	public int getFreq() {
		return this.freq;
	}

	public void increaseFreq() {
		freq++;
	}

	public String toString() {
		return "Frequency: " + freq + "Key: " + key;

	}

}
