package com.nhndev110.beautystore.service.impl;

import com.nhndev110.beautystore.core.BaseService;
import com.nhndev110.beautystore.dao.impl.CategoryDAO;
import com.nhndev110.beautystore.dao.impl.ProductDAO;
import com.nhndev110.beautystore.dto.ProductDTO;
import com.nhndev110.beautystore.model.CategoryModel;
import com.nhndev110.beautystore.model.ProductModel;
import com.nhndev110.beautystore.service.IProductService;
import jakarta.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService extends BaseService<ProductDTO, ProductModel> implements IProductService {

	@Inject
	private ProductDAO productDAO;

	@Inject
	private CategoryDAO categoryDAO;

	@Override
	public List<ProductDTO> getProducts() {
		List<ProductModel> productModels = productDAO.getAll();
		return convertToDTOs(productModels);
	}

	@Override
	public List<ProductDTO> getProducts(String query, int page) {
		List<ProductModel> productModels = productDAO.getAll(query, page);
		return convertToDTOs(productModels);
	}

	@Override
	public Map<String, List<ProductModel>> getProductsByCategory(String categoryId) {
		Map<String, List<ProductModel>> productsByCategory = new HashMap<>();
		List<CategoryModel> categoryModels = categoryDAO.getAll();

		if (categoryId == null || categoryId.isEmpty()) {
			for (CategoryModel categoryModel : categoryModels) {
				productsByCategory.put(
					categoryModel.getName(),
					productDAO.getByCategoryId(categoryModel.getId())
				);
			}
		} else {
			CategoryModel categoryModel = categoryDAO.getById(Long.parseLong(categoryId));
			if (categoryModel != null) {
				productsByCategory.put(
					categoryModel.getName(),
					productDAO.getByCategoryId(categoryModel.getId())
				);
			}
		}

		return productsByCategory;
	}

	@Override
	public List<ProductModel> getProductsByName(String productName) {
		if (productName == null) {
			productName = "";
		}
		return productDAO.getByName(productName);
	}

	@Override
	public ProductModel getProductById(String productId) {
		if (productId == null) {
			return null;
		}

		try {
			int idNum = Integer.parseInt(productId);
			return productDAO.getById(idNum);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	public int countProductByQuery(String query) {
		return productDAO.countByQuery(query);
	}

	@Override
	public ProductDTO insertProduct(ProductModel productModel) {
    Long productId = productDAO.insert(productModel);
    if (productId != null) {
      return convertToDTO(productDAO.getById(productId));
    }
		return null;
	}

	@Override
	public boolean updateProduct(ProductModel productModel) {
		return productDAO.update(productModel);
	}

	@Override
	public boolean deleteProductById(int productId) {
		return productDAO.deleteById(productId);
	}

	@Override
	protected ProductDTO convertToDTO(ProductModel objectModel) {
		return new ProductDTO(
			objectModel.getId(),
			objectModel.getName(),
			objectModel.getImage(),
			objectModel.getDescription(),
			objectModel.getPrice(),
			objectModel.getQuantity(),
			objectModel.isStatus(),
			categoryDAO.getById(objectModel.getCategoryId())
		);
	}

}
