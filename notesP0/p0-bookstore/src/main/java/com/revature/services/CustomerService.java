package com.revature.services;

import com.revature.models.Customer;
import com.revature.repositories.CustomerArray;
import com.revature.repositories.CustomerDao;

public class CustomerService {

	private CustomerDao cd = new CustomerArray();

	public int addCustomer(Customer c) {
		return cd.addCustomer(c);
	}

	public Customer customerList(String username, String password) {
		int i = 0;
		Customer validCustomer = new Customer(username, password);
		for (Customer all : cd.getAllCustomers()) {
			if (validCustomer.getUsername().equals(all.getUsername())
					&& validCustomer.getPassword().equals(all.getPassword())) {
				return cd.getCustomerById(i);
			}
			i++;
		}
		return null;
	}
}
