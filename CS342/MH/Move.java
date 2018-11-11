public abstract class Move 
{
	protected Move ()
	{
		
	}
	
	public abstract void execute () throws DirectionNotFoundException, DirectionLockedException,
										   NoSuchArtifactException, NotCarryingArtifactException;
}
