package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao extends GenericDao<Reimbursement>{
	public List<Reimbursement> getByStatusId(int id);
	public List<Reimbursement> getByAuthorId(int id);
}
