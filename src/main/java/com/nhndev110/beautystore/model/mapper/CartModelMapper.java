package com.nhndev110.beautystore.model.mapper;

import com.nhndev110.beautystore.core.RowMapper;
import com.nhndev110.beautystore.model.CartModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartModelMapper implements RowMapper<CartModel> {

	@Override
	public CartModel mapRow(ResultSet rs) {
		try {
			return new CartModel(
				rs.getLong("id"),
				rs.getLong("user_id"),
				rs.getLong("product_id"),
				rs.getInt("quantity")
			);
		} catch (SQLException ex) {
			Logger.getLogger(CartModelMapper.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

}
