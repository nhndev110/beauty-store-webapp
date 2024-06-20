package com.nhndev110.beautystore.service.impl;

import com.nhndev110.beautystore.core.BaseService;
import com.nhndev110.beautystore.dao.impl.CartDAO;
import com.nhndev110.beautystore.dao.impl.ProductDAO;
import com.nhndev110.beautystore.dao.impl.UserDAO;
import com.nhndev110.beautystore.dto.CartDTO;
import com.nhndev110.beautystore.model.CartModel;
import com.nhndev110.beautystore.model.ProductModel;
import com.nhndev110.beautystore.service.ICartService;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CartService extends BaseService<CartDTO, CartModel> implements ICartService {

	@Inject
	private CartDAO cartDAO;
	
	@Inject
	private ProductDAO productDAO;
	
	@Inject
	private UserDAO userDAO;

	@Override
	public List<CartDTO> getCartByUserId(long userId) {
		List<CartModel> cartModels = cartDAO.getCartByUserId(userId);
		List<CartDTO> cartDTOs = new ArrayList<>();
		for (CartModel cartModel : cartModels) {
			cartDTOs.add(convertToDTO(cartModel));
		}
		return cartDTOs;
	}
	
	@Override
	public CartModel getCartItemByUserIdAndProductId(long userId, long productId) {
		return cartDAO.getCartItemByUserIdAndProductId(userId, productId);
	}
	
	@Override
	public double getTotalCartItemByCartId(long cartId) {
		CartModel cartItem = cartDAO.getCartItemById(cartId);
		ProductModel product = productDAO.getById(cartItem.getProductId());
		return cartItem.getQuantity() * product.getPrice();
	}

	@Override
	public double getSubTotal(List<CartDTO> cart) {
		double subTotal = 0f;
		
		for (CartDTO cartItem : cart) {
			subTotal += cartItem.getQuantity() * cartItem.getProduct().getQuantity();
		}

		return subTotal;
	}

	@Override
	public int getCartSizeByUserId(long userId) {
		return cartDAO.getCartSizeByUserId(userId);
	}

	@Override
	public double getVAT(double subTotal) {
		double VAT = 1;

		return subTotal * VAT / 100;
	}

	@Override
	public double getTotal(double subTotal, double VAT) {
		return subTotal + VAT;
	}

	@Override
	public boolean checkEmptyCartByUserId(long userId) {
		return cartDAO.checkEmptyCartByUserId(userId);
	}

	@Override
	public boolean checkProductInCart(long userId, long productId) {
		return cartDAO.checkProductInCart(userId, productId);
	}
	
	@Override
	public CartModel insertCartItem(long userId, long productId, int quantity) {
    boolean isProductInCart = checkProductInCart(userId, productId);
    long cartItemId;
    
    if (isProductInCart) {
      CartModel cartItem = cartDAO.getCartItemByUserIdAndProductId(userId, productId);
      boolean isUpdated = cartDAO.updateQuantityCartItem(userId, productId, cartItem.getQuantity() + 1);
      cartItemId = cartItem.getId();
    } else {
      cartItemId = cartDAO.insertCartItem(new CartModel(userId, productId, 1));
    }
    
		return cartDAO.getCartItemById(cartItemId);
	}
	
	@Override
	public boolean updateQuantityCartItem(long userId, long productId, int quantity) {
		return cartDAO.updateQuantityCartItem(userId, productId, quantity);
	}
	
	@Override
	public boolean deleteCartItem(long userId, long productId) {
		return cartDAO.deleteCartItem(userId, productId);
	}

	@Override
	public boolean deleteCartByUserId(long userId) {
		return cartDAO.deleteCartByUserId(userId);
	}

	@Override
	protected CartDTO convertToDTO(CartModel objectModel) {
		return new CartDTO(
			objectModel.getId(),
			userDAO.getById(objectModel.getUserId()),
			productDAO.getById(objectModel.getProductId()),
			objectModel.getQuantity()
		);
	}

}
