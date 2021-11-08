package com.revature.repositories;

import java.util.List;

import com.revature.models.Book;
import com.revature.models.BooksToCustomer;

public interface BookDao extends GenericDao<Book>{
	public boolean newOffer(int bookId, double offer, int myId);
	public List<BooksToCustomer> getAllBooksToCustomer();
	public BooksToCustomer getBooksToCustomerById(int id);
	public boolean editBooksToCustomer(BooksToCustomer btc);
	public boolean deleteBooksToCustomerByBookId(int b_id);
}
