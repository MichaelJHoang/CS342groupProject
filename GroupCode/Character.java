//Dilip Vemuri
//Netid : dvemuri3
//This class is a type of character. It can be either an NPC or player. 
//Players use UI method of DecisionMaker and NPCs use AI
//AI for now have limited functionality, Players can do everything that was available to them in HW2
//package hw1cs342;

import java.util.Scanner;
import java.util.Vector;

public abstract class Character {
	
	private int ID;
	protected String name;
	private String description;
	protected Place currPlace;
	protected DecisionMaker decisionMaker;
	protected Vector<Artifact> inventory;
	protected static Vector<Character> characters = new Vector<Character>();
	protected static Vector<Player> players = new Vector<Player>();
	//new fields
	protected int health = 0;	// default = 0;
	protected int luck = 5;		// starting luck of 5 for all characters
	protected int gold = 0;		// default = 0;
	protected int attack = 0;	// default = 0
	protected int defense = 0;	//default = 0
	
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
			while( temp.getID() == 0 || temp.getID() == 1) {
				temp = Place.getRandomPlace();
			}			
			currPlace = temp;
			temp.addCharacter(this);
		}
		else {
			temp = Place.getPlaceByID(placeID);
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
		Place tmp = Place.getPlaceByID(placeID);
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
	
	// add an artifact to a characters inventory\
	void addArtifact( Artifact a) {
		inventory.addElement(a);
	}
	
	// remove artifact by name
    public Artifact removeArtifactByName (String string)
    {
    	for (Artifact item : inventory)
    	{
    		if (item.match(string))
    		{
    			inventory.remove(item);
    			return item;
    		}
    	}    	
    	return null;
    }
    
    // remove a certain amount of gold
    public void removeGold(int x) {
    	gold -= x;
    	System.out.println("After remove gold: " + this.name() + " has " + this.gold());
    }
	
    //add a certain amount of gold
    public void addGold(int x) {
    	gold += x;
    	System.out.println("After add gold: " + this.name() + " has " + this.gold());
    }
    
	//get the name
	public String name() {
		return name;
	}
	
	// get health
	public int health() {
		return health;
	}
	
	// get luck
	public int luck() {
		return luck;
	}
	
	// get gold
	public int gold() {
		return gold;
	}
	
	// get attack
	public int attack() {
		return attack;
	}
	
	// get defense
	public int defense() {
		return defense;
	}
	
	//display game friendly information
	public void display() {
		System.out.println("----------------------NEW TURN----------------------");
		System.out.println(name + "'s Turn");
		System.out.println("Remaining HP: " + this.health() + "  Gold: " + this.gold() + 
				" Carrweight: " + ((Player) this).carryWeight() + "/" + ((Player)this).maxWeight());
		currPlace.display();
		System.out.println( "Type CMD to get a list of commands");
	}
	
	//print all info
	public void print() {
		System.out.println("Character Name: " + name);
		System.out.println("Character ID: " + ID);
		System.out.println("Character Description: " + description.trim());
		System.out.println("Character Location: " + currPlace.getName());
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
	
	public static int getNumPlayers() {
		return players.size();
	}

	//A brunt of the gameplay. Asks the decision maker for a Move class object
	//Then, uses a switch statement to execute the desired move with the desired arguments if necessary.
	public abstract void makeMove();
	
	// match
	public boolean match (String string) { return string.trim().equalsIgnoreCase(name); }
	
	// most NPCs cant take damage
	public void takeDamage(int x) {
		System.out.println(this.name() + " cant take damage");		
	}
	
	// only player's can heal
	public void heal(int x) {
		System.out.println(this.name() + " cant be healed");		
	}
	
	// helper function
	static public Character getCharacterByName(String s) {
		for( Character c: characters) {
			if(c.name().equals(s)) {
				return c;
			}
		}
		System.out.println("Character does not exist");
		return null;
	}
	
	

}
