/*Name: Dilip Vemuri
 * NETID: Dvemuri3
 *Implements DecisionMaker
 *EnemyDogAI is one of two AI types for the DOG class
 *enemy dogs growl and wander around the map
 */
//package hw1cs342;

import java.util.Random;

public class EnemyDogAI implements DecisionMaker{

	public Move getMove(Character c, Place p) {
		// enemy dog should GROWL, GO
		String commands[] = {"GROWL", "GO", "WAIT"};
		
		String move = commands[new Random().nextInt(commands.length)];
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
