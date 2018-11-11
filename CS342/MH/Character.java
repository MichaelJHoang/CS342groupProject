import java.util.*;

public class Character 
{
	private int ID;
	private String name, desc;
	private Place currentPlace;
	
	private static HashMap <Integer, Character> characterMap = new HashMap <Integer, Character>();
	protected static int numPlayers = 0;
	
	private ArrayList <Artifact> inv;
	
	protected decisionMaker decision;
	protected Place currentPlace;
	protected boolean isPlaying;
	
	public Character (Scanner file, int ver, int placeID)
	{
		if (ver != 40)
		{
			System.err.println("Error: Cannot make a character");
			System.exit(-1);
		}
		
		if (placeID > 0)
			currentPlace = Place.getPlaceByID(placeID);
		else if (placeID == 0)
			currentPlace = Place.getRandomPlace();
		else
		{
			System.err.println("Error: Cannot make a character");
			System.exit(-1);
		}
		
		currentPlace.addCharacter(this);
		
		String line = LineCleaner.cleanLine(file);
		Scanner lineScanner = new Scanner (line);
		
		ID = lineScanner.nextInt();
		
		characterMap.put(ID, this);
		inv = new ArrayList<Artifact>();
		
		name = lineScanner.nextLine().trim();
		
		line = LineCleaner.cleanLine(file);
		lineScanner = new Scanner (line);
		int numLines = lineScanner.nextInt();
		desc = "";
		
		for (int x = 0; x < numLines; x++)
			desc += LineCleaner.cleanLine(file) + "\n";
		
		isPlaying = true;
		
		lineScanner.close();
		
		return;
	}
	
	public Character (int ID, String name, String desc, Place currentPlace)
	{
		this.ID = ID;
		this.name = name;
		this.desc = desc;
		this.currentPlace = currentPlace;
		
		currentPlace.addCharacter(this);
		characterMap.put(ID, this);
		
		inv = new ArrayList<Artifact>();
		
		isPlaying = true;
	}
	
	protected static Character getCharacterByID (int targetID) { return characterMap.get(targetID); }
	
	public static void removeCharacter (Character c)
	{
		c.isPlaying = false;
		
		if (c instanceof Player)
		{
			if (--numPlayers <= 0)
			{
				System.out.println("No remaining players - ending game");
				System.exit(-1);
			}
		}
	}
	
	public boolean isPlaying () { return isPlaying; }
	
	public void moveTo (Place place)
	{
		currentPlace.removeCharacter(this);
		currentPlace = place;
		
		if (currentPlace.isExit())
		{
			System.out.println("\n[ " + name + " ] has exited the game");
			dropAllItems();
			removeCharacter(this);
		}
		else
		{
			currentPlace.addCharacter(this);
			System.out.println("[ " + name + " ] has moved to " + currentPlace.getName());
		}
		
		return;
	}
	
	public static Iterator<Character> getIterator() { return characterMap.values().iterator(); }
	
	public static void minimumPlayers (int min)
	{
		for (int x = numPlayers; x <= min; x++)
			new Player (x << 20, "Player " + x, "Random Player " + x, Place.getRandomPlace());
	}
	
	public abstract void makeMove ();
	
	public void display ()
	{
		System.out.println(name + "\n" + desc);
		return;
	}
	
	public void print ()
	{
		System.out.println("\nTODO: PRINT CHARACTER INFO\n");
		return;
	}
	
	public Artifact getArtifactByName (String string)
	{
		for (Artifact item : inv)
			if (item.match(string))
				return item;
	}
	
	public String getRandomArtifact ()
	{
		Random rand = new Random();
		
		if (inv.size() <= 0)
			return "nothing";
		else
			return inv.get(rand.nextInt(inv.size())).getName();
	}
	
	public void dropArtifact (String string) throws NotCarryingArtifactException
	{
		for (Artifact item : inv)
			if (item.match(string))
			{
				inv.remove(item);
				currentPlace.addArtifact(item);
				System.out.println("\n[ " + name + " ] has dropped " + string + " in " + currentPlace.getName());
				return;
			}
		
		throw new NotCarryingArtifactException (string);
	}
	
	public void dropAllItems ()
	{
		for (Artifact item : inv)
		{
			if (!currentPlace.isExit())
				currentPlace.addArtifact(item);
			else
				Place.getRandomPlace().addArtifact(item);
		}
		
		inv = null;
	}
	
	public void displayArtifacts ()
	{
		if (inv.size() > 0)
		{
			System.out.println("\nInventory: ");
			
			for (Artifact item : inv)
				System.out.println("\n" + item.getName());
			
			System.out.println("\nInventory weight: " + Artifact.inventoryTotalWeight(inv));
			System.out.println("Inventory value: " + Artifact.inventoryTotalValue(inv));
		}
		else
			System.out.println("\nNothing is in the inventory.");
		
		return;
	}
	
	public boolean match (String string) { return string.trim().equalsIgnoreCase(name); }
	
	public String getName () { return name; }
	
	public void addArtifact (Artifact item)
	{
		inv.add(item);
	}
}
