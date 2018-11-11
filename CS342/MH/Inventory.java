public class Inventory extends Move
{
	private Character c;
	
	public Inventory (Character c)
	{
		this.c = c;
	}
	
	@Override
	public void execute()
	{
		c.displayArtifacts();
	}
}