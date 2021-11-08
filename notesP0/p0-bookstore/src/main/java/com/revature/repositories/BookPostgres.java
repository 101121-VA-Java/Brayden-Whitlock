package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Book;
import com.revature.models.BooksToCustomer;
import com.revature.models.Customer;
import com.revature.models.Genre;
import com.revature.util.ConnectionUtil;

public class BookPostgres implements BookDao {
	public BookPostgres() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Book> getAll() {
		List<Book> books = new ArrayList<>();
		String sql = "select * from books;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int b_id = rs.getInt("b_id");
				int b_vinNumber = rs.getInt("b_vinNumber");
				String b_title = rs.getString("b_title");
				Boolean b_isSoftCover = rs.getBoolean("b_isSoftCover");
				Boolean b_isAvailable = rs.getBoolean("b_isAvailable");
				String b_author = rs.getString("b_author");
				double b_price = rs.getDouble("b_price");
				int b_newOwner = rs.getInt("b_newOwner");
				Genre b_genre = null;
				String tempGenre = rs.getString("b_genre");
				switch (tempGenre) {
				case "LITERARY_FICTION":
					b_genre = Genre.LITERARY_FICTION;
					break;
				case "MYSTERY":
					b_genre = Genre.MYSTERY;
					break;
				case "THRILLER":
					b_genre = Genre.THRILLER;
					break;
				case "HORROR":
					b_genre = Genre.HORROR;
					break;
				case "HISTORICAL":
					b_genre = Genre.HISTORICAL;
					break;
				case "ROMANCE":
					b_genre = Genre.ROMANCE;
					break;
				case "WESTERN":
					b_genre = Genre.WESTERN;
					break;
				case "BILDUNGSROMAN":
					b_genre = Genre.BILDUNGSROMAN;
					break;
				case "SPECULATIVE_FICTION":
					b_genre = Genre.SPECULATIVE_FICTION;
					break;
				case "SCIENCE_FICTION":
					b_genre = Genre.SCIENCE_FICTION;
					break;
				case "FANTASY":
					b_genre = Genre.FANTASY;
					break;
				case "DYSTOPIAN":
					b_genre = Genre.DYSTOPIAN;
					break;
				case "MAGICAL_REALISM":
					b_genre = Genre.MAGICAL_REALISM;
					break;
				case "REALIST_LITERATURE":
					b_genre = Genre.REALIST_LITERATURE;
					break;
				}

				Book newBook = new Book(b_id, b_vinNumber, b_title, b_genre, b_isSoftCover, b_isAvailable, b_author,
						b_price, new Customer(b_newOwner));
				books.add(newBook);
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public Book getById(int id) {
		String sql = "select * from books where b_id = ? ";
		Book boo = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int b_id = rs.getInt("b_id");
				int b_vinNumber = rs.getInt("b_vinNumber");
				String b_title = rs.getString("b_title");
				Boolean b_isSoftCover = rs.getBoolean("b_isSoftCover");
				Boolean b_isAvailable = rs.getBoolean("b_isAvailable");
				String b_author = rs.getString("b_author");
				double b_price = rs.getDouble("b_price");
				int b_newOwner = rs.getInt("b_newOwner");
//				Genre b_genre = Genre.valueOf(rs.getString("b_genre"));
				Genre b_genre = null;
				switch (rs.getString("b_genre")) {
				case "LITERARY_FICTION":
					b_genre = Genre.LITERARY_FICTION;
					break;
				case "MYSTERY":
					b_genre = Genre.MYSTERY;
					break;
				case "THRILLER":
					b_genre = Genre.THRILLER;
					break;
				case "HORROR":
					b_genre = Genre.HORROR;
					break;
				case "HISTORICAL":
					b_genre = Genre.HISTORICAL;
					break;
				case "ROMANCE":
					b_genre = Genre.ROMANCE;
					break;
				case "WESTERN":
					b_genre = Genre.WESTERN;
					break;
				case "BILDUNGSROMAN":
					b_genre = Genre.BILDUNGSROMAN;
					break;
				case "SPECULATIVE_FICTION":
					b_genre = Genre.SPECULATIVE_FICTION;
					break;
				case "SCIENCE_FICTION":
					b_genre = Genre.SCIENCE_FICTION;
					break;
				case "FANTASY":
					b_genre = Genre.FANTASY;
					break;
				case "DYSTOPIAN":
					b_genre = Genre.DYSTOPIAN;
					break;
				case "MAGICAL_REALISM":
					b_genre = Genre.MAGICAL_REALISM;
					break;
				case "REALIST_LITERATURE":
					b_genre = Genre.REALIST_LITERATURE;
					break;
				}

				boo = new Book(b_id, b_vinNumber, b_title, b_genre, b_isSoftCover, b_isAvailable, b_author, b_price,
						new Customer(b_newOwner));
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boo;
	}

//	private int id;
//	private int vinNumber;
//	private String title;
//	private Genre genre;
//	private boolean isSoftCover;
//	private boolean isAvailable;
//	private String author;
//	private double price;
//	private Customer newOwner;			

	@Override
	public int add(Book c) {
		int genId = -1;
		String sql = "insert into books (b_vinNumber, b_title, b_genre,"
				+ "b_isSoftCover, b_isAvailable, b_author, b_price, b_newOwner) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?) returning b_id;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, c.getVinNumber());
			ps.setString(2, c.getTitle());
			ps.setString(3, c.getGenre().toString());
			ps.setBoolean(4, c.isSoftCover());
			ps.setBoolean(5, c.isAvailable());
			ps.setString(6, c.getAuthor());
			ps.setDouble(7, c.getPrice());
			ps.setInt(8, c.getNewOwner().getId());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				genId = rs.getInt("b_id");
			}

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genId;
	}

//	private int id;
//	private int vinNumber;
//	private String title;
//	private Genre genre;
//	private boolean isSoftCover;
//	private boolean isAvailable;
//	private String author;
//	private double price;
//	private Customer newOwner;			

