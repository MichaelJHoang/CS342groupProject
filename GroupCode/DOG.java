/*Name: Dilip Vemuri
 * NETID: Dvemuri3
 * DOG is an an extension of NPC
 * HMW4: added in hmw4. DOGs start off aggressive to players but will not attack
 * If players use PET dogname , the dog will become friendly, follow the player, and attack
 * the players enemies. 
 */
//package hw1cs342;

import java.util.Scanner;

public class DOG extends NPC{

	Player master = null;	// a player for the dog to follow once he turns friendly
	public DOG(int _id, String _name, String desc, int placeID) {
		super(_id, _name, desc, placeID);
		decisionMaker = new EnemyDogAI();
		attack = 5;
		health = 80;
	}
	
	public DOG(Scanner s, int placeID) {
		super(s, placeID);
		decisionMaker = new EnemyDogAI();
		attack = 5;
		health = 80;
	}
	
	// getter
	public Player master() {
		return master;
	}
	
	// if the Dog receives PETs from a player character, he will turn friendly to that character
	// dog changes ai
	public void turnFriendly(Player c) {
		
		if( master != null) {
			System.out.println("This dog appreciates the pats, but he already has a master");
			return;
		}
		System.out.println("The dog really loved those pets! he will follow you around now!");
		master= c;
		decisionMaker = new FriendlyDogAI();		
	}
	
	//follow a direction
	private void executeGo(String s) {
		currPlace.removeCharacterByName(this.name());
		currPlace = currPlace.followDirection( s);
		currPlace.addCharacter(this);
		if( currPlace.getName().equalsIgnoreCase("exit")){
			this.executeExit();
		}
	}
	
	//Typing exit removes the player from the game
	private void executeExit() {
		//remove the character from GAME, PLACE, AND CHARACTER
		System.out.println("Character " + name + " has exited the game");
		Game.removePlayer(this);
		currPlace.removeCharacterByName(this.name());
		currPlace = Place.getPlaceByID(1);
		characters.remove(this);
	}
	
	// the dog with growl at people it doesnt like
	private void executeGrowl() {
		System.out.println(this.name() + " Lets out an aggressive growl");
	}
	
	// playful bark for friendly npcs
	private void executeBark() {
		System.out.println(this.name() + " Lets out a friendly bark");
	}
	
	// dog can attack enemies of its master
	private void executeAttack(String s) {
		Character c = Character.getCharacterByName(s);
		if( c == null || c.getCurrPlace() != this.getCurrPlace()) {
			System.out.println("attack failed");			
		}
		else {
			System.out.println(this.name() + " Attacked " + c.name() + " !!");
			c.takeDamage(this.attack());
		}				
	}
	
	// dog can pass its turn when nothing to do
	private void executeWait() {
		System.out.println(this.name() + " lazes around...");
	}
	
	// dog will follow its master where he goes
	private void executeFollow() {
		currPlace.removeCharacterByName(this.name());
		currPlace = master.getCurrPlace();
		currPlace.addCharacter(this);
		System.out.println(this.name() + " Followed his master to " + this.getCurrPlace().getName());
	}
	
	// gets the move from the DecisionMaker and uses a switch statement to execute it
	public void makeMove() {
		// when ogre is asleep, he just senses movement
		boolean moveMade = false; //boolean to check if a move that ends the turn was made
		Move tmp;
		while( !moveMade) {
			tmp = decisionMaker.getMove(this, this.currPlace);			
			switch( tmp.type()) {
				case GO:
					this.executeGo(tmp.argument());
					moveMade = true;
					break;				
				case GROWL:
					this.executeGrowl();
					moveMade = true;
					break;
				case BARK:
					this.executeBark();
					moveMade = true;
					break;
				case ATTACK:
					this.executeAttack(tmp.argument());
					moveMade = true;
					break;
				case WAIT:
					this.executeWait();
					moveMade = true;
					break;
				case FOLLOW:
					moveMade = true;
					this.executeFollow();
					break;
				default:
					moveMade = true;
					System.out.println("default DOG action");
			}
		}		
	}

}
