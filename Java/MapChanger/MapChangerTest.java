/**
	*Unit tester for MapChanger class
*/

public class MapChangerTest {

  public static void main(String[] args) {
  
  
    boolean isValid = MapChanger.isValidCharacter('X');
    System.out.println("Expected: true\t Actual: " + isValid);
    
    isValid = MapChanger.isValidCharacter('x');
    System.out.println("Expected: false\t Actual: " + isValid);
	//Add 5 more test cases here for isValidCharacter method
	
    System.out.print("Is \\ a valid character? \nExpected:	true\nActual answer:	"+MapChanger.isValidCharacter('\\')+"\n");
	System.out.print("Is X a valid character? \nExpected:	true\nActual answer:	"+MapChanger.isValidCharacter('X')+"\n");
	System.out.print("Is / a valid character? \nExpected:	true\nActual answer:	"+MapChanger.isValidCharacter('/')+"\n");
	System.out.print("Is ~ a valid character? \nExpected:	true\nActual answer:	"+MapChanger.isValidCharacter('~')+"\n");
	System.out.print("Is + a valid character? \nExpected:	true\nActual answer:	"+MapChanger.isValidCharacter('+')+"\n");
    
	System.out.print("Is r a valid character? \nExpected:	false\nActual answer:	"+MapChanger.isValidCharacter('r')+"\n");
	System.out.print("Is 4 a valid character? \nExpected:	false\nActual answer:	"+MapChanger.isValidCharacter('4')+"\n");
	System.out.print("Is ( a valid character? \nExpected:	false\nActual answer:	"+MapChanger.isValidCharacter('(')+"\n");
	System.out.print("Is = a valid character? \nExpected:	false\nActual answer:	"+MapChanger.isValidCharacter('=')+"\n");
	System.out.print("Is ! a valid character? \nExpected:	false\nActual answer:	"+MapChanger.isValidCharacter('!')+"\n");
	
    //tests for getRandomCharacter
	char[] answers = new char[50];	
	System.out.println("Should have these characters: !@#$%^&*()=");
	
	for (int i = 0; i<50; i++) {
		answers[i] = MapChanger.getRandomCharacter();
		System.out.print(answers[i]);
	}
	System.out.println();
	
	
	//tests for uncoverLine
    String line = MapChanger.uncoverLine("++@#$%~~~/&*");
    System.out.println("Expected: \"++    ~~~/  \"");
    System.out.println("Actual:   \"" + line + "\"");
    
    //Add 5 more test cases here for uncoverLine method
	
	System.out.print("Expected:	\"   +++ ~~~       \" \nActual:		\""+MapChanger.uncoverLine("!@#$+++%~~~^&*()=")+"\"\n");
	System.out.print("Expected:	\"///////////\" \nActual:		\""+MapChanger.uncoverLine("///////////")+"\"\n");
	System.out.print("Expected:	\"   +++   \" \nActual:		\""+MapChanger.uncoverLine("===+++===")+"\"\n");
	System.out.print("Expected:	\"\\\\//~~    \" \nActual:		\""+MapChanger.uncoverLine("\\\\//~~=%$#")+"\"\n");
	System.out.print("Expected:	\"~               \" \nActual:		\""+MapChanger.uncoverLine("~*&^@$%@#)$_@)()")+"\"\n");
  }
}
