/* Franky Zhang
 * The Game Class asks user to input a command they want the player to do. It will keep looping and 
 * playing the game until player reaches exit or user inputs exit. It also takes in the scanner 
 * to read from a file given by game tester to create the game: sets up all the places, directions, and
 * artifacts. It will also hold an inventory for the player.
 */
import java.util.ArrayList;
import java.util.Scanner;


public class Game {
	private String name;
	private static Place current;		//current Place the user is at
	private int numPlaces;			//num of places
	private int numDir;				//num of directions
	private int numArtf;			//num of artifacts
	private ArrayList<Artifact> artifact = new ArrayList<Artifact>(); //stores user artifacts
	
	public Game(Scanner s) {
		
		
		String next = CleanLineScanner.getCleanLine(s);
		
		this.name = next.substring(8);				//name of the game	
		
		int input = 0;							//used for switch case to determine which type
		
		/* This reads in each line and groups it according to whether it is a place, direction,
		 * or artifact. Then it will have a switch case to add the places, directions, artifacts to
		 * their respective place.
		 */
		while(s.hasNextLine()) {
			
			next = CleanLineScanner.getCleanLine(s);
			if(next.equals(null)) {
				input = 0;
			} else if(next.startsWith("PLACES")) {
				input = 1;
				numPlaces = Integer.parseInt(next.substring(7));		//number of places
			} else if(next.startsWith("DIRECTION")) {
				input = 2;
				numDir = Integer.parseInt(next.substring(11));			//number of directions
			} else if(next.startsWith("ARTIFACTS")) {				
				input = 3;
				numArtf = Integer.parseInt(next.substring(10));			//number of artifacts
			}
			
			switch(input) {
			
			case 1:
				next = s.nextLine();
				while(s.hasNextLine() && Place.numAdded < numPlaces+2) {
					new Place(s);
				}
				input = 0;
			case 2:
				next = s.nextLine();
				while(s.hasNextLine() && Direction.numAdded < numDir) {
					new Direction(s);
				}
				input = 0;
			case 3:
				next = s.nextLine();
				while(s.hasNextLine() && Artifact.numAdded < numArtf) {
					new Artifact(s);
				}
				input = 0;
			default:
				break;
			}
			
		}
		
	}
		
	//returns current place
	public static Place getCurrentPlace() {
		return current;
	}
	
	//print for debugging
	public void print() {
		System.out.println("Game Name: " + name + " current Room: " + current.name() +
						   " Description: " + current.description());
	}
	
	
	/* Starts the Game
	 * Takes in commands from user until user decides he doesn't want to play 
	 * anymore or if character leaves the game. Supports all commands from 3.1
	 */
	public void play() {
		
		System.out.println("\n\t" + name + "\n");		
		
		System.out.println("Enter what you would like to do in the following format:"
				+ "\n1. GO direction 	(Abbreviations or Full Name)"
				+ "\n2. LOOK		(Displays the Artifacts present in the Room)"
				+ "\n3. direction 		(Abbrivations or Full Name)"
				+ "\n4. GET artifact		(Picks up the artifact named)"
				+ "\n5. DROP artifact	(Drops the the artifact named)"
				+ "\n6. USE artifact		(Uses the artifact named in the currentent room)"
				+ "\n7. INVENTORY or INVE	(List the artifacts in your possession and it's values)"	
				+ "\n8. QUIT or EXIT to leave the game");
		
		current = Place.place.get(2);			//starting place
		
		while(true) {
			
			//Checks if the current room is Exit Room if so quits the game
			//else prints the description of the current room
			if(current.name().equalsIgnoreCase("Exit")) {
				System.out.println(current.description());
				System.out.println("Left the game, see you!");
				break;
			} else if (current.name().equalsIgnoreCase("Nowhere")) {
				System.out.println(current.description());
				System.out.println("Nothing can be done, left the game, see you!");
				break;
			} else {
				System.out.println(current.description());
			}
			
			System.out.println("Enter where would you like to go: ");
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();
			String dir;
			String artf;
			input = input.toUpperCase();
			
			//Checks if the user input is QUIT or EXIT 
			if(input.equalsIgnoreCase("QUIT") || input.equalsIgnoreCase("EXIT")) {
				System.out.println("see ya!");
				break;
			} else if (input.startsWith("LOOK")) {
				//looks in the artifacts in the current place
				current.lookArtifact();
								
			} else if (input.startsWith("GO")) {
				//if input starts with go, pass the second parameter as direction
				
				dir = input.substring(input.indexOf(" ") + 1);	
				current = current.followDirection(dir);  //follows that direction
			
			} else if (input.startsWith("GET")) {
				//if there is a get, replace the remaining parameters with the artifact name
				//if artifact is in room pick it up
				artf = input.substring(input.indexOf(" ") + 1);
				Artifact pickup = current.pickArtifact(artf);
				this.artifact.add(pickup);
				
			} else if (input.startsWith("DROP")) {
				//if artifact is in inventory, drop it in current place
				
				artf = input.substring(input.indexOf(" ") + 1);
				if(this.artifact.size() < 1) {
					System.out.println("You don't have anything in your possession");
				}
				int i = 0;
				while(i < this.artifact.size()) {
					if(this.artifact.get(i).name().equalsIgnoreCase(artf)) {
						current.addArtifact(this.artifact.get(i));	 			//adds the artifact to the room
						System.out.println("Dropped " + artf + " in " + current.name());
						this.artifact.remove(i);						//removes the artifact from user inventory
						break;
					} else if (i == this.artifact.size() - 1) {
						System.out.println("You don't have any artifacts called " + artf);
					}
					i++;
				}
				
			} else if (input.startsWith("USE")) {
				//calls use() method on artifact which have a non-zero keyPattern value
				
				artf = input.substring(input.indexOf(" ") + 1);
				if(this.artifact.size() == 0) {
					System.out.println("You don't have anything in your possession");
				}
				
				//loops to find the artifact in user inventory
				//call the use() function to use the artifact if found
				int i = 0;
				while(i < artifact.size()) {
					if(artifact.get(i).name().equalsIgnoreCase(artf)) {
						artifact.get(i).use();
						break;
					} else if (i == artifact.size() -1) {
						System.out.println("\nYou Don't have any artifact called " + artf);
					}
					i++;
				}
				
			} else if (input.equalsIgnoreCase("INVENTORY") ||
						input.equalsIgnoreCase("INVE")) {
				//list the artifacts in the players inventory
				int i = 0;
				while(i < artifact.size()) {
					System.out.println("Name: " + artifact.get(i).name()
									+ " Value: " + artifact.get(i).value()
									+ " Mobility: " + artifact.get(i).size()
									+ " Description: " + artifact.get(i).description());
				    i++;
				}
			} else {
				//if the input is only direction just follows that direction
				current = current.followDirection(input);

			}
		}
	}
}
