package week4.hcmute.dao;

import week4.hcmute.models.UserModel;

public interface dao {
	UserModel findByUserName(String username);
	
	void insert(UserModel user);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	void update(UserModel user);
}
