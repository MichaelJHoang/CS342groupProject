/* Franky Zhang xzhan40
 * Gametester asks for an input file name from user
 * then passes the file to the game class to begin parsing
 * places directions artifacts and characters, then it'll start
 * playing the game
 */
//package hw1cs342;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class GameTest {

	public static void main(String[] args) throws FileNotFoundException{
		
		System.out.println("Name: all of our names");
		System.out.println("NetID: all of our netids");
		System.out.println("Assignment: HW4 CS342");
		File test = null;
		Scanner keyboard = KeyboardScanner.getKeyboardScanner();
		
		int nChars = 0;		
		//no command line arguments
		if( args.length == 0) {
			//System.out.println("Please entire file name: ");
			//String fileName = keyboard.nextLine();
			
			test = new File("MysticCity51.gdf");
			while( !test.exists()) {
				System.out.println("Not a valid file name, try again:");
				//fileName = keyboard.nextLine();
				//test = new File(fileName);
			}
		}
		//one command line argument
		else if(args.length == 1) {
            test = new File(args[0]);
            String fileName;
			while( !test.exists()) {
				System.out.println("Could not open " + args[0] + ", Please enter a filename");
				fileName = keyboard.nextLine();
				test = new File(fileName);
			}
        }
		//2 command line arguments
		else if( args.length == 2) {
			test = new File(args[0]);
			while( !test.exists()) {
				String fileName;
				System.out.println("Could not open " + args[0] + ", Please enter a filename");
				fileName = keyboard.nextLine();
				test = new File(fileName);
			}
			nChars = Integer.parseInt(args[1]);
		}
		//2 many command line arguments
		else if( args.length > 2) {
			System.out.println("Too many command line arguments, please run again");
			System.exit(0);
		}
		
        Scanner scnr = new Scanner(test);
		Game p = new Game(scnr, nChars);
		p.play();
		scnr.close();
	}//end main

}
