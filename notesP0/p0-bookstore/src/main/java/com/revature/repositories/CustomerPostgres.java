package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Customer;
import com.revature.util.ConnectionUtil;

public class CustomerPostgres implements CustomerDao {

	public CustomerPostgres() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Customer c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean edit(Customer c) {

//		update [table_name] 
//				set [col1_name] = [col1_value] where [condition];

		String sql = "update customers set c_name = ?, c_username = ?,"
				+ " c_password = ?, c_email = ?, c_cardNumber = ?, "
				+ "c_isEmployee = ? where c_id = ? returning c_id;";

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			int rowsChanged = -1; 
			ps.setString(1, c.getName());
			ps.setString(2, c.getUsername());
			ps.setString(3, c.getPassword());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getCardNumber());
			ps.setBoolean(6, c.isEmployee());
			ps.setInt(6, c.getId());

			rowsChanged = ps.executeUpdate();

			if (rowsChanged > 0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}
}
