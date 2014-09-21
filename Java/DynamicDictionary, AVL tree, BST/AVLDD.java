import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
import java.io.*;
import java.util.*;

/**
 * AVL Dynamic Dictionary class
 * @author B
 *
 */
public class AVLDD {
	
	/** root of AVL tree  */
	private AVLNode root;
	/** number of active elements in the tree */
	private int count;
	
	/**
	 * Constructor of new AVL trees
     */
	public AVLDD() {
		count = 0;
		root = null;
	}
	/**
	 * Returns the data of the root of the tree
	 * @return data of root of tree
     */
	public String root() {
		return root.data;
	}
	
	/**
	 * Boolean indicating whether tree is empty
	 * @return true for empty
     */
	public boolean isEmpty() {
		return (root == null);
	}
	
	/**
	 * Public insert method
	 * @param k is key
	 * @param s is String of data
     */
	public void insert(int k, String s) {
		//System.out.println("inserting " + k);
		//find(k,root);
		count++;
		root = insert(k,s,root);

	}
	
	/**
	 * Private insert method
	 * @param k is key
	 * @param s is String of data
	 * @param r is node at which it is being inserted
	 * @return AVLNode of where it's finally inserted
     */
	public AVLNode insert(int k, String s, AVLNode r) {
		
		if (r == null) return new AVLNode(k, s, null, null);
		//System.out.println(" r.key: " + r.key + "	k: " + k);
		//System.out.println("r.height: " + r.height);

		if (k == r.key) {
			//System.out.println("evaluating as already there");
			r.data = s; r.on = true; count--; 
			//r.height = Math.max(height(r.left), height(r.right)) + 1;
			return r;
		}
		
		
		if (k < r.key) { // insertion on left
			r.left = insert(k,s,r.left);
			r.left.parent = r;
			if (height(r.left) - height(r.right) > 1) { // check if left is larger
				//System.out.println("left is too heavy when inserting " + k + " at " + r.data);
				
				if (k < r.left.key) { // if inserted on the LL somewhere
					//System.out.println("it's an LL"); 
					r = rotateR(r);
				}
				if (k > r.left.key) {// if inserted on the LR somewhere
					//System.out.println("it's an LR");
					r.left = rotateL(r.left);
					r = rotateR(r);
				}
			}
		}	
		if (k > r.key) { // insertion on right
			r.right = insert(k,s,r.right);
			r.right.parent = r;
			if (height(r.right) - height(r.left) > 1) { // checking if right is larger
				// right is larger
				//System.out.println("right is too heavy when inserting " + k + " at " + r.data);
				if (k > r.right.key) { //(r.right.right != null) && (r.right.right.key <= k)) {
					//System.out.println("it's an RR"); 
					r = rotateL(r);
				}
				if (k < r.right.key) {
					//System.out.println("its an RL");
					r.right = rotateR(r.right);
					r = rotateL(r);
				}
			}
		}
			
		r.height = Math.max(height(r.left), height(r.right)) + 1;
		return r;
	}
	
	
	/**
	 * Performs right rotation starting at given node r
	 * @param r current root node to be rotated
	 * @return AVLNode with rotation complete
     */
	private AVLNode rotateR(AVLNode r) {
		AVLNode tmp= r.left;
		if(tmp.right != null) { //if right child of tmp exists
			//System.out.println("found right child of tmp");
			r.left = tmp.right;
			r.left.parent = r;
		} else {r.left = null;}
		tmp.right = r;
		if (r.parent == null) { // determining if r is root
			//System.out.println("evaluating as root");
			tmp.parent = null;
			root = tmp;
		} else {
			tmp.parent = r.parent;
			if(tmp.key < tmp.parent.key) {tmp.parent.left = tmp;}
			else {tmp.parent.right = tmp;}
			}
		r.parent = tmp;
		r.height = Math.max(height(r.left), height(r.right)) + 1;
		tmp.height = Math.max(height(tmp.left), height(tmp.right)) + 1;
		return tmp;
	}
	
	/**
	 * Performs left rotation starting at given node r
	 * @param r current root node to be rotated
	 * @return AVLNode with rotation complete
     */
	private AVLNode rotateL(AVLNode r) {
		AVLNode tmp= r.right;
		if(tmp.left != null) { //if left child of tmp exists
			//System.out.println("found left child of tmp");
			r.right = tmp.left;
			r.right.parent = r;
		} else {r.right = null;}
		tmp.left = r;
		if (r.parent == null) { // determining if r is root
			//System.out.println("evaluating as root");
			tmp.parent = null;
			root = tmp;
		} else {
			tmp.parent = r.parent;
			if(tmp.key < tmp.parent.key) {tmp.parent.left = tmp;}
			else {tmp.parent.right = tmp;}
			}
		r.parent = tmp;
		r.height = Math.max(height(r.left), height(r.right)) + 1;
		tmp.height = Math.max(height(tmp.left), height(tmp.right)) + 1;
		return tmp;
	}
	
	/**
	 * Public version of remove method, implementing lazy delete, calls private version on root
	 * @pre must contain element
	 * @param k - key of integer to be removed
	 *      
	 */
	public void remove(int k) { // must not be empty, and must be in tree
		remove(k, root);
	}
	
	/**
	 * Private version of remove method, implementing lazy delete
	 * @param k - key of integer to be removed
	 * @param r node at which to start the search
	 * @return AVLNode that was "removed"  
	 */
	private AVLNode remove(int k, AVLNode r) {
		AVLNode removed = find(k,r);
		if (removed == null) {
			return null;
		}
		removed.on = false;
		count--;
		//System.out.println(removed.data + " removed");
		return removed;
	}
	
	/**
	 * Public version of find method
	 * @pre must contain given element
	 * @param k - key of integer to be found
	 * @return String indicating empty or found      
	 */
	public String find(int k) {
		String answer = (find(k,root) == null) ? "empty" : "found";
		return answer;
	}
	
	/**
	 * Private version of find method, implementing lazy delete
	 * @pre must contain given element
	 * @param k - key of integer to be found
	 * @param r where to start the search
	 * @return AVL node found or null if tree is empty
	 */
	private AVLNode find(int k, AVLNode r) { //pre: must be in tree
		if(r == null) {return null;}
		if ((k == r.key) && (r.on = true)) {
			//System.out.println("found " + r.data); 
			return r;}
		if (k < r.key) {return find(k, r.left);}
		else {return find(k, r.right);}
	}
	
	/**
	 * Returns height of tree 
	 * @return height of tree
	 */
	public int height() {
		if (root==null) return -1;
		return 1 + Math.max(height(root.right), height(root.left));
	}
	
	/**
	 * Returns height of given node
	 * @param r, node for which height will be returned
	 * @return integer indicating height	 
	 */
	private int height(AVLNode r) {
		return r == null ? -1 : r.height;}
	
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
	private AVLNode findMin(AVLNode r) {
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
	private AVLNode findMax(AVLNode r) {
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
	private void print(AVLNode a) {
		if (a == null) {return;}
		if ((a.left != null)) {print(a.left);}
		System.out.print("value: " + a.data + ", on: " + a.on + " ");
		if (a != root) {System.out.print("parent: " + a.parent.key + ", ");} else {System.out.print("root ");}
		System.out.println("height by method: " + height(a));
		print(a.right);
	}		

}
