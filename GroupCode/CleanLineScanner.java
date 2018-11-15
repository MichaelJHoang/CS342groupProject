/* Franky Zhang
 * This class gets a clean line from a scanner input.
 * This removes all the commands and white spaces between lines
 * so that the program only reads the useful parts of a file.
 */
//package hw1cs342;

import java.util.Scanner;

public class CleanLineScanner {
	
	public static String getCleanLine( Scanner s) {
		String line = null;			
		while(s.hasNextLine()){
            line = s.nextLine();
			line= line.split("//", 2)[0];
			line = line.trim();
            if( line.length() == 0)
            	continue;
           break;
        }			
		return line;		
	}
}
