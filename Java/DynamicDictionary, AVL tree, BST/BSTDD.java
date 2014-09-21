    /**
     * Binary Search Tree Dynamic Dictionary Class
     * @author B
     *
     */
public class BSTDD {
    /** indicates which node is root */
	private BSTNode root;
	/** indicates count of nodes in tree*/
	private int count;
	
    /**
     * Constructor method
     *
     */
	public BSTDD() {
		count = 0;
		root = null;
	}
	
    /**
     * Method indicating whether tree is empty
     *@return true if empty, false if not
     */
	public boolean isEmpty() {
		return (root ==null);
	}
	
	/**
	 * Public insert method
	 * @param k is key
	 * @param s is String of data
     */
	public void insert(int k, String s) {
		count++;
		root = insert(k,s,root);
	}
	
	/**
	 * Private insert method
	 * @param k is key
	 * @param s is String of data
	 * @param r is node at which it is being inserted
	 * @return BSTNode of where it's finally inserted
     */
	private BSTNode insert(int k, String s, BSTNode r) {
		if (r==null) return new BSTNode(k, s, null, null);
		if (k == r.key) r.data = s;
		if (k < r.key) r.left = insert(k,s,r.left);
		else r.right = insert(k,s,r.right);
		return r;
	}
	
	/**
	 * Public version of remove method, implementing lazy delete, calls private version on root
	 * @pre must contain element
	 * @param k - key of integer to be removed
	 *      
	 */
	public void remove(int k) {
		count--;
		root = remove(k,root);
	}
	
	/**
	 * Private version of remove method, implementing real removal
	 * @param k - key of integer to be removed
	 * @param r node at which to start the search
	 * @return node that was "removed"  
	 */
	private BSTNode remove(int k, BSTNode r) {
		// pre: node with key k must be in dictionary
		if (r== null) return null;
		
		if (k < r.key) r.left = remove(k, r.left);
		if (k > r.key) r.right = remove(k, r.right);
		if (k == r.key) { //one or zero children
			if ((r.right == null) || (r.left == null)) {
				r = (r.left == null) ? r.right : r.left;
			}
			else { // two children
				BSTNode tmp = findMax(r.left);
				r.key = tmp.key;
				r.data = tmp.data;
				r.left = remove(tmp.key, r.left);	
			}
		}
		return r;
	}
	
	/**
	 * Public version of find method
	 * @pre must contain given element
	 * @param k - key of integer to be found
	 * @return String indicating empty or found      
	 */
	public String find(int k) {
		return Integer.toString(find(k, root));
	}
	
	/**
	 * Private version of find method, implementing lazy delete
	 * @pre must contain given element
	 * @param k - key of integer to be found
	 * @param r where to start the search
	 * @return String indicating empty or found 
	 */
	private int find(int k, BSTNode r) {
		if( r == null) {return -1;}
		System.out.println(r.data);
		if (k == r.key) {return r.key;}
		if (k < r.key) {return find(k, r.left);}
		else {return find(k, r.right);}
	}
	
	/**
	 * Public method which gets height of total tree
	 * @return integer indicating height of tree	 
	 */
	public int height() {return height(root);}	
	
	/**
	 * Private method which returns of given node
	 * @param r, node for which height will be returned
	 * @return integer indicating height	 
	 */
	private int height(BSTNode r) {
		if (r==null) return -1;
		return 1 + Math.max(height(r.right), height(r.left));
	}
	
	/**
	 * Returns count of active elements in tree
	 * @return integer indicating count	 
	 */	
	public int count() {return count;}
	
	/**
	 * Public method of method that returns smallest element in tree
	 * @return String data of smallest element	 
	 */	
	public String findMin() {return findMin(root).data;}
	
	/**
	 * Private method of method that returns smallest element in tree
	 * @param r, where to start the search
	 * @return Node with smallest key
	 */	
	private BSTNode findMin(BSTNode r) {
		if (r.left == null) {return r;}
		return findMin(r.left);
	}
	
	/**
	 * Public method of method that returns largest element in tree
	 * @return String data of largest element	 
	 */	
	public String findMax() {return findMax(root).data;}
	
	/**
	 * Private method of method that returns largest element in tree
	 * @param r, where to start the search
	 * @return Node with largest key
	 */	
	private BSTNode findMax(BSTNode r) {
		if (r.right == null) {return r;}
		return findMax(r.right);
	}

	/**
	 * Public method that calls private print method
	 */	
	public void print() {
		print(root);
		System.out.println();
	}
	
	/**
	 * Private method that prints data of tree in order of its key
	 * @param a node to start at
	 */		
	private void print(BSTNode a) {
		if (a == null) {return;}
		if (a.left != null) {print(a.left);}
		System.out.print(a.data + ", ");
		print(a.right);
	}
	
	
	

}
