package com.nhndev110.beautystore.model.mapper;

import com.nhndev110.beautystore.core.RowMapper;
import com.nhndev110.beautystore.model.CategoryModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoryModelMapper implements RowMapper<CategoryModel> {

	@Override
	public CategoryModel mapRow(ResultSet rs) {
		try {
			return new CategoryModel(
				rs.getLong("id"),
				rs.getString("name"),
				rs.getString("thumbnail")
			);
		} catch (SQLException ex) {
			Logger.getLogger(CategoryModelMapper.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

}
