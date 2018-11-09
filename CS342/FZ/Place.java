/* Franky Zhang
 * This Place class holds the directions, artifacts, and places. It has methods that help the place class
 * navigate like following a direction and handling artifacts such as picking up keys and other loot.
 */


import java.util.ArrayList;
import java.util.Scanner;

public class Place {

	private int iD;
	private String name;
	private String description;
	public static int numAdded;
	private static boolean nowhere = false;
	private static boolean exit = false;
	private ArrayList<Direction> direction = new ArrayList<Direction>();  //stores all directions in a place
	private ArrayList<Artifact> artifact = new ArrayList<Artifact>();  //stores all artifacts in a place
	public static ArrayList<Place> place = new ArrayList<Place>();	//stores all the places in game
	
	public Place (Scanner s) {
		
		String next = CleanLineScanner.getCleanLine(s);
		if(!next.equals("0") && !next.equals(null)) {
			// This will read and add the lines for places that are not exit or nowhere 
			String[] data = next.split(" ", -1);
			try {
				this.iD = Integer.parseInt(data[0]);		//get the ID
			} 
			catch (NumberFormatException nfe){
				
			}
			
			//Gets the name of the place
			int i = 1;
			while(i < data.length) {
				if(i == 1) {
					this.name = data[i];
				} 
				else if(i > 1){
					this.name += data[i];	
				}
				
				this.name += " ";
				i++;
			}
			this.name = this.name.trim();
			
			//Gets Description
			String descLine = s.nextLine();			//gets number of lines of description
			int num = Integer.parseInt(descLine);
			String description = null;
			i = 0;
			while(i < num) {                       //gets description 
				descLine = s.nextLine();
				if(i == 0) {
					description = descLine;
				} else {
					description += descLine;
				}
				description += "\n";
				i++;
			}
			this.description = description; //adds description to place
			place.add(this); //adds to place array
			numAdded = place.size();
		}
		else if(next.equals("0") || next.equals(null)){
			//add nowhere location
			if(!nowhere) {
				this.iD = 0;
				this.name = "Nowhere";
				this.description = "You are now in the middle of nowhere.";
				place.add(this);
				nowhere = true;
			//add exit location
			} else if(!exit && nowhere) {
				this.iD = 1;
				this.name = "Exit";
				this.description = "Goodbye, you have found the exit";
				place.add(this);
				exit = true;
			}
		}
		
	}
	
	/* This function is used to get Place from ID. 
	 * Returns the Place that has the same ID as the ID sent in.
	 */
	public static Place getPlaceByID(int iD) {
		int i = 0;
		while(i < place.size()) {
			int idget = place.get(i).iD;
			//checks every id in array list and matches it with id given in, if matches, return place
			if(idget == Math.abs(iD)) {
				return place.get(i);
			}
			i++;
		}
		return null;
	}
	
	
	/* Uses the key on all the directions in this place by 
	 * calling useKey in direction.
	 */
	public void useKey(Artifact key) {
		int i = 0;
		while(i < this.direction.size()) {
			direction.get(i).useKey(key);
			i++;
		}
	}
	
	// Prints out all the artifacts in the current room
	public void lookArtifact() {
		
		if(this.artifact.size() == 0) {
			System.out.println("There are no artifacts in this room");
		} else {
			int i = 0;
			while(i < this.artifact.size()) {
				this.artifact.get(i).print();
				i++;
			}
		}
	}
	
	
	/* Takes in a String for name of the artifact and searches through the
	 * artifact array list to look for it. if it finds the artifact it
	 * returns the artifact back to Game where it will be added to the 
	 * user's inventory, and removes the artifact from the place's collection
	 * of artifacts.
	 */
	public Artifact pickArtifact(String a) {
		Place curr = Game.getCurrentPlace();
		Artifact pick;
		int i = 0;
		while (i < curr.artifact.size()) {
			if(curr.artifact.get(i).name().equalsIgnoreCase(a)) {
				pick = curr.artifact.get(i);
				curr.artifact.remove(i);
				System.out.println(pick.name() + " picked up");
				return pick;
			} else if(i == curr.artifact.size() - 1) {
				System.out.println("There is no artifact called " + a + " in this room");
				return null;
			}
			i++;
		}
		
		System.out.println("There are no artifacts in this room");
		return null;
	}
	
	//returns the name
	public String name() {
		return name;
	}
	
	//returns the description
	public String description() {
		return description;
	}
	
	//adds direction
	public void addDirection(Direction d) {
		direction.add(d); 	
	}
	
	//adds artifacts 
	public void addArtifact(Artifact a) {
		artifact.add(a);
	}
	
	//follows the direction the user wants to go, if locked it will return back to the current place
	public Place followDirection(String dir) {
		
		for(int i = 0; i < direction.size(); i++) {
			if(direction.get(i).match(dir)) {			//check if direction is valid
				return direction.get(i).follow();		//returns new room if unlocked, else old
			}
		}
		System.out.println("This direction is invalid."); //if not valid will output
		return this;
	}
	
	//prints info for debugging
	public void print() {
		System.out.print("\nID: " + iD + "\nName: " + name + "\nDesc: " + description);
		
	}
	
}
