package com.revature.services;

import com.revature.models.Customer;
import com.revature.repositories.CustomerDao;
import com.revature.repositories.CustomerList;

public class CustomerService {

	private CustomerDao cd = new CustomerList();

	public int add(Customer c) {
		return cd.add(c);
	}

	public Customer customerList(String username, String password) {
		int i = 0;
		Customer validCustomer = new Customer(username, password);
		for (Customer all : cd.getAll()) {
			if (validCustomer.getUsername().equals(all.getUsername())
					&& validCustomer.getPassword().equals(all.getPassword())) {
				return cd.getById(i);
			}
			i++;
		}
		return null;
	}
}
