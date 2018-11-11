import java.util.*;

public class Get extends Move
{
	private Character c;
	private Place place;
	private String string;
	
	public Get (Character c, Place place, String string)
	{
		this.c = c;
		this.place = place;
		this.string = string;
	}
	
	@Override
	public void execute() throws NoSuchArtifactFoundException
	{
		Artifact item = place.removeArtifactByName(string);
		
		if (item != null)
		{
			c.addArtifact(item);
			
			System.out.println("\n[ " + c.getName() + " ] has picked up " + item.getName());
		}
		
		return;
	}
}
