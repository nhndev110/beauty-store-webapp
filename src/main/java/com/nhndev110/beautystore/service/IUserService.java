package com.nhndev110.beautystore.service;

import com.nhndev110.beautystore.dto.UserDTO;
import com.nhndev110.beautystore.model.UserModel;

public interface IUserService {

	public UserDTO getUserByEmailOrPhone(String emailOrPhone);

	public UserDTO getUserByEmailOrPhoneAndPassword(String emailOrPhone, String password);
	
	public UserDTO insertUser(UserModel user);

	public UserDTO getUserByRememberToken(String rememberToken);
	
	public boolean updateUserOnlyRememberToken(UserDTO user);

}
