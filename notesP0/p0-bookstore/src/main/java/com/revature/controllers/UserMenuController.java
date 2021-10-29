package com.revature.controllers;

import java.util.Scanner;

import com.revature.services.UserService;

public class UserMenuController {
	private Scanner sc;
	private UserService us;

	public UserMenuController() {
		sc = new Scanner(System.in);
		us = new UserService();
	}

	/*
	 * the customer can... 1. make payments 2. view items 3. make an offer 4. view
	 * remaining payments 5. log out -call front controller
	 * 
	 */
	public void CustomerMenu() {
		boolean run = true;
		do {

			System.out.println("Please select one of the following options:");
			System.out.println("1: make payments");
			System.out.println("2: view item by vin");
			System.out.println("3: view all items");
			System.out.println("4: make an offer");
			System.out.println("5: view remaining payments");
			System.out.println("6: log out");

			String choice = sc.nextLine();

			switch (choice) {
			case "1":
				//make an input to say input payment and replace 0
				us.newPayment(0);
				break;
			case "2":
				//make an input to input vin number and replace 0
				us.viewBook(0);
				break;
			case "3":
				us.viewAllBooks(); // get a list of all the books and print it with .toString
				break;
			case "4":
				us.newOffer(0); // make an offer for a book and replace 0 
				break;
			case "5":
				us.viewOtherPayments(); // look at payments left to do 
				break;
			case "6":
				run = false;
				break;
			default:
				System.out.println("Invalid Input");
			}
		} while (run);
	}

	/*
	 * The owner can... 1. make payments 2. view items 3. make an offer 4. view
	 * remaining payments 5. add items 6. delete items 7. accept/reject offer 8.
	 * view all payments 9. edit items 10. view sales history 11. add employees 12.
	 * log out -call front controller
	 * 
	 */
	public void OwnerMenu() {
		boolean run = true;
		do {

			System.out.println("Please select one of the following options:");
			System.out.println("1: make payments");
			System.out.println("2: view item by vin");
			System.out.println("3: view all items");
			System.out.println("4: make an offer");
			System.out.println("5: view remaining payments");
			System.out.println("6: add items");
			System.out.println("7: delete items");
			System.out.println("8: accept/reject offer");
			System.out.println("9: view all payments");
			System.out.println("10: edit items");
			System.out.println("11: view sales history");
			System.out.println("12: add employees");
			System.out.println("13: log out");

			String choice = sc.nextLine();

			switch (choice) {
			case "1":
				/* show a list of all the books that an offer was excepted
				 * by the owner on show a list of id, title, isSoftCover, 
				 * isAvalible and agreed upon balance as well as if the 
				 * book is owned by the user or not let the user choose a 
				 * book to lower the balance on by having them input the id 
				 * for that book make a payment of type double and replace 0 
				 * (must be less then or equal to the price of the book) take the 
				 * id from a specific book above and access the balance of the book and 
				 * take away the payment from it then save the amount payed to a list and 
				 * return true or false if it all works.
				 * (reference the card number if possible)-extra  
				 */
				us.newPayment(0);
				break;
			case "2":
				/* make an int input of vin number with sanner and replace 0 
				 * then  print the book id, title, vin number, genra, isSoftCover,
				 * isAvalible, author and expected minimum price with .toString
				 */
				us.viewBook(0);
				break;
			case "3":
				/* get a list of all the books and print the 
				 * book title, vin number, genra, isSoftCover, isAvalible, author 
				 * and expected minimum price with .toString
				 */
				us.viewAllBooks(); 
				break;
			case "4":
				/* make an offer with scanner for a book and replace 0 
				 * possibly allow an option to look at books with view book or
				 * view all books. or set the new offer to be available in view
				 * book and view all books
				 */
				us.newOffer(0); 
				break;
			case "5":
				/* look at balance of specific id of customer and show all
				 * the balances of all the books yet to be payed for display
				 * these amounts to the user with .toString
				 */
				us.viewOtherPayments(); 
				break;
			case "6":
				/* use a scanner to input the necessary info to make a book.
				 */
				us.addBook(null); 
				break;
			case "7":
				/* remove a book based on an input id 
				 * (see if i can figure out how to remove a title/vin number and not just a single book) -bonus
				 */
				us.removeBook(null); 
				break;
			case "8":
				/* get a list of offers that has the id of the person sending the request and 
				 * there offers and say if it is accepted or rejected. 
				 * assign the id of that book to the id of the customer/owner/employee 
				 * this will need a lot of work. 
				 */
				us.reviewOffer();
				break;
			case "9":
				/* print a list of all payments made by the user and what it was payed to
				 *  id, title, and vin number and remaining balance on these books 
				 */
				us.reviewAllPayments(); 
				break;
			case "10":
				/* use a scanner to input the necessary info to make a book 
				 * send in the id of the book to be changed and edit it send back true 
				 * if successful
				 */
				us.editBook(null);

				break;
			case "11":
				/* print a list of all payments made by all users and what it was payed to
				 * id, title, and vin number and remaining balance on these books 
				 */
				us.reviewAllPayments();
				break;
			case "12":
				/* pass in an id number of a Customer object and change the flag of the 
				 * isEmployee boolean to true.
				 */
				break;
			case "13":
				run = false;
				break;
			default:
				System.out.println("Invalid Input");
			}
		} while (run);

	}

	/*
	 * The employees can... 1. make payments 2. view items 3. make an offer 4. view
	 * remaining payments 5. add items 6. delete items 7. accept/reject offer 8.
	 * view all payments 9. edit items 10. view sales history 11. log out -call
	 * front controller
	 * 
	 */
	public void EmployeeMenu() {
		boolean run = true;
		do {

			System.out.println("Please select one of the following options:");
			System.out.println("1: make payments");
			System.out.println("2: view item by vin");
			System.out.println("3: view all items");
			System.out.println("4: make an offer");
			System.out.println("5: view remaining payments");
			System.out.println("6: add items");
			System.out.println("7: delete items");
			System.out.println("8: accept/reject offer");
			System.out.println("9: view all payments");
			System.out.println("10: edit items");
			System.out.println("11: view sales history");
			System.out.println("12: log out");

			String choice = sc.nextLine();

			switch (choice) {
			case "1":

				break;
			case "2":

				break;
			case "3":

				break;
			case "4":
				break;
			case "5":

				break;
			case "6":

				break;
			case "7":

				break;
			case "8":

				break;
			case "9":
				break;
			case "10":

				break;
			case "11":

				break;
			case "12":
				run = false;
				break;
			default:
				System.out.println("Invalid Input");
			}
		} while (run);
	}
}
