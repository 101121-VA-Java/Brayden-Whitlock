package com.revature.services;

import com.revature.exceptions.UsernameInUseException;
import com.revature.models.Customer;
import com.revature.repositories.CustomerDao;
import com.revature.repositories.CustomerList;

public class CustomerService {

	private CustomerDao cd = new CustomerList();

	public int add(Customer c) throws UsernameInUseException {
		int i = 0;
		for (Customer all : cd.getAll()) {
			if (c.getUsername().equals(all.getUsername())) {
				throw new UsernameInUseException();
			}
			if (all.getId() == i) {
				i++;
			}
		}
		c.setId(i);
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
