/*Name: Dilip Vemuri
 * NETID: Dvemuri3
 * Player is an extension of Character. Player decision maker is set by UI
 * HMW4: Implemented new stats (carrweight,hp,gold), implemented new commands
 * like PET ,TRADE, and ATTACK. Players and can now recieve damage and DIE
 */
//package hw1cs342;
public interface DecisionMaker {
	
	public Move getMove(Character c, Place p);

}
