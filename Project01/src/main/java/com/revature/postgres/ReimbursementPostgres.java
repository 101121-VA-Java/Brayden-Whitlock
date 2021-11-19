package com.revature.postgres;

import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;

public class ReimbursementPostgres implements ReimbursementDao{

	public ReimbursementPostgres() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Reimbursement> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursement getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Reimbursement c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean edit(Reimbursement c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
