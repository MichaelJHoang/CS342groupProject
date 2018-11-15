/*Name: Dilip Vemuri
 * NETID: Dvemuri3
 * command class structure that encapsulates a move
 * Added new MoveTypes: SENSE, SMASH, ROAR, SNORE, TRADE, PET, ATTACK
 * GROWL, FOLLOW, BARK, CMD
 */
//package hw1cs342;

public class Move {
	
	public enum MoveType{
		USE, EXIT, INVENTORY, GET, DROP, LOOK, GO, NONE, WAIT, QUIT, 
		SENSE, SMASH, ROAR, SNORE, 
		TRADE, PET, ATTACK, 
		GROWL, FOLLOW, BARK, CMD;		
		
		//function to test if a string is in the enum
		public static boolean contains(String s) {
		    for (MoveType m : MoveType.values()) {
		        if (m.name().equals(s)) {
		            return true;
		        }
		    }
		    return false;
		}
	}
	//Move variables
	private MoveType type;
	private String argument;
	
	//constructor
	public Move( String _action, String _argument) {
		type = MoveType.valueOf(_action);
		argument = _argument;
	}
	
	//getters
	MoveType type() {
		return type;
	}
	
	//a function to see a string is a proper Move
	static boolean moveContains(String s) {
		return MoveType.contains(s);
	}
	
	//return the arguments
	String argument() {
		return argument;
	}

}
