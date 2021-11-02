package com.revature.services;

import com.revature.models.Book;
import com.revature.models.Customer;
import com.revature.repositories.BookDao;
import com.revature.repositories.BookList;
import com.revature.repositories.CustomerDao;
import com.revature.repositories.CustomerList;

public class UserService {

	/*
	 * 1. make payments 2. view item by vin 3. view all items 4. make an offer 5.
	 * view remaining payments 6. add items 7. delete items 8. accept/reject offer
	 * 9. view all payments 10. edit items 11. view sales history 12. add employees
	 * 13. view all items
	 */

	private CustomerDao cd = new CustomerList();
	private BookDao bd = new BookList();

	public boolean newPayment(double payment) {
		return false;
	}

	public Book viewBook(int vinNumber) {
		return null;
	}
	
	public Book viewAllBooks() { // list of books is the return type
		return null;
		
	}

	public double newOffer(double offer) {
		return offer;
	}

	public void viewOtherPayments() { // return list of Book.payments

	}

	public int addBook(Book b) { 
		int i = 0;
		for (Book all : bd.getAll()) {
			if (all.getId() == i) {
				i++;
			}
		}
		b.setId(i);
		b.setAvailable(true);
		System.out.println(bd.toString());
		return bd.add(b);

	}

	public boolean removeBook(Book b) { // good
		return false;

	}

	public boolean reviewOffer() { // pass in whatever is saving the offers
		return false;

	}

	public void reviewAllPayments() { // return list of payments

	}

	public boolean editBook(Book b) { //
		return false; 

	}

	public void salesHistory() { // return list of sales

	}

	public Customer addEmployee(int id) {
		return null;
	}
}
