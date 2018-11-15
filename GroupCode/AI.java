/*Name: Dilip Vemuri
 * NETID: Dvemuri3
 * Implements the DecisionMaker interface
 * Generic NPCs wander around and do nothing
 */
//Limited functionality for AI currently. Selects from two possible moves for NPCs to make
//package hw1cs342;
import	 java.util.Random;

public class AI implements DecisionMaker{
	
	public Move getMove(Character c, Place p) {
		//GET RANDOM MOVE: WAIT, GO
		//for go, get a random direction from the current place.
		String npcCommands[] = {"WAIT", "GO"};
		//get random command
		String move = npcCommands[new Random().nextInt(npcCommands.length)];
		String argument = "";
		//if command was GO, get random direction
		if( move.equals("GO")) {
			argument = p.getRandomDirection();
		}
		//put the random move into a move class
		Move tmp = new Move(move, argument);
		return tmp;		
	}

}
