package com.revature.controllers;

import java.util.Scanner;

import com.revature.models.Book;
import com.revature.models.Genre;
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
				/*
				 * show a list of all the books that an offer was excepted by the owner on show
				 * a list of id, title, isSoftCover, isAvalible and agreed upon balance as well
				 * as if the book is owned by the user or not let the user choose a book to
				 * lower the balance on by having them input the id for that book make a payment
				 * of type double and replace 0 (must be less then or equal to the price of the
				 * book) take the id from a specific book above and access the balance of the
				 * book and take away the payment from it then save the amount payed to a list
				 * and return true or false if it all works. (reference the card number if
				 * possible)-extra
				 */
				us.newPayment(0);
				break;
			case "2":
				/*
				 * make an int input of vin number with sanner and replace 0 then print the book
				 * id, title, vin number, genra, isSoftCover, isAvalible, author and expected
				 * minimum price with .toString
				 */
				us.viewBook(0);
				break;
			case "3":
				/*
				 * get a list of all the books and print the book title, vin number, genra,
				 * isSoftCover, isAvalible, author and expected minimum price with .toString
				 */
				us.viewAllBooks();
				break;
			case "4":
				/*
				 * make an offer with scanner for a book and replace 0 possibly allow an option
				 * to look at books with view book or view all books. or set the new offer to be
				 * available in view book and view all books
				 */
				us.newOffer(0);
				break;
			case "5":
				/*
				 * look at balance of specific id of customer and show all the balances of all
				 * the books yet to be payed for display these amounts to the user with
				 * .toString
				 */
				us.viewOtherPayments();
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
				/*
				 * show a list of all the books that an offer was excepted by the owner on show
				 * a list of id, title, isSoftCover, isAvalible and agreed upon balance as well
				 * as if the book is owned by the user or not let the user choose a book to
				 * lower the balance on by having them input the id for that book make a payment
				 * of type double and replace 0 (must be less then or equal to the price of the
				 * book) take the id from a specific book above and access the balance of the
				 * book and take away the payment from it then save the amount payed to a list
				 * and return true or false if it all works. (reference the card number if
				 * possible)-extra
				 */
				us.newPayment(0);
				break;
			case "2":
				/*
				 * make an int input of vin number with sanner and replace 0 then print the books
				 * id, title, vin number, genra, isSoftCover, isAvalible, author and expected
				 * minimum price with .toString
				 */
				us.viewBook(0);
				break;
			case "3":
				/*
				 * get a list of all the books and print the book title, vin number, genra,
				 * isSoftCover, isAvalible, author and expected minimum price with .toString
				 */
				us.viewAllBooks();
				break;
			case "4":
				/*
				 * make an offer with scanner for a book and replace 0 possibly allow an option
				 * to look at books with view book or view all books. or set the new offer to be
				 * available in view book and view all books
				 */
				us.newOffer(0);
				break;
			case "5":
				/*
				 * look at balance of specific id of customer and show all the balances of all
				 * the books yet to be payed for display these amounts to the user with
				 * .toString
				 */
				us.viewOtherPayments();
				break;
			case "6":
				/*
				 * use a scanner to input the necessary info to make a book.
				 */
