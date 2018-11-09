/* Franky Zhang
 * This is the artifact class which will get the artifacts from the file
 * and add it to the list of artifacts in the place according to its place ID
 * it also has a method to use an artifact and return artifact info
 */



import java.util.Scanner;

public class Artifact {

	private String name;
	private int value;
	private int size;
	private String description;
	public static int numAdded;
	
	private int ID;
	private Place placeID;
	private int keyPattern;
	
	public Artifact(Scanner s) {
		
		String next = CleanLineScanner.getCleanLine(s);
		
		
		/* This will add read the lines to extract the information for the Artifacts
		 * The first line is the placeID, second line has Artifact ID, value, size, and keyPattern.
		 */
		if(!next.equals("0") && !next.equals(null)) { //must not equal 0 or null
			
			this.placeID = Place.getPlaceByID(Integer.parseInt(next));
			
			next = CleanLineScanner.getCleanLine(s);			        //gets the clean line
			String data[] = next.split(" ", -1);		//splits the line based on spaces
			
			this.ID = Integer.parseInt(data[0]);			//id
			this.value = Integer.parseInt(data[1]);			//value
			this.size = Integer.parseInt(data[2]);			//size
			this.keyPattern = Integer.parseInt(data[3]);	//keypattern
			
			//Get name 
			int i = 4;
			while(i < data.length) {
				if(i == 4) {
					this.name = data[i];
				}
				if(i > 4) {
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
			this.description = description; //adds description to artifact
			numAdded++;						//increment the counter to add indicated artifact was created
			placeID.addArtifact(this);		//adds to the artifact collection of the the placeID Place
		}
	}
	public int value() { //returns the value
		return value;
	}
	public int size() { //returns the mobility 
		return size;
	}
	public int pattern() { //returns the keyPattern 
		return keyPattern;
	}
	public String name() { //returns the name
		return name;
	}
	public String description() { //returns the description
		return description;
	}
	public void use() {	
		Place curr = Game.getCurrentPlace(); //gets current place
		curr.useKey(this); //uses the key
	}
	
	
	//prints the info
	public void print() {
		System.out.println("Name: " + name + " ID: " + 
							ID + " value: " + value + " Mobility: " + size +
							" Description: " + description);
	}
}
