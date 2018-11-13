/* Franky Zhang
 * This is the artifact class which will get the artifacts from the file
 * and add it to the list of artifacts in the place according to its place ID
 * it also has a method to use an artifact and return artifact info
 */
//package hw1cs342;
import java.util.Scanner;

public class Artifact {
	private String name; //name of the item
	private String description; //A description of the item
	private int value; //variable to hold the value of an artifact
	private int mobility; //movability of an item
	private int keyPattern; //use to determine what keys fit to what doors
	private int id;	
	//constructor
	public Artifact( Scanner s){		
		String line = CleanLineScanner.getCleanLine(s);		
		Scanner test = new Scanner(line);
		int locationID = test.nextInt();
		
		line = CleanLineScanner.getCleanLine(s);
		Scanner test2 = new Scanner(line);
		id = test2.nextInt();
		value = test2.nextInt();
		mobility = test2.nextInt();
		keyPattern = test2.nextInt();
		name = test2.nextLine();
		name = name.trim();
		
		line = CleanLineScanner.getCleanLine(s);
		Scanner test3 = new Scanner(line);
		int numDesc = test3.nextInt();
		description = "";
		for( int i = 0; i < numDesc; i++) {
			description += s.nextLine();
			description += "\n";			
		}		
		//add the artifact to the correct location
		if( locationID < 0) {
			locationID = Math.abs(locationID);
			Character c = Character.getCharacterByID(locationID);
			c.addArtifact(this);
			//add to character
		}
		else if( locationID == 0) {
			//put in random place
			Place p = Place.getRandomPlace();
			//make sure it doesn't go to exit/nowhere
			while( p.getID() == 0 || p.getID() == 1) {
				p = Place.getRandomPlace();
			}
			p.addArtifact(this);
		}
		else {
			Place temp = Place.getPlaceByID(locationID);
			temp.addArtifact(this);
			//add to place
		}
		
		test.close();
		test2.close();
		test3.close();
	
	}
	
	//return the value
	int value() {
		return value;
	}
	
	//return the size
	int size() {
		return mobility;
	}
	
	//return the name
	String name() {
		return name;
	}
	
	//return the description
	String description() {
		return description;
	}
	
	//return the keyPattern
	int getKeyPattern() {
		return keyPattern;
	}
	
	//return the ID
	int id() {
		return id;
	}
	
	//gets the current place and then passes the key to the current places usekey method
	void use(Character c, Place p) {
		if( this.keyPattern <= 0) {
			System.out.println("This item cannot be used");
			return;
		}
		else {
			p.useKey(this);
		}
	}
	
	
	boolean match( String s) {
		if( s.equalsIgnoreCase(name))
			return true;
		
		return false;
	}
	
	//function to display relevant info and one to print all info
	void display() {
		System.out.println(description.trim());		
	}
	
	//print all data
	void print() {
		System.out.println("Name: " + name);
		System.out.println("Description: " + description.trim());
		System.out.println("Value: " + value);
		System.out.println("Mobility: " + mobility);
		System.out.println("KeyPattern: " + keyPattern);
		System.out.println("ID: " + id);
	}

}
