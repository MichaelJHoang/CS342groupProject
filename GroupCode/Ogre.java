/*Name: Dilip Vemuri
 * NETID: Dvemuri3
 * Ogre is an extension of NPC. has two decision makers: OgreAsleepAI and OgreAwakeAI
 * HMW4: Added in hmw4. The ogre is a type of npc with two states: awake and asleep
 * It starts off asleep and can be awaken as the game is played. Once awake, it can attack Players
 * It will not leave its starting area
 */

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
	
	
	@Override
	// Ogres can take damage and eventually die
	public void takeDamage(int x) {
		health -= x;
		System.out.println(this.name() + " Took " + x + " damage! Remaining hp: " + this.health());
		//check if health goes below 0
		if( health <= 0) {
			System.out.println(this.name() + " has been slain!!");
			Game.removeNPC(this);
			currPlace.removeCharacterByName(this.name());
			characters.remove(this);
		}
	}
	
	// functions to switch the Decision maker of the Ogre dynamically
	private void awakenOgre() {
		decisionMaker = new OgreAwakeAI();
	}
	
	// the ogre can sense people while sleeping
	private void executeSense() {
		if( currPlace.getNumChars() > 1) {
			System.out.println("The ogre sensed someone in his lair ! He comes to life!");
			this.awakenOgre();
			this.executeRoar();
		}
	}
	
	// the ogre can snore while asleep
	private void executeSnore() {
		System.out.println("The ogre lets out a loud snore, indicating he is sound asleep");
	}
	
	// the ogre can let out a roar while asleep
	private void executeRoar() {
		System.out.println("The Ogre let's out a deafening roar, letting all know he is angry");
	}
	
	// the ogre can attack people in the room. Damage based on the attack stat
	private void executeSmash() {
		// get every character in the room
		System.out.println("The Ogre flails around wildly, hitting everybody in the room!");
		Iterator<Character> test = this.currPlace.getPlayerList();
		int numPlayers = currPlace.getNumChars();
		Character[] hitlist = new Character[numPlayers];
		int i = 0;
		while( test.hasNext()) {
			Character c = test.next();
			if(c instanceof Player)
			{
				hitlist[i] = c;
				i++;
			}				
		}
		for(int j = 0; j < i; j++) {
			hitlist[j].takeDamage(this.attack());
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
