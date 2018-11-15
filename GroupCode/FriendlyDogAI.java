/*Name: Dilip Vemuri
 * NETID: Dvemuri3
 *Implements DecisionMaker
 *Friendly dog follows its master around and attacks his enemies
 */
//package hw1cs342;

import java.util.Iterator;
import java.util.Random;

public class FriendlyDogAI implements DecisionMaker{

	public Move getMove(Character c, Place p) {
		// TODO Auto-generated method stub
		String commands1[] = {"BARK", "WAIT"};
		String move = null;
		String argument = null;
		Player master = ((DOG) c).master();
		//check if master is in same location
		if(p.containsCharacter(master.name())) {
			// everyone who is not Master is an enemy
			if( p.getNumChars() > 2) {
				Iterator<Character> itr = p.getPlayerList();
				Character tmpc;
				while( itr.hasNext()) {
					tmpc = itr.next();
					if( !(tmpc.equals(c)) && !(tmpc.equals(master)) && !(tmpc instanceof Merchant)) {
						move = "ATTACK";
						argument = tmpc.name();
						Move tmp1 = new Move(move, argument);
						return tmp1;
					}
				}				
				move = "WAIT";
			}
			else {
				move = commands1[new Random().nextInt(commands1.length)];
			}
		}
		else {
			move = "FOLLOW";
			argument = master.name();
		}
		
		Move tmp = new Move(move, argument);
		return tmp;
	}

}
