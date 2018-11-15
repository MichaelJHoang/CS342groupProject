/*Name: Dilip Vemuri
 * NETID: Dvemuri3
 * Player is an extension of Character. Player decision maker is set by UI
 * HMW4: Implemented new stats (carrweight,hp,gold), implemented new commands
 * like PET ,TRADE, and ATTACK. Players and can now recieve damage and DIE
 */
//package hw1cs342;
import java.util.Scanner;

public class Player extends Character{	
	// new fields
	static final int maxHp = 100;
	static final int maxWeight = 100;
	private int carryWeight = 0; // add up mobilites of inventory
	private boolean overEncumbered = false;

	
	public Player(int _id, String _name, String desc, int placeID) {
		super(_id, _name, desc, placeID);
		decisionMaker = new UI();
		health = maxHp;
		gold = 10;
		attack = 5;
		defense = 0;
		players.addElement(this);
	}
	
	public Player(Scanner s, int placeID) {
		super(s, placeID);
		// default hp for players is 100
		health = maxHp;
		gold = 100;
		decisionMaker = new UI();
		attack = 5;
		defense = 0;
		players.addElement(this);
	}	
	// the player add artifact method should increase the carryweight of the player
	// if the player has too many things, they will be over encumbered
	@Override
	void addArtifact( Artifact a) {
		inventory.addElement(a);
		carryWeight += a.size();
	}
	
	// Player can take damage and die
	@Override
	public void takeDamage(int x) {
		health -= x;
		System.out.println(this.name() + "Took 10 damage! Remaining hp: " + this.health());
		//check if health goes below 0
		if( health <= 0) {
			System.out.println(this.name() + " died!!");
			this.executeExit();
		}
	}
	
	// function to heal players
	@Override
	public void heal(int x) {
		health += x;
		if( health > maxHp) {
			health = maxHp;
		}
	}
	
	// returns the max hp constant of a player
	public int maxHp() {
		return maxHp;
	}
	
	// returns the current carryWeight of player
	public int carryWeight() {
		return carryWeight;
	}
	
	// returns the maxWeight of a player
	public int maxWeight() {
		return maxWeight;
	}	
	
	// Player can drop an artifact in an area
	void dropArtifact( Artifact art) {
		//remove item for characters inventory
		String name = art.name();
		if( inventory.size() == 0) {
			System.out.println("No items to drop");
			return;
		}
		int bool = 0;
		for( Artifact a: inventory){
			if(a.name().equals(name.trim())) {
				currPlace.addArtifact(a);
				inventory.remove(a);
				// decrease carryweight
				carryWeight -= a.size();
				if( carryWeight < 0) {
					carryWeight = 0;
				}
				bool = 1;
				break;
			}
		}		
		if(bool == 0) {
			System.out.println("Item does not exist");
		}
	}
	
