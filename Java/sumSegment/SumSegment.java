import java.util.*;

public class SumSegment{

	public static void main (String[] args) {
		
		int[] int_array = getArray();
		
		int[] all_sums = arrayOfSums(int_array);
		
		System.out.println(Arrays.toString(int_array));
		
		
		
	}
	
	public static int[] getArray() {
	
		Random RAND = new Random();
		//int[] integers = new int[RAND.nextInt(100)];
		int[] integers = new int[4];
		
		
		for (int i = 0; i < integers.length; i++) {
			integers[i] = RAND.nextInt(100) - RAND.nextInt(100);
		}
		
		return integers;
	}
	
	
	public static int[] arrayOfSums(int[] array) {
		
		int possible_sums = 0;
		System.out.println(array.length);
		
		for (int len = array.length; len > 0; len--) {
			possible_sums += array.length - len + 1;
		}
		
		int[] sums = new int[possible_sums];
		
		// making sums - needs to sum in units of i, until reaching end, 
		//and then advance unit i by one, until i is the whole length
		
		
		for (int i = 0; i < sums.length; i++){ // will run through sums array to fill it in
			int step = 1;
			
			for (int j = sums.length; j > 0; j--) { 
				// for each unit in the sums array, counting down, fill in sums array with sum
				int ind_sum = 0;
				
				for (int k = 0; k < j; k++) {
					//sum up to j units for individual sum
					ind_sum += array[k];
				}
			
			System.out.println(ind_sum);
			}
				
				
				
			//	sums[
			//	sums[i] += array[i];	
		}
		
		System.out.println(Arrays.toString(sums));
		
		return sums;
	}
	
}
