package com.nhndev110.beautystore.service;

import com.nhndev110.beautystore.dto.ProductDTO;
import com.nhndev110.beautystore.model.ProductModel;
import java.util.List;
import java.util.Map;

public interface IProductService {

	public List<ProductDTO> getProducts();

	public List<ProductDTO> getProducts(String query, int page);
	
	public Map<String, List<ProductModel>> getProductsByCategory(String categoryId);

	public int countProductByQuery(String query);

	public ProductModel getProductById(String productId);

	public List<ProductModel> getProductsByName(String productName);

	public ProductDTO insertProduct(ProductModel productModel);

	public boolean updateProduct(ProductModel productModel);

	public boolean deleteProductById(int id);

}
