import java.util.*;

public class PlayerInterface implements DecisionMaker
{
	public PlayerInterface()
	{
		
	}
	
	public Move getMove (Character c, Place place)
	{
		Scanner input = KeyboardScanner.getKeyBoardScanner();
		String line;
		
		System.out.println("\n[ " + c.getName() + " ]'s turn: ");
		
		place.display();
		
		System.out.println("---> ");
		line = input.nextLine();
		line = line.trim();
		
		if (line.equalsIgnoreCase("EXIT" || line.equalsIgnoreCase("QUIT") || line.equalsIgnoreCase("Q")))
		{
			return new Quit(c, place);
		}
		
		if (line.equalsIgnoreCase("LOOK"))
			return new Look (c, place);
		
		if (line.equalsIgnoreCase("INVE") || line.equalsIgnoreCase("INVENTORY") || line.equalsIgnoreCase("I"))
		{
			return new Inventory(c);
		}
		
		if (line.length() > 4 && line.substring(0, 4).equalsIgnoreCase("GET"))
			return new Get(c, place, line.substring(4).trim());
		
		if (line.length() > 5 && line.substring(0, 5).equalsIgnoreCase("DROP"))
			return new Use(c, place, line.substring(4).trim());
		
		if (line.length() > 3 && line.substring(0, 3).equalsIgnoreCase("GO"))
			return new Go(c, place, line.substring(3));
		else
			return new Go(c, place, line);
	}
}
