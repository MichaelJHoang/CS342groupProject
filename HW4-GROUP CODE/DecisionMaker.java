//Decision maker interface. Contains function getmove, implemented by AI and UI
package hw1cs342;

public interface DecisionMaker {
	
	public Move getMove(Character c, Place p);

}
