package com.revature.controllers;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Book;
import com.revature.models.BooksToCustomer;
import com.revature.models.Customer;
import com.revature.models.Genre;
import com.revature.repositories.BookDao;
import com.revature.repositories.BookPostgres;
import com.revature.repositories.CustomerDao;
import com.revature.repositories.CustomerPostgres;
import com.revature.services.CustomerService;
import com.revature.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserMenuController {
	private Scanner sc;
	private UserService us;
	private CustomerDao cd = new CustomerPostgres();
	private BookDao bd = new BookPostgres();
	private CustomerService cs = new CustomerService();
	private static Logger log = LogManager.getRootLogger();

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
			System.out.println("6: view owned items");
			System.out.println("7: log out");

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
				int id5 = 0;
				do {
					try {
						System.out.println("Please enter your Username: ");
						String username5 = sc.nextLine();
						System.out.println("Please enter your Password: ");
						String password5 = sc.nextLine();
						id5 = cs.specificCustomer(username5, password5).getId();
						double totalPrice1 = 0;
						if (id5 != 0) {
							for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
								if (all.getC_id() == id5 && bd.getById(all.getB_id()).isAvailable() == true
										&& all.isB_price_accepted()) {
									System.out.println("You have : $" + all.getB_price() + " Left to pay for "
											+ bd.getById(all.getB_id()).getTitle());
									totalPrice1 += all.getB_price();
								}
							}
							if (totalPrice1 != 0) {
								System.out.println("Your total is : $" + totalPrice1);
								System.out.println("Will you pay your total? Yes or No?");
								String payed = sc.nextLine();
								Boolean isPayed = false;
								if (payed.trim().toLowerCase().equals("y")
										|| payed.trim().toLowerCase().equals("yes")) {
									isPayed = true;
								} else {
									isPayed = false;
								}
								if (isPayed) {
									for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
										if (all.getB_id() == bd.getById(all.getB_id()).getId()
												&& bd.getById(all.getB_id()).isAvailable() == true
												&& all.getC_id() == id5 && all.isB_price_accepted()) {
											Book soldBook = new Book();
											soldBook = bd.getById(all.getB_id());
											soldBook.setAvailable(false);
											soldBook.setNewOwner(cd.getById(id5));
											bd.edit(soldBook);
										}
									}
									System.out.println("Your total has been payed\n");
								} else {
									System.out.println("Your total has not been payed\n");
								}
							} else {
								System.out.println("You have no books to pay for!");
							}
						}
					} catch (Exception e) {
						log.error("Wrong username or password please try agin!!\n");
//						System.out.println("Wrong Username or Password\n");
					}
				} while (id5 == 0);
				break;
			case "2":
				/*
				 * make an int input of vin number with sanner and replace 0 then print the
				 * books id, title, vin number, genra, isSoftCover, isAvalible, author and
				 * expected minimum price with .toString
				 */
				System.out.println("Please enter a book vinNumber: ");
				Integer vin = Integer.valueOf(sc.nextLine());
				int i = 0;
				try {
					for (Book all : us.viewAllBooks()) {
						i++;
						if (all.getVinNumber() == vin && all.isAvailable()) {
							System.out.println("\nBook # " + i);
							System.out.println("Title: " + all.getTitle());
							System.out.println("Author: " + all.getAuthor());
							System.out.println("Genre: " + all.getGenre());
							if (all.isSoftCover()) {
								System.out.println("It is a soft bound book.");
							} else {
								System.out.println("It is a hard bound book.");
							}
							System.out.println("Price: $" + all.getPrice());
							System.out.println("Vin #: " + all.getVinNumber());
							System.out.println();
						}

					}
				} catch (Exception e) {
					log.error("This book id dosent exist! Please try agin!");
					break;
				}
				break;
			case "3":
				/*
				 * get a list of all the books and print the book title, vin number, genra,
				 * isSoftCover, isAvalible, author and expected minimum price with .toString
				 */
				int y = 0;
				System.out.println();
				for (Book all : us.viewAllBooks()) {
					if (all.isAvailable()) {
						y++;
						System.out.println("Book # " + y);
						System.out.println("Title: " + all.getTitle());
						System.out.println("Author: " + all.getAuthor());
						System.out.println("Genre: " + all.getGenre());
						if (all.isSoftCover()) {
							System.out.println("It is a soft bound book.");
						} else {
							System.out.println("It is a hard bound book.");
						}
						System.out.println("Price: $" + all.getPrice());
						System.out.println("Vin #: " + all.getVinNumber());
						System.out.println();
					}
				}
				break;
			case "4":
				/*
				 * make an offer with scanner for a book and replace 0 possibly allow an option
				 * to look at books with view book or view all books. or set the new offer to be
				 * available in view book and view all books
				 */
				boolean offerMade = true;
				while (offerMade) {
					System.out.println();
					for (Book all : us.viewAllBooks()) {
						if (all.isAvailable()) {
							System.out.println("Book Id# : " + all.getId());
							System.out.println("Title: " + all.getTitle());
							System.out.println("Author: " + all.getAuthor());
							System.out.println("Genre: " + all.getGenre());
							if (all.isSoftCover()) {
								System.out.println("It is a soft bound book.");
							} else {
								System.out.println("It is a hard bound book.");
							}
							System.out.println("Price: $" + all.getPrice());
							System.out.println("Vin #: " + all.getVinNumber());
							System.out.println();
						}
					}
					System.out.println("\nPlease enter the id of the book desired: ");
					Integer id1 = Integer.valueOf(sc.nextLine());
					System.out.println("Please enter the potential new price of the book desired for review.\n"
							+ "The new price can't be much less then the listed price.\n" + "The minimum price is: $"
							+ bd.getById(id1).getPrice());
					Double price1 = Double.valueOf(sc.nextLine());
					while (price1 + 10 < bd.getById(id1).getPrice()) {
						System.out.println("This is much less then the minimum price please enter a new price!!");
						price1 = Double.valueOf(sc.nextLine());
					}
					System.out.println("Please enter your Username: ");
					String username1 = sc.nextLine();
					System.out.println("Please enter your Password: ");
					String password1 = sc.nextLine();
//				Customer validCustomer = new Customer(username1, password1);
					int id2 = 0;
//				for (Customer all : cd.getAll()) {
//					if (validCustomer.getUsername().equals(all.getUsername())
//							&& validCustomer.getPassword().equals(all.getPassword())) {
//						id2 = all.getId();
//					}
//				}
					id2 = cs.specificCustomer(username1, password1).getId();
					boolean oldOffer = true;

					for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
						if (all.getC_id() == id2 && all.getB_id() == id1) {
							oldOffer = false;
							offerMade = false;
							System.out.println("You already made an offer for this book of " + all.getB_price());
						}
					}
					if (offerMade && oldOffer) {
						offerMade = false;
						us.newOffer(id1, price1, id2);
						System.out.println("Your submition has been logged please waight for aproval");
					}
				}
				break;
			case "5":
				/*
				 * look at balance of specific id of customer and show all the balances of all
				 * the books yet to be payed for display these amounts to the user with
				 * .toString
				 */
				int id4 = 0;
				do {
					try {
						System.out.println("Please enter your Username: ");
						String username2 = sc.nextLine();
						System.out.println("Please enter your Password: ");
						String password2 = sc.nextLine();
						id4 = cs.specificCustomer(username2, password2).getId();
						double totalPrice = 0;
						if (id4 != 0) {
							for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
								if (all.getC_id() == id4 && all.isB_price_accepted()) {
									System.out.println("You have : $" + all.getB_price() + " Left to pay for "
											+ bd.getById(all.getB_id()).getTitle());
									totalPrice += all.getB_price();
								}
							}
						}
						if (totalPrice != 0) {
							System.out.println("Your total is : $" + totalPrice);
						} else {
							System.out.println("There are no books you need to pay for!!\n");
						}
					} catch (Exception e) {
//						System.out.println("Wrong Username or Password!");
						log.error("Wrong Username or Password!");
					}
				} while (id4 == 0);
				break;

			case "6":
				int id6 = 0;
				do {
					try {
						System.out.println("Please enter your Username: ");
						String username6 = sc.nextLine();
						System.out.println("Please enter your Password: ");
						String password6 = sc.nextLine();
						id6 = cs.specificCustomer(username6, password6).getId();
						boolean doOwn = false;
						if (id6 != 0) {
							for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
								if (all.getC_id() == id6 && bd.getById(all.getB_id()).isAvailable() == false
										&& all.isB_price_accepted()) {
									System.out.println("You own " + bd.getById(all.getB_id()).getTitle());
									doOwn = true;
								}
							}
							if (!doOwn) {
								System.out.println("You don't own any books!\n");
							}
						}
					} catch (Exception e) {
						log.error("Wrong username or password please try agin!!\n");
//						System.out.println("Wrong username or password please try agin!!\n");
					}
				} while (id6 == 0);
				break;


			case "7":
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
			System.out.println(" 1: make payments");
			System.out.println(" 2: view item by vin");
			System.out.println(" 3: view all items");
			System.out.println(" 4: make an offer");
			System.out.println(" 5: view remaining payments");
			System.out.println(" 6: view owned items");
			System.out.println(" 7: add items");
			System.out.println(" 8: delete items");
			System.out.println(" 9: accept/reject offer");
			System.out.println("10: view all payments");
			System.out.println("11: edit items");
			System.out.println("12: view company sales history");
			System.out.println("13: add employees");
			System.out.println("14: log out");

			String choice = sc.nextLine();

			/*
			 * 1. 2. done 3. done 4. 5. 6.done 7.done 8. 9. 10. 11. 12.
			 * 
			 */

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
				int id5 = 0;
				do {
					try {
						System.out.println("Please enter your Username: ");
						String username5 = sc.nextLine();
						System.out.println("Please enter your Password: ");
						String password5 = sc.nextLine();
						id5 = cs.specificCustomer(username5, password5).getId();
						double totalPrice1 = 0;
						if (id5 != 0) {
							for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
								if (all.getC_id() == id5 && bd.getById(all.getB_id()).isAvailable() == true
										&& all.isB_price_accepted()) {
									System.out.println("You have : $" + all.getB_price() + " Left to pay for "
											+ bd.getById(all.getB_id()).getTitle());
									totalPrice1 += all.getB_price();
								}
							}
							if (totalPrice1 != 0) {
								System.out.println("Your total is : $" + totalPrice1);
								System.out.println("Will you pay your total? Yes or No?");
								String payed = sc.nextLine();
								Boolean isPayed = false;
								if (payed.trim().toLowerCase().equals("y")
										|| payed.trim().toLowerCase().equals("yes")) {
									isPayed = true;
								} else {
									isPayed = false;
								}
								if (isPayed) {
									for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
										if (all.getB_id() == bd.getById(all.getB_id()).getId()
												&& bd.getById(all.getB_id()).isAvailable() == true
												&& all.getC_id() == id5 && all.isB_price_accepted()) {
											Book soldBook = new Book();
											soldBook = bd.getById(all.getB_id());
											soldBook.setAvailable(false);
											soldBook.setNewOwner(cd.getById(id5));
											bd.edit(soldBook);
										}
									}
									System.out.println("Your total has been payed\n");
								} else {
									System.out.println("Your total has not been payed\n");
								}
							} else {
								System.out.println("You have no books to pay for!");
							}
						}
					} catch (Exception e) {
						log.error("Wrong username or password please try agin!!\n");
//						System.out.println("Wrong Username or Password\n");
					}
				} while (id5 == 0);
				break;
			case "2":
				/*
				 * make an int input of vin number with sanner and replace 0 then print the
				 * books id, title, vin number, genra, isSoftCover, isAvalible, author and
				 * expected minimum price with .toString
				 */
				System.out.println("Please enter a book vinNumber: ");
				Integer vin = Integer.valueOf(sc.nextLine());
				int i = 0;
				try {
					for (Book all : us.viewAllBooks()) {
						i++;
						if (all.getVinNumber() == vin && all.isAvailable()) {
							System.out.println("\nBook # " + i);
							System.out.println("Title: " + all.getTitle());
							System.out.println("Author: " + all.getAuthor());
							System.out.println("Genre: " + all.getGenre());
							if (all.isSoftCover()) {
								System.out.println("It is a soft bound book.");
							} else {
								System.out.println("It is a hard bound book.");
							}
							System.out.println("Price: $" + all.getPrice());
							System.out.println("Vin #: " + all.getVinNumber());
							System.out.println();
						}

					}
				} catch (Exception e) {
					log.error("This book id dosent exist! Please try agin!");
					break;
				}
				break;
			case "3":
				/*
				 * get a list of all the books and print the book title, vin number, genra,
				 * isSoftCover, isAvalible, author and expected minimum price with .toString
				 */
				int y = 0;
				System.out.println();
				for (Book all : us.viewAllBooks()) {
					if (all.isAvailable()) {
						y++;
						System.out.println("Book # " + y);
						System.out.println("Title: " + all.getTitle());
						System.out.println("Author: " + all.getAuthor());
						System.out.println("Genre: " + all.getGenre());
						if (all.isSoftCover()) {
							System.out.println("It is a soft bound book.");
						} else {
							System.out.println("It is a hard bound book.");
						}
						System.out.println("Price: $" + all.getPrice());
						System.out.println("Vin #: " + all.getVinNumber());
						System.out.println();
					}
				}
				break;
			case "4":
				/*
				 * make an offer with scanner for a book and replace 0 possibly allow an option
				 * to look at books with view book or view all books. or set the new offer to be
				 * available in view book and view all books
				 */
				boolean offerMade = true;
				while (offerMade) {
					System.out.println();
					for (Book all : us.viewAllBooks()) {
						if (all.isAvailable()) {
							System.out.println("Book Id# : " + all.getId());
							System.out.println("Title: " + all.getTitle());
							System.out.println("Author: " + all.getAuthor());
							System.out.println("Genre: " + all.getGenre());
							if (all.isSoftCover()) {
								System.out.println("It is a soft bound book.");
							} else {
								System.out.println("It is a hard bound book.");
							}
							System.out.println("Price: $" + all.getPrice());
							System.out.println("Vin #: " + all.getVinNumber());
							System.out.println();
						}
					}
					System.out.println("\nPlease enter the id of the book desired: ");
					Integer id1 = Integer.valueOf(sc.nextLine());
					System.out.println("Please enter the potential new price of the book desired for review.\n"
							+ "The new price can't be much less then the listed price.\n" + "The minimum price is: $"
							+ bd.getById(id1).getPrice());
					Double price1 = Double.valueOf(sc.nextLine());
					while (price1 + 10 < bd.getById(id1).getPrice()) {
						System.out.println("This is much less then the minimum price please enter a new price!!");
						price1 = Double.valueOf(sc.nextLine());
					}
					System.out.println("Please enter your Username: ");
					String username1 = sc.nextLine();
					System.out.println("Please enter your Password: ");
					String password1 = sc.nextLine();
//				Customer validCustomer = new Customer(username1, password1);
					int id2 = 0;
//				for (Customer all : cd.getAll()) {
//					if (validCustomer.getUsername().equals(all.getUsername())
//							&& validCustomer.getPassword().equals(all.getPassword())) {
//						id2 = all.getId();
//					}
//				}
					id2 = cs.specificCustomer(username1, password1).getId();
					boolean oldOffer = true;

					for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
						if (all.getC_id() == id2 && all.getB_id() == id1) {
							oldOffer = false;
							offerMade = false;
							System.out.println("You already made an offer for this book of " + all.getB_price());
						}
					}
					if (offerMade && oldOffer) {
						offerMade = false;
						us.newOffer(id1, price1, id2);
						System.out.println("Your submition has been logged please waight for aproval");
					}
				}
				break;
			case "5":
				/*
				 * look at balance of specific id of customer and show all the balances of all
				 * the books yet to be payed for display these amounts to the user with
				 * .toString
				 */
				int id4 = 0;
				do {
					try {
						System.out.println("Please enter your Username: ");
						String username2 = sc.nextLine();
						System.out.println("Please enter your Password: ");
						String password2 = sc.nextLine();
						id4 = cs.specificCustomer(username2, password2).getId();
						double totalPrice = 0;
						if (id4 != 0) {
							for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
								if (all.getC_id() == id4 && all.isB_price_accepted()) {
									System.out.println("You have : $" + all.getB_price() + " Left to pay for "
											+ bd.getById(all.getB_id()).getTitle());
									totalPrice += all.getB_price();
								}
							}
						}
						if (totalPrice != 0) {
							System.out.println("Your total is : $" + totalPrice);
						} else {
							System.out.println("There are no books you need to pay for!!\n");
						}
					} catch (Exception e) {
//						System.out.println("Wrong Username or Password!");
						log.error("Wrong Username or Password!");
					}
				} while (id4 == 0);
				break;

			case "6":
				int id6 = 0;
				do {
					try {
						System.out.println("Please enter your Username: ");
						String username6 = sc.nextLine();
						System.out.println("Please enter your Password: ");
						String password6 = sc.nextLine();
						id6 = cs.specificCustomer(username6, password6).getId();
						boolean doOwn = false;
						if (id6 != 0) {
							for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
								if (all.getC_id() == id6 && bd.getById(all.getB_id()).isAvailable() == false
										&& all.isB_price_accepted()) {
									System.out.println("You own " + bd.getById(all.getB_id()).getTitle());
									doOwn = true;
								}
							}
							if (!doOwn) {
								System.out.println("You don't own any books!\n");
							}
						}
					} catch (Exception e) {
						log.error("Wrong username or password please try agin!!\n");
//						System.out.println("Wrong username or password please try agin!!\n");
					}
				} while (id6 == 0);
				break;

			case "7":
				/*
				 * use a scanner to input the necessary info to make a book.
				 */
				System.out.println("Please enter a vinNumber: ");
				String vin1 = sc.nextLine();
				int vinNumber = Integer.parseInt(vin1);
				System.out.println("Please enter a title: ");
				String title = sc.nextLine();
				System.out.println("Choose a genre please type a number from 1-14: "); // find out how to use an enum
																						// correctly
				System.out.println(" 1. Literary Fiction\n" + " 2. Mystery\n" + " 3. Thriller\n" + " 4. Horrer\n"
						+ " 5. Historical\n" + " 6. Romance\n" + " 7. Western\n" + " 8. Bildungsroman\n"
						+ " 9. Speculative Fiction\n" + "10. Science Fiction\n" + "11. Fantasy\n" + "12. Dystopian\n"
						+ "13. Macical Realism\n" + "14. Realist Literature\n");
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
					genre = Genre.HORROR;
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

				System.out.println("Please type yes if soft cover: ");
				String SoftCover = sc.nextLine();
				Boolean isSoftCover;
				if (SoftCover.trim().toLowerCase().equals("y") || SoftCover.trim().toLowerCase().equals("yes")) {
					isSoftCover = true;
				} else {
					isSoftCover = false;
				}
				// Boolean isAvalible = true; // set in user service---
				System.out.println("Please enter Author: ");
				String author = sc.nextLine();
				System.out.println("Please enter minimum price: ");
				String minPrice = sc.nextLine();
				double price = Double.parseDouble(minPrice);
				Book newBook = new Book(vinNumber, title, genre, isSoftCover, author, price);
				us.addBook(newBook);
				break;
			case "8":
				/*
				 * remove a book based on an input id (see if i can figure out how to remove a
				 * title/vin number and not just a single book) -bonus
				 */
				System.out.println();
				for (Book all : us.viewAllBooks()) {
					if (all.isAvailable()) {

						System.out.println("Book Id# : " + all.getId());
						System.out.println("Title: " + all.getTitle());
						System.out.println("Author: " + all.getAuthor());
						System.out.println("Genre: " + all.getGenre());
						if (all.isSoftCover()) {
							System.out.println("It is a soft bound book.");
						} else {
							System.out.println("It is a hard bound book.");
						}
						System.out.println("Price: $" + all.getPrice());
						System.out.println("Vin #: " + all.getVinNumber());
						System.out.println("Please type " + all.getId() + " to delete this book!");
						System.out.println();
					}
				}
				System.out.println("Please enter the id number of the book to be deleted: ");
				Integer idNumber = Integer.valueOf(sc.nextLine());
				try {
					for (Book all : us.viewAllBooks()) {
						if (all.getId() == idNumber) {
							us.removeBook(all);
							System.out.println("Book Id# : " + all.getId() + "\n was deleted.");
							System.out.println();
						}
					}
				} catch (Exception e) {
					log.error("This book id dosent exist! Please try agin!");
					break;
				}
				break;
			case "9":
				/*
				 * get a list of offers that has the id of the person sending the request and
				 * there offers and say if it is accepted or rejected. assign the id of that
				 * book to the id of the customer/owner/employee this will need a lot of work.
				 */

				for (BooksToCustomer all : us.viewAllOffers()) {
					if (!all.isB_price_accepted() && bd.getById(all.getB_id()).isAvailable()) {
						System.out.println("\nOffer id# : " + all.getbooks_to_customer_id());
						System.out.println("Wanted book title : " + bd.getById(all.getB_id()).getTitle());
						System.out.println("Customer desiring book : " + cd.getById(all.getC_id()).getName());
						System.out.println("The potential price of this book is : $" + all.getB_price());
						System.out.println("Type " + all.getbooks_to_customer_id() + " to accept this offer");
					}
				}
				System.out.println("\nPlease enter the id number of the request you would like to accept. ");
				System.out.println("Any offers for the same book will be rejected.");
				System.out.println("Enter 0 if you wish to except no offer!");
				Integer idNumberOfRequest = Integer.valueOf(sc.nextLine());
				if (idNumberOfRequest != 0) {
					us.reviewOffer(idNumberOfRequest);
					System.out.println("The request has been accepted.");
				} else {
					System.out.println("You rejected the request!");
				}
				break;
			case "10":
				/*
				 * print a list of all payments made by the user and what it was payed to id,
				 * title, and vin number and remaining balance on these books
				 */
				try {
					System.out.println("Please enter your Username: ");
					String username2 = sc.nextLine();
					System.out.println("Please enter your Password: ");
					String password2 = sc.nextLine();
					int id10 = cs.specificCustomer(username2, password2).getId();

					for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
						if (bd.getById(all.getB_id()).isAvailable() == false && all.getC_id() == id10) {
							System.out.println("You have purchesed " + bd.getById(all.getB_id()).getTitle() + " for "
									+ all.getB_price());
						}
					}
				} catch (Exception e) {

				}
