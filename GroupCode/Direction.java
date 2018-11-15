/*
 * Author: Jon-Michael Hoang | jhoang6
 * 
 * CS 342 Term Project: Some Dungeon Game
 * 
 * Computer: Windows 10
 * IDE: Eclipse
 * 
 * File: Direction.java
 * 
 */
//package hw1cs342;
import java.util.*;

public class Direction
{
    private int ID, lockPattern;
    private Place from;
    private Place to;
    private dirType type;
    private boolean lock;
    
    
    /*
     * 
     * Allow for enumerated directions so that there wont be any restricted
     * directional input
     * 
     */
    private enum dirType
    {
    	NONE ("None", "None"),
    	NORTH ("North", "N"), SOUTH ("South", "S"), EAST ("East", "E"), WEST ("West", "W"),
    	U ("Up", "U"), D ("Down", "D"),
    	NE ("Northeast", "NE"), NW ("Northwest", "NW"),
    	SE ("Southeast", "SE"), SW ("Southwest", "SW"),
    	NNE ("North-Northeast", "NNE"), NNW ("North-Northwest", "NNW"),
    	ENE ("East-Northeast", "ENE"), WNW ("West-Northwest", "WNW"),
    	ESE ("East-Southeast", "ESE"), WSW ("West-Southwest", "WSW"),
    	SSE ("South-Southeast", "SSE"), SSW ("South-Southwest", "SSW");
    	
    	private String full, abbr;
    	
    	dirType (String full, String abbr)
    	{
    		this.full = full;
    		this.abbr = abbr;
    	}
    	
    	public boolean match (String string)
    	{
    		if (this == NONE)
    			return false;
    		
    		string = string.trim();
    		
    		return string.equalsIgnoreCase(full) || string.equalsIgnoreCase(abbr);
    	}
    	
	    //function to return text field
		public String toString() {
			return full;
		}
    }
    
    /*
     * 
     * Direction constructor - take in the file, parse it as much as needed and
     * voila.
     * 
     */
    public Direction (Scanner file)
    {
        String line = CleanLineScanner.getCleanLine(file);
        Scanner lineScanner = new Scanner (line);
        
        ID = lineScanner.nextInt();
        
        int sourceID = lineScanner.nextInt();
        from = Place.getPlaceByID (sourceID); 
        
        String direction = lineScanner.next();
        
        // default direction type
        type = dirType.NONE;
        
        // match direction types
        for (dirType t : dirType.values())
        {
        	if (t.match(direction))
        	{
        		type = t;
        		break;
        	}
        }
        
        int destinationID = lineScanner.nextInt();
        
        // lock based on destID
        lock = (destinationID <= 0) ? true : false;
        destinationID = (lock) ? -destinationID : destinationID;
        
        // destination from this place to wherever is next
        to = Place.getPlaceByID (destinationID);
        
        lockPattern = lineScanner.nextInt();
        // assign lock pattern
        lockPattern = lockPattern > 0 ? lockPattern : 0;
        
        from.addDirection(this);
        
        lineScanner.close();
        return;
    }
    
    // make sure that the destination isn't locked
    // return to current place if it is
    // otherwise take them to the next dest
    public Place follow () { return (lock) ? from : to; }
    
    // use the key on all directions within the place
    boolean useKey (Artifact item)
    {
    	if ( item.getKeyPattern() == this.lockPattern) {
    		lock = !lock;
    		System.out.println("Unlocked the " + type.toString() );
    		return true;
    	}
    	
    	return false;
    }
    
    // simple functions
    public boolean match (String string) { return type.match(string); }
    
    protected void lock () { lock = true; }
    
    protected void unlock () { lock = false; }
    
    protected boolean isLocked () { return lock; }
    
    // prints info
    protected void print ()
    {
        System.out.println("Direction " + ID + " travels " + type + " from " +
                           from.getName() + " to " + to.getName() + ",\nand is " +
        		           (lock ? "locked." : "not locked.") +
        		           " Whose lock pattern is " + lockPattern);
    }
}
