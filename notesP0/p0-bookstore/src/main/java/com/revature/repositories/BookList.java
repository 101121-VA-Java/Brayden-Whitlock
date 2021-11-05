package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Book;

//public class BookList implements BookDao {
//
//	public List<Book> books = new ArrayList<Book>();
//
//	public BookList() {
//	}
//
//	public BookList(List<Book> books) {
//		super();
//		this.books = books;
//	}
//
//	@Override
//	public List<Book> getAll() {
//		return this.books;
//	}
//
//	@Override
//	public Book getById(int id) {
//		for (Book b : books) {
//			if (b.getId() == id) {
//				return b;
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public int add(Book b) {
//		books.add(b);
//		return b.getId();
//	}
//
//	@Override
//	public boolean edit(Book b) {
//		for (Book f : books) {
//			if (f.getId() == b.getId() && !b.equals(f)) {
//				books.set(b.getId(), b);
//				return true;
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public boolean deleteById(int id) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}
