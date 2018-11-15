/*Name: Dilip Vemuri
 * NETID: Dvemuri3
 * Implements decisionmaker
 * Ogres Ai for when he is awake. Can attack players
 */
//package hw1cs342;

import java.util.Random;

public class OgreAwakeAI implements DecisionMaker{

	@Override
	public Move getMove(Character c, Place p) {
		String ogreCommands1[] = {"ROAR", "WAIT"};
		String ogreCommands2[] = {"SMASH", "ROAR"};
		String move;
		if( p.getNumChars() > 1) {
			move = ogreCommands2[new Random().nextInt(ogreCommands1.length)];
		}
		else
			move = ogreCommands1[new Random().nextInt(ogreCommands1.length)];
		
		String argument = "";		
		Move tmp = new Move(move, argument);
		return tmp;		
	}

}
