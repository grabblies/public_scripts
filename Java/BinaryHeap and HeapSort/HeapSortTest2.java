import java.util.*;
import java.io.*;

/**
 * 
 * Implements two types of sorting algorithms 
 * @author B
 *
 */

 
public class HeapSortTest2{
	/**count iterator for sorting and printing**/
	private static int count = 0;
	/**random number generator**/
	private static Random rand = new Random(10);
	/**dummy array to be sorted**/
	static double[] A; //The array to be sorted
	/**dummy array 2 to be sorted**/
	static double[] B; //A copy of A, to sort with the quadratic sorting algorithm
	/**number of trials for each array size**/
	static int NUM_OF_TRIALS = 100;

	/**
	 *Calls arguments for algorithms, compares the time, and outputs chart to file and to screen
	 *
	 */	
	public static void main(String[] args) {

		try {
			File outputfile = new File("O(n)data.txt");	
			if (outputfile.exists()) {
				outputfile = new File("O(n)data.txt");
			}
			
			PrintStream output = new PrintStream(outputfile);
			Scanner in = new Scanner(System.in);

			long startTime;
			long changeTime;
			long stopTime;
			long[] heapTime = new long[NUM_OF_TRIALS+1];
			long[] quadTime = new long[NUM_OF_TRIALS+1];
			
			long totalHeapTime =0;
			long totalQuadTime =0;
			long avgHeapTime = 0;
			long avgQuadTime = 0;
		
			System.out.println("array size	avg heapsort time	avg quadsort time");
			output.println("array size	avg heapsort time	avg quadsort time");			
			for(int n = 2; n < 15; n++) {
				for(int i = 0; i <= NUM_OF_TRIALS; i++) {
					A = new double[(int)Math.pow(2,n)];
					generateData(A);
					B = A.clone();
					
					startTime = System.nanoTime();
					heapSort(A);
					changeTime = System.nanoTime();
					quadSort(B);
					stopTime = System.nanoTime();
					
					heapTime[i] = changeTime- startTime;
					totalHeapTime += heapTime[i];
					quadTime[i] = stopTime -changeTime;
					totalQuadTime += quadTime[i];
				}

			avgHeapTime = totalHeapTime/NUM_OF_TRIALS;
			avgQuadTime = totalQuadTime/NUM_OF_TRIALS;
			
			System.out.println(Math.pow(2,n) +"	"+avgHeapTime+"	"+avgQuadTime);
			output.println(Math.pow(2,n) +"	"+avgHeapTime+"	"+avgQuadTime);			
			}
			
			System.out.printf("Heapsort took an average of %6d ms while quadSort took an average of %6d ms\n",
				(avgHeapTime)/1000,
				(avgQuadTime)/1000);
			
		} catch (FileNotFoundException e) {
		    System.out.println("problem creating file");
			System.exit(1);
		}
	}
	
	
	/**
	*@param awry is array to be sorted
	*@pre must be array of doubles
	**/
	public static void quadSort(double[] awry) {
		double min;
		int min_loc = 0;
		
		for (int i = 0; i < awry.length; i++) { // changing bounds from lower to end, advancing lower
			min = awry[i]; // resetting min every time to what's at the lower bound;
			
			for(int j = i; j < awry.length; j++) { // j is scanning subsection for min
				if(awry[j] <= min) {
					min = awry[j];
					min_loc = j;
				}
			}
			swap(awry, i, min_loc); // the lowest in the subsection is moved to the first value
		}
	}
	
