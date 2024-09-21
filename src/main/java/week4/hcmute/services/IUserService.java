package week4.hcmute.services;

import week4.hcmute.models.UserModel;

public interface IUserService {
	UserModel findByUserName(String username);
	UserModel login(String username, String password);
	
	void insert(UserModel user);
	boolean register(String email, String password, String username, String fullname, String phone);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	void update(UserModel user);
}
