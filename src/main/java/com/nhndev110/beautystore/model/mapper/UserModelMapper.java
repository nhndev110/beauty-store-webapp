package com.nhndev110.beautystore.model.mapper;

import com.nhndev110.beautystore.core.RowMapper;
import com.nhndev110.beautystore.model.UserModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserModelMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet rs) {
    UserModel user = null;
		try {
			user = new UserModel(
				rs.getLong("id"),
				rs.getString("name"),
				rs.getString("email"),
				rs.getString("phone"),
				rs.getString("address"),
				rs.getString("password"),
				rs.getString("role"),
				rs.getString("remember_token")
			);
		} catch (SQLException ex) {
			Logger.getLogger(UserModelMapper.class.getName()).log(Level.SEVERE, null, ex);
		}
    
		return user;
	}
	
}
