package com.nhndev110.beautystore.dao;

import java.util.List;
import com.nhndev110.beautystore.model.UserModel;

public interface IUserDAO {

	public List<UserModel> getAll();
	
	public UserModel getById(long id);
	
	public UserModel getByAuthToken(String authToken);
	
	public UserModel getByRememberToken(String rememberToken);

	public UserModel getByEmailOrPhoneAndPassword(String emailOrPhone, String password);

	public UserModel getByEmailOrPhone(String emailOrPhone);

	public Long insert(UserModel userModel);
	
	public boolean update(UserModel userModel);

}