	//function to check if a player is overencumbered or not
	private void checkCarryWeight() {
		if( carryWeight > maxWeight)
			overEncumbered = true;
		else
			overEncumbered = false;
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
	
	//use an item, currently only applies to keys
	private void executeUse(String s) {
		int bool = 0;
		for(Artifact a: inventory) {
			if( a.name().equals(s.trim())) {
				a.use(this, currPlace);
				bool = 1;
			}
		}
		if( bool == 0)
			System.out.println("Item does not exist...");		
	}
	
	//Typing exit removes the player from the game
	private void executeExit() {
		//remove the character from GAME, PLACE, AND CHARACTER
		System.out.println("Character " + name + " has exited the game");
		Game.removePlayer(this);
		currPlace.removeCharacterByName(this.name());
		currPlace = Place.getPlaceByID(1);
		characters.remove(this);
		players.remove(this);
	}
	
	//look at the players inventory
	private void executeInventory() {
		System.out.println(this.name() + "'s inventory: ");
		for( Artifact a: this.inventory) {
			System.out.println("\t" + a.name());
		}
		System.out.println("Carry weight: " + carryWeight + "/" + maxWeight);
	}
	
	//drop item from inventory into the room
	private void executeDrop(String s) {
		//remove item for characters inventory
		int bool = 0;
		for( Artifact a: inventory){
			if(a.name().equals(s.trim())) {
				this.dropArtifact(a);
				bool = 1;
				break;
			}
		}		
		if(bool == 0) {
			System.out.println("Item does not exist");
		}
	}
	
	//get an item in the room, as long as it passes the mobility check
	private void executeGet(String s) {
		s = s.trim();
		Artifact temp = currPlace.removeArtifactByName(s);
		if( temp == null) {
			return;
		}
		//check if it is mobile
		if( temp.size() < 0) {
			System.out.println("This item is too heavy to pick up");
			currPlace.addArtifact(temp);
			return;
		}
		this.addArtifact(temp);
	}
	
	//look around the room
	private void executeLook( ) {
		currPlace.display();		
	}
	
	//this function is public so an npc can wait
	private void executeWait() {
		//do nothing
		System.out.println( this.name() + " passes their turn");
	}
	
	// get the name of the character from the user
	// see if they are in same location
	// open a "trade window" function that will execute the rest	
	private void executeTrade(String s) {
		Character tmp = Character.getCharacterByName(s);
		// make sure character exists and is int he same location
		if(tmp == null) return;
		if(tmp != null && tmp.getCurrPlace().equals(this.getCurrPlace())) {
			if(tmp instanceof Merchant) {
				// call the trade window function, probably from merchant class
				((Merchant) tmp).tradeWindow(this);
				return;
			}
			else {
				System.out.println("Character is not a merchant. Cannot be traded with");
				return;
			}
		}
		System.out.println("That character doesn't exist in this area");		
	}
	
	//PET  and DOG type NPC
	private void executePet(String s) {
		Character tmp = Character.getCharacterByName(s);
		if(tmp instanceof DOG) {
			System.out.println("You pet the dog");
			((DOG) tmp).turnFriendly(this);
		}
		else
			System.out.println("You can only PET dogs");		
		return;
	}
	
	// Attack another player/npc. Dogs/merchants cant be attacked
	private void executeAttack( String s) {
		Character tmp = Character.getCharacterByName(s);
		if( tmp == null) {
			return;
		}
		else if( tmp.equals(this)) {
			System.out.println("Don't hit yourself...");
			return;
		}
		else if( tmp instanceof Merchant || tmp instanceof DOG) {
			System.out.println("You can't attack merchants or dogs...");
			return;
		}
		else if( !(tmp.getCurrPlace().equals(this.currPlace))){
			System.out.println("Character is not in the same location");
			return;
		}		
		System.out.println(this.name() + " Attacked " + tmp.name() + " !!");
		tmp.takeDamage(this.attack());
	}
	
	// a command to show other commands
	private void executeCMD() {
		System.out.println("A List of valid moves: ");
		System.out.println("\tGO XXX");
		System.out.println("\tUSE XXX");
		System.out.println("\tINVENTORY");
		System.out.println("\tEXIT");
		System.out.println("\tDROP XXX");
		System.out.println("\tGET XXX");
		System.out.println("\tDROP XXX");
		System.out.println("\tLOOK");
		System.out.println("\tWAIT");
		System.out.println("\tTRADE XXX");
		System.out.println("\tPET XXX");
		System.out.println("\tATTACK XXX");
	}
	//A brunt of the gameplay. Asks the decision maker for a Move class object
	//Then, uses a switch statement to execute the desired move with the desired arguments if necessary.
	public  void makeMove() {
		boolean moveMade = false; //boolean to check if a move that ends the turn was made
		Move tmp;
		while( !moveMade) {
			tmp = decisionMaker.getMove(this, this.currPlace);
			this.checkCarryWeight();	
			// if you are overencumbered, only allowed moves are drop, inventory and look
			if( overEncumbered && ( !tmp.type().name().equals("DROP") && !tmp.type().name().equals("INVENTORY") 
						&& !tmp.type().name().equals("LOOK") && !tmp.type().name().equals("WAIT"))){
				System.out.println("You are over encumbered! please DROP an item before making a move!");
				continue;				
			}
			switch( tmp.type()) {
				case GO:
					executeGo( tmp.argument());
					System.out.println(this.name() + " is now in " + currPlace.getName());
					moveMade = true;
					break;
				case USE:
					executeUse(tmp.argument());
					moveMade = true;
					break;
				case EXIT:
					executeExit();
					moveMade = true;
					break;
				case INVENTORY:
					executeInventory();
					break;
				case DROP:
					executeDrop(tmp.argument());
					break;
				case GET:
					executeGet(tmp.argument());
					break;
				case LOOK:
					executeLook();
					break;
				case WAIT:
					executeWait();
					moveMade = true;
					break;
				case TRADE:
					executeTrade(tmp.argument());
					moveMade = true;
					break;
				case PET:
					executePet(tmp.argument());
					moveMade = true;
					break;
				case NONE:
					System.out.println("Not a valid move");
					break;
				case ATTACK:
					this.executeAttack( tmp.argument());
					moveMade = true;
					break;
				case QUIT:
					System.out.println("Closing the game...");
					System.exit(0);
				case CMD:
					this.executeCMD();
					break;
				default:
					System.out.println("Default");			
			}//end switch
		}// end while loop				
	}


}
