package com.revature.controllers;

import com.revature.services.CustomerService;

import java.util.Scanner;

import com.revature.models.Customer;

public class CustomerController {

	private CustomerService cs = new CustomerService();

//	public void findCustomer() {
//		try {
//			Customer c = cs.
//		}
//	}

	public void registerCustomer(Scanner scan) {
		System.out.println("Please enter a Name: ");
		String name = scan.nextLine();
		System.out.println("Please enter a Username: ");
		String username = scan.nextLine();
		System.out.println("Please enter a Password: ");
		String password = scan.nextLine();
		System.out.println("Please enter a Email: ");
		String email = scan.nextLine();
		System.out.println("Please enter a CardNumber: ");
		Integer cardNumber = scan.nextInt();
		scan.nextLine();

		Customer newCustomer = new Customer(name, username, password, email, cardNumber);
		cs.addCustomer(newCustomer);
		System.out.println("Customer has been registered\n");
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
		if (cs.customerList(username, password) != null) {
			isLoggedIn = true;
			System.out.println("Customer " + username + " has been loged in!\n");
		}
		if (!isLoggedIn) {
			System.out.println("Wrong Username and Password...\n");
		}

//		Customer validCustomer = new Customer(username, password);
//		if(cs.customerList(username, password) != null) {
//			isLoggedIn = true;
//			System.out.println("Customer " + validCustomer.getUsername() + " has been loged in!\n");
//		}
//		for (Customer all : cs.customerList()) {
//			if (validCustomer.getUsername().equals(all.getUsername())
//					&& validCustomer.getPassword().equals(all.getPassword())) {
//				System.out.println("Customer " + validCustomer.getUsername() + " has been loged in\n");
//				isLoggedIn = true;
//			}
//		}	
	}
}
