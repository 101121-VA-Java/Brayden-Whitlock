package com.revature.postgres;

import java.io.IOException;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.Type;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class ReimbursementPostgres implements ReimbursementDao {

	@Override
	public List<Reimbursement> getAll() {
		String sql = "select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, reimb_description, U.ers_users_id author_id, U.ers_username author, M.ers_users_id resolver_id,  M.ers_username resolver, S.reimb_status_id,  S.reimb_status Status, T.reimb_type_id, T.reimb_type R_Type\n"
				+ "	from ers_reimbursement R\n" + "	left join ers_users U on R.reimb_author = U.ers_users_id \n"
				+ "	left join ers_users M on R.reimb_resolver = M.ers_users_id\n"
				+ "	left join ers_reimbursement_status S on R.reimb_status_id = S.reimb_status_id\n"
				+ "	left join ers_reimbursement_type T on R.reimb_type_id = T.reimb_type_id;";
		List<Reimbursement> Reimbs = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				Timestamp submitted = rs.getTimestamp("reimb_submitted");
				Timestamp resolved = rs.getTimestamp("reimb_resolved");
				String descrip = rs.getString("reimb_description");
				int authorId = rs.getInt("author_id");
				String author = rs.getString("author");
				int resolverId = rs.getInt("resolver_id");
				String resolver = rs.getString("resolver");
				int statusId = rs.getInt("reimb_status_id");
				String status = rs.getString("status");
				int typeId = rs.getInt("reimb_type_id");
				String type = rs.getString("r_type");

				Reimbursement newReib = new Reimbursement(id, amount, submitted, resolved, descrip, new User(authorId, author),
						new User(resolverId, resolver), new Status(statusId, status), new Type(typeId, type));
				Reimbs.add(newReib);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return Reimbs;
	}

	@Override
	public Reimbursement getById(int id) {
		String sql = "select reimb_id, reimb_amount, reimb_submitted, reimb_resolved, "
				+ "reimb_description, U.ers_users_id author_id, U.ers_username author, "
				+ "M.ers_users_id resolver_id,  M.ers_username resolver, S.reimb_status_id, "
				+ "S.reimb_status Status, T.reimb_type_id, T.reimb_type R_Type\n" + "	from ers_reimbursement R\n"
				+ "	left join ers_users U on R.reimb_author = U.ers_users_id \n"
				+ "	left join ers_users M on R.reimb_resolver = M.ers_users_id\n"
				+ "	left join ers_reimbursement_status S on R.reimb_status_id = S.reimb_status_id\n"
				+ "	left join ers_reimbursement_type T on R.reimb_type_id = T.reimb_type_id where reimb_id = ?;";
		Reimbursement Reimb = null;

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int reimb_id = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				Timestamp submitted = rs.getTimestamp("reimb_submitted");
				Timestamp resolved = rs.getTimestamp("reimb_resolved");
				String descrip = rs.getString("reimb_description");
				int authorId = rs.getInt("author_id");
				String author = rs.getString("author");
				int resolverId = rs.getInt("resolver_id");
				String resolver = rs.getString("resolver");
				int statusId = rs.getInt("reimb_status_id");
				String status = rs.getString("status");
				int typeId = rs.getInt("reimb_type_id");
				String type = rs.getString("r_type");

				Reimb = new Reimbursement(reimb_id, amount, submitted, resolved, descrip, new User(authorId, author),
						new User(resolverId, resolver), new Status(statusId, status), new Type(typeId, type));

			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return Reimb;
	}

	public List<Reimbursement> getByAuthorId(int id) {
		String sql = "select reimb_id, reimb_amount, reimb_submitted, reimb_resolved,"
				+ " reimb_description, U.ers_users_id author_id, U.ers_username author,"
				+ " M.ers_users_id resolver_id,  M.ers_username resolver, S.reimb_status_id,"
				+ " S.reimb_status Status, T.reimb_type_id, T.reimb_type R_Type\n"
				+ "	from ers_reimbursement R\n"
				+ "	left join ers_users U on R.reimb_author = U.ers_users_id \n"
				+ "	left join ers_users M on R.reimb_resolver = M.ers_users_id\n"
				+ "	left join ers_reimbursement_status S on R.reimb_status_id = S.reimb_status_id\n"
				+ "	left join ers_reimbursement_type T on R.reimb_type_id = T.reimb_type_id where reimb_author = ?;";
		List<Reimbursement> Reimbs = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int reimb_id = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				Timestamp submitted = rs.getTimestamp("reimb_submitted");
				Timestamp resolved = rs.getTimestamp("reimb_resolved");
				String descrip = rs.getString("reimb_description");
				int authorId = rs.getInt("author_id");
				String author = rs.getString("author");
				int resolverId = rs.getInt("resolver_id");
				String resolver = rs.getString("resolver");
				int statusId = rs.getInt("reimb_status_id");
				String status = rs.getString("status");
				int typeId = rs.getInt("reimb_type_id");
				String type = rs.getString("r_type");

				Reimbursement newReib = new Reimbursement(reimb_id, amount, submitted, resolved, descrip,
						new User(authorId, author), new User(resolverId, resolver), new Status(statusId, status), new Type(typeId, type));
				Reimbs.add(newReib);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return Reimbs;
	}

	public List<Reimbursement> getByStatusId(int id) {
		String sql = "select reimb_id, reimb_amount, reimb_submitted, reimb_resolved,"
				+ " reimb_description, U.ers_users_id author_id, U.ers_username author,"
				+ " M.ers_users_id resolver_id,  M.ers_username resolver, S.reimb_status_id,"
				+ " S.reimb_status Status, T.reimb_type_id, T.reimb_type R_Type\n"
				+ "	from ers_reimbursement R\n"
				+ "	left join ers_users U on R.reimb_author = U.ers_users_id \n"
				+ "	left join ers_users M on R.reimb_resolver = M.ers_users_id\n"
				+ "	left join ers_reimbursement_status S on R.reimb_status_id = S.reimb_status_id\n"
				+ "	left join ers_reimbursement_type T on R.reimb_type_id = T.reimb_type_id where R.reimb_status_id = ?;";
		List<Reimbursement> Reimbs = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int reimb_id = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				Timestamp submitted = rs.getTimestamp("reimb_submitted");
				Timestamp resolved = rs.getTimestamp("reimb_resolved");
				String descrip = rs.getString("reimb_description");
				int authorId = rs.getInt("author_id");
				String author = rs.getString("author");
				int resolverId = rs.getInt("resolver_id");
				String resolver = rs.getString("resolver");
				int statusId = rs.getInt("reimb_status_id");
				String status = rs.getString("status");
				int typeId = rs.getInt("reimb_type_id");
				String type = rs.getString("r_type");

				Reimbursement newReimb = new Reimbursement(reimb_id, amount, submitted, resolved, descrip,
						new User(authorId, author), new User(resolverId, resolver), new Status(statusId, status), new Type(typeId, type));
				Reimbs.add(newReimb);
			}
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		return Reimbs;
	}

	@Override
	public int add(Reimbursement r) {
		Reimbursement newReib = r;
		String sql = "insert into ers_reimbursement (reimb_amount, reimb_submitted,"
				+ " reimb_description, reimb_author, reimb_status_id, reimb_type_id)"
				+ " values (?, ?, ?, ?, ?, ?) returning reimb_id;";

		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setDouble(1, r.getReimAmount());
//			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.setTimestamp(2, r.getSubmit());
//			ps.setTimestamp(3, r.getResolve());
			ps.setString(3, r.getDescrip());
			ps.setInt(4, r.getAuthor().getId());
//			ps.setInt(6, r.getResolver().getId());
			// no resolver yet take this out
//			ps.setInt(6, 0);
			ps.setInt(5, r.getStatus().getStatusId());
			ps.setInt(6, r.getType().getTypeId());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				newReib.setReimId(rs.getInt("REIMB_ID"));
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return newReib.getReimId();
	}

	@Override
	public boolean edit(Reimbursement r) {
		String sql = "update ers_reimbursement set reimb_amount = ?, reimb_submitted = ?, "
				+ "reimb_resolved = ?, reimb_description = ?, reimb_author = ?, reimb_resolver = ?, "
				+ "reimb_status_id = ?, reimb_type_id = ? where reimb_id = ?;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()) {
			PreparedStatement ps = con.prepareStatement(sql);
			int rowsChanged = -1;
			ps.setDouble(1, r.getReimAmount());
			ps.setTimestamp(2, r.getSubmit());
			ps.setTimestamp(3, r.getResolve());
			ps.setString(4, r.getDescrip());
			ps.setInt(5, r.getAuthor().getId());
			ps.setInt(6, r.getResolver().getId());
			ps.setInt(7, r.getStatus().getStatusId());
			ps.setInt(8, r.getType().getTypeId());

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
		String sql = "delete from ers_reimbursement where reimb_id = ?;";
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
