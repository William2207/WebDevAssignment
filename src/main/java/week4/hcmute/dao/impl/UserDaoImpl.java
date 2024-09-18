package week4.hcmute.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import week4.hcmute.configs.DBConnectSQLServer;
import week4.hcmute.dao.dao;
import week4.hcmute.models.UserModel;

public class UserDaoImpl implements dao{

	@Override
	public UserModel findByUserName(String username) {
		String sql = "select * from users where username=?";
		
		try {
			Connection conn = new DBConnectSQLServer().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username); //truyen tham so
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
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		return null;
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
