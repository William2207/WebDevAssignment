package week4.hcmute.services;

import week4.hcmute.models.UserModel;

public interface IUserService {
	UserModel findByUserName(String username);
	UserModel login(String username, String password);
}
