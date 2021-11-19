package com.revature.services;

import java.util.List;
import java.util.stream.Collectors;

import com.revature.dao.DaoFactory;
import com.revature.dao.UserDao;
import com.revature.exceptions.UsernameInUseException;
import com.revature.models.Role;
import com.revature.models.User;

public class UserService {
	private UserDao cd;

	public UserService() {
		cd = DaoFactory.getDAOFactory().getUserDao();
	}

	public List<User> getUsers() {
		List<User> users = cd.getAll().stream().map(u -> {
			u.setPassword(null);
			return u;
		}).collect(Collectors.toList());
		return users;
	}

	public User getUserById(int id) {
		User u = cd.getById(id);
		if (u != null) {
			u.setPassword(null);
		}
		return u;
	}

	public int addUser(User c) throws UsernameInUseException {
		for (User all : cd.getAll()) {
			if (c.getUsername().equals(all.getUsername())) {
				throw new UsernameInUseException();
			}
		}
		c.setRole(new Role(1)); // need to add the correct object here
		return cd.add(c);
	}

	public User getUserByUAP(String username, String password) {
		User validCustomer = new User(username, password);
		for (User all : cd.getAll()) {
			if (validCustomer.getUsername().equals(all.getUsername())
					&& validCustomer.getPassword().equals(all.getPassword())) {
				return all;
			}
		}
		return null;
	}

	public boolean updateUser(User u) {
		return cd.edit(u);
	}
}
