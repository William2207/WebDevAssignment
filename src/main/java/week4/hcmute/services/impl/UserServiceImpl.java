package week4.hcmute.services.impl;

import week4.hcmute.dao.dao;
import week4.hcmute.dao.impl.UserDaoImpl;
import week4.hcmute.models.UserModel;
import week4.hcmute.services.IUserService;

public class UserServiceImpl implements IUserService {
	dao userDao = new UserDaoImpl();

	@Override
	public UserModel findByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(username);
	}

	@Override
	public UserModel login(String username, String password) {
		// TODO Auto-generated method stub
		UserModel user = this.findByUserName(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public boolean register(String username, String password, String email, String fullname, String phone) {
		if (userDao.checkExistUsername(username)) {
			return false;
		}
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		userDao.insert(new UserModel(username, email, password, fullname, null, 1, phone, date));
		return true;
	}

	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public void insert(UserModel user) {
		userDao.insert(user);
	}
	@Override
	public void update(UserModel user) {
		userDao.update(user);
	}
	
	public static void main(String[] args) {

		try {
			IUserService userService = new UserServiceImpl();
			System.out.println(userService.login("hongphuc", "123"));

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

}