	/**
	*Sorts array from smallest to largest using heapsort algorithm
	*@param awry is array to be sorted
	*@pre must be array of doubles
	**/	
	public static void heapSort(double[] awry) {
		double min = awry[0];
		int min_location = 0;
		for (int i = 0; i < awry.length; i++) { //finding min and swapping it out for root
			if (awry[i] < min) { // finding min
				min = awry[i];
				min_location = i;
			}
			swap(awry, 0, min_location); // putting minimum at the root
		}		
		
		for (int i = 1; i < awry.length; i++) { // i here should strictly refer to interval across whole array
			int index = i;	//need another variable that can change value from i to i/2
			while (awry[index] < awry[index/2]) { //while ith element is bigger than its parents
				swap(awry, index, index/2); //swap ith element with parents
				index = index/2; //transition ith element to be parent of ith element
			}
			count++; // for printing as a heap
		}
		
		//make top of heap (unit 1 not zero) into tmp
		//swap with last unit
		//reduce count
		//make new top of heap trickle down to appropriate place
		
		//need to swap array[count] and array[1] and then decrement count
		for (int i = count; i > 0; i--) {
			//printAsHeap(awry);
			swap(awry, count, 1);
			count--;
			//System.out.println("");
			//System.out.println("Count is: "+ count);
			//printAsHeap(awry);
			int j = 1;
			
			
			while((2*j < awry.length)&&((awry[j] > awry[2*j]) || (awry[j] > awry[2*j+1]))) { 
			//while children are still in array, and an element is bigger than one of its children

					
				if(2*j +1 <= count) { //right child is in range, so must have both children
					//System.out.println("Position " +j+ " has two children");
					if(awry[2*j] > awry[2*j+1]) { //if left child is bigger
						//System.out.println("Left child "+ awry[2*j]+" is greater, swapping with right");
						swap(awry, j, 2*j+1); //swapping between parent and smaller child, in this case right one
						j = 2*j+1;// j advances to become index of child with which it was swapped
						//printAsHeap(awry);
					} else { // if right child is bigger or if equal
						//System.out.println("right child "+ awry[2*j+1]+" is bigger, swapping with left");// swap with left child
						swap(awry, j, 2*j); // swap with left child
						j = 2*j;// j advances to become index of child with which it was swapped
						//printAsHeap(awry);
					}
				} else if ((2*j <=count) && (awry[j] > awry[2*j])){ // no right child, but there is left child
					//System.out.println(awry[j] + " is greater than " +awry[2*j] + ": " + (awry[j] > awry[2*j])); 
					//System.out.println("Position " +j+ " has one left child");
					//System.out.println("No right child, but there is a left child, so swap");
					//if (awry[j] > awry[2*j]) { //need this if loop because it is still checking if [j] is greater 
					// than [2*j] OR [2*j +1], where the j's can fall outside the heap, so need to recheck for value in heap
					
					swap(awry,j, 2*j);
					j= 2*j; // j advances
					//System.out.println("");
					//System.out.println("Printing as heap after swap with left child");
					//printAsHeap(awry);
					break;
				} else { //no children
					//System.out.println("Position " +j+ " has no children");
					break; //leave it where it is
				}
			}
		}
		
		for (int i = 1; i < (awry.length/2 +1); i++) { //reordering array from smallest to largest
			swap(awry,i, awry.length-i);
		}
	}	
	
	/**
	*fills array with random doubles
	*@param awry is double array to be filled
	*@pre must be array of doubles
	**/	
	static void generateData(double[] A){//Fills array A[] with random values.
		for (int i=0; i<A.length; i++) A[i]= rand.nextDouble();
	}	

	/**
	*prints array of doubles
	*@param doop is double array to be printed
	*@pre must be array of doubles
	**/		
	public static void print(double[] doop) {
		System.out.print("[");
		for (int i=0; i < doop.length-1; i++) {
			System.out.print(doop[i] + ", ");
		}
		System.out.println(doop[doop.length-1] + "]");
	}
	
	/**
	*swaps two values in an array of doubles
	*@param ray is the array in which the swap is occurring
	*@param a is the first location of the swap
	*@param b is the second swap location
	*@pre ray must be array of doubles, a and b must be integers
	**/		
	public static void swap(double[] ray, int a, int b) {
		if((a > ray.length-1) || (b > ray.length-1)) {
			System.out.println("one of your values was out of bounds");
			return;
		}
		double tmp = ray[a];
		ray[a] = ray[b];
		ray[b] = tmp;
	}	
	
	/**
	*prints array of doubles as a binary heap
	*@param heap is double array to be printed
	*@pre must be array of doubles
	**/			
	public static void printAsHeap(double[] heap) {
		int tabs = (int)(Math.log(count*1.0)/Math.log(2))+1;
		double row = 0.;
		
		for (int k = 0; k<tabs-1; k++) {
			System.out.print("	");
		}
		System.out.print("   ");

		for (int i = 1; i <= count; i++) { //each entry i should print on the (int) log_2 (i) row of the tree
			//System.out.println("Is "+ Math.log(1.*i)/Math.log(2) + " less than " + Math.ceil((Math.log(1.* i) / Math.log(2))));

			if ((double)i < Math.pow(2.0,row)) {
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