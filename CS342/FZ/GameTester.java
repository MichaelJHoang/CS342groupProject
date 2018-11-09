/* Franky Zhang xzhan40
 * Gametester asks for an input file name from user
 * then passes the file to the game class to begin parsing
 * places directions artifacts and characters, then it'll start
 * playing the game
 */


import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class GameTester {

	public static void main(String[] args) throws FileNotFoundException {
	
		System.out.println("\nName: Franky Zhang netID: xzhan40");
		String filename;
		String directory = System.getProperty("user.dir");  //looks for directory of files
		Scanner scanFile;
		
		System.out.println("Enter the full filename plus extentions to load: "); //filename needs to be in current dir
		Scanner scan = new Scanner(System.in);
		filename = scan.nextLine();
		
		filename = directory + "/" + filename;  //loads the filename inside directory
		
		File file = new File(filename); 
		scanFile = new Scanner (file); 
		
		Game newGame = new Game(scanFile); //starts scanning file
		newGame.play(); //file done scanning, play game
		scanFile.close();
		scan.close();
		
	}

}
