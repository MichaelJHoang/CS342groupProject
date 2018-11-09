//Name:Dilip Vemuri
//NETID:dvemuri3
//A Class to encapsulate a move so that it can be executed easily in the character class. Contains Enum MoveType
//package hw1cs342;

public class Move {
	
	public enum MoveType{
		USE, EXIT, INVENTORY, GET, DROP, LOOK, GO, NONE, WAIT, QUIT;
		
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
