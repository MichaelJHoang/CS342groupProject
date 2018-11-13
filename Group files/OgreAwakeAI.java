//package hw1cs342;

import java.util.Random;

public class OgreAwakeAI implements DecisionMaker{

	@Override
	public Move getMove(Character c, Place p) {
		String npcCommands[] = {"ROAR", "SMASH", "WAIT"};
		//get random command
		String move = npcCommands[new Random().nextInt(npcCommands.length)];
		String argument = "";		
		// ogre smash ?
		// roar?
		Move tmp = new Move(move, argument);
		return tmp;		
	}

}
