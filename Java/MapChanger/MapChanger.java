import java.util.*;
import java.io.*;

/**
	*This class changes treasure maps by obscuring and/or uncovering them
	*@author BDavis
	*
*/

public class MapChanger {

	public static final String VALID_CHARACTERS = "+/\\~X";
	public static final String OBSCURE_CHARACTERS = "!@#$%^&*()=";
	public static final Random RAND = new Random();

	/**
		*Calls user interface
	*/

	public static void main(String [] args) {
	
		userInterface();
	
	}

	/**
		*User interface for this class 
		*
	*/
	
	public static void userInterface() {
		
		Scanner console = new Scanner(System.in);
		
		System.out.println("Hello there and welcome to the incredibly versatile map obscurer/revealer.");
		System.out.print("This software was designed with you in mind.");
		System.out.println("So how can we help you today?");
		System.out.println("Would you like to obscure(O), reveal(R), or quit(Q)?");
		
		String answer = console.next();
		boolean obscure = false;
		
		while (!answer.equalsIgnoreCase("Q") && !answer.equalsIgnoreCase("R") && !answer.equalsIgnoreCase("O")) {
			System.out.println("That's not a valid answer. Would you like to obscure(O), reveal(R), or quit(Q)?");
			answer = console.next(); //question for grader - do I need to update answer here?
		}
		
		if (answer.equalsIgnoreCase("Q")) {
			System.out.println("So soon? Well then, goodbye!");
			System.exit(1);
		}
		
		else if (answer.equalsIgnoreCase("O")) {
			System.out.println("Obscure? Okay!");
			obscure = true;
		}
		
		else {
			System.out.println("Reveal? Okay!");
		}
		
		//System.out.println("Obscure: "+obscure);
		
		Scanner george = getInputScanner(console);
		PrintStream jone = getOutputPrintStream(console);
		
		processFile(obscure, george, jone);
		
		System.out.println("Ding! Your file is ready.");
	
	}
	
	/**
		*Creates scanner directed at input file
		*@param passed by the user interface
		*@return input scanner directed at input file
	*/	
	
	public static Scanner getInputScanner(Scanner console) {
		
		Scanner inputfile = null;
		
		try {
			
			System.out.println("Which file is the map in?");
			
			File poppy = new File(console.next());
			
			while (!poppy.exists()) {
				System.out.println("That file doesn't exist. What file is the map in?");
				poppy = new File(console.next());
			}
			
			inputfile = new Scanner(poppy);
			
		} catch (FileNotFoundException e) {
			System.out.println("couldn't find the file - exception");
			System.exit(1);
		
		}
		return inputfile;	
	}
	
	/**
		*Returns PrintStream for output file
		*Use a try/catch block to catch and handle any FileNotFoundException's that occur
		*public static PrintStream getOutputPrintStream(Scanner console){
		*@return PrintStream directed at output document chosen by user
	*/	
	
	public static PrintStream getOutputPrintStream(Scanner inpoot) {
	
		PrintStream output = null;
		String answer = null;
		String filename = null;
		
		System.out.println("What is the name of the file you'd like to output your map to?");
		
		try {
		
			filename = inpoot.next();
		
			File outputfile = new File(filename);
			
			//System.out.println(outputfile.exists());
			
			if (outputfile.exists()) {
			
				System.out.println("That file already exists. Would you like to overwrite that file? (y/n)");
				answer = inpoot.next();
			
				while (!answer.equalsIgnoreCase("y") && outputfile.exists()) {
					
					System.out.println("In that case, what would you like to name your output file?");
					outputfile = new File(inpoot.next());
					
					if (outputfile.exists()) {
						System.out.println("That file already exists. Do you want to overwrite that?");
						answer = inpoot.next();
					}	
				}
			}
			
			
			output = new PrintStream(outputfile);

		} catch (FileNotFoundException e) {
		    System.out.println("problem creating file");
			System.exit(1);
		}
		
		return output;	
	
	}
	
	/**
		*This reveals or obscures the map
		*@param obscure indicates whether the document needs to be obscured or revealed
		*@param input is the scanner reading from the input file
		*@param output writes to the target file
	*/	
	
	public static void processFile(boolean obscure, Scanner input, PrintStream output) {
		//If obscure is true, obscures map in input and outputs obscured map.
		//If obscure is false, uncovers map in input and outputs uncovered map.
		
		
		if (obscure = true) {
			while (input.hasNextLine()) {
				output.println(uncoverLine(input.nextLine()));
				//System.out.println(uncoverLine(input.nextLine()));
			}	
		}
		
		else {
			while (input.hasNextLine()) {
				output.println(uncoverLine(input.nextLine()));
				//System.out.println(uncoverLine(input.nextLine()));
			}
		}
		
	}
	
	/**
		*Takes string and replaces spaces with obscure characters
		*@param line is the line to be obscured
		*@return obscured line
	*/	
	
	public static String obscureLine(String line) {
	
		int length = line.length();
		char[] lineArray = new char[length];
		
		//changing elements of original string, then inputing them into an array to make a new string
		for (int i = 0; i<length; i++) {
		
			char ch = line.charAt(i);
			
			if (ch == ' ') {
				ch = getRandomCharacter();
			}
			
			lineArray[i] = ch;
		}
		
		String obscureline = new String(lineArray);
		return obscureline;
	}
	
	
	/**
		*This method removes the junk from the lines on the map
		*@param line full of random junk to be removed
		*@return line full of purity and clarity
	*/	
	
	public static String uncoverLine(String line) {
	//returns string containing uncovered line
	
		int length = line.length();
		char[] loomy = new char[length];
		
		for (int i = 0; i<length; i++) {
			
			char ch = line.charAt(i);
			
			if (!isValidCharacter(ch)) {
				ch = ' ';
			}
			
			loomy[i] = ch;
		}
		
		String uncovered = new String(loomy);
		return uncovered;
	
	}
	
	/**
		*Determines whether or not character belongs to VALID_CHARACTERS, returns true if it is
		*@param the character being tested against VALID_CHARACTERS
		*@return boolean indicating wheather or not ch is a valid character
	*/	
	
	public static boolean isValidCharacter(char ch) {
		//returns true if char is + / \ ~ X
		
		boolean valid = false;
		
		int numofvalids = VALID_CHARACTERS.length();

		for (int i = 0; i<numofvalids; i++) {
			if (ch == VALID_CHARACTERS.charAt(i)) {
				valid = true;
			}
		}

		return valid;
	}
	
	/**
		*This method returns a random character to the method that calls it
		*@return random character chosen from OBSCURE_CHARACTERS
	*/	
	
	public static char getRandomCharacter() {
		
		//OBSCURE_CHARACTERS = "!@#$%^&*()=";
		
		int lenfiller = OBSCURE_CHARACTERS.length();
		char[] filler = new char[lenfiller];
		
		for (int i = 0; i<lenfiller; i++) {
			filler[i] = OBSCURE_CHARACTERS.charAt(i);
		}
		
		char obscure = filler[RAND.nextInt(lenfiller)];
		
		return obscure;
	}
}