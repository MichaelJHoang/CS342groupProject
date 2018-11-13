//package hw1cs342;

import java.util.Random;

public class OgreAsleepAI implements DecisionMaker{

	@Override
	public Move getMove(Character c, Place p) {
		String npcCommands[] = {"SNORE", "SENSE"};
		//get random command
		String move = npcCommands[new Random().nextInt(npcCommands.length)];
		// if ogre is asleep he just senses the room
		System.out.println("The ogre is asleep...but he is easily disturbed");
		Move tmp = new Move(move, "");
		return tmp;
	}

}
