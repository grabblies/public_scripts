import java.util.*;
/**
	*asks for a string and reverses it, with Style!
	*@author BDaves
	*/

public class StringReverser2 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Give me a string, any string");
		String entry = input.nextLine();
		
		System.out.println(reverse(entry));

	}
	
	
	/**
	*takes a string and reverses it
	*@param String string
	*@return reverse of string as string
	*/
	
	public static String reverse(String original) {
		int len = original.length();
		for(int x=1; x<=len; x++) {
			System.out.print(original.charAt(len-x));
			}
		String end = "";
		return end;

	
	}
	
}