package com.revature.controllers;

import java.util.Scanner;

import com.revature.exceptions.UsernameInUseException;
import com.revature.models.User;
//import com.revature.models.Customer;
import com.revature.services.UserService;

public class CustomerController {

	private UserService cs = new UserService();
//	private UserMenuController umc = new UserMenuController();

	public void registerCustomer(Scanner scan) {
		System.out.println("Please enter a fistName: ");
		String firstName = scan.nextLine();
		while (firstName.trim().length() < 3) {
			System.out.println("Please enter a first name longer than 2 characters: ");
			firstName = scan.nextLine();
		}
		System.out.println("Please enter a lastName: ");
		String lastName = scan.nextLine();
		while (lastName.trim().length() < 3) {
			System.out.println("Please enter a last name longer than 2 characters: ");
			lastName = scan.nextLine();
		}
		System.out.println("Please enter a Username: ");
		String username = scan.nextLine();
		while (username.trim().length() < 3) {
			System.out.println("Please enter a Username longer than 2 characters: ");
			username = scan.nextLine();
		}
		System.out.println("Please enter a Password: ");
		String password = scan.nextLine();
		while (password.trim().length() < 4) {
			System.out.println("Please enter a Password longer than 3 characters: ");
			password = scan.nextLine();
		}
		System.out.println("Please enter a Email: ");
		String email = scan.nextLine();

		User newCustomer = new User(firstName, lastName, username, password, email);

		try {
			cs.add(newCustomer);
			if (cs.specificCustomer(username, password).getId() == 1) { // if owner is logged in do this
				System.out.println("Owner " + username + " has been registered!\n");
			} else { // if customer is logged in do this
				System.out.println("Customer " + username + " has been registered!\n");
			}	
		} catch (UsernameInUseException e) {

			System.out.println("Username is already in use.\nPlease try again.\n");
		}
	}

	public void loginCustomer(Scanner scan) {
		System.out.println("Please enter a Username: ");
		String username = scan.nextLine();
		System.out.println("Please enter a Password: ");
		String password = scan.nextLine();
		/*
		 * check if username exists check if password exists if yes check if both have
		 * the same index in the array of customer
		 */
		boolean isLoggedIn = false;
		cs.specificCustomer(username, password);
		if (cs.specificCustomer(username, password) != null) {
			isLoggedIn = true;
//			if (cs.specificCustomer(username, password).getId() == 1) { // if owner is logged in do this
//				System.out.println("Owner " + username + " has been logged in!\n");
//				umc.OwnerMenu();
//			} else if (cs.specificCustomer(username, password).isEmployee() == true) {
//				System.out.println("Employee " + username + " has been logged in!\n");
//				umc.EmployeeMenu();
//			} else { // if customer is logged in do this
//				System.out.println("Customer " + username + " has been logged in!\n");
//				umc.CustomerMenu();
//			}
		}
		if (!isLoggedIn) {
			System.out.println("Wrong Username and Password...\n");
		}
	}
}
