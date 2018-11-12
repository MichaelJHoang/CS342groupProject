//Singleton class to get keyboard input, can be used in other classes


import java.util.Scanner;

public class KeyboardScanner {
	//single class instance of KeyboardScanner
	private static KeyboardScanner singleton = null;
	//scanner object
	private static Scanner keyboard;	
	//constructor
	private KeyboardScanner(){ 
		keyboard = new Scanner(System.in);
	}
	//return the keyboard. Makes an instance of the class if doesnt exist
	public static Scanner getKeyboardScanner(){
		if(singleton == null)
			singleton = new KeyboardScanner();
		return keyboard;
	}
	

}
