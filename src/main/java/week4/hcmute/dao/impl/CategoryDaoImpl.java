package week4.hcmute.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import week4.hcmute.configs.DBConnectSQLServer;
import week4.hcmute.dao.ICategoryDao;
import week4.hcmute.models.CatogryModel;

public class CategoryDaoImpl implements ICategoryDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<CatogryModel> findAll() {
		String sql = "Select * from Categories";
		List<CatogryModel> list = new ArrayList<>();
		try {
			conn = new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				CatogryModel category = new CatogryModel();
				category.setCategoryid(rs.getInt("categoryID"));
				category.setCategoryname(rs.getString("categoryName"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));
				list.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public CatogryModel findById(int id) {
		String sql = "Select * from Categories where categoryID=?";

		try {
			conn = new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			CatogryModel category = new CatogryModel();
			while (rs.next()) {

				category.setCategoryid(rs.getInt("categoryID"));
				category.setCategoryname(rs.getString("categoryName"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));

			}
			conn.close();
			ps.close();
			rs.close();
			return category;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public CatogryModel findByname(String name) {
		String sql = "select * from Categories where categoryName=?";

		try {
			conn = new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			CatogryModel category = new CatogryModel();
			while (rs.next()) {

				category.setCategoryid(rs.getInt("categoryID"));
				category.setCategoryname(rs.getString("categoryName"));
				category.setImages(rs.getString("images"));
				category.setStatus(rs.getInt("status"));

			}
			conn.close();
			ps.close();
			rs.close();
			return category;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void insert(CatogryModel category) {
		String sql = "INSERT INTO Categories(categoryName,images,status) VALUES (?,?,?)";

		try {
			conn = new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryname());
			ps.setString(2, category.getImages());
			ps.setInt(3, category.getStatus());
			ps.executeUpdate();
			conn.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(CatogryModel category) {
		// TODO Auto-generated method stub
		String sql = " UPDATE Categories set categoryName=?, images=?, status=? Where categoryID=?";

		try {
			conn = new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, category.getCategoryname());
			ps.setString(2, category.getImages());
			ps.setInt(3, category.getStatus());
			ps.setInt(4, category.getCategoryid());
			ps.executeQuery();

			conn.close();
			ps.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Categories WHERE categoryID = ?";
		try {
			conn = new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateStatus(int id, int status) {
		// TODO Auto-generated method stub
		String sql = " update Categories set status=? where categoryID=? ";

		try {
			conn = new DBConnectSQLServer().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, id);
			ps.executeQuery();

			conn.close();
			ps.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
