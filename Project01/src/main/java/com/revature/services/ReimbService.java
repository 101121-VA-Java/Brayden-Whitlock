package com.revature.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.dao.DaoFactory;
import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.Type;
import com.revature.models.User;

public class ReimbService {
	private ReimbursementDao rd;

	public ReimbService() {
		rd = DaoFactory.getDAOFactory().getReimbursementDao();
	}
	
	public List<Reimbursement> getReimbursements() {
		List<Reimbursement> reimb = rd.getAll().stream().map(u -> {
//			User f = ud.getByUsername(u.getAuthor().getUsername());
//			u.setAuthor(f);
			return u;
		}).collect(Collectors.toList());
		return reimb;
	}
	
	public Reimbursement getReimbById(int id){
		Reimbursement r = rd.getById(id);
		return r;
	}
	
	public List<Reimbursement> getReimbByAuthorId(int userId) {
		List<Reimbursement> r = rd.getByAuthorId(userId);
		return r;
	}
	
	public List<Reimbursement> getReimbByStatusId(int statusId) {
		List<Reimbursement> r = rd.getByStatusId(statusId);
		return r;
	}
	
	public int addReimb(String token, Reimbursement r) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		r.setStatus(new Status(1));
//		r.setResolver(new User(0));
//		r.setAuthor(ud.getById(Integer.parseInt(token.split(":")[0])));
		r.setAuthor(new User(Integer.parseInt(token.split(":")[0])));
		
//		if(r.getType().getType().equals("Lodging")) {
//			r.setType(new Type(1));
//		}
//		else if(r.getType().getType().equals("Travel")) {
//			r.setType(new Type(2));
//		}
//		else if(r.getType().getType().equals("Food")) {
//			r.setType(new Type(3));
//		}
//		else {
//			r.setType(new Type(4));
//		}
		r.setSubmit(timestamp);
//		the above setAuthor may need some work 
//		i put in token to get author
		return rd.add(r);
	}
	
	public boolean updateReimb(String token, Reimbursement r) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		r.setResolve(timestamp);
		r.setResolver(new User(Integer.parseInt(token.split(":")[0])));
		return rd.edit(r);
	}
	
	public boolean deleteReimbById(int id) {
		return rd.deleteById(id);
	}
}
