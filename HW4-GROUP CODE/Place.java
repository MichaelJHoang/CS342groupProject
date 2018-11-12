/*
 * Author: Jon-Michael Hoang | jhoang6
 * 
 * CS 342 Term Project: Some Dungeon Game
 * 
 * Computer: Windows 10
 * IDE: Eclipse
 * 
 * File: Place.java
 * 
 */

import java.util.*;

public class Place
{
    private int ID;
    private String name;
    private String desc;
    
    private ArrayList<Direction> directionList = new ArrayList <Direction> ();
    private ArrayList<Artifact> artifactList = new ArrayList <Artifact> ();
    private ArrayList<Character> playerList = new ArrayList <Character> ();
    
    // identify the user's "spawn point"
    private static boolean spawn = true;
    
    // using a hashmap because a place is a place with a unique placeID
    private static HashMap <Integer, Place> placeList = new HashMap <Integer, Place>();
   
    /*
     * 
     * constructor to build the places within the game
     * specified by the characteristics of the given GDF file
     * 
     */
    public Place (Scanner file)
    {
    	// algorithm for making sure everything is
    	// collected and organized
    	
    	// always make sure to clean the line first of any comments
    	String line = CleanLineScanner.getCleanLine(file);
    	Scanner lineScanner = new Scanner (line);
    	ID = lineScanner.nextInt();
    	
    	// skip garbage
    	lineScanner.skip("[ \t]*");
        name = lineScanner.nextLine();
        
        line = CleanLineScanner.getCleanLine (file);
        lineScanner = new Scanner (line);
        int numLines = lineScanner.nextInt();
        desc = "";
        
        // account for the place's description
        for (int x = 0; x < numLines; x++)
        	desc += CleanLineScanner.getCleanLine(file) + "\n";
        
        placeList.put(ID, this);
        
        // identify the spawn point
        if (spawn)
        	spawn = false; // now turn it off
        
        // create the exits and twilight zone
        new Place (1, "EXIT", "THIS IS THE EXIT");
    	new Place (0, "???", "where r u goin lmao");
    	
        return;
    }
    
    /*
     * 
     * Second constructor to make the two places above in
     * the first constructor
     * 
     */
    public Place (int ID, String name, String desc)
    {
    	this.ID = ID;
    	this.name = name;
    	this.desc = desc;

    	placeList.put(ID, this);
    	
    	return;
    }
    
    // simple functions
    public static Place getPlaceByID (int ID) { return placeList.get(ID); }
    
    protected String getName () { return name; }
    
    protected String getDesc () { return desc; }
    
    protected int getID () { return ID; }
    
    protected void addDirection (Direction dir) { directionList.add(dir); return;}
    
    protected void lockDirection (int element) { directionList.get(element).lock(); }
    
    public void addArtifact (Artifact item) { artifactList.add(item); return; }
    
    public void addCharacter (Character name) { playerList.add(name); return; }
    
    public boolean isExit () { return (ID == 0 || ID == 1) ? true : false; }
    
    // goes through the list of artifacts and removes the specified item and has it placed into the
    // user's inventory
    public Artifact removeArtifactByName (String string)
    {
    	for (Artifact item : artifactList)
    	{
    		if (item.match(string))
    		{
    			artifactList.remove(item);
    			return item;
    		}
    	}
    	
    	return null;
    }
    
    public Character removeCharacterByName (String string)
    {
    	for (Character name : playerList)
    	{
    		if (name.match(string))
    		{
    			playerList.remove(name);
    			return name;
    		}
    	}
		return null;
    }
    
    // iterates through the directionList to see if there are any valid directions
    // returns where the iterator points and if nothing good is pointed, return this
    protected Place followDirection (String input)
    {
        for (Direction dir : directionList)
        {
        	if (dir.match(input) && !dir.isLocked())
        		return dir.follow();
        }
        
        System.out.println("LOCKED DIRECTION - PLEASE TRY AGAIN.");
        return this;
    }
    
    // print info
    public void print ()
    {
    	System.out.println("\nPlace ID: " + ID + " - " + name);
    	System.out.println(desc);
    	
    	if (directionList.size() > 0)
    	{
    		System.out.println("This place has the following directions available: ");
    		for (Direction dir : directionList)
    			dir.print();
    	}
    	else
    	{
    		System.out.println("This place has no directions available.");
    	}
    	
    	if (artifactList.size() > 0)
    	{
    		System.out.println("Items available within this place: ");
    		for (Artifact item : artifactList)
    			item.print();
    	}
    }
    
    // print out the list of items in the room
    protected void printAll_artifacts ()
    {
    	System.out.println("Items you see: ");
    	
    	if (artifactList.size() > 0)
			for (Artifact item : artifactList)
			{
				System.out.println(item.name() + " - " + item.description());
			}
    }

    // use the key in every direction available within the room
    protected void useKey (Artifact item)
    {
    	for (Direction iter : directionList) {
    		if( iter.useKey(item)) 
    			return;
    	}
    	System.out.println("Could not unlock any doors here");
    	return;
    }
    
    public static Place getRandomPlace()
    {
    	Random rand = new Random ();
    	
    	do
    	{
    		int decision = rand.nextInt(placeList.values().size());
    		int x = 0;
    		
    		for (Place place : placeList.values())
    		{
    			if (x++ == decision && !place.isExit())
    				return place;
    		}
    	} while (true);
    }
    
    public String getRandomDirection ()
    {
    	Random rand = new Random ();
    	
    	if (directionList.size() <= 0)
    		return "nothing";
    	else
    		return directionList.get(rand.nextInt(directionList.size())).toString();
    }
    
    public String getArtifact ()
    {
    	Random rand = new Random ();
    	
    	if (artifactList.size() <= 0)
    		return "nothing";
    	else
    		return artifactList.get(rand.nextInt(artifactList.size())).toString();
    }
    
	static Place getFirstPlace() {
		return placeList.get(0);
	}
	
	//display relevant information
	void display() {
		System.out.println("Place Name: " + name );
		System.out.println("Place Description:\n" + desc.trim());		
		System.out.println("Items in the room: ");
		for( Artifact a: artifactList){
			System.out.println("\t" + a.name());
		}
		System.out.println("Characters in the room: ");
		for( Character c: playerList) {
			System.out.println("\t" + c.name());
		}
	}

	static void printAll() {
		System.out.println("Write this function");
	}
}
