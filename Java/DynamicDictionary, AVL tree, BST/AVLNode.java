/**
 * AVLNode class for building AVL trees
 * @author B
 *
 */

public class AVLNode {
	
    public int key;
    public String data;
    public AVLNode left, right;
    public int height;
    public AVLNode parent;
    public boolean on;
    
    /**
     * AVLNode constructor
     * @param k is the key
     * @param s is the data
     * @param l is the left child
     * @param r is the right child
     *
     */
    public AVLNode(int k, String s, AVLNode l, AVLNode r) {
        this.on = true;
    	this.key = k;
        this.data = s;
        this.left = l;
        this.right = r;
        //this.height = 0;
    }
    /**
     * Prints relevant information about a particular node
     *
     */
    public void print() {
    	System.out.println("Key: " + Integer.toString(key));
    	System.out.println("Data: " + data);
    	System.out.println("On: " + on);
    	System.out.println("Height: " + height);
    }
}
