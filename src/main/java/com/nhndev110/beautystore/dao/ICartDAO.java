package com.nhndev110.beautystore.dao;

import com.nhndev110.beautystore.core.GenericDAO;
import java.util.List;
import com.nhndev110.beautystore.model.CartModel;

public interface ICartDAO extends GenericDAO<CartModel> {

	public List<CartModel> getCartByUserId(long userId);
	
	public CartModel getCartItemById(long cartId);
	
	public CartModel getCartItemByUserIdAndProductId(long userId, long productId);

	public int getCartSizeByUserId(long userId);

	public boolean checkProductInCart(long userId, long productId);

	public boolean checkEmptyCartByUserId(long userId);
	
	public long insertCartItem(CartModel cartItem);
	
	public boolean updateQuantityCartItem(long userID, long productId, int quantity);

	public boolean deleteCartItem(long userId, long productId);

	public boolean deleteCartByUserId(long userId);

}