//				us.reviewAllPayments();
				break;
			case "11":
				/*
				 * use a scanner to input the necessary info to make a book send in the id of
				 * the book to be changed and edit it send back true if successful
				 */
				for (Book all : bd.getAll()) {
					if (all.isAvailable()) {
						System.out.println("Book title: " + all.getTitle());
						System.out.println("Book id#: " + all.getId());
					}
				}
				System.out.println("Please enter the id of the book you want to edit: ");
				String newestId = sc.nextLine();
				int id3 = Integer.parseInt(newestId);
				System.out.println("Please enter an updated vinNumber: ");
				String vin2 = sc.nextLine();
				int vinNumber1 = Integer.parseInt(vin2);
				System.out.println("Please enter an updated title: ");
				String title1 = sc.nextLine();
				System.out.println("Please chooes an updated genre please type #1-14: "); // find out how to use an enum
				System.out.println(" 1. LITERARY_FICTION\n" + " 2. MYSTERY\n" + " 3. THRILLER\n" + " 4. HORRER\n"
						+ " 5. HISTORICAL\n" + " 6. ROMANCE\n" + " 7. WESTERN\n" + " 8. BILDUNGSROMAN\n"
						+ " 9. SPECULATIVE_FICTION\n" + "10. SCIENCE_FICTION\n" + "11. FANTASY\n" + "12. DYSTOPIAN\n"
						+ "13. MAGICAL_REALISM\n" + "14. REALIST_LITERATURE\n");
				String tempGenre1 = sc.nextLine();
				Genre genre1 = null;
				switch (tempGenre1) {
				case "1":
					genre1 = Genre.LITERARY_FICTION;
					break;
				case "2":
					genre1 = Genre.MYSTERY;
					break;
				case "3":
					genre1 = Genre.THRILLER;
					break;
				case "4":
					genre1 = Genre.HORROR;
					break;
				case "5":
					genre1 = Genre.HISTORICAL;
					break;
				case "6":
					genre1 = Genre.ROMANCE;
					break;
				case "7":
					genre1 = Genre.WESTERN;
					break;
				case "8":
					genre1 = Genre.BILDUNGSROMAN;
					break;
				case "9":
					genre1 = Genre.SPECULATIVE_FICTION;
					break;
				case "10":
					genre1 = Genre.SCIENCE_FICTION;
					break;
				case "11":
					genre1 = Genre.FANTASY;
					break;
				case "12":
					genre1 = Genre.DYSTOPIAN;
					break;
				case "13":
					genre1 = Genre.MAGICAL_REALISM;
					break;
				case "14":
					genre1 = Genre.REALIST_LITERATURE;
					break;
				}

				System.out.println("Please enter a if soft cover y/n: ");
				String SoftCover1 = sc.nextLine();
				Boolean isSoftCover1;
				if (SoftCover1.trim().toLowerCase().equals("y") || SoftCover1.trim().toLowerCase().equals("yes")) {
					isSoftCover1 = true;
				} else {
					isSoftCover1 = false;
				}
				System.out.println("Please enter if available: ");
				String available = sc.nextLine();
				Boolean isAvailable;
				if (available.trim().toLowerCase().equals("y") || available.trim().toLowerCase().equals("yes")) {
					isAvailable = true;
				} else {
					isAvailable = false;
				}
				// set in user service---
				System.out.println("Please enter Author: ");
				String author1 = sc.nextLine();
				System.out.println("Please enter minimum price: ");
				String minPrice1 = sc.nextLine();
				double price3 = Double.parseDouble(minPrice1);
				Book newBook1 = new Book(id3, vinNumber1, title1, genre1, isSoftCover1, isAvailable, author1, price3);
				us.editBook(newBook1);
				break;
			case "12":
				/*
				 * print a list of all payments made by all users and what it was payed to id,
				 * title, and vin number and remaining balance on these books
				 */
				us.reviewAllPayments();
				break;
			case "13":
				/*
				 * pass in an id number of a Customer object and change the flag of the
				 * isEmployee boolean to true.
				 */
				for (Customer all : cd.getAll()) {
					if (!all.isEmployee()) {
						System.out.println("Customer name : " + all.getName());
						System.out.println("Customer id # : " + all.getId());
						System.out.println();
					}
				}
				System.out.println("Please enter the id of the customer you want to edit: ");
				int idToBeEdited = Integer.parseInt(sc.nextLine());

				System.out.println("Is this customer a new employee enter yes or no: ");
				String testIsEmployee = sc.nextLine();
				Boolean isEmployee;
				if (testIsEmployee.trim().toLowerCase().equals("y")
						|| testIsEmployee.trim().toLowerCase().equals("yes")) {
					isEmployee = true;
				} else {
					isEmployee = false;
				}
				if (isEmployee) {
					Customer newCustomer = new Customer(idToBeEdited, isEmployee);
					us.editCustomers(newCustomer);
					System.out.println(cd.getById(idToBeEdited).getName() + " is now an employee!!\n");
				} else {
					System.out.println("You did not add " + cd.getById(idToBeEdited).getName() + " as an employee!\n");
				}
				break;
			case "14":
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
			System.out.println(" 1: make payments");
			System.out.println(" 2: view item by vin");
			System.out.println(" 3: view all items");
			System.out.println(" 4: make an offer");
			System.out.println(" 5: view remaining payments");
			System.out.println(" 6: view owned items");
			System.out.println(" 7: add items");
			System.out.println(" 8: delete items");
			System.out.println(" 9: accept/reject offer");
			System.out.println("10: view all payments");
			System.out.println("11: edit items");
			System.out.println("12: view sales history");
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
				int id5 = 0;
				do {
					try {
						System.out.println("Please enter your Username: ");
						String username5 = sc.nextLine();
						System.out.println("Please enter your Password: ");
						String password5 = sc.nextLine();
						id5 = cs.specificCustomer(username5, password5).getId();
						double totalPrice1 = 0;
						if (id5 != 0) {
							for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
								if (all.getC_id() == id5 && bd.getById(all.getB_id()).isAvailable() == true
										&& all.isB_price_accepted()) {
									System.out.println("You have : $" + all.getB_price() + " Left to pay for "
											+ bd.getById(all.getB_id()).getTitle());
									totalPrice1 += all.getB_price();
								}
							}
							if (totalPrice1 != 0) {
								System.out.println("Your total is : $" + totalPrice1);
								System.out.println("Will you pay your total? Yes or No?");
								String payed = sc.nextLine();
								Boolean isPayed = false;
								if (payed.trim().toLowerCase().equals("y")
										|| payed.trim().toLowerCase().equals("yes")) {
									isPayed = true;
								} else {
									isPayed = false;
								}
								if (isPayed) {
									for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
										if (all.getB_id() == bd.getById(all.getB_id()).getId()
												&& bd.getById(all.getB_id()).isAvailable() == true
												&& all.getC_id() == id5 && all.isB_price_accepted()) {
											Book soldBook = new Book();
											soldBook = bd.getById(all.getB_id());
											soldBook.setAvailable(false);
											soldBook.setNewOwner(cd.getById(id5));
											bd.edit(soldBook);
										}
									}
									System.out.println("Your total has been payed\n");
								} else {
									System.out.println("Your total has not been payed\n");
								}
							} else {
								System.out.println("You have no books to pay for!");
							}
						}
					} catch (Exception e) {
//						System.out.println("Wrong Username or Password\n");
						log.error("Wrong Username or Password!");
					}
				} while (id5 == 0);
				break;
			case "2":
				/*
				 * make an int input of vin number with sanner and replace 0 then print the
				 * books id, title, vin number, genra, isSoftCover, isAvalible, author and
				 * expected minimum price with .toString
				 */
				System.out.println("Please enter a book vinNumber: ");
				Integer vin = Integer.valueOf(sc.nextLine());
				int i = 0;
				try {
					for (Book all : us.viewAllBooks()) {
						i++;
						if (all.getVinNumber() == vin && all.isAvailable()) {
							System.out.println("\nBook # " + i);
							System.out.println("Title: " + all.getTitle());
							System.out.println("Author: " + all.getAuthor());
							System.out.println("Genre: " + all.getGenre());
							if (all.isSoftCover()) {
								System.out.println("It is a soft bound book.");
							} else {
								System.out.println("It is a hard bound book.");
							}
							System.out.println("Price: $" + all.getPrice());
							System.out.println("Vin #: " + all.getVinNumber());
							System.out.println();
						}

					}
				} catch (Exception e) {
					log.info("This will only happen if the book donsent exist");
					break;
				}
				break;
			case "3":
				/*
				 * get a list of all the books and print the book title, vin number, genra,
				 * isSoftCover, isAvalible, author and expected minimum price with .toString
				 */
				int y = 0;
				System.out.println();
				for (Book all : us.viewAllBooks()) {
					if (all.isAvailable()) {
						y++;
						System.out.println("Book # " + y);
						System.out.println("Title: " + all.getTitle());
						System.out.println("Author: " + all.getAuthor());
						System.out.println("Genre: " + all.getGenre());
						if (all.isSoftCover()) {
							System.out.println("It is a soft bound book.");
						} else {
							System.out.println("It is a hard bound book.");
						}
						System.out.println("Price: $" + all.getPrice());
						System.out.println("Vin #: " + all.getVinNumber());
						System.out.println();
					}
				}
				break;
			case "4":
				/*
				 * make an offer with scanner for a book and replace 0 possibly allow an option
				 * to look at books with view book or view all books. or set the new offer to be
				 * available in view book and view all books
				 */
				boolean offerMade = true;
				while (offerMade) {
					System.out.println();
					for (Book all : us.viewAllBooks()) {
						if (all.isAvailable()) {
							System.out.println("Book Id# : " + all.getId());
							System.out.println("Title: " + all.getTitle());
							System.out.println("Author: " + all.getAuthor());
							System.out.println("Genre: " + all.getGenre());
							if (all.isSoftCover()) {
								System.out.println("It is a soft bound book.");
							} else {
								System.out.println("It is a hard bound book.");
							}
							System.out.println("Price: $" + all.getPrice());
							System.out.println("Vin #: " + all.getVinNumber());
							System.out.println();
						}
					}
					System.out.println("\nPlease enter the id of the book desired: ");
					Integer id1 = Integer.valueOf(sc.nextLine());
					System.out.println("Please enter the potential new price of the book desired for review.\n"
							+ "The new price can't be much less then the listed price.\n" + "The minimum price is: $"
							+ bd.getById(id1).getPrice());
					Double price1 = Double.valueOf(sc.nextLine());
					while (price1 + 10 < bd.getById(id1).getPrice()) {
						System.out.println("This is much less then the minimum price please enter a new price!!");
						price1 = Double.valueOf(sc.nextLine());
					}
					System.out.println("Please enter your Username: ");
					String username1 = sc.nextLine();
					System.out.println("Please enter your Password: ");
					String password1 = sc.nextLine();
//				Customer validCustomer = new Customer(username1, password1);
					int id2 = 0;
//				for (Customer all : cd.getAll()) {
//					if (validCustomer.getUsername().equals(all.getUsername())
//							&& validCustomer.getPassword().equals(all.getPassword())) {
//						id2 = all.getId();
//					}
//				}
					id2 = cs.specificCustomer(username1, password1).getId();
					boolean oldOffer = true;

					for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
						if (all.getC_id() == id2 && all.getB_id() == id1) {
							oldOffer = false;
							offerMade = false;
							System.out.println("You already made an offer for this book of " + all.getB_price());
						}
					}
					if (offerMade && oldOffer) {
						offerMade = false;
						us.newOffer(id1, price1, id2);
						System.out.println("Your submition has been logged please waight for aproval");
					}
				}
				break;
			case "5":
				/*
				 * look at balance of specific id of customer and show all the balances of all
				 * the books yet to be payed for display these amounts to the user with
				 * .toString
				 */
				int id4 = 0;
				do {
					System.out.println("Please enter your Username: ");
					String username2 = sc.nextLine();
					System.out.println("Please enter your Password: ");
					String password2 = sc.nextLine();
					id4 = cs.specificCustomer(username2, password2).getId();
					double totalPrice = 0;
					if (id4 != 0) {
						for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
							if (all.getC_id() == id4 && all.isB_price_accepted()) {
								System.out.println("You have : $" + all.getB_price() + " Left to pay for "
										+ bd.getById(all.getB_id()).getTitle());
								totalPrice += all.getB_price();
							}
						}
						if (totalPrice != 0) {
							System.out.println("Your total is : $" + totalPrice);
						} else {
							System.out.println("There are no books you need to pay for!!\n");
						}
					} else {
						System.out.println("Wrong Username or Password");
					}
				} while (id4 == 0);
				break;

			case "6":
				int id6 = 0;
				do {
					try {
						System.out.println("Please enter your Username: ");
						String username6 = sc.nextLine();
						System.out.println("Please enter your Password: ");
						String password6 = sc.nextLine();
						id6 = cs.specificCustomer(username6, password6).getId();
						boolean doOwn = false;
						if (id6 != 0) {
							for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
								if (all.getC_id() == id6 && bd.getById(all.getB_id()).isAvailable() == false
										&& all.isB_price_accepted()) {
									System.out.println("You own " + bd.getById(all.getB_id()).getTitle());
									doOwn = true;
								}
							}
							if (!doOwn) {
								System.out.println("You don't own any books!\n");
							}
						}
					} catch (Exception e) {
//						System.out.println("Wrong username or password please try agin!!\n");
						log.error("Wrong Username or Password! Please try agin!");
					}
				} while (id6 == 0);
				break;

			case "7":
				/*
				 * use a scanner to input the necessary info to make a book.
				 */
				System.out.println("Please enter a vinNumber: ");
				String vin1 = sc.nextLine();
				int vinNumber = Integer.parseInt(vin1);
				System.out.println("Please enter a title: ");
				String title = sc.nextLine();
				System.out.println("Choose a genre please type a number from 1-14: "); // find out how to use an enum
																						// correctly
				System.out.println(" 1. Literary Fiction\n" + " 2. Mystery\n" + " 3. Thriller\n" + " 4. Horrer\n"
						+ " 5. Historical\n" + " 6. Romance\n" + " 7. Western\n" + " 8. Bildungsroman\n"
						+ " 9. Speculative Fiction\n" + "10. Science Fiction\n" + "11. Fantasy\n" + "12. Dystopian\n"
						+ "13. Macical Realism\n" + "14. Realist Literature\n");
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
					genre = Genre.HORROR;
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

				System.out.println("Please type yes if soft cover: ");
				String SoftCover = sc.nextLine();
				Boolean isSoftCover;
				if (SoftCover.trim().toLowerCase().equals("y") || SoftCover.trim().toLowerCase().equals("yes")) {
					isSoftCover = true;
				} else {
					isSoftCover = false;
				}
				// Boolean isAvalible = true; // set in user service---
				System.out.println("Please enter Author: ");
				String author = sc.nextLine();
				System.out.println("Please enter minimum price: ");
				String minPrice = sc.nextLine();
				double price = Double.parseDouble(minPrice);
				Book newBook = new Book(vinNumber, title, genre, isSoftCover, author, price);
				us.addBook(newBook);
				break;
			case "8":
				/*
				 * remove a book based on an input id (see if i can figure out how to remove a
				 * title/vin number and not just a single book) -bonus
				 */
				System.out.println();
				for (Book all : us.viewAllBooks()) {
					if (all.isAvailable()) {

						System.out.println("Book Id# : " + all.getId());
						System.out.println("Title: " + all.getTitle());
						System.out.println("Author: " + all.getAuthor());
						System.out.println("Genre: " + all.getGenre());
						if (all.isSoftCover()) {
							System.out.println("It is a soft bound book.");
						} else {
							System.out.println("It is a hard bound book.");
						}
						System.out.println("Price: $" + all.getPrice());
						System.out.println("Vin #: " + all.getVinNumber());
						System.out.println("Please type " + all.getId() + " to delete this book!");
						System.out.println();
					}
				}
				System.out.println("Please enter the id number of the book to be deleted: ");
				Integer idNumber = Integer.valueOf(sc.nextLine());
				try {
					for (Book all : us.viewAllBooks()) {
						if (all.getId() == idNumber) {
							us.removeBook(all);
							System.out.println("Book Id# : " + all.getId() + "\n was deleted.");
							System.out.println();
						}
					}
				} catch (Exception e) {
//					System.out.println("This id donsen't exist");
					log.error("This book id dosent exist! Please try agin!");
					break;
				}
				break;
			case "9":
				/*
				 * get a list of offers that has the id of the person sending the request and
				 * there offers and say if it is accepted or rejected. assign the id of that
				 * book to the id of the customer/owner/employee this will need a lot of work.
				 */

				for (BooksToCustomer all : us.viewAllOffers()) {
					if (!all.isB_price_accepted() && bd.getById(all.getB_id()).isAvailable()) {
						System.out.println("\nOffer id# : " + all.getbooks_to_customer_id());
						System.out.println("Wanted book title : " + bd.getById(all.getB_id()).getTitle());
						System.out.println("Customer desiring book : " + cd.getById(all.getC_id()).getName());
						System.out.println("The potential price of this book is : $" + all.getB_price());
						System.out.println("Type " + all.getbooks_to_customer_id() + " to accept this offer");
					}
				}
				System.out.println("\nPlease enter the id number of the request you would like to accept. ");
				System.out.println("Any offers for the same book will be rejected.");
				System.out.println("Enter 0 if you wish to except no offer!");
				Integer idNumberOfRequest = Integer.valueOf(sc.nextLine());
				if (idNumberOfRequest != 0) {
					us.reviewOffer(idNumberOfRequest);
					System.out.println("The request has been accepted.");
				} else {
					System.out.println("You rejected the request!");
				}
				break;
			case "10":
				/*
				 * print a list of all payments made by the user and what it was payed to id,
				 * title, and vin number and remaining balance on these books
				 */
				double totalPrice3 = 0;
				for (BooksToCustomer all : bd.getAllBooksToCustomer()) {
					if (bd.getById(all.getB_id()).isAvailable() == false) {
						System.out.println("Customer : " + cd.getById(all.getC_id()).getName() + " has purchesed "
								+ bd.getById(all.getB_id()).getTitle() + " for " + all.getB_price());
						totalPrice3 += all.getB_price();
					}
				}
				System.out.println("The company has made : $" + totalPrice3);
