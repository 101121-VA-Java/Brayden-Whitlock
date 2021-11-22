package com.revature.services;

import java.util.List;
import java.util.stream.Collectors;

import com.revature.dao.DaoFactory;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.models.Reimbursement;
import com.revature.models.Status;

public class ReimbService {
	private ReimbursementDao rd;
	private UserDao ud;

	public ReimbService() {
		rd = DaoFactory.getDAOFactory().getReimbursementDao();
	}
	
	public List<Reimbursement> getReimbursements() {
		List<Reimbursement> reimb = rd.getAll().stream().map(u -> {
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
		r.setStatus(new Status(1, "Pending"));
		r.setAuthor(ud.getById(Integer.parseInt(token.split(":")[0])));
//		the above setAuthor may need some work 
//		i put in token to get author
		return rd.add(r);
	}
	
	public boolean updateReimb(Reimbursement r) {
		return rd.edit(r);
	}
	
	public boolean deleteReimbById(int id) {
		return rd.deleteById(id);
	}
}
