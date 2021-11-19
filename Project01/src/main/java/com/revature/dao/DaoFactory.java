package com.revature.dao;

import com.revature.postgres.UserPostgres;

public class DaoFactory {
	private static DaoFactory df;
	private UserDao ud;
	
	public DaoFactory() {
	}
	
	public static synchronized DaoFactory getDAOFactory() {
		if (df == null) {
			df = new DaoFactory();
		}
		return df;
	}
	
	public UserDao getUserDao() {
		if (ud == null) {
			ud = new UserPostgres();
		}
		return ud;
	}

}
