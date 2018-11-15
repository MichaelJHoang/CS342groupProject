/*Name: Dilip Vemuri
 * NETID: Dvemuri3
 * Merchant extends NPC. For right now, Merchants do nothing on their turns
 * HMW4: Added in hmw4. Merchants on their turn do nothing, but Players can interact with
 * Merchants on their trun and buy and sell goods. Merchants inventory is generated at the start of the game
 * and is changed by buying/selling
 */
//package hw1cs342;

import java.util.Scanner;

public class Merchant extends NPC{
	
	public Merchant(int _id, String _name, String desc, int placeID) {
		super(_id, _name, desc, placeID);
		decisionMaker = new MerchantAI();
		gold = 200;
	}
	
	public Merchant(Scanner s, int placeID) {
		super(s, placeID);
		decisionMaker = new MerchantAI();
		gold = 200;
	}
	
	// function to display the items added by a merchant
	private void displayItems() {
		System.out.println("Welcome to my shop! Here's a list of my items:");
		for(Artifact a: inventory) {
			System.out.println("\t" + a.name() + " Cost: " + a.value());
		}
		System.out.println("Merchant gold: " + this.gold() );
	}
	
	// function to get a command from the player
	private String getCommand() {
		System.out.println("Please choose an option: BUY, SELL, OR EXIT");
		Scanner s = KeyboardScanner.getKeyboardScanner();
		String line = s.nextLine();
		line.trim();
		return line;
	}
	
	//buy and sell from persepctive of character
	private void buy(Character c) {		
		System.out.println("What item do you want to buy?");
		Scanner s = KeyboardScanner.getKeyboardScanner();
		String item = s.nextLine();
		item.trim();
		// get the artifact from the inventory
		Artifact tmp = this.removeArtifactByName(item);
		if(tmp == null) {
			System.out.println("This item does not exist");
			return;
		}
		int price = tmp.value();
		// check if buyer has enough gold
		if( c.gold() < price) {
			System.out.println("Sorry! not enough gold!");
			this.addArtifact(tmp);
			return;
		}
		// book keeping
		c.removeGold(price);
		c.addArtifact(tmp);
		this.addGold(price);
		System.out.println("Thanks for shopping!");
		
	}
	
	// function for the player to sell items to Merchant
	private void sell(Character c) {		
		System.out.println("What item do you want to sell?");
		Scanner s = KeyboardScanner.getKeyboardScanner();
		String item = s.nextLine();
		item.trim();
		// get item
		Artifact tmp = c.removeArtifactByName(item);
		{
			if(tmp == null) {
				System.out.println("You dont have that item to sell");
				return;
			}
		}
		// book keeping
		int price = tmp.value();
		if( this.gold() < price) {
			System.out.println("I don't have enough gold to buy that item..sorry");
			c.addArtifact(tmp);
			return;
		}		
		this.removeGold(price);
		this.addArtifact(tmp);
		c.addGold(price);
		System.out.println("Thanks for shopping!");
	}
	
	// this function is the interface the player interacts with to buy and sell from merchant
	public void tradeWindow(Character c) {
		// show list of items and merchants available gold
		this.displayItems();
		System.out.println("Player gold: " + c.gold());
		// offer to buy and sell
		String cmd = getCommand();
		switch( cmd.trim()) {
			case "BUY":
				this.buy(c);
				break;
			case "SELL":
				this.sell(c);
				break;
			case "EXIT":
				System.out.println("Thanks for stopping by!");
				break;
			default:
				System.out.println("Hmm i cant seem to do that");
				break;
		}
		// execute one buy and one sell, or exit. 3 options for now
	}

	
	// merchants should do nothing all game, maybe announce that they are in a location
	@Override
	public void makeMove() {		
		System.out.println("The Merchant announces that he's in " + this.getCurrPlace().getName() + " and he has wares to sell!");		
	}

}
