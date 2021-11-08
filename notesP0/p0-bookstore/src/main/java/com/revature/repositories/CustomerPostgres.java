package com.revature.repositories;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.util.ConnectionUtil;

public class CustomerPostgres implements CustomerDao {
//	List<Customer> customers = new ArrayList<>();

	public CustomerPostgres() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Customer> getAll() {
		List<Customer> customers = new ArrayList<>();
		String sql = "select * from customers;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int c_id = rs.getInt("c_id");
				String c_name = rs.getString("c_name");
				String c_username = rs.getString("c_username");
				String c_password = rs.getString("c_password");
				String c_email = rs.getString("c_email");
				String c_cardNumber = rs.getString("c_cardNumber");
				Boolean c_isEmployee = rs.getBoolean("c_isEmployee");
				int c_owner = rs.getInt("c_owner");

				Customer newCustomer = new Customer(c_id, c_name, c_username, c_password, c_email, c_cardNumber,
						c_isEmployee, new Customer(c_owner));
				customers.add(newCustomer);
			}
		} catch (SQLException | IOException c) {
			c.printStackTrace();
		}
		return customers;
	}

	@Override
	public Customer getById(int id) {
		String sql = "select * from customers where c_id = ? ";
		Customer cus = null;
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id); // 1 refers to the first '?'

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int c_id = rs.getInt("c_id");
				String c_name = rs.getString("c_name");
				String c_username = rs.getString("c_username");
				String c_password = rs.getString("c_password");
				String c_email = rs.getString("c_email");
				String c_cardNumber = rs.getString("c_cardNumber");
				Boolean c_isEmployee = rs.getBoolean("c_isEmployee");
				int c_owner = rs.getInt("c_owner");

				cus = new Customer(c_id, c_name, c_username, c_password, c_email, c_cardNumber, c_isEmployee,
						new Customer(c_owner));
			}
		} catch (SQLException | IOException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		}
		return cus;
	}

	@Override
	public int add(Customer c) {
		int genId = -1;
		String sql = "insert into customers (c_name, c_username, c_password, c_email, c_cardNumber)"
				+ "values (?, ?, ?, ?, ?) returning c_id;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setString(2, c.getUsername());
			ps.setString(3, c.getPassword());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getCardNumber());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				genId = rs.getInt("c_id");
			}

		} catch (SQLException | IOException c1) {
			// TODO Auto-generated catch block
			c1.printStackTrace();
		}
		return genId;
	}

	@Override
	public boolean edit(Customer c) {
		String sql = "update customers set c_name = ?, c_username = ?,"
				+ " c_password = ?, c_email = ?, c_cardNumber = ?," 
				+ " c_isEmployee = ? where c_id = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			int rowsChanged = -1;
			ps.setString(1, c.getName());
			ps.setString(2, c.getUsername());
			ps.setString(3, c.getPassword());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getCardNumber());
			ps.setBoolean(6, c.isEmployee());
			ps.setInt(7, c.getId());

			rowsChanged = ps.executeUpdate();

			if (rowsChanged > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException | IOException c1) {
			c1.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		String sql = "delete from customers where c_id = ?;";
		int rowsChanged = -1;
		try (Connection con = ConnectionUtil.getConnectionFromFile();) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rowsChanged = ps.executeUpdate();
			if (rowsChanged > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException | IOException c) {
			// TODO Auto-generated catch block
			c.printStackTrace();
		}
		return false;
	}
}
