package com.revature.services;

import com.revature.exceptions.UsernameInUseException;
import com.revature.models.User;
import com.revature.repositories.UserDao;
import com.revature.repositories.UserPostgres;

public class UserService {

	private UserDao cd = new UserPostgres();

	public int add(User c) throws UsernameInUseException {

		for (User all : cd.getAll()) {
			if (c.getUsername().equals(all.getUsername())) {
				throw new UsernameInUseException();
			}
		}
		c.setRole(null); // need to add the correct object here
		return cd.add(c);
	}

	public User specificCustomer(String username, String password) {
		User validCustomer = new User(username, password);
		for (User all : cd.getAll()) {
			if (validCustomer.getUsername().equals(all.getUsername())
					&& validCustomer.getPassword().equals(all.getPassword())) {
				return all;
			}
		}
		return null;
	}
}
