
public class BinaryHeapTest{

	public static void main(String[] args) {

		BinaryHeap heap = new BinaryHeap(100);
		for(int i = 1; i <30; i++) {
		heap.insert(i);
		}
		heap.print();
		heap.deleteMin();
		heap.print();
	}


}