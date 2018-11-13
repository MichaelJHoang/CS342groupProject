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
	
	public int gold() {
		return gold;
	}

	
	private void displayItems() {
		System.out.println("Welcome to my shop! Here's a list of my items:");
		for(Artifact a: inventory) {
			System.out.println("\t" + a.name() + " Cost: " + a.value());
		}
		System.out.println("Merchant gold: " + this.gold() );
	}
	
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
		
		System.out.println("Player gold: " + c.gold());
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
		c.removeGold(price);
		c.addArtifact(tmp);
		this.addGold(price);
		System.out.println("Thanks for shopping!");
		
	}
	
	private void sell(Character c) {		
		System.out.println("What item do you want to sell?");
		Scanner s = KeyboardScanner.getKeyboardScanner();
		String item = s.nextLine();
		item.trim();
		
		Artifact tmp = c.removeArtifactByName(item);
		{
			if(tmp == null) {
				System.out.println("You dont have that item to sell");
				return;
			}
		}
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
	
	@Override
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
		System.out.println("As of now, merchants cannot do anything wtihout a player interacting with them");		
	}

}
