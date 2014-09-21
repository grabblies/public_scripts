/**
 * BSTNode class for building BST trees
 * @author B
 *
 */
public class BSTNode {
	public int key;
	public String data;
	public BSTNode right;
	public BSTNode left;
	
	/**
	 * Constructor method
	 * @param k is key
	 * @param s is data
	 * @param l is left child
	 * @param r is right child
	 *
	 */	
	public BSTNode(int k, String s, BSTNode l, BSTNode r) {
		this.key = k;
		this.data = s;
		this.left = l;
		this.right = r;
	}
	
	/**
	 * Prints relevant info about a BSTNode
	 *
	 */
	public void print() {
		System.out.println("Key:" + key);
		System.out.println("Data:" + data);
	}
}
