package com.revature.dao;

import com.revature.models.User;

public interface UserDao extends GenericDao<User>{
	public User getByUsername(String username);
}
