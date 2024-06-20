package com.nhndev110.beautystore.model.mapper;

import com.nhndev110.beautystore.core.RowMapper;
import com.nhndev110.beautystore.model.ProductModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductModelMapper implements RowMapper<ProductModel> {

	@Override
	public ProductModel mapRow(ResultSet rs) {
		try {
			return new ProductModel(
				rs.getLong("id"),
				rs.getString("name"),
				rs.getString("image"),
				rs.getString("description"),
				rs.getDouble("price"),
				rs.getInt("quantity"),
				rs.getBoolean("status"),
				rs.getLong("category_id")
			);
		} catch (SQLException ex) {
			Logger.getLogger(ProductModelMapper.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

}