	@Override
	public boolean edit(Book c) {
		String sql = "update books set b_vinNumber = ?, b_title = ?, "
				+ "b_genre = ?, b_isSoftCover = ?, b_isAvailable = ?,"
				+ " b_author = ?, b_price = ?, b_newOwner = ? where b_id = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			int rowsChanged = -1;
			ps.setInt(1, c.getVinNumber());
			ps.setString(2, c.getTitle());
			ps.setString(3, c.getGenre().toString());
			ps.setBoolean(4, c.isSoftCover());
			ps.setBoolean(5, c.isAvailable());
			ps.setString(6, c.getAuthor());
			ps.setDouble(7, c.getPrice());
			ps.setInt(8, c.getNewOwner().getId());
			ps.setInt(9, c.getId());

			rowsChanged = ps.executeUpdate();

			if (rowsChanged > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		String sql = "delete from books where b_id = ?;";
		int rowsChanged = -1;
		try (Connection con = ConnectionUtil.getConnectionFromFile();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rowsChanged = ps.executeUpdate();
			if (rowsChanged > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException | IOException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		}
		return false;
	}

	public boolean newOffer(int bookId, double offer, int myId) {
		String sql = "insert into books_to_customer (b_id, c_id, b_price, b_price_accepted) values (?, ?, ?, ?);";
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.setInt(2, myId);
			ps.setDouble(3, offer);
			ps.setBoolean(4, false);
			ps.executeUpdate();
			return true;
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<BooksToCustomer> getAllBooksToCustomer() {
		List<BooksToCustomer> booksToCustomers = new ArrayList<>();

		String sql = "select * from books_to_customer;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

//			book_to_customers_id serial primary key,
//			b_id integer references books(b_id),
//			c_id integer references customers(c_id),
//			b_price decimal,
//			b_price_accepted boolean

			while (rs.next()) {
				int book_to_customers_id = rs.getInt("book_to_customers_id");
				int b_id = rs.getInt("b_id");
				int c_id = rs.getInt("c_id");
				double b_price = rs.getDouble("b_price");
				boolean b_price_accepted = rs.getBoolean("b_price_accepted");

				BooksToCustomer newBooksToCustomerList = new BooksToCustomer(book_to_customers_id, b_id, c_id, b_price,
						b_price_accepted);
				booksToCustomers.add(newBooksToCustomerList);
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return booksToCustomers;
	}

	public BooksToCustomer getBooksToCustomerById(int id) {
		String sql = "select * from books_to_customer where book_to_customers_id = ? ";
		BooksToCustomer btc = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id); // 1 refers to the first '?'

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int book_to_customers_id = rs.getInt("book_to_customers_id");
				int b_id = rs.getInt("b_id");
				int c_id = rs.getInt("c_id");
				double b_price = rs.getDouble("b_price");
				boolean b_price_accepted = rs.getBoolean("b_price_accepted");

				btc = new BooksToCustomer(book_to_customers_id, b_id, c_id, b_price, b_price_accepted);
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return btc;
	}
	

	public boolean editBooksToCustomer(BooksToCustomer btc) {
		String sql = "update books_to_customer set b_id = ?," + " c_id = ?, b_price = ?, b_price_accepted = ?"
				+ " where book_to_customers_id = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			int rowsChanged = -1;
			ps.setInt(1, btc.getB_id());
			ps.setInt(2, btc.getC_id());
			ps.setDouble(3, btc.getB_price());
			ps.setBoolean(4, btc.isB_price_accepted());
			ps.setInt(5, btc.getbooks_to_customer_id());

			rowsChanged = ps.executeUpdate();

			if (rowsChanged > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteBooksToCustomerByBookId(int b_id) {
		String sql = "delete from books_to_customer where b_id = ? and b_price_accepted = false;";
		int rowsChanged = -1;
		try (Connection con = ConnectionUtil.getConnectionFromFile();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, b_id);
			rowsChanged = ps.executeUpdate();
			if (rowsChanged > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException | IOException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		}
		return false;
	}
}
