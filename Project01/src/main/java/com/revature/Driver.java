package com.revature;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.FrontController;
import com.revature.util.ConnectionUtil;

public class Driver {

	public Driver() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		FrontController running = new FrontController();
		running.run();
		try {
			Connection c = ConnectionUtil.getConnectionFromFile();
			System.out.println(c.getMetaData().getDriverName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
