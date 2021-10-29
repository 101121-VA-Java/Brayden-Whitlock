package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;

public class CustomerList implements CustomerDao {

	public List<Customer> customers = new ArrayList<Customer>();

	public CustomerList() {
	}

	public CustomerList(List<Customer> customers) {
		super();
		this.customers = customers;
	}

	@Override
	public List<Customer> getAll() {
		return this.customers;
	}

	@Override
	public Customer getById(int id) {
		for (Customer c : customers) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}

	@Override
	public int add(Customer c) {
		customers.add(c);
		return c.getId();
	}

	@Override
	public boolean edit(Customer c) {
		for (Customer f : customers) {
			if (f.getId() == c.getId() && !c.equals(f)) {
				customers.set(c.getId(), c);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
