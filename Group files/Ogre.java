//package hw1cs342;

import java.util.Iterator;
import java.util.Scanner;

public class Ogre extends NPC{
	// all Ogres have 500 hp	
	//ogres start off with the asleepAI
	public Ogre(int _id, String _name, String desc, int placeID) {
		super(_id, _name, desc, placeID);
		decisionMaker = new OgreAsleepAI();
		health = 500;
		attack = 10;
		defense = 5;
	}
	
	public Ogre(Scanner s, int placeID) {
		super(s, placeID);
		decisionMaker = new OgreAsleepAI();
		health = 500;
		attack = 10;
		defense = 5;
	}
	
	// functions to switch the Decision maker of the Ogre dynamically
	private void awakenOgre() {
		decisionMaker = new OgreAwakeAI();
	}
	
	private void sedateOgre() {
		decisionMaker = new OgreAsleepAI();
	}
	
	private void executeSense() {
		if( currPlace.getNumChars() > 1) {
			System.out.println("The ogre sensed someone in his lair ! He comes to life!");
			this.awakenOgre();
			this.executeRoar();
		}
	}
	
	private void executeSnore() {
		System.out.println("The ogre lets out a loud snore, idicating he is sound asleep");
	}
	
	
	private void executeRoar() {
		System.out.println("The Ogre let's out a deafening roar, letting all know he is angry");
	}
	
	
	private void executeSmash() {
		// get every character in the room
		// deal 20 damage to their hp
		System.out.println("The Ogre flails around wildly, hitting everybody in the room!");
		Iterator<Character> test = currPlace.getPlayerList();
		while( test.hasNext()) {
			Character c = test.next();
			if( c.equals(this)) {
				continue;
			}
			if(c instanceof Player)
				c.takeDamage(10);
		}
	}
	
	// the ogre waits
	private void executeWait() {
		//do nothing
		System.out.println( this.name() + " passes their turn");
	}
	
	//make move function for ogre
	@Override
	public void makeMove() {
		// when ogre is asleep, he just senses movement
		boolean moveMade = false; //boolean to check if a move that ends the turn was made
		Move tmp;
		while( !moveMade) {
			tmp = decisionMaker.getMove(this, this.currPlace);			
			switch( tmp.type()) {
				case SENSE:
					this.executeSense();
					moveMade = true;
					break;
				case SNORE:
					this.executeSnore();
					moveMade = true;
					break;
				case WAIT:
					this.executeWait();
					moveMade = true;
					break;
				case ROAR:
					this.executeRoar();
					moveMade = true;
					break;
				case SMASH:
					this.executeSmash();
					moveMade = true;
					break;
				default:
					moveMade = true;
					System.out.println("default ogre action");
			}
		}		
	}

}
