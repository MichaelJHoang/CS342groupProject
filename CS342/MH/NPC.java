import java.util.*;

public class NPC 
{
	public NPC (int ID, String name, String desc, Place place)
	{
		super (ID, name, desc, place);
		decision = new NPC_decider();
	}
	
	public NPC (Scanner file, int ver, int placeID)
	{
		super (file, ver, placeID);
		decision = new NPC_decider();
	}
	
	public void makeMove ()
	{
		if (isPlaying)
		{
			try
			{
				decision.getMove (this, currentPlace).execute();
			}
			catch (DirectionNotFoundException e)
			{
				System.out.println("\nNPC cannot go that way: Invalid\n");
			}
			catch (DirectionLockedException e)
			{
				System.out.println("\nNPC cannot go that way: Locked\n");
			}
			
			System.out.println("\n[ " + name + " ] is currently in " + currentPlace.getName());
		}
		
		return;
	}
}
