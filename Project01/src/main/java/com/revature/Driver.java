package com.revature;

import static io.javalin.apibuilder.ApiBuilder.*;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbController;
import com.revature.controllers.UserController;
import com.revature.models.Reimbursement;
import com.revature.postgres.ReimbursementPostgres;
import com.revature.util.ConnectionUtil;

import io.javalin.Javalin;

public class Driver {

	public Driver() {
		// TODO Auto-generated constructor stub
	}

//	private static UserPostgres us = new UserPostgres();
	private static ReimbursementPostgres rp = new ReimbursementPostgres();

	public static void main(String[] args) {
//		FrontController running = new FrontController();
//		running.run();

//		use to test reimbursement postgres
//		

		List<Reimbursement> Remis = rp.getAll();

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
			path("users", () -> {
				post(UserController::registerUser);
				get(UserController::getUsers);
				

				// use brackets to indicate path param name
				path("{id}", () -> {
					get(UserController::getUserById);
					put(UserController::updateUserInfo);
//					delete(UserController::deleteUserById);
				});
			});
			path("reimbursements", () -> {
				post(ReimbController::addReimb);
				get(ReimbController::getReimbs);
				// use brackets to indicate path param name
				path("{id}", () -> {
					get(ReimbController::getReimbById);
					put(ReimbController::updateReimb);
//					delete(ReimbController::deleteReimbById);

				});
				path("author", () -> {
					path("{id}", () -> {
						get(ReimbController::getReimbByAuthorId);
						put(ReimbController::updateReimb);
					});
				});
				path("status", () -> {
					path("{id}", () -> {
						get(ReimbController::getReimbByStatusId);
						put(ReimbController::updateReimb);
					});
				});
			});
			path("auth", () -> {
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

		for (Reimbursement r : Remis) {
			System.out.println("UserId: " + r.getReimId() + " Amount: " + r.getReimAmount() + " Submitted: "
					+ r.getSubmit() + " Resolved: " + r.getResolve() + " Descrip: " + r.getDescrip() + " Author: "
					+ r.getAuthor().getUsername() + " Resolver: " + r.getResolver().getUsername() + " Status Id: "
					+ r.getStatus().getStatusId() + " Status: " + r.getStatus().getStatus() + " Type Id: "
					+ r.getType().getTypeId() + " Type: " + r.getType().getType());
		}
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
