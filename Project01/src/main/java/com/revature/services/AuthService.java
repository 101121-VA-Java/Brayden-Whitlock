package com.revature.services;

import java.util.Arrays;

import com.revature.dao.DaoFactory;
import com.revature.dao.UserDao;
import com.revature.models.User;

public class AuthService {

	private UserDao ud;

	public AuthService() {
		ud = DaoFactory.getDAOFactory().getUserDao();
	}

	public String login(String username, String password) {
		System.out.println("Auth Service Login");

		String token = null;
//		User principle = null;
		User principle = ud.getByUsername(username);
		// change for statement later to get employee by username
//		for (User all : ud.getAll()) {
//			if (all.getUsername().equals(username)) {
//				principle = all;
//			}
//		}
		if (principle != null && principle.getPassword().equals(password)) {
			token = principle.getId() + ":" + principle.getRole().getRoleId();
		}
		return token;
	}

//	@SuppressWarnings("unlikely-arg-type")
	public boolean checkPermission(String token, int... allowedRoles) {
		if (token == null) {
			return false;
		}
//		this splits the token into two parts seprated by the : and puts it into an array for later use
		String[] info = token.split(":");

//		used to check the id of the user witch is the fist int in info
		int token_id = Integer.parseInt(info[0]);

//		used to check the id of the role of the user witch is stored in the second space of info
		int token_role = Integer.parseInt(info[1]);

		User principal = ud.getById(token_id);

//      may need to do some work on this 
		if (principal != null && token_role == principal.getRole().getRoleId()
				&& Arrays.asList(allowedRoles).contains(token_role)) {
			return true;
		}
		return false;
	}
}
