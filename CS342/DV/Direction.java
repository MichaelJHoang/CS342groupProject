//name: Dilip Vemuri
//ACCC: dvemuri3
//Direction class: The direction class is what links rooms together
//The direction class also has the ability to be locked and require certain items in order to pass
//package hw1cs342;

import java.util.Scanner;

public class Direction {	
	private enum DirType{
		N("North", "N"), E("East", "E"), S( "South", "S"), W("West", "W"), U( "Up", "U"), D("Down","D"),
		NE("North-East", "NE"), NW("North-West", "NW"), SE("South-East", "SE"), SW("South-West", "SW"),
		NNE("North-NorthEast", "NNE"), NNW("North-NorthWest", "NNW"), ENE("East-NorthEast", "ENE"),
		WNW("West-NorthWest", "WNW"), ESE("East-SouthEast", "ESE"), WSW("West-SouthWest", "WSW"),
		SSE("South-SouthEast", "SSE"), SSW("South-SouthWest", "SSW");
		//the directions text
		private String text;
		//the directions abbreviation
		private String abbreviation;
		//constructor 
		DirType( String text, String abbreviation){
			this.text = text;
			this.abbreviation = abbreviation;
		}
	    //function to return text field
		public String toString() {
			return text;
		}
		//return the abbreviation
		public String getAbbrev() {
			return abbreviation;
		}
		//boolean function to see if given text matches
		boolean match(String s) {
			//check if it matches the text or abbreviation
			if( s.equalsIgnoreCase(text) || s.equalsIgnoreCase(abbreviation))
				return true;
			return false;
		}		
		
	}//end DirType...
	
	private int directionID;
	private Place placeFrom;
	private Place placeTo;
	private DirType direction;
	private boolean locked;
	private int lockPattern;
	
	//constructor
	public Direction( Scanner s) {
		String line = CleanLineScanner.getCleanLine(s);
		//System.out.println(line);
		Scanner test = new Scanner(line);		
		directionID = test.nextInt();		
		int placeID = test.nextInt();		
		placeFrom = Place.getPlaceById( placeID);		
		String dir = test.next();
		direction = DirType.valueOf(dir);
		
		placeID = test.nextInt();
		if( placeID <= 0) {
			placeID = Math.abs( placeID);
			locked = true;
		}
		else
			locked = false;
		
		placeTo = Place.getPlaceById( placeID);		
		lockPattern = test.nextInt();		
		//do this last
		placeFrom.addDirection(this);
		test.close();
	}
	
	//see if a passed string matches the direction
	boolean match( String s){
		//call DirType match
		return direction.match(s);
	}
	
	//lock
	void lock( ){
		locked = true;
	}
	
	//unlock
	void unlock( ){
		locked = false;
	}
	
	//check if locked
	boolean isLocked( ) {
		return locked;
	}
	
	//follow the direction to a certain place, as long as its unlocked
	Place follow() {
		if( !locked)
			return placeTo;
		else {
			System.out.println("Door is locked...");
			return placeFrom;
		}
			
	}
	
	//function to use the key in a certain direction. checks the lock and key pattern
	boolean useKey(Artifact a) {
		if( a.getKeyPattern() == this.lockPattern ) {
			this.unlock();
			return true;
		}		
		return false;
	}
	
	//get the direction in string form
	String getDirection() {
		return this.direction.toString();
	}
	
	//display info in game friendly manner
	void display() {
		System.out.println("This direction leads to " + placeTo.name());
		if( this.isLocked())
			System.out.println("This direction is currently locked");
	}
	
	//print relevant information
	void print() {
		System.out.println("Direction ID:" + directionID );
		System.out.println("Place from: " + placeFrom.name());
		System.out.println("Place to: " + placeTo.name());
		System.out.println("Direction: " + direction.toString());
		System.out.println("Lock Pattern: " + lockPattern);
		if( locked)
			System.out.println("Locked.");
		else
			System.out.println("Unlocked.");
	}

}
