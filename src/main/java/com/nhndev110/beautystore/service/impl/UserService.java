package com.nhndev110.beautystore.service.impl;

import com.nhndev110.beautystore.core.BaseService;
import com.nhndev110.beautystore.dao.impl.UserDAO;
import com.nhndev110.beautystore.dto.UserDTO;
import com.nhndev110.beautystore.model.UserModel;
import com.nhndev110.beautystore.service.IUserService;
import jakarta.inject.Inject;

public class UserService extends BaseService<UserDTO, UserModel> implements IUserService {

	@Inject
	private UserDAO userDAO;

	@Override
	public UserDTO getUserByEmailOrPhone(String emailOrPhone) {
		return convertToDTO(userDAO.getByEmailOrPhone(emailOrPhone));
	}

	@Override
	public UserDTO getUserByEmailOrPhoneAndPassword(String emailOrPhone, String password) {
    UserModel userModel = userDAO.getByEmailOrPhoneAndPassword(emailOrPhone, password);
    return (userModel == null) ? null : convertToDTO(userModel);
	}
	
	@Override
	public UserDTO getUserByRememberToken(String rememberToken) {
		return convertToDTO(userDAO.getByRememberToken(rememberToken));
	}

	@Override
	public UserDTO insertUser(UserModel user) {
		long userId = userDAO.insert(user);
		return convertToDTO(userDAO.getById(userId));
	}
	
	@Override
	public boolean updateUserOnlyRememberToken(UserDTO user) {
		UserModel oldUser = userDAO.getById(user.getId());
		oldUser.setRememberToken(user.getRememberToken());
		return userDAO.update(oldUser);
	}

	@Override
	protected UserDTO convertToDTO(UserModel objectModel) {
		return new UserDTO(
			objectModel.getId(),
			objectModel.getName(),
			objectModel.getEmail(),
			objectModel.getPhone(),
			objectModel.getAddress(),
			objectModel.getRole(),
			objectModel.getRememberToken()
		);
	}

}
