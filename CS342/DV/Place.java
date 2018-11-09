//name: Dilip Vemuri
//ACCC: dvemuri3
//Place class: The place class represents different rooms in the game world
//It contains passages to other rooms and also contains items
//package hw1cs342;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Place {
	private int placeID;
	private String placeName;
	private String placeDescription;
	private Vector<Direction> directions;
	private Vector<Artifact> items;
	private Vector<Character> characters; 	//collection of characters in each place
	private static Vector<Place> places = new Vector<Place>(); 	//static collection of all places
	
	//Old constructor. Still used to code exit and nowhere 
	public Place( int ID, String name, String description) {
		placeID = ID;
		placeName = name;
		placeDescription = description;
		directions = new Vector<Direction>();
		items = new Vector<Artifact>();
		characters = new Vector<Character>();
		places.addElement(this);
	}	
	
	//Constructor using scanner
	public Place( Scanner s) {
		String line = CleanLineScanner.getCleanLine(s);
		Scanner scan = new Scanner(line);
		placeID = scan.nextInt();
		placeName = scan.nextLine();
		placeName = placeName.trim();
		//get another line
		line = CleanLineScanner.getCleanLine(s);
		Scanner get = new Scanner(line);
		int numDescLines = get.nextInt();
		placeDescription = "";
		for( int i = 0; i < numDescLines; i++) {
			placeDescription += s.nextLine();
			placeDescription += "\n";			
		}
		directions = new Vector<Direction>();
		items = new Vector<Artifact>();
		characters = new Vector<Character>();
		if( placeID >= 2)
			places.addElement(this);
		
		get.close();
	}
	
	//return the name
	String name( ) {
		return placeName;
	}
	
	//return description of the place
	String description() {
		return placeDescription;
	}
	
	//return placeID
	int getPlaceID() {
		return placeID;
	}
	
	//add a direction to a place
	void addDirection( Direction d){
		this.directions.addElement( d);
	}	
	
	//function to add an artifact to a certain place
	void addArtifact( Artifact a) {
		items.addElement(a);
	}
	
	//remove an artifact by name
	Artifact removeArtifactByName( String s) {
		for( int i = 0; i < items.size(); i++) {
			if( s.equals(items.get(i).name())) {
				return items.remove(i);
			}
		}
		System.out.println("That item does not exist here...");
		return null;
	}
	
	//function to add a character to a place
	void addCharacter( Character c) {
		characters.addElement(c);
	}
	
	//remove a character
	void removeCharacter( Character c) {
		characters.remove(c);
	}
	
	//display relevant information
	void display() {
		System.out.println("Place Name: " + placeName );
		System.out.println("Place Description:\n" + placeDescription.trim());		
		System.out.println("Items in the room: ");
		for( Artifact a: items){
			System.out.println("\t" + a.name());
		}
		System.out.println("Characters in the room: ");
		for( Character c: characters) {
			System.out.println("\t" + c.name());
		}
	}
	
	//follows a direction by calling direction methods
	Place followDirection( String s) {
		//check all directions follow it
		for( Direction d : directions) {
			if( d.match( s)) 
				return d.follow();	
				
		}	
		System.out.println("Not a valid direction...");
		return this;
	}
	
	//function to get a certain place by the ID number
	static Place getPlaceById( int ID) {
		for( Place p : places) {
			if (p.placeID == ID) {
				return p;
			}
		}
		//else doesn't exist
		//System.out.println("Place does not exist");
		return null;		
	}
	
	//passes to the direction use key method
	void useKey(Artifact a) {
		for( Direction d : directions) {
			if( d.useKey(a)) {
				System.out.println("You unlocked the " + d.getDirection());
				return;				
			}				
		}
		//not able to unlock anything
		System.out.println(a.name() + " has no effect in this room...");		
	}	
	
	//print relevant class information
	void print() {
		System.out.println("Place ID: " + placeID );
		System.out.println("Place Name:" + placeName );
		System.out.println("Place Description:" + placeDescription);
		for( Direction d: directions){
			d.print();
		}
		for( Artifact a: items){
			a.print();
		}
		for( Character c: characters) {
			c.print();
		}
	}
	
	//prints all the places
	static void printAll() {
		for( Place p: places) {
			p.print();
			System.out.println("----------------------------------------------------");
		}
	}
	
	//gets a random place. used in a few constructors and AI
	static Place getRandomPlace() {
		return places.get(new Random().nextInt(places.size()));
	}
	
	//used to get the first place in the Vector list
	static Place getFirstPlace() {
		return places.elementAt(0);
	}
	
	//use in the AI method to pick a random valid direction
	String getRandomDirection() {
		Direction tmp = directions.get(new Random().nextInt(directions.size()));
		return tmp.getDirection();
	}

}
