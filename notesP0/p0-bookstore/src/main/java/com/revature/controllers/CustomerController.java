package com.revature.controllers;

import com.revature.services.CustomerService;

import java.util.Scanner;

import com.revature.exceptions.UsernameInUseException;
import com.revature.models.Customer;

public class CustomerController {

	private CustomerService cs = new CustomerService();
	private UserMenuController umc = new UserMenuController();

	public void registerCustomer(Scanner scan) {
		System.out.println("Please enter a Name: ");
		String name = scan.nextLine();
		while (name.trim().length() < 3) {
			System.out.println("Please enter a Name longer than 2 characters: ");
			name = scan.nextLine();
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

		System.out.println("Please enter a Card Number: ");
		String cardNumber = scan.nextLine();
		try {
			while (cardNumber.trim().length() < 8) {
				if (Integer.parseInt(cardNumber) < 9999999) {
					System.out.println("Please enter a Card Number longer than 7 integers: ");
					cardNumber = scan.nextLine();
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(
					"Please enter a Card Number longer than 7 integers and consists only of integers.\nPlease try again.\n");
			return;
		}
//		while (cardNumber.trim().length() < 8 || Integer.parseInt(cardNumber) > 9999999) { 
//			System.out.println("Please enter a Card Number longer than 7 integers and is a number: ");
//			cardNumber = scan.nextLine();
//		}

		Customer newCustomer = new Customer(name, username, password, email, cardNumber);

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
			if (cs.specificCustomer(username, password).getId() == 1) { // if owner is logged in do this
				System.out.println("Owner " + username + " has been logged in!\n");
				umc.OwnerMenu();
			} else if (cs.specificCustomer(username, password).isEmployee() == true) {
				System.out.println("Employee " + username + " has been logged in!\n");
				umc.EmployeeMenu();
			} else { // if customer is logged in do this
				System.out.println("Customer " + username + " has been logged in!\n");
				umc.CustomerMenu();
			}
		}
		if (!isLoggedIn) {
			System.out.println("Wrong Username and Password...\n");
		}
	}
}
