import java.util.*;
import java.io.*;

/**
*model for game of Hangman
*@author Bryan Davis
**/

public class Hangman {

	private static final int NUMBER_OF_CLUES = 125;
	private static final int NUMBER_OF_GUESSES = 6;
	
	/**
	*this serves as a list of all the possible clues that can be used by the computer to play Hangman
	**/
	private Clue[] clues;

	/**
	*An integer that stores the index into the Clue array identifying the Clue being used in the current game
	**/
	private int clueNum;

	/**
	*A String representing an obscured version of the phrase being guessed.
	**/
	private String obscured;

	/**
	*An integer that stores the current number of incorrect guesses made by the player
	**/
	private int numIncorrect;

	/**
	*A Random object that will generate random integers to index into the Clue array
	**/
	private Random rand;

	/**
	*This is the constructor of the class. 
	**/	
	public Hangman() {
		processFile();
		obscured = "";
		numIncorrect = 0;
		rand = new Random();
		clueNum = 0; // should start off at 0?
	}
	
	/**
	*This is the constructor used for testing the Hangman class. 
	**/	
	public Hangman(int seed) {
		this();
		rand.setSeed(seed);
	}

	/**
	*processes the clues.txt file that contains the category and phrase pairs that make up a Clue 
	**/	
	private void processFile() {
		try {
			clues = new Clue[NUMBER_OF_CLUES];		
			Scanner fileScan = new Scanner(new File("clues.txt"));
			for (int i = 0; i < NUMBER_OF_CLUES; i++) {
				clues[i] = processLine(fileScan.nextLine());
				//System.out.println(clues[i]);
			}
			//System.out.println(clues.length);
			
		} catch (FileNotFoundException e) {
			System.exit(1);
		}
	}
	
	/**
	*The processLine method is a private method that tokenizes a line from the 
	*clues.txt file and returns a Clue object.
	@param line from clues.txt
	@return Clue from clues.txt
	**/		
	private Clue processLine(String line) {
	
		Scanner clueScan = new Scanner(line);
		clueScan.useDelimiter(":");
		String category = clueScan.next();
		String phrase = clueScan.next();
		Clue fatty = new Clue(category, phrase);
		return fatty;
	}
	
	/**
	*The newGame() method sets up the instance variables of the class for a new game.
	**/		
	public void newGame() {
		clueNum = rand.nextInt(NUMBER_OF_CLUES);
		obscured = "";
		numIncorrect = 0;
		generateClue();
	}

	/**
	*The generateClue() private method gets called any time a new game is started.
	**/		
	private void generateClue() {
		for (int i = 0; i < clues[clueNum].getPhrase().length(); i ++) {
			if (clues[clueNum].getPhrase().charAt(i) == '\'') {
			obscured += "'";
			}
			else if (clues[clueNum].getPhrase().charAt(i) == ' ') {
			obscured += " ";
			}
			else {
			obscured += "_";
			}
		}
	}
	
	/**
	*The isCorrectGuess() method receives a letter guessed by the player. 
	*The method returns true if the phrase contains the guessed letter and false if it does not. 
	*@param letter guess from user
	*@return boolean whether or not it is in the phrase
	**/		
	public boolean isCorrectGuess(char letter) {
		char capLetter = Character.toUpperCase(letter);
		boolean truefalse = false;
		for (int i = 0; i < clues[clueNum].getPhrase().length(); i++) {
			if (clues[clueNum].getPhrase().charAt(i) == capLetter) {
				obscured = obscured.substring(0,i) + Character.toUpperCase(letter) + obscured.substring(i+1);
				//System.out.println(getVisiblePhrase());
				truefalse = true;
			}
		}
		if (truefalse == false) {
			numIncorrect++;
		}
		return truefalse;
	}
	
	/**
	*returns true if the user has lost the game by making 6 incorrect guesses.
	*returns false if the player has not yet made 6 incorrect guesses.
	*@return true/false Boolean whether player has lost or not
	**/	
	public boolean isGameLost() {
		if (numIncorrect == NUMBER_OF_GUESSES) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	*returns true if the user has won the game by guessing all of the correct letters. 
	*Otherwise, this method returns false.
	*@return boolean true/false whether the player has won or not
	**/		
	public boolean isGameWon() {
		//System.out.println("obscured: " + obscured);
		//System.out.println("phrase: " + getCurrentPhrase());
		if (obscured.equals(getCurrentPhrase())) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	*The getVisiblePhrase() method returns a version of the obscured phrase that's ready for display in the GUI. 
	*@return String with spaced underscores
	**/	
	public String getVisiblePhrase() {
	String visible = "";
	for (int n = 0; n < obscured.length(); n++) {
		visible += obscured.substring(n,n+1)+" ";
	}
	return visible;
	}


	/**
	*The getCurrentCategory method returns the category of the current Clue object being used in the Hangman game.
	**/		
	public String getCurrentCategory() {
		return clues[clueNum].getCategory();
	}
		
	
	/**
	*The getCurrentPhrase method returns the phrase of the current Clue object being used in the Hangman game.
	**/	
	public String getCurrentPhrase() {
		return clues[clueNum].getPhrase();
	}
	
}