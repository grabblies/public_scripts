import java.util.*;
import java.lang.Math;


public class BinaryHeap {

	private int[] heap;
	private int count;

	
	public BinaryHeap(int elements) {
		heap = new int[elements+1];
		heap[0] = (int) Double.NEGATIVE_INFINITY;
		count = 0;
	}

	public void insert(int number) {
		if (count+1 > heap.length-1) {
			System.out.println("Heap is heaped");
			return;
		}

		count++;
		int i = count;
		while (number < heap[i/2]) {
			heap[i] = heap[i/2];
			i = i/2;
		}
		heap[i] = number;
	}

	public void deleteMin() {
		heap[1] = heap[count--];
		int num = heap[1];
		int i = 1;
		
		while (i < count) {
			if(heap[2*i] != 0) {
		
		}
	}
	
		

	
	public void print() {
		double row = 1.;
		System.out.println("Count is: " + count);
		
		for (int i = 1; i <= count; i++) { //each entry i should print on the (int) log_2 (i) row of the tree
			//System.out.println("Is "+ Math.log(1.*i)/Math.log(2) + " less than " + Math.ceil((Math.log(1.* i) / Math.log(2))));

			if ((double)i < Math.pow(2.0,row)) {
				System.out.print("{" + heap[i] + "} ");
			} else {
				row++;
				System.out.println();
				System.out.print("{" + heap[i] + "} ");
			}
		}		
	}
}