package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.services.AuthService;
import com.revature.services.ReimbService;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class ReimbController {

	private static ReimbService rs = new ReimbService();
	private static AuthService as = new AuthService();

	public static void getReimbs(Context ctx) {
		String token = ctx.header("Authorization");

		if (!as.checkPermission(token, 2)) {
			ctx.status(HttpCode.UNAUTHORIZED);
			return;
		}

		List<Reimbursement> r = rs.getReimbursements();
		ctx.json(r);
		ctx.status(HttpCode.OK);
	}

	public static void getReimbById(Context ctx) {

		String token = ctx.header("Authorization");

		if (!as.checkPermission(token, 2)) {
			ctx.status(HttpCode.UNAUTHORIZED);
			return;
		}

		int rId = Integer.parseInt(ctx.pathParam("id"));
		Reimbursement r = rs.getReimbById(rId);
		if (r != null) {
			ctx.json(r);
			ctx.status(HttpCode.OK);
		} else {
//			ctx.status(404);
			ctx.status(HttpCode.NOT_FOUND);
		}
	}

	public static void getReimbByAuthorId(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));
		List<Reimbursement> r = rs.getReimbByAuthorId(id);
		if (r != null) {
			ctx.json(r);
			ctx.status(HttpCode.OK);
		} else {
//			ctx.status(404);
			ctx.status(HttpCode.NOT_FOUND);
		}
	}

	public static void getReimbByStatusId(Context ctx) {

		String authorId = ctx.queryParam("author_id");
		List<Reimbursement> r = null;
		List<Reimbursement> statusAndAuthorList = new ArrayList<>();
//		System.out.println("this is a test: " + authorId);
		int statusId = Integer.parseInt(ctx.pathParam("id"));
		if (authorId != null) {
			int authorNumber = Integer.parseInt(authorId);
			r = rs.getReimbByAuthorId(authorNumber);
			for (Reimbursement statusAndAuthor : r) {
				if (statusAndAuthor.getStatus().getStatusId() == statusId) {
					statusAndAuthorList.add(statusAndAuthor);
				}
			}
			ctx.json(statusAndAuthorList);
			ctx.status(HttpCode.OK);
//			System.out.println("this is a test2: " + r);
		} else {
			r = rs.getReimbByStatusId(statusId);
			if (r != null) {
				ctx.json(r);
				ctx.status(HttpCode.OK);
			} else {
				ctx.status(HttpCode.NOT_FOUND);
			}
		}
	}

	public static void addReimb(Context ctx) {
		String token = ctx.header("Authorization");
		Reimbursement newReimb = null;

		newReimb = rs.getReimbById(rs.addReimb(token, ctx.bodyAsClass(Reimbursement.class)));

		if (newReimb == null) {
			ctx.status(HttpCode.BAD_REQUEST);
		} else {
			ctx.status(HttpCode.CREATED);
		}
	}

	public static void updateReimb(Context ctx) {
		String token = ctx.header("Authorization");

		int id = Integer.parseInt(ctx.pathParam("id"));

		Reimbursement r = ctx.bodyAsClass(Reimbursement.class);

		r.setReimId(id);

		if (rs.updateReimb(token, r)) {
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(400);
		}
	}

	public static void deleteReimbById(Context ctx) {

	}
}
