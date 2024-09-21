package week4.hcmute.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import week4.hcmute.configs.DBConnectSQLServer;
import week4.hcmute.dao.dao;
import week4.hcmute.models.UserModel;

public class UserDaoImpl implements dao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public UserModel findByUserName(String username) {
		String sql = "select * from users where username=?";
		try {
			conn = new DBConnectSQLServer().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username); // truyen tham so
			ResultSet rs = ps.executeQuery(); // thuc thi phat bieu prepare roi dua kq vao resultset
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFullname(rs.getString("fullname"));
				user.setImages(rs.getString("images"));
				user.setPhone(rs.getString("phone"));
				user.setRoleid(rs.getInt("roleid"));
				user.setCreatedate(rs.getDate("createdate"));

				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// insert
	public void insert(UserModel user) {
		String sql = "INSERT INTO [users](email, username, fullname, password, images, roleid,phone, createdate) VALUES (?,?,?,?,?,?,?,?)";
		try {
			conn = new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getFullname());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getImages());
			ps.setInt(6, user.getRoleid());
			ps.setString(7, user.getPhone());
			ps.setDate(8, user.getCreatedate());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// update
	public void update(UserModel user) {
		String sql = "UPDATE [dbo].[users]\r\n" + "   SET [password] = ?\r\n" + " WHERE email=?";
		try {
			conn = new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getEmail());
			ps.executeUpdate();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "select * from [users] where email = ?";
		try {
			conn = new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;
	}

	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "select * from [users] where username = ?";
		try {
			conn = new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception ex) {
		}
		return duplicate;
	}

	public static void main(String[] args) {

		try {
			dao userDao = new UserDaoImpl();
			System.out.println(userDao.findByUserName("hongphuc"));

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
}
