package com.revature;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.models.Customer;
import com.revature.repositories.CustomerDao;
import com.revature.repositories.CustomerList;

public class CustomerDaoTest {
	
	private static CustomerDao cd;
//	private int id;
//	private String name;
//	private String username;
//	private String password;
//	private int cardNumber;
//	private Customer owner;
	
	@BeforeEach
	public void setup() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer(0, "Bob", "BestBob", "Boss", "coolguy@name.com", 1234, null));
		customers.add(new Customer(1, "Tom", "SuperTom", "Password", "extraguy@name.com", 5678, null));
		cd = new CustomerList(customers);
	}
	
	@Test
	public void getAllCheck() {
		List<Customer> expected = new ArrayList<Customer>();
		expected.add(new Customer(0, "Bob", "BestBob", "Boss", "coolguy@name.com", 1234, null));
		expected.add(new Customer(1, "Tom", "SuperTom", "Password", "extraguy@name.com", 5678, null));
		List<Customer> actual = cd.getAll();
		assertEquals(expected, actual);
	}
	
	@Test
	public void addValid() {
		List<Customer> expected = new ArrayList<Customer>();
		expected.add(new Customer(0, "Bob", "BestBob", "Boss", "coolguy@name.com", 1234, null));
		expected.add(new Customer(1, "Tom", "SuperTom", "Password", "extraguy@name.com", 5678, null));
		expected.add(new Customer(2, "Greg", "MegaGreg", "word", "happyguy@name.com", 2468, null));
		cd.add(new Customer(2, "Greg", "MegaGreg", "word", "happyguy@name.com", 2468, null));
		List<Customer> actual = cd.getAll();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getByIdValid() {
		Customer expected = new Customer(1, "Tom", "SuperTom", "Password", "extraguy@name.com", 5678, null);
		Customer actual = cd.getById(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getByIdValidTwo() {
		Customer expected = new Customer(0, "Bob", "BestBob", "Boss", "coolguy@name.com", 1234, null);
		Customer actual = cd.getById(0);
		assertEquals(expected, actual);
	}
	
	@Test
	public void editIfTrue() {
		Customer expected = new Customer(0, "Nick", "HappyNick", "word", "frankguy@name.com", 1357, null);
		Customer c = new Customer(0, "Nick", "HappyNick", "word", "frankguy@name.com", 1357, null);
		cd.edit(c);
		Customer actual = cd.getById(0);
		assertEquals(expected, actual);
	}
	
//	@Test
//	public void deleteCustomerIfTrue() {
//		List<Customer> expected = new ArrayList<Customer>();
//		expected.add(new Customer(1, "Tom", "SuperTom", "Password", "extraguy@name.com", 5678, null));
//		cd.deleteById(0);
//		List<Customer> actual = cd.getAll();
//		assertEquals(expected, actual);
//	}
}
