import java.util.*;

public class Player extends Character
{
	public Player (int ID, String name, String desc, Place place)
	{
		super(ID, name, desc, place);
		decision = new playerInterface();
		numPlayers++;
	}
	
	public Player (Scanner file, int ver, int placeID)
	{
		super (file, ver, placeID);
		decision = new playerInterface();
		numPlayers++;
	}
	
	public void makeMove ()
	{
		if (isPlaying)
		{
			Move move = decision.getMove (this, currentPlace);
			
			try
			{
				move.execute();
			}
			catch (DirectionNotFoundException e)
			{
				System.out.println("\nCannot go that way: Invalid\n");
			}
			catch (DirectionLockedException e)
			{
				System.out.println("\nCannot go that way: Locked\n");
			}
			catch (NoSuchArtifactFoundException e)
			{
				System.out.println("\nItem not found.\n");
			}
			catch (NotCarryingArtifactException e)
			{
				System.out.println("\nItem not found in inventory.\n");
			}
			
			if (move instanceof Get || move instanceof Drop)
				displayItems();
		}
		
		return;
	}
}
