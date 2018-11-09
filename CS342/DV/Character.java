//Name:Dilip Vemuri
//NETID:dvemuri3
//This class is a type of character. It can be either an NPC or player. 
//Players use UI method of DecisionMaker and NPCs use AI
//AI for now have limited functionality, Players can do everything that was available to them in HW2
//package hw1cs342;

import java.util.Scanner;
import java.util.Vector;

public class Character {
	
	private int ID;
	private String name;
	private String description;
	private Place currPlace;
	protected DecisionMaker decisionMaker;
	private Vector<Artifact> inventory;
	private static Vector<Character> characters = new Vector<Character>();
	
	//constructors
	public Character(Scanner s, int placeID) {
		String line = CleanLineScanner.getCleanLine(s);	
		Scanner test = new Scanner(line);
		ID = test.nextInt();
		name = test.nextLine();
		name = name.trim();
		
		line = CleanLineScanner.getCleanLine(s);
		Scanner test2 = new Scanner(line);
		int numDesc = test2.nextInt();
		
		description = "";
		for( int i = 0; i < numDesc; i++) {
			description += s.nextLine();
			description += "\n";			
		}
		//add character to proper place, 0 being random
		Place temp;
		if( placeID == 0) {
			temp = Place.getRandomPlace();
			//make sure it doesnt go to exit/nowhere
			while( temp.getPlaceID() == 0 || temp.getPlaceID() == 1) {
				temp = Place.getRandomPlace();
			}			
			currPlace = temp;
			temp.addCharacter(this);
		}
		else {
			temp = Place.getPlaceById(placeID);
			currPlace = temp;
			temp.addCharacter(this);			
		}
		inventory = new Vector<Artifact>();
		//add to the collection of characters in Character class
		characters.addElement(this);		
	}
	
	//second constructor
	public Character(int _id, String _name, String desc, int placeID) {
		ID = _id;
		name = _name;
		description = desc;
		Place tmp = Place.getPlaceById(placeID);
		currPlace = tmp;
		tmp.addCharacter(this);
		inventory = new Vector<Artifact>();
		characters.addElement(this);
	}
	
	//function to get a character by their ID
	public static Character getCharacterByID( int id) {
		for( Character c : characters) {
			if (c.ID == id) {
				return c;
			}
		}
		//else doesn't exist
		System.out.println("Character does not exist");
		return null;	
	}
	
	//function to get the location of a character
	public Place getCurrPlace() {
		return currPlace;
	}
	
	//add an artifact to a characters inventory
	void addArtifact( Artifact a) {
		inventory.addElement(a);
	}	
	
	//get the name
	public String name() {
		return name;
	}
	
	//display game friendly information
	public void display() {
		System.out.println("----------------------NEW TURN----------------------");
		System.out.println(name + "'s Turn");
		currPlace.display();		
	}
	
	//print all info
	public void print() {
		System.out.println("Character Name: " + name);
		System.out.println("Character ID: " + ID);
		System.out.println("Character Description: " + description.trim());
		System.out.println("Character Location: " + currPlace.name());
		System.out.println("Inventory: ");
		for(Artifact a: inventory) {
			System.out.println( a.name());
		}
	}
	
	//print all the characters in the game
	static void printAll() {
		for(Character c: characters) {
			c.print();
			System.out.println();
		}
	}	
	
	//follow a direction
	private void executeGo(String s) {
		currPlace.removeCharacter(this);
		currPlace = currPlace.followDirection( s);
		currPlace.addCharacter(this);
		if( currPlace.name().equalsIgnoreCase("exit")){
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
		currPlace.removeCharacter(this);
		currPlace = Place.getPlaceById(1);
		characters.remove(this);
	}
	
	//look at the players inventory
	private void executeInventory() {
		System.out.println(this.name() + "'s inventory: ");
		for( Artifact a: this.inventory) {
			System.out.println( a.name());
		}
	}
	
	//drop item from inventory into the room
	private void executeDrop(String s) {
		//remove item for characters inventory
		if( inventory.size() == 0) {
			System.out.println("No items to drop");
			return;
		}
		int bool = 0;
		for( Artifact a: inventory){
			if(a.name().equals(s.trim())) {
				currPlace.addArtifact(a);
				inventory.remove(a);	
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
		this.inventory.addElement( temp);
	}
	
	//look around the room
	private void executeLook( ) {
		currPlace.display();		
	}
	
	//this function is public so an npc can wait
	public void executeWait() {
		//do nothing
		System.out.println( this.name() + " passes their turn");
	}
	//A brunt of the gameplay. Asks the decision maker for a Move class object
	//Then, uses a switch statement to execute the desired move with the desired arguments if necessary.
	void makeMove() {
		boolean moveMade = false; //boolean to check if a move that ends the turn was made
		Move tmp;
		while( !moveMade) {
			tmp = decisionMaker.getMove(this, this.currPlace);
			switch( tmp.type()) {
				case GO:
					executeGo( tmp.argument());
					System.out.println(this.name() + " is now in " + currPlace.name());
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
				case NONE:
					System.out.println("Not a valid move");
					break;
				case QUIT:
					System.out.println("Closing the game...");
					System.exit(0);
				default:
					System.out.println("Default");			
			}//end switch
		}//end while loop		
	}
	

}
