package com.revature;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.models.Customer;
import com.revature.repositories.CustomerArray;
import com.revature.repositories.CustomerDao;

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
		Customer[] customers =  {new Customer(0, "Bob", "BestBob", "Boss", 1234, null)};
		cd = new CustomerArray(customers); 
	}
	
	@Test
	public void getAllCustomers() {
		Customer[] expected = {new Customer(0, "Bob", "BestBob", "Boss", 1234, null)};
		Customer[] actual = cd.getAllCustomers();
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void addCustomerValid() {
		Customer[] expected = {new Customer(0, "Bob", "BestBob", "Boss", 1234, null), 
				new Customer(1, "Tom", "SuperTom", "Password", 5678, null)};
		
		cd.addCustomer(new Customer(1, "Tom", "SuperTom", "Password", 5678, null));
		Customer[] actual = cd.getAllCustomers();
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void getCustomerByIdValid() {
		Customer expected = new Customer(1, "Tom", "SuperTom", "Password", 5678, null);
		Customer actual = cd.getCustomerById(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void getCustomerByIdValidTwo() {
		Customer expected = new Customer(0, "Bob", "BestBob", "Boss", 1234, null);
		Customer actual = cd.getCustomerById(0);
		assertEquals(expected, actual);
	}
	

}
