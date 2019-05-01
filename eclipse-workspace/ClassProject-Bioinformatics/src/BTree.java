
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BTree {
	private int degree;
	private BTreeNode root;
	private int currentOffset;
	private int nodeSize;
	private int insertPoint;
	private int maxChild;
	private static int seqLength;
//	public final int STARTING_ADDRESS = 32;

	private File binFile;
	private RandomAccessFile disk;

	public BTree(int degree, String file, int seqSize) {
		this.degree = degree;
		//maxChild = 2 * degree;
		//maxKeys = (2 * degree) - 1;
		seqLength = seqSize;

		nodeSize = (32 * degree - 3);
		currentOffset = 12;
		insertPoint = (currentOffset + nodeSize);

		BTreeNode temp = new BTreeNode();
		root = temp;
		root.setOffset(currentOffset);
		temp.setLeaf(true);
		temp.setNumKeys(0);

		try {
			binFile = new File(file);
			binFile.delete();
			binFile.createNewFile();
			disk = new RandomAccessFile(file, "rw");
		} catch (FileNotFoundException ee) {
			System.err.println("file is missing!");
			System.exit(-1);
		} catch (IOException ioe) {
			System.err.println("IO Exception occurred!");
			System.exit(-1);
		}

	}

	public BTree(int degree, File file) {
		try {
			disk = new RandomAccessFile(file, "r");
		} catch (FileNotFoundException ee) {
			System.err.println("file is missing!");
		}

	}

	public BTree() {
		super();
	}

	public BTreeNode getRoot() {
		return root;
	}

	/**
	 * Returns the degree of the BTree.
	 * 
	 * @return
	 */
	public int getDegree() {
		return this.degree;
	}

	/**
	 * Returns the value of the node associated with the specified key.
	 * 
	 * @param key
	 * @return
	 */
	public long get(long key) {
		return key; // should return the value at the key location
	}

	/**
	 * Inserts a value into a specified location.
	 * 
	 * @param node
	 * @param key
	 */
	public void insert(long key) {
		BTreeNode start = root;
		int i = start.getNumKeys();
		if (i == (2 * degree) - 1) {
			TreeObject object = new TreeObject(key);
			while (i > 0 && object.compareTo(start.getKey(i - 1)) == 0) {
				i--;
			}
			if (i < start.getNumKeys()) {
			}
			if (i > 0 && object.compareTo(start.getKey(i - 1)) == 0) {
				start.getKey(i - 1).increaseFreq();
			} else {
				BTreeNode n = new BTreeNode();
				n.setOffset(start.getOffset());
				root = n;
				start.setOffset(insertPoint);
				start.setParent(n.getOffset());
				n.setLeaf(false);
				n.addChild(start.getOffset());
				splitChild(n, 0, start);
				BTreeInsertNotFull(n, key);
			}
		} else {
			BTreeInsertNotFull(start, key);
		}
	}

	public void BTreeInsertNotFull(BTreeNode start, long key) {
		int i = start.getNumKeys();

		TreeObject object = new TreeObject(key);
		if (start.isLeaf()) {
			if (start.getNumKeys() != 0) {
				while (i > 0 && object.compareTo(start.getKey(i - 1)) < 0) {
					i--;
				}
			}
			if (i > 0 && object.compareTo(start.getKey(i - 1)) == 0) {
				start.getKey(i - 1).increaseFreq();
			} else {
				start.addKey2(object, i);
				start.setNumKeys(start.getNumKeys() + 1);
			}
			writeNode(start, start.getOffset());
		} else {
			while (i > 0 && object.compareTo(start.getKey(i - 1)) < 0) {
				i--;
			}
			if (i > 0 && object.compareTo(start.getKey(i - 1)) == 0) {
				start.getKey(i - 1).increaseFreq();
				writeNode(start, start.getOffset());
				return;
			}
			int off = start.getChild(i);
			BTreeNode c = readNode(off);
			if (c.getNumKeys() == (2 * degree) - 1) {
				int j = c.getNumKeys();
				while (j > 0 && object.compareTo(c.getKey(j - 1)) < 0) {
					j--;
				}
				if (j > 0 && object.compareTo(c.getKey(j - 1)) == 0) {
					c.getKey(j - 1).increaseFreq();
					// wrtieNode(c,c.getOff)
					return;
				} else {
					splitChild(start, i, c);
					if (object.compareTo(start.getKey(i)) > 0) {
						i++;
					}
				}

			}
			off = start.getChild(i);
			BTreeNode child = readNode(off);
			BTreeInsertNotFull(child, key);
		}
	}

	public void splitChild(BTreeNode start, int i, BTreeNode c) {
		BTreeNode d = new BTreeNode();
		d.setLeaf(c.isLeaf());
		d.setParent(c.getParent());
		for (int j = 0; j < degree - 1; j++) {
			d.addKey(c.removeKey(degree));
			d.setNumKeys(d.getNumKeys() + 1);
			c.setNumKeys(c.getNumKeys() - 1);
		}
		if (!c.isLeaf()) {
			for (int j = 0; j < degree; j++) {
				d.addChild(c.removeChild(degree));
			}
		}
		start.addKey2(c.removeKey(degree - 1), i);
		start.setNumKeys(start.getNumKeys() + 1);
		c.setNumKeys(c.getNumKeys() - 1);
		if (start == root && start.getNumKeys() == 1) {
			writeNode(c, insertPoint);
			insertPoint += nodeSize;
			d.setOffset(insertPoint);
			start.addChild2(d.getOffset(), i + 1);
			writeNode(d, insertPoint);
			writeNode(start, currentOffset);
			insertPoint += nodeSize;
		} else {
			writeNode(c, c.getOffset());
			d.setOffset(insertPoint);
			writeNode(d, insertPoint);
			start.addChild2(d.getOffset(), i + 1);
			writeNode(start, start.getOffset());
			insertPoint += nodeSize;
		}
	}

	public TreeObject search(BTreeNode start, long key) {
		int i = 0;
		TreeObject obj = new TreeObject(key);
		while (i < start.getNumKeys() && (obj.compareTo(start.getKey(i)) > 0)) {
			i++;
		}
		if (i < start.getNumKeys() && obj.compareTo(start.getKey(i)) == 0) {
			return start.getKey(i);
		}
		if (start.isLeaf()) {
			return null;
		} else {
			int offset = start.getChild(i);
			BTreeNode y = readNode(offset);
			return search(y, key);
		}
	}

	public void inOrderPrint(BTreeNode node) {
		System.out.println(node);
		if (node.isLeaf() == true) {
			for (int i = 0; i < node.getNumKeys(); i++) {
				System.out.println(node.getKey(i));
			}
			return;
		}
		for (int i = 0; i < node.getNumKeys() + 1; ++i) {
			int offset = node.getChild(i);
			BTreeNode y = readNode(offset);
			inOrderPrint(y);
			if (i < node.getNumKeys())
				System.out.println(node.getKey(i));
		}
	}

//	public void inOrderPrintToWriter(BTreeNode node, PrintWriter PWriter, int sequenceLength) throws IOException {
//        GeneBankConvert gbc = new GeneBankConvert();
//        for (int i = 0; i < node.getNumKeys(); i++){
//            PWriter.print(node.getKey(i).getFreq()+ " ");
//            PWriter.println(gbc.convertLongToString(node.getKey(i).getData(),sequenceLength));
//        } if (!node.isLeaf()) {
//	        for (int i = 0; i < node.getNumKeys() + 1; ++i) {
//	            int offset = node.getChild(i);
//	            BTreeNode y = readNode(offset);
//	            inOrderPrintToWriter(y,PWriter,sequenceLength);
//	            if (i < node.getNumKeys()) {
//	                PWriter.print(node.getKey(i).getFreq() + " ");
//                    PWriter.println(gbc.convertLongToString(node.getKey(i).getData(),sequenceLength));
//	            }
//	        }
//        }
//}

	public void writeNode(BTreeNode nd, int off) {
		int i = 0;
		try {
			writeNodeData(nd, nd.getOffset());
			disk.writeInt(nd.getParent());
			for (i = 0; i < (2 * degree) - 1; i++) {
				if (i < nd.getNumKeys() + 1 && !nd.isLeaf()) {
					disk.writeInt(nd.getChild(i));
				} else if (i >= nd.getNumKeys() + 1 || nd.isLeaf()) {
					disk.writeInt(0);
				}
				if (i < nd.getNumKeys()) {
					long data = nd.getKey(i).getKey();
					disk.writeLong(data);
					int frequency = nd.getKey(i).getFreq();
					disk.writeInt(frequency);
				} else if (i >= nd.getNumKeys() || nd.isLeaf()) {
					disk.writeLong(0);
				}

			}
			if (i == nd.getNumKeys() && !nd.isLeaf()) {
				disk.writeInt(nd.getChild(i));
			}
		} catch (IOException ioe) {

		}
	}

	public void writeTreeData() {
		try {
			disk.seek(0);
			disk.writeInt(degree);
			disk.writeInt(32 * degree - 3);
			disk.writeInt(12);
		} catch (IOException ioe) {
			System.err.println("IO Exception occurred!");
			System.exit(-1);
		}
	}

	public void writeNodeData(BTreeNode start, int off) {
		try {
			disk.seek(off);
			disk.writeBoolean(start.isLeaf());
			disk.writeInt(start.getNumKeys());
		} catch (IOException ioe) {
			System.err.println("IOException!");
			System.exit(-1);
		}
	}

	public BTreeNode readNode(int off) {
		BTreeNode n = null;

		n = new BTreeNode();
		TreeObject object = null;
		n.setOffset(off);
		int k = 0;
		try {
			disk.seek(off);
			boolean isLeaf = disk.readBoolean();
			n.setLeaf(isLeaf);
			int temp = disk.readInt();
			n.setNumKeys(temp);
			int parent = disk.readInt();
			n.setParent(parent);
			for (k = 0; k < (2 * degree) - 1; k++) {
				if (k < n.getNumKeys() + 1 && !n.isLeaf()) {
					int child = disk.readInt();
					n.addChild(child);
				} else if (k >= n.getNumKeys() + 1 || n.isLeaf()) {
					disk.seek(disk.getFilePointer() + 4);
				}
				if (k < n.getNumKeys()) {
					long value = disk.readLong();
					int frequency = disk.readInt();
					object = new TreeObject(value, frequency);
					n.addKey(object);
				}
			}
			if (k == n.getNumKeys() && !n.isLeaf()) {
				int child = disk.readInt();
				n.addChild(child);
			}
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
			System.exit(-1);
		}

		return n;
	}

	public void readNodeData() {
		try {
			disk.seek(0);
			degree = disk.readInt();
			nodeSize = disk.readInt();
			currentOffset = disk.readInt();

		} catch (IOException ioe) {
			System.err.println("IOException!");
			System.exit(-1);
		}
	}

	/**
	 * Returns a string representation of the BTree. //We might not need this idk
	 */
	public String Convert() {

		return "";
	}

}
