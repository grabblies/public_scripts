/**
*tests methods in Hangman.java
*@author Bryan Davis
**/

public class HangmanTest {
	
	/**
	*calls other testing methods
	**/
	public static void main(String [] args) {
      	testCorrectGuess();
		testIsGameLost();
		testIsGameWon();
		testGetVisiblePhrase();
		testGetCurrentCategory();
		testGetCurrentPhrase();
   }
	   
	/**
	*tests correct guess method
	**/
	public static void testCorrectGuess() {
   	Hangman h = new Hangman(0);		
        h.newGame();		
        boolean isCorrectGuess = h.isCorrectGuess('E');		
        System.out.println("Expected: true  Actual: " + isCorrectGuess);
		
        isCorrectGuess = h.isCorrectGuess('a');		
        System.out.println("Expected: true  Actual: " + isCorrectGuess);

        isCorrectGuess = h.isCorrectGuess('x');		
        System.out.println("Expected: false  Actual: " + isCorrectGuess);

        isCorrectGuess = h.isCorrectGuess('w');		
        System.out.println("Expected: true  Actual: " + isCorrectGuess);
		
        isCorrectGuess = h.isCorrectGuess('M');		
        System.out.println("Expected: false  Actual: " + isCorrectGuess);

        isCorrectGuess = h.isCorrectGuess('I');		
        System.out.println("Expected: false  Actual: " + isCorrectGuess);
		System.out.println("");
		
	Hangman two = new Hangman(0);
		two.newGame();
        isCorrectGuess = two.isCorrectGuess('I');		
        System.out.println("Expected: false  Actual: " + isCorrectGuess);

        isCorrectGuess = two.isCorrectGuess('g');		
        System.out.println("Expected: false  Actual: " + isCorrectGuess);		

        isCorrectGuess = two.isCorrectGuess('w');		
        System.out.println("Expected: true  Actual: " + isCorrectGuess);

        isCorrectGuess = two.isCorrectGuess('s');		
        System.out.println("Expected: true  Actual: " + isCorrectGuess);		
   }
   
	/**
	*tests isGameLost method
	**/
	public static void testIsGameLost() {
		Hangman test = new Hangman(0);
		test.isCorrectGuess('z');
		test.isCorrectGuess('z');
		test.isCorrectGuess('z');
		test.isCorrectGuess('z');
		test.isCorrectGuess('z');
		test.isCorrectGuess('z');
		System.out.println("1. Is game lost? Should be: true \nActual: " + test.isGameLost());
		System.out.println();
		
		test.newGame();
		test.isCorrectGuess('s');
		test.isCorrectGuess('a');
		test.isCorrectGuess('i');
		test.isCorrectGuess('z');
		test.isCorrectGuess('z');
		test.isCorrectGuess('z');
		System.out.println("2. Is game lost? Should be: false \nActual: " + test.isGameLost());
		System.out.println();		
		
		test.newGame();
		test.isCorrectGuess('f');
		test.isCorrectGuess('w');
		test.isCorrectGuess('z');
		test.isCorrectGuess('x');
		test.isCorrectGuess('w');
		test.isCorrectGuess('z');
		System.out.println("3. Is game lost? Should be: true \nActual: " + test.isGameLost());
		System.out.println();
		
	}
	
	/**
	*tests isGameWon method
	**/	
	public static void testIsGameWon() {
		Hangman test = new Hangman(0);
		test.newGame();
		test.isCorrectGuess('z');
		test.isCorrectGuess('z');
		test.isCorrectGuess('z');

		System.out.println("Is game won? Should be: false \nActual: " + test.isGameWon());
		System.out.println();	
		
		test.isCorrectGuess('t');
		test.isCorrectGuess('h');
		test.isCorrectGuess('e');
		test.isCorrectGuess('l');
		test.isCorrectGuess('a');
		test.isCorrectGuess('s');
		test.isCorrectGuess('r');
		test.isCorrectGuess('w');

		System.out.println("Is game won? Should be: true \nActual: " + test.isGameWon());
		System.out.println();	
		
	}
	
	/**
	*tests getVisiblePhrase method
	**/	
	public static void testGetVisiblePhrase() {
		Hangman test = new Hangman(0);
		test.newGame();

		test.isCorrectGuess('t');
		test.isCorrectGuess('h');
		test.isCorrectGuess('e');
		test.isCorrectGuess('l');
		test.isCorrectGuess('a');
		test.isCorrectGuess('s');
		test.isCorrectGuess('r');
		test.isCorrectGuess('w');
		
		System.out.println("Should be: T H E   L A S T   S T R A W  \nIs: " + test.getVisiblePhrase());
	}
	
	/**
	*tests getCurrentCategory method
	**/
	public static void testGetCurrentCategory() {
		Hangman test = new Hangman(0);
		test.newGame();	
		System.out.println("Should be: PHRASES  \nIs: " + test.getCurrentCategory());
		
	
	}
	
	/**
	*tests getCurrentPhrase method
	**/	
	public static void testGetCurrentPhrase() {
		Hangman test = new Hangman(0);
		test.newGame();	
		System.out.println("Should be: THE LAST STRAW  \nIs: " + test.getCurrentPhrase());	
	}
   
}