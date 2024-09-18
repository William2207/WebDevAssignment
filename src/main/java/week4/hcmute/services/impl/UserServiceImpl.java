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
		if(user!=null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}
	public static void main(String[] args) {

		try {
			IUserService userService = new UserServiceImpl();
			System.out.println(userService.login("hongphuc","123"));

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	
}
