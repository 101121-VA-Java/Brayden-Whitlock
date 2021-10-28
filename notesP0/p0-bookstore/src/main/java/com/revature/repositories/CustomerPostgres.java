package com.revature.repositories;

import java.util.List;

import com.revature.models.Customer;

public class CustomerPostgres implements CustomerDao  {

	public CustomerPostgres() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Customer c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean edit(Customer c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
