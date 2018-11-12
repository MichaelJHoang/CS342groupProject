//NPC inherits from Character, sets decisionMaker to AI


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
}