//				us.reviewAllPayments();
				break;
			case "11":
				/*
				 * use a scanner to input the necessary info to make a book send in the id of
				 * the book to be changed and edit it send back true if successful
				 */
				for (Book all : bd.getAll()) {
					if (all.isAvailable()) {
						System.out.println("Book title: " + all.getTitle());
						System.out.println("Book id#: " + all.getId());
					}
				}
				System.out.println("Please enter the id of the book you want to edit: ");
				String newestId = sc.nextLine();
				int id3 = Integer.parseInt(newestId);
				System.out.println("Please enter an updated vinNumber: ");
				String vin2 = sc.nextLine();
				int vinNumber1 = Integer.parseInt(vin2);
				System.out.println("Please enter an updated title: ");
				String title1 = sc.nextLine();
				System.out.println("Please chooes an updated genre please type #1-14: "); // find out how to use an enum
				System.out.println(" 1. LITERARY_FICTION\n" + " 2. MYSTERY\n" + " 3. THRILLER\n" + " 4. HORRER\n"
						+ " 5. HISTORICAL\n" + " 6. ROMANCE\n" + " 7. WESTERN\n" + " 8. BILDUNGSROMAN\n"
						+ " 9. SPECULATIVE_FICTION\n" + "10. SCIENCE_FICTION\n" + "11. FANTASY\n" + "12. DYSTOPIAN\n"
						+ "13. MAGICAL_REALISM\n" + "14. REALIST_LITERATURE\n");
				String tempGenre1 = sc.nextLine();
				Genre genre1 = null;
				switch (tempGenre1) {
				case "1":
					genre1 = Genre.LITERARY_FICTION;
					break;
				case "2":
					genre1 = Genre.MYSTERY;
					break;
				case "3":
					genre1 = Genre.THRILLER;
					break;
				case "4":
					genre1 = Genre.HORROR;
					break;
				case "5":
					genre1 = Genre.HISTORICAL;
					break;
				case "6":
					genre1 = Genre.ROMANCE;
					break;
				case "7":
					genre1 = Genre.WESTERN;
					break;
				case "8":
					genre1 = Genre.BILDUNGSROMAN;
					break;
				case "9":
					genre1 = Genre.SPECULATIVE_FICTION;
					break;
				case "10":
					genre1 = Genre.SCIENCE_FICTION;
					break;
				case "11":
					genre1 = Genre.FANTASY;
					break;
				case "12":
					genre1 = Genre.DYSTOPIAN;
					break;
				case "13":
					genre1 = Genre.MAGICAL_REALISM;
					break;
				case "14":
					genre1 = Genre.REALIST_LITERATURE;
					break;
				}

				System.out.println("Please enter a if soft cover y/n: ");
				String SoftCover1 = sc.nextLine();
				Boolean isSoftCover1;
				if (SoftCover1.trim().toLowerCase().equals("y") || SoftCover1.trim().toLowerCase().equals("yes")) {
					isSoftCover1 = true;
				} else {
					isSoftCover1 = false;
				}
				System.out.println("Please enter if available: ");
				String available = sc.nextLine();
				Boolean isAvailable;
				if (available.trim().toLowerCase().equals("y") || available.trim().toLowerCase().equals("yes")) {
					isAvailable = true;
				} else {
					isAvailable = false;
				}
				// set in user service---
				System.out.println("Please enter Author: ");
				String author1 = sc.nextLine();
				System.out.println("Please enter minimum price: ");
				String minPrice1 = sc.nextLine();
				double price3 = Double.parseDouble(minPrice1);
				Book newBook1 = new Book(id3, vinNumber1, title1, genre1, isSoftCover1, isAvailable, author1, price3);
				us.editBook(newBook1);
				break;
			case "12":
				/*
				 * print a list of all payments made by all users and what it was payed to id,
				 * title, and vin number and remaining balance on these books
				 */
				us.reviewAllPayments();
				break;
			case "13":
				run = false;
				break;
			default:
				System.out.println("Invalid Input");
			}
		} while (run);
	}
}
