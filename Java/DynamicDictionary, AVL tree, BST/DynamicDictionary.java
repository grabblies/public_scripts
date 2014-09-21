
public class DynamicDictionary {
	
	private BSTNode root;
	private int count;
	
	public DynamicDictionary() {
		count = 0;
		root = null;
	}
	
	public boolean isEmpty() {
		return (root ==null);
	}
	
	public void insert(int k, String s) {
		count++;
		root = insert(k,s,root);
	}
	
	private BSTNode insert(int k, String s, BSTNode r) {
		if (r==null) return new BSTNode(k, s, null, null);
		if (k == r.key) {r.data = s};
		if (k < r.key) r.left = insert(k,s,r.left);
		else r.right = insert(k,s,r.right);
		return r;
	}
	
	public void remove(int k) {
		count--;
		root = remove(k,root);
	}
	
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
	
	public String find(int k) {
		return Integer.toString(find(k, root));
	}
	private int find(int k, BSTNode r) {
		if( r == null) {return -1;}
		System.out.println(r.data);
		if (k == r.key) {return r.key;}
		if (k < r.key) {return find(k, r.left);}
		else {return find(k, r.right);}
	}
	
	public int height() {return height(root);}	
	private int height(BSTNode r) {
		if (r==null) return -1;
		return 1 + Math.max(height(r.right), height(r.left));
	}
	
	public int count() {return count;}
	
	
	public String findMin() {return findMin(root).data;}
	private BSTNode findMin(BSTNode r) {
		if (r.left == null) {return r;}
		return findMin(r.left);
	}
	
	public String findMax() {return findMax(root).data;}
	private BSTNode findMax(BSTNode r) {
		if (r.right == null) {return r;}
		return findMax(r.right);
	}
	
	public void print() {
		print(root);
		System.out.println();
	}
	
	private void print(BSTNode a) {
		if (a == null) {return;}
		if (a.left != null) {print(a.left);}
		System.out.print(a.data + ", ");
		print(a.right);
	}
	
	
	

}
