import java.io.*;
import java.util.*;

/**
	*Asks user for html file to scan and then removes html tags contained in the document
	*@author BDavis
*/

public class HtmlStripper {
	
	
	/**
		*calls the userInterface to start the program
		*
	*/
	
	public static void main(String [] args) {
	
		userInterface();
	}

	/**
		*Hands scanners back and forth, first getting file name based on user input in getInput, and then handing a scanner 
		*directed at that file to processFile
		*
	*/
	
	public static void userInterface() {
		
		processFile(getInput(new Scanner(System.in)));
	}

	/**
		*Chooses a file based on user input to then create a scanner for
		*@param console is the console scanner looking at entry by the user
		*@return scanner filescan, the scanner looking at the file chosen by the user
	*/	
	
	public static Scanner getInput(Scanner console) {
	
		Scanner filescan = null;
		
		try {
		System.out.println("Printed by getInput: Which html file do you want to strip down to its bones?");
		String input = console.next();
		
		File htmlfile = new File(input);
		filescan = new Scanner(htmlfile);
		}
		
		catch (FileNotFoundException e) {
			System.out.println("File doesn't exist");
			System.exit(1);
		}
		
		return filescan;
	}

	/**
		*Processes file chosen by user
		*@param input is scanner passed by userInterface to look at the file
	*/	
	
	public static void processFile(Scanner input) {
		
		//String test = "you cr<azy>appy";
		//String subtest = test.substring(0,test.indexOf("<")) + test.substring(test.indexOf(">")+1,test.length());
		
		//System.out.println(subtest);
		
		while (input.hasNext()) {

			String line = input.nextLine();
			
			while (line.contains(">") || line.contains("<")){
				
				line = line.substring(0,line.indexOf("<")) + line.substring(line.indexOf(">")+1,line.length());
			}

			System.out.println(line);
			
		}
	}
}