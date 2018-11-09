/* Franky Zhang
 * This class gets a clean line from a scanner input.
 * This removes all the commands and white spaces between lines
 * so that the program only reads the useful parts of a file.
 */
import java.util.Scanner;
public class CleanLineScanner {
	public static String getCleanLine(Scanner s) {
		String next = s.nextLine();
		
		next = next.replaceAll("\\s+", " ");	//remove all double spaces
		int index = next.indexOf("//");			//locate the comments
		
		//Checks if the line is empty or the line is comments
		if(next.length() == 0) {
			return "0";
		} else if(index == 0) {
			return "0";
		}

		//check to see if there is comment in the line or not.
		//negative value means no comment and just reads the length of the line
		if(index < 0) {
			next = next.substring(0, next.length());
		} else {
			next = next.substring(0, index-1);		//extract substring without the comment
		}
		next.trim();							//replaces all the trailing whitespaces
		
		return next;
	}
}
