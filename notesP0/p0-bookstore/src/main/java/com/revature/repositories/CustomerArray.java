package com.revature.repositories;

import com.revature.models.Customer;

public class CustomerArray implements CustomerDao {
	
	private Customer[] customer;

	public CustomerArray() {
		// TODO Auto-generated constructor stub
	}
	
	public CustomerArray(Customer[] customer) {
		super();
		this.customer = customer;
	}

	@Override
	public Customer[] getAllCustomers() {
		return this.customer;
	}

	@Override
	public Customer getCustomerById(int id) {
		return this.customer[id];
	}

	@Override
	public int addCustomer(Customer c) {
		// TODO Auto-generated method stub
		Customer[] temp = new Customer[customer.length + 1];
		int i = 0;
		for(; i < customer.length; i++) {
			temp[i] = customer[i];
		}
		c.setId(i);
		temp[i] = c;
		customer = temp;
		
		return i;
	}

	@Override
	public boolean editCustomer(Customer c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCustomer(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
