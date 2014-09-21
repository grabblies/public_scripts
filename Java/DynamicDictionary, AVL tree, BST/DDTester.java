import java.util.*;
import java.io.*;

public class DDTester {

	/**count iterator for sorting and printing**/
	private static int count = 0;
	/**random number generator**/
	private static Random rand = new Random(30);
	private static Random r = new Random(10);
	/**number of trials for each array size**/
	static int NUM_OF_TRIALS = 500;
	static int N = 1000; // powers of 2 to be considered
	
	/**
	 *Inserts and removes from Dynamic Dictionaries, compares the time, and outputs chart to file and to screen
	 *
	 */	
	public static void main(String[] args) {// Argument: number of inserts to be performed
		int n = (args.length == 0) ? 10 : Integer.parseInt(args[0]); // default value is n =10
		try {
			// setting up file output
			File outputfile = new File("DD Analysis.txt");	
			if (outputfile.exists()) {
				outputfile = new File("DD Analysis.txt");
			}
			PrintStream output = new PrintStream(outputfile);
			Scanner in = new Scanner(System.in);
			
			
			//AVLDD dict1 = new AVLDD(); System.out.println("AVL"); output.println("AVL");
			BSTDD dict1 = new BSTDD(); System.out.println("BST"); output.println("BST");

			//An integer argument to Random() ensures that the same sequence is generated each run
			//Perform 2n insertions into the dictionary...
			System.out.println("inserts");
			output.println("inserts");
			System.out.println("n	time	height	count");
			output.println("n	time	height	count");
			
			for (int z = 0; z <= n; z++) {
				int y = (int)Math.pow(2.0,(double)z);

				long time1 = System.nanoTime();
				
				// random elements
				//for (int i=0; i <= y; i++){int k = r.nextInt(2*N); dict1.insert(k, "The Number is " + Integer.toString(k));}

					
				//increasing elements
				// for (int i=0; i<y; i++) dict1.insert(i, "The Number is " + Integer.toString(i));	
					
				//decreasing elements
				 for (int i=0; i<y; i++) dict1.insert(n-i, "The Number is " + Integer.toString(n-i));
					
				System.out.println(y + "	" + ((System.nanoTime() - time1)/1000) + "	" + dict1.height() + "	" + dict1.count());
				output.println(y + "	" + ((System.nanoTime() - time1)/1000) + "	" + dict1.height() + "	" + dict1.count());
			}	
			
			System.out.println("removals");
			output.println("removals");
			for (int z = 0; z <= n; z++) {
				int y = (int)Math.pow(2.0,(double)z);

				long time1 = System.nanoTime();

				//**random removes from same sequence
				//for (int i=0; i < y/2; i++) dict1.remove(r.nextInt(2*N));
				
				//**removes from increasing sequence
				//for (int i=0; i < y/2; i++) dict1.remove(i);
				
				//**removes from decreasing sequence
				for (int i=0; i < y/2; i++) dict1.remove(n-i);

				System.out.println(y + "	" + ((System.nanoTime() - time1)/1000) + "	" + dict1.height() + "	" + dict1.count());
				output.println(y/2 + "	" + ((System.nanoTime() - time1)/1000) + "	" + dict1.height() + "	" + dict1.count());
			}	


		
		} catch (FileNotFoundException e) {
		    System.out.println("problem creating file");
			System.exit(1);
		}	
	}
}
