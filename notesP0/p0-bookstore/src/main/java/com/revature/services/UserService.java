package com.revature.services;

import java.util.List;

import com.revature.models.Book;
import com.revature.models.BooksToCustomer;
import com.revature.models.Customer;
import com.revature.repositories.BookDao;
//import com.revature.repositories.BookList;
import com.revature.repositories.BookPostgres;
import com.revature.repositories.CustomerDao;
//import com.revature.repositories.CustomerList;
import com.revature.repositories.CustomerPostgres;

public class UserService {

	/*
	 * 1. make payments 2. view item by vin 3. view all items 4. make an offer 5.
	 * view remaining payments 6. add items 7. delete items 8. accept/reject offer
	 * 9. view all payments 10. edit items 11. view sales history 12. add employees
	 * 13. view all items
	 */

//	private CustomerDao cd = new CustomerList();
	private CustomerDao cd = new CustomerPostgres();
	private BookDao bd = new BookPostgres();
//	private BookDao bd = new BookPostgres;

	public boolean newPayment(double payment) {
		return false;
	}

	public List<BooksToCustomer> viewAllOffers() {
		List<BooksToCustomer> booksToCustomer = null;
		booksToCustomer = bd.getAllBooksToCustomer();
		return booksToCustomer;
	}

	public List<Book> viewAllBooks() { // list of books is the return type
		List<Book> books = null;
		books = bd.getAll();
		return books;
	}

	public boolean newOffer(int bookId, double offer, int myId) {
		// make a list in the database with the id of the book and the id of the
		// customer as well as the potential price of the book
		return bd.newOffer(bookId, offer, myId);
	}
	
	public void viewOwnedItems() {
		
	}
	
	public int addBook(Book b) {

		b.setAvailable(true);
		b.setNewOwner(cd.getById(1));
//		System.out.println(bd.toString());
		return bd.add(b);

	}

	public boolean removeBook(Book b) { // good
		if (b.getId() != 1) {
			return bd.deleteById(b.getId());
		}
		return false;
	}

	public boolean reviewOffer(int requestId) { // pass in whatever is saving the offers
		BooksToCustomer newBookToCustomer = bd.getBooksToCustomerById(requestId);
		newBookToCustomer.setB_price_accepted(true);
		if (bd.editBooksToCustomer(newBookToCustomer)) {
			bd.deleteBooksToCustomerByBookId(newBookToCustomer.getB_id());
			return true;
		} else {
			return false;
		}
	}

	public void reviewAllPayments() { // return list of payments
		double total = 0;
		for(BooksToCustomer all : bd.getAllBooksToCustomer()) {
			if(bd.getById(all.getB_id()).isAvailable() == false) {
				System.out.println(cd.getById(all.getC_id()).getName() + " paid $" + all.getB_price() + " to buy " + bd.getById(all.getB_id()).getTitle());
				total += all.getB_price();
			}
		}
		System.out.println("The company has made a total of $" + total);
	}

	public boolean editBook(Book b) {
		b.setNewOwner(cd.getById(1));
		return bd.edit(b);
	}

	public void salesHistory() { // return list of sales

	}

	public Customer editCustomers(Customer c) {
		Customer newEmployee = cd.getById(c.getId());
		newEmployee.setEmployee(true);
		cd.edit(newEmployee);
		return null;
	}
}
