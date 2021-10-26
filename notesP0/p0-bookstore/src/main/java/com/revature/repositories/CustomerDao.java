package com.revature.repositories;

import com.revature.models.Customer;

public interface CustomerDao {
	Customer[] getAllCustomers();
	Customer getCustomerById(int id);
	int addCustomer(Customer c);
	boolean editCustomer(Customer c);
	boolean deleteCustomer(int id);
}
