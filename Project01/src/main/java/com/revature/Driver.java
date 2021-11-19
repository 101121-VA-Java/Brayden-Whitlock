package com.revature;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.controllers.AuthController;
import com.revature.controllers.UserController;
import com.revature.models.User;
import com.revature.postgres.UserPostgres;
import com.revature.util.ConnectionUtil;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;

public class Driver {

	public Driver() {
		// TODO Auto-generated constructor stub
	}

//	private static UserPostgres us = new UserPostgres();
	public static void main(String[] args) {
//		FrontController running = new FrontController();
//		running.run();
		
		Javalin app = Javalin.create((config) -> {
			config.enableCorsForAllOrigins();
			config.defaultContentType = "application/json";
		});
		app.start();
		
		app.before(ctx -> {
		    ctx.header("Access-Control-Allow-Headers", "Authorization");
		    ctx.header("Access-Control-Expose-Headers", "Authorization");
		});
		
//     need to add app.get reimbursements	
		app.routes(() -> {
			path("users", ()->{
				get(UserController::getUsers);
				post(UserController::registerUser);
				// use brackets to indicate path param name
				path("{id}",() ->{
					get(UserController::getUserById);
					post(UserController::updateUserInfo);
				});
			});
			path("auth", () ->{
				post(AuthController::login);
			});
		});
		
//		app.get("users", (ctx) ->{
//			List<User> users = us.getAll();
//			
//			ctx.json(users);
//			// implicit
//			ctx.status(HttpCode.OK);
//		});
		
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
