package com.nhndev110.beautystore.dao.impl;

import com.nhndev110.beautystore.core.AbstractDAO;
import java.util.List;
import com.nhndev110.beautystore.model.CartModel;
import com.nhndev110.beautystore.dao.ICartDAO;
import com.nhndev110.beautystore.model.mapper.CartModelMapper;

public class CartDAO extends AbstractDAO<CartModel> implements ICartDAO {

  @Override
  public List<CartModel> getCartByUserId(long userId) {
    String sql = "SELECT * FROM cart WHERE user_id = ?";
    return queryDAO(sql, new CartModelMapper(), userId);
  }

  @Override
  public CartModel getCartItemByUserIdAndProductId(long userId, long productId) {
    String sql = "SELECT * FROM cart WHERE user_id = ? AND product_id = ?";
    List<CartModel> cartModels = queryDAO(sql, new CartModelMapper(), userId, productId);
    if (!cartModels.isEmpty()) {
      return cartModels.get(0);
    }
    return null;
  }

  @Override
  public int getCartSizeByUserId(long userId) {
    String sql = "SELECT * FROM cart WHERE user_id = ?";
    List<CartModel> cartModels = queryDAO(sql, new CartModelMapper(), userId);
    if (!cartModels.isEmpty()) {
      return cartModels.size();
    }
    return 0;
  }

  @Override
  public CartModel getCartItemById(long cartId) {
    String sql = "SELECT * FROM cart WHERE id = ?";
    List<CartModel> cartModels = queryDAO(sql, new CartModelMapper(), cartId);
    if (!cartModels.isEmpty()) {
      return cartModels.get(0);
    }
    return null;
  }

  @Override
  public boolean checkProductInCart(long userId, long productId) {
    String sql = "SELECT * FROM cart WHERE user_id = ? AND product_id = ?";
    return !queryDAO(sql, new CartModelMapper(), userId, productId).isEmpty();
  }

  @Override
  public boolean checkEmptyCartByUserId(long userId) {
    String sql = "SELECT * FROM cart WHERE user_id = ?";
    return queryDAO(sql, new CartModelMapper(), userId).isEmpty();
  }

  @Override
  public long insertCartItem(CartModel cartItem) {
    String sql = """
               INSERT INTO cart (product_id, user_id, quantity)
               VALUES (?, ?, ?)""";
    return insertDAO(
      sql,
      cartItem.getProductId(),
      cartItem.getUserId(),
      cartItem.getQuantity()
    );
  }

  @Override
  public boolean updateQuantityCartItem(long userId, long productId, int quantity) {
    String sql = """
      UPDATE cart
      SET
        quantity = ?
      WHERE user_id = ? AND product_id = ?""";
    return updateDAO(sql, quantity, userId, productId);
  }

  @Override
  public boolean deleteCartItem(long userId, long productId) {
    String sql = "DELETE FROM cart WHERE user_id = ? AND product_id = ?";
    return updateDAO(sql, userId, productId);
  }

  @Override
  public boolean deleteCartByUserId(long userId) {
    String sql = "DELETE FROM cart WHERE user_id = ?";
    return updateDAO(sql, userId);
  }

}
