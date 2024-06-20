package com.nhndev110.beautystore.service;

import com.nhndev110.beautystore.dto.CartDTO;
import com.nhndev110.beautystore.model.CartModel;
import java.util.List;

public interface ICartService {

	public List<CartDTO> getCartByUserId(long userId);
	
	public CartModel getCartItemByUserIdAndProductId(long userId, long productId);
	
	public double getTotalCartItemByCartId(long cartId);
	
	public boolean checkEmptyCartByUserId(long userId);
	
	public int getCartSizeByUserId(long userId);
	
	public boolean checkProductInCart(long userId, long productId);
	
	public double getSubTotal(List<CartDTO> cart);
	
	public double getVAT(double subTotal);
	
	public double getTotal(double subTotal, double VAT);
	
	public CartModel insertCartItem(long userId, long productId, int quantity);
	
	public boolean updateQuantityCartItem(long userId, long productId, int quantity);

	public boolean deleteCartItem(long userId, long productId);
	
	public boolean deleteCartByUserId(long userId);

}
