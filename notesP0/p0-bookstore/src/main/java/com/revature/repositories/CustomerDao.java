package com.revature.repositories;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerDao extends GenericDao<Customer>{
	List<Customer> getAll();
	Customer getById(int id);
	int add(Customer c);
	boolean edit(Customer c);
	boolean deleteById(int id);
}
