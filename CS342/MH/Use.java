public class Use extends Move
{
	private Character c;
	private Place place;
	private String string;
	
	public Use (Character c, Place place, String string)
	{
		this.c = c;
		this.place = place;
		this.string = string;
	}
	
	@Override
	public void execute()
	{
		Artifact item = c.getArtifactByName(string);
		
		if (item != null)
			//item.use(c, place);
			item.use();
		
		return;
	}
}