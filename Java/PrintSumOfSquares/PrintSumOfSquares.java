import java.util.*;

/**
	*sums up squares of integers inputed by user using do while loop
	*@author Bdavis
	*
*/

public class PrintSumOfSquares {
	
	/**
		*calls userInterface
	*/
	
	public static void main(String [] args) {
	
		userInterface();

	}
	

	/**
		*sums up squares of integers inputted by user, ignoring non-integers and giving the sum if the entry == -1
	*/
	
	public static void userInterface() {
		
		int sum = 0;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Give me an integer (-1 to quit)");
		
		//handling initial non-integer input
		while (!input.hasNextInt()) {
			input.next();
			System.out.println("That's not an integer");
			System.out.println("Give me an integer (-1 to quit)");
		
		}
		
		
		// handling integer input
		do{
				
			int stuff = input.nextInt();
			
			if (stuff == -1) {
					System.out.print("The sum of squares is : "+sum);
					System.exit(1);
				}
				
			if (stuff != -1) {
				sum += stuff*stuff;
				System.out.println("Give me an integer (-1 to quit)");	
				System.out.println(sum);
			}
			
			// inner loop to take care of non-integer entry after the initial loop has been initialized
			while (!input.hasNextInt()) {
				input.next();
				System.out.println("That's not an integer\nGive me an integer (-1 to quit  )");
			}
			
		} while (input.hasNextInt());
			
	}
}

