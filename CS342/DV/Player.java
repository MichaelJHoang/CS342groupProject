//Name:Dilip Vemuri
//NETID:dvemuri3
//Player inherits from Character, sets DecisionMaker to UI
//package hw1cs342;

import java.util.Scanner;

public class Player extends Character{
	//extended constructor
	public Player(int _id, String _name, String desc, int placeID) {
		super(_id, _name, desc, placeID);
		decisionMaker = new UI();
	}
	
	public Player(Scanner s, int placeID) {
		super(s, placeID);
		decisionMaker = new UI();
	}


}
