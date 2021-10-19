//package scanner-demo;
import java.util.Scanner;

public class ScannerDriver {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
//		System.out.println("Hello World!");
//		System.out.println("What's your name?");
//		
//		String name = sc.nextLine();
//		
//		System.out.println("Hello " + name);
//		System.out.println("Pick a number:");
//		
//		int num = sc.nextInt();
//		sc.nextLine(); // this sc.nextLine() flushes/grabs the leftovers from the previous input capture
////		String number = sc.nextLine();
//		// some validation
////		int num = Integer.parseInt(number);
//		
//		System.out.println("You picked " + num);
//		
//		ScannerDriver.loginScreen();
		ScannerDriver.menu();
		
		// Please close your resources people
		sc.close();
		
		/*
		 * Create a menu that gives a user multiple options:
		 * 		- 1: get a random number
		 * 		- 2: Using StringBuffer, reverse a word of the user's choice
		 * 		- 3: exit the program
		 * This menu should repeat until the user decides to exit.
		 */
	}
	
	public static void loginScreen() {
		String uname = "admin";
		String pass = "pass";
		
		System.out.println("Please enter your username:");
		String username = sc.nextLine();
		System.out.println("Please enter your password:");
		String password = sc.nextLine();
		if(uname.equals(username) && pass.equals(password)) {
			System.out.println("You're logged in.");
		} else {
			System.out.println("Not quite.");
		}
	}
	
	public static void menu () {
		System.out.println(" Please Enter a number from 1 to 3\n"
				+ " - 1: get a random number\n"
				+ " - 2: Using StringBuffer, reverse a word of the user's choice\n"
				+ " - 3: exit the program\n" 
				+ "My next choice is: ");
		int choice = sc.nextInt();
		sc.nextLine();
		
		while (choice != 3) {
			switch (choice) {
				case 1:
					double number = Math.round(Math.random()*10000);
					System.out.println("A random number: " + (int) number + "\n");
					System.out.println(" Please Enter a number from 1 to 3\n"
							+ " - 1: get a random number\n"
							+ " - 2: Using StringBuffer, reverse a word of the user's choice\n"
							+ " - 3: exit the program\n"
							+ "My next choice is: ");
					break;
				case 2: 
					System.out.println("Please enter a word: ");
					StringBuffer sb = new StringBuffer(sc.nextLine()); 
					System.out.println(sb.reverse() + "\n");
					System.out.println(" Please Enter a number from 1 to 3\n"
							+ " - 1: get a random number\n"
							+ " - 2: Using StringBuffer, reverse a word of the user's choice\n"
							+ " - 3: exit the program\n"
							+ "My next choice is: ");
					break;
				default:
					System.out.println("\nNo Good Try Agin\n\n" + " Please Enter a number from 1 to 3\n"
							+ " - 1: get a random number\n"
							+ " - 2: Using StringBuffer, reverse a word of the user's choice\n"
							+ " - 3: exit the program\n"
							+ "My next choice is: ");
					
			}
			choice = sc.nextInt();
			sc.nextLine();
			
			
			
					
		}
	}
	
}
