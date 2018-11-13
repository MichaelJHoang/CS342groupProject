//Implements DecisionMaker class
//Asks user for move and encapsulates the move into in a type of Move
//package hw1cs342;
import java.util.Scanner;


public class UI implements DecisionMaker{
	
	public Move getMove(Character c, Place p) {	
		//ask user for move
		System.out.println("Enter a command: ");
		Scanner s = KeyboardScanner.getKeyboardScanner();
		String line = s.nextLine();
		Scanner lineS = new Scanner(line);
		//parse through the line
		//write a static move function, is valid command. pass first argument into there. 
		String move = lineS.next();
		//check if valid move
		if(!Move.moveContains(move)) {
			move = "NONE";
		}
		//make sure there is a next word
		String argument = "";
		if( lineS.hasNext()) {
			argument = lineS.nextLine();			
		}		
		argument = argument.trim();

		Move tmp = new Move( move, argument);
		lineS.close();
		return tmp;
	}

}
