package com.nhndev110.beautystore.dao.impl;

import com.nhndev110.beautystore.core.AbstractDAO;
import java.util.List;
import com.nhndev110.beautystore.model.UserModel;
import com.nhndev110.beautystore.dao.IUserDAO;
import com.nhndev110.beautystore.model.mapper.UserModelMapper;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

  @Override
  public List<UserModel> getAll() {
    String sql = "SELECT * FROM users";
    return queryDAO(sql, new UserModelMapper());
  }

  @Override
  public UserModel getById(long id) {
    String sql = "SELECT * FROM users WHERE id = ?";
    List<UserModel> userModels = queryDAO(sql, new UserModelMapper(), id);
    return userModels.isEmpty() ? null : userModels.get(0);
  }

  @Override
  public UserModel getByAuthToken(String authToken) {
    String sql = "SELECT * FROM users WHERE auth_token = ?";
    List<UserModel> userModels = queryDAO(sql, new UserModelMapper(), authToken);
    return userModels.isEmpty() ? null : userModels.get(0);
  }

  @Override
  public UserModel getByRememberToken(String rememberToken) {
    String sql = "SELECT * FROM users WHERE remember_token = ?";
    List<UserModel> userModels = queryDAO(sql, new UserModelMapper(), rememberToken);
    return userModels.isEmpty() ? null : userModels.get(0);
  }

  @Override
  public UserModel getByEmailOrPhoneAndPassword(String emailOrPhone, String password) {
    String sql;

    if (emailOrPhone.contains("@")) {
      sql = "SELECT * FROM users WHERE email = ? AND password = ?";
    } else {
      sql = "SELECT * FROM users WHERE phone = ? AND password = ?";
    }
    List<UserModel> userModels = queryDAO(sql, new UserModelMapper(), emailOrPhone, password);
    return userModels.isEmpty() ? null : userModels.get(0);
  }

  @Override
  public UserModel getByEmailOrPhone(String emailOrPhone) {
    String sql;

    if (emailOrPhone.contains("@")) {
      sql = "SELECT * FROM users WHERE email = ?";
    } else {
      sql = "SELECT * FROM users WHERE phone = ?";
    }

    List<UserModel> userModels = queryDAO(sql, new UserModelMapper(), emailOrPhone);
    return userModels.isEmpty() ? null : userModels.get(0);
  }

  @Override
  public Long insert(UserModel userModel) {
    String sql = "INSERT INTO users (name, email, phone, password) VALUES (?, ?, ?, ?)";
    return insertDAO(
      sql,
      userModel.getName(),
      userModel.getEmail(),
      userModel.getPhone(),
      userModel.getPassword()
    );
  }

  @Override
  public boolean update(UserModel userModel) {
    String sql = """
      UPDATE users
      SET
        name = ?,
        email = ?,
        phone = ?,
        address = ?,
        password = ?,
        role = ?,
        remember_token = ?
      WHERE
        id = ?
    """;

    return updateDAO(
      sql,
      userModel.getName(),
      userModel.getEmail(),
      userModel.getPhone(),
      userModel.getAddress(),
      userModel.getPassword(),
      userModel.getRole(),
      userModel.getRememberToken(),
      userModel.getId()
    );
  }

}
