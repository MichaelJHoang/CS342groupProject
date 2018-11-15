/*Name: Dilip Vemuri
 * NETID: Dvemuri3
 * NPC inherits from player
 * HMW4: NPC has been extended by 3 new classes: OGRE, MERCHANT, DOG 
 * A generic NPC type that uses the generic AI still exists
 */
//package hw1cs342;

import java.util.Scanner;

public class NPC extends Character{
	//extended constructors
	public NPC(int _id, String _name, String desc, int placeID) {
		super(_id, _name, desc, placeID);
		decisionMaker = new AI();
	}
	
	public NPC(Scanner s, int placeID) {
		super(s, placeID);
		decisionMaker = new AI();
	}
	
	public void makeMove() {
		return;
	}
}