//				private int vinNumber;       !
//				private String title;        !
//				private Genre genre;         ---
//				private boolean isSoftCover; !
//				private boolean isAvailable; --- set to true in user service 
//				private String author;       !
//				private double price;        !

				System.out.println("Please enter a vinNumber: ");
				String vin = sc.nextLine();
				int vinNumber = Integer.parseInt(vin);
				System.out.println("Please enter a title: ");
				String title = sc.nextLine();
				System.out.println("Please chooes a genre please type 1-14: "); // find out how to use an enum
				System.out.println(" 1. LITERARY_FICTION\n"
						+ " 2. MYSTERY\n"
						+ " 3. THRILLER\n"
						+ " 4. HORRER\n"
						+ " 5. HISTORICAL\n"
						+ " 6. ROMANCE\n"
						+ " 7. WESTERN\n"
						+ " 8. BILDUNGSROMAN\n"
						+ " 9. SPECULATIVE_FICTION\n"
						+ "10. SCIENCE_FICTION\n"
						+ "11. FANTASY\n"
						+ "12. DYSTOPIAN\n"
						+ "13. MAGICAL_REALISM\n"
						+ "14. REALIST_LITERATURE\n");
				String tempGenre = sc.nextLine(); 
				Genre genre = null;
				switch (tempGenre) {
				case "1":
					genre = Genre.LITERARY_FICTION;
					break;
				case "2":
					genre = Genre.MYSTERY;
					break;
				case "3":
					genre = Genre.THRILLER;
					break;
				case "4":
					genre = Genre.HORRER;
					break;
				case "5":
					genre = Genre.HISTORICAL;
					break;
				case "6":
					genre = Genre.ROMANCE;
					break;
				case "7":
					genre = Genre.WESTERN;
					break;
				case "8":
					genre = Genre.BILDUNGSROMAN;
					break;
				case "9":
					genre = Genre.SPECULATIVE_FICTION;
					break;
				case "10":
					genre = Genre.SCIENCE_FICTION;
					break;
				case "11":
					genre = Genre.FANTASY;
					break;
				case "12":
					genre = Genre.DYSTOPIAN;
					break;
				case "13":
					genre = Genre.MAGICAL_REALISM;
					break;
				case "14":
					genre = Genre.REALIST_LITERATURE;
					break;
				}
				
				System.out.println("Please enter a if soft cover y/n: ");
				String SoftCover = sc.nextLine();
				Boolean isSoftCover;
				if(SoftCover.trim().toLowerCase() == "y" || SoftCover.trim().toLowerCase() == "yes") {
					isSoftCover = true;
				}
				else {
					isSoftCover = false;
				}
				//Boolean isAvalible = true;        // set in user service---
				System.out.println("Please enter Author: ");
				String author = sc.nextLine();
				System.out.println("Please enter minimum price: ");
				String minPrice = sc.nextLine();
				double price = Double.parseDouble(minPrice);
				Book newBook = new Book(vinNumber, title, genre, isSoftCover, author, price);
				
				us.addBook(newBook);
				break;
			case "7":
				/*
				 * remove a book based on an input id (see if i can figure out how to remove a
				 * title/vin number and not just a single book) -bonus
				 */
				us.removeBook(null);
				break;
			case "8":
				/*
				 * get a list of offers that has the id of the person sending the request and
				 * there offers and say if it is accepted or rejected. assign the id of that
				 * book to the id of the customer/owner/employee this will need a lot of work.
				 */
				us.reviewOffer();
				break;
			case "9":
				/*
				 * print a list of all payments made by the user and what it was payed to id,
				 * title, and vin number and remaining balance on these books
				 */
				us.reviewAllPayments();
				break;
			case "10":
				/*
				 * use a scanner to input the necessary info to make a book send in the id of
				 * the book to be changed and edit it send back true if successful
				 */
				us.editBook(null);

				break;
			case "11":
				/*
				 * print a list of all payments made by all users and what it was payed to id,
				 * title, and vin number and remaining balance on these books
				 */
				us.reviewAllPayments();
				break;
			case "12":
				/*
				 * pass in an id number of a Customer object and change the flag of the
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
				/*
				 * show a list of all the books that an offer was excepted by the owner on show
				 * a list of id, title, isSoftCover, isAvalible and agreed upon balance as well
				 * as if the book is owned by the user or not let the user choose a book to
				 * lower the balance on by having them input the id for that book make a payment
				 * of type double and replace 0 (must be less then or equal to the price of the
				 * book) take the id from a specific book above and access the balance of the
				 * book and take away the payment from it then save the amount payed to a list
				 * and return true or false if it all works. (reference the card number if
				 * possible)-extra
				 */
				us.newPayment(0);
				break;
			case "2":
				/*
				 * make an int input of vin number with sanner and replace 0 then print the book
				 * id, title, vin number, genra, isSoftCover, isAvalible, author and expected
				 * minimum price with .toString
				 */
				us.viewBook(0);
				break;
			case "3":
				/*
				 * get a list of all the books and print the book title, vin number, genra,
				 * isSoftCover, isAvalible, author and expected minimum price with .toString
				 */
				us.viewAllBooks();
				break;
			case "4":
				/*
				 * make an offer with scanner for a book and replace 0 possibly allow an option
				 * to look at books with view book or view all books. or set the new offer to be
				 * available in view book and view all books
				 */
				us.newOffer(0);
				break;
			case "5":
				/*
				 * look at balance of specific id of customer and show all the balances of all
				 * the books yet to be payed for display these amounts to the user with
				 * .toString
				 */
				us.viewOtherPayments();
				break;
			case "6":
				/*
				 * use a scanner to input the necessary info to make a book.
				 */
				us.addBook(null);
				break;
			case "7":
				/*
				 * remove a book based on an input id (see if i can figure out how to remove a
				 * title/vin number and not just a single book) -bonus
				 */
				us.removeBook(null);
				break;
			case "8":
				/*
				 * get a list of offers that has the id of the person sending the request and
				 * there offers and say if it is accepted or rejected. assign the id of that
				 * book to the id of the customer/owner/employee this will need a lot of work.
				 */
				us.reviewOffer();
				break;
			case "9":
				/*
				 * print a list of all payments made by the user and what it was payed to id,
				 * title, and vin number and remaining balance on these books
				 */
				us.reviewAllPayments();
				break;
			case "10":
				/*
				 * use a scanner to input the necessary info to make a book send in the id of
				 * the book to be changed and edit it send back true if successful
				 */
				us.editBook(null);

				break;
			case "11":
				/*
				 * print a list of all payments made by all users and what it was payed to id,
				 * title, and vin number and remaining balance on these books
				 */
				us.reviewAllPayments();
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