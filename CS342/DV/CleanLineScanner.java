//Name:Dilip Vemuri
//NETID:dvemuri3
//Function to get a cleanline
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
