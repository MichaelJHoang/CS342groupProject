//name: Dilip Vemuri
//ACCC: dvemuri3
//Game class is here to play the game. It has a collection of places and items in it
//This class contains the play function, which actual executes the playing of the game
//package hw1cs342;

import java.util.Scanner;
import java.util.Vector;

public class Game {

	private String name;
	private static int numPlayers = 0;
	private static int numOfCharacters = 0;
	//collection of characters (separate for NPC and Player?)
	private static Vector<Character> players = new Vector<Character>();
	private static Vector<Character> npcs = new Vector<Character>();
	
	
	public Game( Scanner s, int nChars) {
        String line = CleanLineScanner.getCleanLine(s);
        Scanner test = new Scanner(line);
        String p = test.next();
        //verify File type, version and then store name into game name
        if( ! "GDF".equalsIgnoreCase(p)) {
        	System.out.println("INCORRECT FILE FORMAT, NEEDS GDF");
        }
        if( test.nextDouble() != 4.0) {
        	System.out.println("INCORRECT FILE VERSION, NEEDS 4.0");
        }
        test.skip(" ");
        name = test.nextLine();
        System.out.println( name);
        //-------------------------------PLACES---------------------------------------------
        //get the next clean line, should be places followed by number
        line = CleanLineScanner.getCleanLine(s);
		Scanner test2 = new Scanner(line);
		p = test2.next();
		int numPlaces = test2.nextInt();
		//loop for the number of places
		for( int i = 0; i < numPlaces; i++) {
			Place newPlace = new Place(s);
		}
		//manually code in exit and nowhere
		Place exit = new Place(1, "EXIT", "Go here to exit the game");
		Place noWhere = new Place(0, "NOWHERE", "you're not supposed to be here...");
		//-------------------------------Directions-----------------------------------------
		line = CleanLineScanner.getCleanLine(s);
		Scanner test3 = new Scanner( line);
		p = test3.next();
		int numDirec = test3.nextInt();		
		for( int i = 0; i < numDirec; i++){
			new Direction(s);
		}
		//-------------------------------Character-----------------------------------------
		line = CleanLineScanner.getCleanLine(s);
		Scanner test5 = new Scanner(line);
		p = test5.next();
		int numCharacters = test5.nextInt();
		numOfCharacters = numCharacters;
		Character ch;
		//repeat for number of characters
		for( int i = 0; i < numCharacters; i++) {
			//check NPC vs Player
			line = CleanLineScanner.getCleanLine(s);
			Scanner tmp = new Scanner(line);
			p = tmp.next();
			int placeID = tmp.nextInt();
			//add to NPC or PLAYER
			if( p.equalsIgnoreCase("NPC")) {
				ch = new NPC(s, placeID);
				npcs.addElement(ch);
			}
			else if( p.equalsIgnoreCase("PLAYER")) {
				ch = new Player(s, placeID);
				players.addElement(ch);
				numPlayers++;
			}			
			tmp.close();
		}
		//if ncharactesr from command line is > than num characters from file
		if( nChars > numCharacters) {
			int id = 1000;
			int difference = nChars - numCharacters;
			for( int i = 0; i < difference; i++) {
				String name = "Generic Player";
				String idAsString = Integer.toString(id);
				name += idAsString;
				ch = new Player( id, name , "Manually created player character", Place.getFirstPlace().getPlaceID() );
				id++;
				players.addElement(ch);
				numPlayers++;
				numOfCharacters++;
			}
		}
		//if num of characters from file was 0
		else if( numCharacters == 0) {
			//create one character
			ch = new Player( 1, "Generic Player", "Manually created player character", Place.getFirstPlace().getPlaceID());
			players.addElement(ch);
			numPlayers++;
			numOfCharacters++;
		}
		//-------------------------------Artifacts-----------------------------------------
		line = CleanLineScanner.getCleanLine(s);
		Scanner test4 = new Scanner(line);
		p = test4.next();
		int numArtifacts = test4.nextInt();
		for( int i = 0; i < numArtifacts; i++) {
			new Artifact( s);
		}
		System.out.println("Num characters: " + numOfCharacters + " Num players: " + numPlayers);
        test.close();
        test2.close();
        test3.close();
        test4.close();
        test5.close();
	}
	
	//4 functions to add and remove players/npcs (used for EXIT function)
	static public void addPlayer(Character c) {
		players.addElement(c);
		numPlayers++;
		numOfCharacters++;
	}
	
	
	static public void removePlayer(Character c) {
		players.remove(c);
		numPlayers--;
		numOfCharacters--;
	}
	
	
	static public void addNPC(Character c) {
		npcs.addElement(c);
		numOfCharacters++;
	}
	
	
	static public void removeNPC(Character c) {
		npcs.remove(c);
		numOfCharacters--;
	}	
	
	//prints the relevant data
	void print() {
		System.out.println("Game Name:" + name );
		System.out.println("Games players ");		
		for(Character c: players) {
			System.out.println(c.name());
		}
		System.out.println("Games npcs: ");
		for(Character c: npcs) {
			System.out.println(c.name());
		}
		Place.printAll();
	}
	
	//Loops through all characters and calls their makeMove method
	void play() {
		while( players.size() > 0 ) {
			for(int i = 0; i < players.size(); i++) {
				Character c = players.get(i);
				c.display();
				c.makeMove();
				System.out.println();
			}
			//Npcs turn
			System.out.println("---------Please wait while NPC's make their move---------");
			for( Character c: npcs) {
				System.out.println("NPC " + c.name() + "'s turn");
				c.makeMove();
			}
		}
		System.out.println("Exiting game...");
	}

}
