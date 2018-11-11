import java.util.*;

public class NPC_decider implements DecisionMaker
{
	public NPC_decider ()
	{
		
	}
	
	public Move getMove (Character c, Place place)
	{
		Random rand = new Random();
		
		int decisionVariable = rand.nextInt(100);
		
		if (decisionVariable > 10)
			return new Go(c, place, place.getRandomDirection());
		else if (decisionVariable > 8)
			return new Get(c, place, place.getRandomArtifact());
		else if (decisionVariable > 6)
			return new Drop(c, place.getRandomArtifact());
		else if (decisionVariable > 4)
			return new Use(c, place, c.getRandomArtifact());
		
		return new NoMove();
	}
}
