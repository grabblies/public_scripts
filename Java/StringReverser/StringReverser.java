import java.util.*;
/**
	*takes a string and reverses it, hopefully
	*@author BDaves
	*/

public class StringReverser {
	public static void main(String[] args) {
		System.out.println(reverse("Hello There!"));
		System.out.println(reverse("your name"));  
		System.out.println(reverse("RACECAR looks the same "));
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