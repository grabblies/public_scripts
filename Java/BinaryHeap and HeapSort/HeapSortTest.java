import java.util.*;

public class HeapSortTest{

private static int LENGTH_OF_ARRAY = 15;
private static int count = 0;
private static Random rand = new Random();
	
	public static void main(String[] args) {
		System.out.println("1/2 = " + 1/2);
		System.out.println("3/2 = " + 3/2);
		
		
		double[] rayboy = new double[LENGTH_OF_ARRAY];
		for(int i = 0; i < rayboy.length; i++) {
			rayboy[i] = 1.*rand.nextInt(20);	
		}
		System.out.println("Original Array");
		print(rayboy);
		System.out.println("Sorting Array");
		heapSort(rayboy);
		System.out.println("Reprinting Array");
		print(rayboy);
		System.out.println("Printing as Heap");
		printAsHeap(rayboy);
	}
	
	public static void heapSort(double[] awry) {
		double min = awry[0];
		int min_location = 0;
		for (int i = 0; i < awry.length; i++) { //finding min and swapping it out for root
			if (awry[i] < min) {
				min = awry[i];
				min_location = i;
			}
			swap(awry, 0, min_location);
		}		
		
		for (int i = 1; i < awry.length; i++) {
			heap(awry ,awry[i]);
		}
		System.out.println("Heaping completed");
		printAsHeap(awry);
		System.out.println("before reordering");
		print(awry);
		

		for (int i = 0; i < awry.length/2; i++) {
			//System.out.println(awry.length-1-i);
			swap(awry, 0, awry.length-1-i);
			count--;
			
			if ((awry[2*i+1] > awry.length-1) || (awry[2*i+2] == awry.length-1)) {
				System.out.println("");
				System.out.println("OUT OF BOUNDS");
				break;
			}
			
			while((awry[i] > awry[2*i+1]) || (awry[i] > awry[2*i +2])) {
				System.out.println("Trying to find a place for "+awry[i]);
				printAsHeap(awry);
				if (awry[2*i+1] < awry[2*i+2]) {
					swap(awry, i, 2*i+1);
					i = 2*i+1;
				} else {
					swap(awry, i, 2*i+2);
					i = 2*i+2;
				}
			}
		}
	}
	
	public static void placeMin(double[] ray, int index) {
		
	}
	
	public static void heap(double[] heap, double number) {
		if (count+1 > heap.length-1) { // on the lookout for out-of-range things; not necessary here
			System.out.println("Heap is heaped");
			return;
		}

		count++;
		int i = count;
		
		while (number < heap[(i/2)-1]) {
			if (heap[i] == heap[(i/2)-1]) {
				heap[i] = heap[(i/2)-1];
				break;
			}
			//System.out.println("Comparing " + heap[i] + " and " + heap[i/2]);
			heap[i] = heap[(i/2)-1];
			i = (i/2)-1;
		}

		heap[i] = number;
	}

	
	public static void print(double[] poop) {
		System.out.print("[");
		for (int i=0; i < poop.length-1; i++) {
			System.out.print(poop[i] + ", ");
		}
		System.out.println(poop[poop.length-1] + "]");
	}
	
	public static void swap(double[] ray, int a, int b) {
		if((a > ray.length-1) || (b > ray.length-1)) {
			System.out.println("one of your values was out of bounds");
			return;
		}
		double tmp = ray[a];
		ray[a] = ray[b];
		ray[b] = tmp;
	}	
	
	public static void printAsHeap(double[] heap) {
		int tabs = (int)(Math.log(count*1.0)/Math.log(2))+1;
		double row = 0.;
		System.out.println("Count is: " + count);
		
		for (int k = 0; k<tabs-1; k++) {
			System.out.print("	");
		}
		System.out.print("   ");

		for (int i = 0; i <= count; i++) { //each entry i should print on the (int) log_2 (i) row of the tree
			//System.out.println("Is "+ Math.log(1.*i)/Math.log(2) + " less than " + Math.ceil((Math.log(1.* i) / Math.log(2))));

			if ((double)i+1 < Math.pow(2.0,row)) {
				System.out.print("{" + heap[i] + "} ");
			} else {
				row++;
				System.out.println();
				for(int j =0 ; j < tabs; j++) {
					System.out.print("	");
					tabs--;
				}
				System.out.print("{" + heap[i] + "} ");
			}
		}			
		System.out.println("");
	}	
}