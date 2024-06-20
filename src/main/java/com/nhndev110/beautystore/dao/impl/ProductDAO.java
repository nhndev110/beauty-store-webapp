package com.nhndev110.beautystore.dao.impl;

import com.nhndev110.beautystore.core.AbstractDAO;
import com.nhndev110.beautystore.core.CURDDAO;
import java.util.List;
import com.nhndev110.beautystore.model.ProductModel;
import com.nhndev110.beautystore.dao.IProductDAO;
import com.nhndev110.beautystore.model.mapper.ProductModelMapper;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO, CURDDAO<ProductModel> {

	@Override
	public List<ProductModel> getAll() {
		String sql = "SELECT * FROM products ORDER BY id DESC";
		return queryDAO(sql, new ProductModelMapper());
	}

	@Override
	public List<ProductModel> getAll(String query, int page) {
		String sql = "SELECT * FROM products WHERE id = ? OR `name` LIKE ? ORDER BY id DESC LIMIT 10 OFFSET ?";
		return queryDAO(sql, new ProductModelMapper(), query, "%" + query + "%", 10 * (page - 1));
	}

	@Override
	public ProductModel getById(long id) {
		String sql = "SELECT * FROM products WHERE id = ?";
		List<ProductModel> productModels = queryDAO(sql, new ProductModelMapper(), id);
		if (!productModels.isEmpty()) {
			return queryDAO(sql, new ProductModelMapper(), id).get(0);
		}
		return null;
	}

	@Override
	public List<ProductModel> getByName(String query) {
		String sql = "SELECT * FROM products WHERE `name` LIKE ?";
		return queryDAO(sql, new ProductModelMapper(), "%" + query + "%");
	}
	
	@Override
	public List<ProductModel> getByCategoryId(long categoryId) {
		String sql = "SELECT * FROM products WHERE category_id = ?";
		return queryDAO(sql, new ProductModelMapper(), categoryId);
	}

	@Override
	public int countByQuery(String query) {
		String sql = "SELECT * FROM products WHERE id = ? OR `name` LIKE ?";
		return queryDAO(sql, new ProductModelMapper(), query, "%" + query + "%").size();
	}

	@Override
	public Long insert(ProductModel productModel) {
		String sql = "INSERT INTO products (`name`, image, `description`, price, quantity, `status`, category_id)\n"
			+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		return insertDAO(
			sql,
			productModel.getName(),
			productModel.getImage(),
			productModel.getDescription(),
			productModel.getPrice(),
			productModel.getQuantity(),
			productModel.isStatus(),
			productModel.getCategoryId()
		);
	}

	@Override
	public boolean update(ProductModel productModel) {
		String sql = "UPDATE products\n"
			+ "SET\n"
			+ "	`name` = ?,\n"
			+ "	image = ?,\n"
			+ "	`description` = ?,\n"
			+ "	price = ?,\n"
			+ "	quantity = ?,\n"
			+ "	`status` = ?,\n"
			+ "	category_id = ?\n"
			+ "WHERE id = ?";

		return updateDAO(
			sql,
			productModel.getName(),
			productModel.getImage(),
			productModel.getDescription(),
			productModel.getPrice(),
			productModel.getQuantity(),
			productModel.isStatus(),
			productModel.getCategoryId(),
			productModel.getId()
		);
	}

	@Override
	public boolean deleteById(long id) {
		String sql = "DELETE FROM products WHERE id = ?";
		return updateDAO(sql, id);
	}

}
