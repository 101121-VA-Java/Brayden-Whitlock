package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.models.Customer;
import com.revature.repositories.CustomerDao;
//import com.revature.repositories.CustomerList;

public class CustomerDaoTest {

	private static CustomerDao cd;
//	private int id;
//	private String name;
//	private String username;
//	private String password;
//	private int cardNumber;
//	private boolean isEmployee;
//	private Customer owner;
//
//	@BeforeEach
//	public void setup() {
//		ArrayList<Customer> customers = new ArrayList<Customer>();
//		customers.add(new Customer(0, "Owner", "OwnerUsername", "OwnerPassword", "Owner@name.com", "12345678",false,  null));
//		customers.add(new Customer(1, "Bob", "BestBob", "Boss", "coolguy@name.com", "12345678", false, null));
//		customers.add(new Customer(2, "Tom", "SuperTom", "Password", "extraguy@name.com", "56789012", false, null));
//		cd = new CustomerList(customers);
//	}
//
//	@Test
//	public void getAllCheck() {
//		List<Customer> expected = new ArrayList<Customer>();
//		expected.add(
//				new Customer(0, "Owner", "OwnerUsername", "OwnerPassword", "Owner@name.com", "12345678",false, null));
//		expected.add(new Customer(1, "Bob", "BestBob", "Boss", "coolguy@name.com", "12345678", false, null));
//		expected.add(new Customer(2, "Tom", "SuperTom", "Password", "extraguy@name.com", "56789012", false,  null));
//		List<Customer> actual = cd.getAll();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void addValid() {
//		List<Customer> expected = new ArrayList<Customer>();
//		expected.add(
//				new Customer(0, "Owner", "OwnerUsername", "OwnerPassword", "Owner@name.com", "12345678",false, null));
//		expected.add(new Customer(1, "Bob", "BestBob", "Boss", "coolguy@name.com", "12345678", false, null));
//		expected.add(new Customer(2, "Tom", "SuperTom", "Password", "extraguy@name.com", "56789012", false, null));
//		expected.add(new Customer(3, "Greg", "MegaGreg", "word", "happyguy@name.com", "24681357", false, null));
//		cd.add(new Customer(3, "Greg", "MegaGreg", "word", "happyguy@name.com", "24681357", false, null));
//		List<Customer> actual = cd.getAll();
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void getByIdValid() {
//		Customer expected = new Customer(2, "Tom", "SuperTom", "Password", "extraguy@name.com", "56789012", false,
//				null);
//		Customer actual = cd.getById(2);
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void getByIdValidTwo() {
//		Customer expected = new Customer(1, "Bob", "BestBob", "Boss", "coolguy@name.com", "12345678", false, null);
//		Customer actual = cd.getById(1);
//		assertEquals(expected, actual);
//	}
//
//	@Test
//	public void editIfTrue() {
//		Customer expected = new Customer(1, "Nick", "HappyNick", "word", "frankguy@name.com", "13579246", false, null);
//		Customer c = new Customer(1, "Nick", "HappyNick", "word", "frankguy@name.com", "13579246", false, null);
//		cd.edit(c);
//		Customer actual = cd.getById(1);
//		assertEquals(expected.getName(), actual.getName());
//	}

//	@Test
//	public void deleteCustomerIfTrue() {
//		List<Customer> expected = new ArrayList<Customer>();
//		expected.add(new Customer(1, "Tom", "SuperTom", "Password", "extraguy@name.com", 5678, null));
//		cd.deleteById(0);
//		List<Customer> actual = cd.getAll();
//		assertEquals(expected, actual);
//	}
}
