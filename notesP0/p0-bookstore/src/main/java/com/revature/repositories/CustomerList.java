package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;

public class CustomerList implements CustomerDao {

	public List<Customer> customer = new ArrayList<Customer>();

	public CustomerList() {
	}

	public CustomerList(List<Customer> customer) {
		super();
		this.customer = customer;
	}

	@Override
	public List<Customer> getAll() {
		return this.customer;
	}

	@Override
	public Customer getById(int id) {
		for (Customer c : customer) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}

	@Override
	public int add(Customer c) {
		customer.add(c);
		return c.getId();
	}

	@Override
	public boolean edit(Customer c) {
		for (Customer f : customer) {
			if (f.getId() == c.getId() && !c.equals(f)) {
				customer.set(c.getId(), c);
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
