/* Franky Zhang
 * This direction class holds the enum dirType for the different directions.
 * It also gets directions from a file and interprets them and puts them in their
 * proper place. Also handles the usekey function which unlocks doors with a key artifact.
 */


import java.util.Scanner;


public class Direction {

	private int iD;
	private Place from;
	private Place to;
	private DirType dir;
	private int lockPattern;
	public static int numAdded;
	
	
	private enum DirType {
		NONE("None", "NONE"),
		NORTH("North", "N"),
		SOUTH("South", "S"),
		EAST("East", "E"),
		WEST("West", "W"),
		UP("Up", "U"),
		DOWN("Down", "D"),
		NORTHEAST("Northeast", "NE"),
		NORTHWEST("Northwest", "NW"),
		SOUTHEAST("Southeast", "SE"),
		SOUTHWEST("Southwest", "SW"),
		NORTHNORTHEAST("North-Northeast", "NNE"),
		NORTHNORTHWEST("North-Northwest", "NNW"),
		EASTNORTHEAST("East-Northeast", "ENE"),
		WESTNORTHWEST("West-Northwest", "WNW"),
		EASTSOUTHEAST("East-Southeast", "ESE"),
		WESTSOUTHWEST("West-Southwest", "WSW"),
		SOUTHSOUTHEAST("South-Southeast", "SSE"),
		SOUTHSOUTHWEST("South-Southwest", "SSW");
		
		private final String text;
		private final String abbr;
		
		private DirType(String text, String abbr) {
			this.text = text;
			this.abbr = abbr;
		}
	
		//returns the text version of the direction
		public String toString() {
			return this.text;
		}
		
		//if the user direction matches the text or abbr returns true
		public boolean match (String s) {
			
			if(this.text.equalsIgnoreCase(s) || this.abbr.equalsIgnoreCase(s)) 
				return true;

			return false;
			
		}
		
		//converts the String direction read in from the file to DirType
		public static DirType convert (String s) {
			for(DirType dir : DirType.values()) {
				if(dir.text.equalsIgnoreCase(s) || dir.abbr.equalsIgnoreCase(s)) {
					return dir;
				}
			}
			
			return DirType.NONE;
			
		}
	}
	
	public Direction (Scanner dir_scn) {
		
		
		String next = CleanLineScanner.getCleanLine(dir_scn);
		
		if(!next.equals("0") && !next.equals(null)) {
			// This will read in the lines in the file by the scanner for direction.
			String[] data = next.split(" ", -1);
			this.iD = Integer.parseInt(data[0]); //direction id
			this.from = Place.getPlaceByID(Integer.parseInt(data[1])); //gets the place from iD using the function in Place
			this.dir = DirType.convert(data[2]);		//converts the string to DirType
			this.to = Place.getPlaceByID(Integer.parseInt(data[3])); //gets the place to iD using the function in Place
			this.lockPattern = Integer.parseInt(data[4]); //lock pattern
			
			if(Integer.parseInt(data[3]) <= 0) {
				this.iD = this.iD * -1;  //changes id to negative if locked
			}
			
			numAdded++;				//increment to indicate direction was created
			from.addDirection(this); //add direction to place
		}
		
	}
	
	
	/* Checks if the lockPattern and keyPattern match and if they do 
	 * it will unlock or lock the direction.
	 */
	public void useKey(Artifact artifact) {
		
		if(artifact.pattern() == lockPattern) {
			iD = iD * -1;
			
			if(iD > 0) {
				System.out.println("A Room was Unlocked\n");
			} 
			else if (iD < 0){
				System.out.println("A Room was Locked\n");
			}
		}
		
	}
	
	//calls match() in enum DirType 
	public boolean match(String s) {
		return dir.match(s);
	}
	
	//checks if the room is locked or not
	public boolean isLocked() {
		
		if(iD < 0) {
			return true; //locked
		}
		return false; //unlocked
	}

	
	//checks if place is unlocked, if it is go to new place, if not stay at old place
	public Place follow() {
		//if place is locked return;
		if(this.isLocked()) {
			System.out.println("Room is Locked!");
			return from;
		} else {
			//if place is unlocked go to new place
			return to;
		}
		
	}
	
	
	//print info for debugging
	void print() {
		System.out.print("iD: " + iD + " From: " + from.name() + " To: " + to.name() +
						" Direction: " + dir.toString() + " Lock Pattern: " + lockPattern);
	}

}
