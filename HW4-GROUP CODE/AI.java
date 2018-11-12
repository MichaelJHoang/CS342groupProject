//implements decisionMaker class
//Limited functionality for AI currently. Selects from two possible moves for NPCs to make
import java.util.Random;

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
		//hard code for ogre to wait in the room all game.
		if( c.name().equals("The Ugly Green Ogre")) {
			move = "WAIT";
			argument = "";
		}
		//put the random move into a move class
		Move tmp = new Move(move, argument);
		return tmp;		
	}

}
