package com.revature.repositories;

import com.revature.models.Customer;

public class CustomerArray{ //implements CustomerDao {

//	private Customer[] customer = new Customer[0];
//
//	public CustomerArray() {
//	}
//
//	public CustomerArray(Customer[] customer) {
//		super();
//		this.customer = customer;
//	}
//	
//
//	@Override
//	public Customer[] getAllCustomers() {
//		return this.customer;
//	}
//
//	@Override
//	public Customer getCustomerById(int id) {
//		return this.customer[id];
//	}
//
//	@Override
//	public int addCustomer(Customer c) {
//		Customer[] temp = new Customer[customer.length + 1];
//		int i = 0;
//		for (; i < customer.length; i++) {
//			temp[i] = customer[i];
//		}
//		c.setId(i);
//		temp[i] = c;
//		customer = temp;
//
//		return i;
//	}
//
//	@Override
//	public boolean editCustomer(Customer c) {
//
//		return false;
//	}
//
//	@Override
//	public boolean deleteCustomer(int id) {
//		Customer[] temp = new Customer[customer.length + 1];
//		for (int i = 0; i < customer.length; i++) {
//			temp[i] = customer[i];
//			if (customer[i].getId() == id) {
//				customer[i] = customer[customer.length - 1];
//				return true;
//			}
//		}
//		return false;
//	}
}
