package com.revature.controllers;

import java.util.Scanner;

public class FrontController {
	private Scanner sc;
	private CustomerController cc;
	
	public FrontController() {
		sc = new Scanner(System.in);
		cc = new CustomerController();
	}
	
	public void run() {
		boolean run = true;
		do{
			System.out.println("Please select an option:");
			System.out.println("1: register");
			System.out.println("2: login");
			System.out.println("3: exit");
			
			String choice = sc.nextLine();
			
			switch(choice) {
			case "1":
				cc.registerCustomer(sc);
				break;
			case "2":
				cc.loginCustomer(sc);
				break;
			case "3":
				run = false;
				break;
			default:
				System.out.println("Invalid Input");
			}
		}while(run); 
	}
}
