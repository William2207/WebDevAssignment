package week4.hcmute.dao;

import week4.hcmute.models.UserModel;

public interface dao {
	UserModel findByUserName(String username);
	
}
