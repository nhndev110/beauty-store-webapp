package com.nhndev110.beautystore.controller.user;

import com.google.gson.Gson;
import com.nhndev110.beautystore.dto.CartDTO;
import com.nhndev110.beautystore.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.nhndev110.beautystore.model.CartModel;
import com.nhndev110.beautystore.model.UserModel;
import com.nhndev110.beautystore.service.impl.CartService;
import com.nhndev110.beautystore.service.impl.CategoryService;
import com.nhndev110.beautystore.utils.FormatUtils;
import jakarta.inject.Inject;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart/*"})
public class CartController extends HttpServlet {

	@Inject
	CategoryService categoryService;

	@Inject
	CartService cartService;

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String action = req.getPathInfo();

		if (action == null || action.isEmpty()) {
			action = "/show";
		}

		switch (action) {
			case "/add" -> addToCart(req, resp);
			case "/show" -> {
        req.setAttribute("title", "Cart Page");
        showCart(req, resp);
      }
			case "/delete" -> deleteCartItem(req, resp);
			case "/clear" -> emptyCart(req, resp);
			case "/update" -> updateCartItem(req, resp);
			default -> throw new AssertionError();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("categoryList", categoryService.getCategories());

		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	protected void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		long userId = user.getId();
		boolean isEmptyCart = cartService.checkEmptyCartByUserId(userId);

		if (!isEmptyCart) {
			List<CartDTO> cart = cartService.getCartByUserId(userId);

			double subTotal = cartService.getSubTotal(cart);
			req.setAttribute("subtotal", subTotal);

			double VAT = cartService.getVAT(subTotal);
			req.setAttribute("vat", VAT);

			req.setAttribute("total", cartService.getTotal(subTotal, VAT));
			session.setAttribute("cart", cart);
		}

		req.setAttribute("isEmptyCart", isEmptyCart);

		req.getRequestDispatcher("/views/user/cart.jsp").forward(req, resp);

		req.removeAttribute("isEmptyCart");
		req.removeAttribute("subtotal");
		req.removeAttribute("total");
		req.removeAttribute("vat");
	}

	protected void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		Map<String, Object> respData = new HashMap<>();
		if (user != null) {
			String productIdRaw = req.getParameter("product_id");
			try {
				// Lấy ra productId, userId
				long productId = Long.parseLong(productIdRaw);
				long userId = user.getId();

				// Ktra sản phẩm có nằm trong giỏ hàng không
				boolean isProductInCart = cartService.checkProductInCart(userId, productId);

				// Thêm sản phẩm vào giỏ hàng
				if (isProductInCart) {
					CartModel cartItem = cartService.getCartItemByUserIdAndProductId(userId, productId);
					cartService.updateQuantityCartItem(userId, productId, cartItem.getQuantity() + 1);
				} else {
					cartService.insertCartItem(userId, productId, 1);
				}

				List<CartDTO> cart = cartService.getCartByUserId(userId);
				session.setAttribute("cart", cart);

				// Trả phản hồi về
				respData.put("title", "Success");
				respData.put("msg", "Added product in cart!");
				respData.put("status", "success");
				respData.put("isProductInCart", isProductInCart);
			} catch (NumberFormatException ex) {
				Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			respData.put("title", "Error");
			respData.put("msg", "Please log in!");
			respData.put("status", "error");
			respData.put("redirect", req.getContextPath() + "/login");
		}

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		String respDataJsonString = new Gson().toJson(respData);
		resp.getWriter().write(respDataJsonString);
	}

	protected void deleteCartItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserModel user = (UserModel) req.getSession().getAttribute("user");

		if (user != null) {
			String productIdRaw = req.getParameter("product_id");
			try {
				long productId = Long.parseLong(productIdRaw);
				long userId = user.getId();

				boolean isDeletedCartItem = cartService.deleteCartItem(userId, productId);
				boolean isEmptyCart = cartService.checkEmptyCartByUserId(userId);

				List<CartDTO> cart = cartService.getCartByUserId(userId);
				req.getSession().setAttribute("cart", cart);

				Map<String, Object> respData = new HashMap<>();

				if (isEmptyCart) {
					respData.put("isEmptyCart", isEmptyCart);
					respData.put("status", "success");
				} else {
					if (isDeletedCartItem) {
						double subTotal = cartService.getSubTotal(cart);
						double VAT = cartService.getVAT(subTotal);

						respData.put("subtotal", FormatUtils.getMoneyString(cartService.getSubTotal(cart)));
						respData.put("vat", FormatUtils.getMoneyString(cartService.getVAT(subTotal)));
						respData.put("total", FormatUtils.getMoneyString(cartService.getTotal(subTotal, VAT)));
						respData.put("status", "success");
					} else {
						respData.put("title", "Error");
						respData.put("msg", "An error occurred");
						respData.put("status", "error");
					}
				}

				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				String respDataJsonString = new Gson().toJson(respData);
				resp.getWriter().write(respDataJsonString);
			} catch (NumberFormatException ex) {
				Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	protected void updateCartItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserModel user = (UserModel) req.getSession().getAttribute("user");

		if (user != null) {
			String productIdRaw = req.getParameter("product_id");
			String quantityRaw = req.getParameter("quantity");
			String type = req.getParameter("type");
			try {
				long userId = user.getId();
				long productId = Integer.parseInt(productIdRaw);
				int quantity = -1;
				if (quantityRaw != null) {
					quantity = Integer.parseInt(quantityRaw);
				}

				Map<String, Object> respData = new HashMap<>();

				if (type == null || type.isBlank()) {
					if (quantity > 0) {
						cartService.updateQuantityCartItem(userId, productId, quantity);
						respData.put("msg", "Updated quantity success!");
						respData.put("status", "success");
					} else {
						respData.put("msg", "Error! An error occurred. Please try again later");
						respData.put("status", "error");
					}
				} else {
					CartModel cartItem = cartService.getCartItemByUserIdAndProductId(userId, productId);

					List<CartDTO> cart = cartService.getCartByUserId(userId);
					double subTotal = cartService.getSubTotal(cart);
					double VAT = cartService.getVAT(subTotal);

					switch (type) {
						case "asc":
							cartService.updateQuantityCartItem(userId, productId, cartItem.getQuantity() + 1);
							respData.put("msg", "Updated quantity success!");
							respData.put("subtotal", FormatUtils.getMoneyString(cartService.getSubTotal(cart)));
							respData.put("vat", FormatUtils.getMoneyString(cartService.getVAT(subTotal)));
							respData.put("total", FormatUtils.getMoneyString(cartService.getTotal(subTotal, VAT)));
							respData.put("status", "success");
							break;
						case "desc":
							if (cartItem.getQuantity() - 1 > 0) {
								cartService.updateQuantityCartItem(userId, productId, cartItem.getQuantity() - 1);
								respData.put("subtotal", FormatUtils.getMoneyString(cartService.getSubTotal(cart)));
								respData.put("vat", FormatUtils.getMoneyString(cartService.getVAT(subTotal)));
								respData.put("total", FormatUtils.getMoneyString(cartService.getTotal(subTotal, VAT)));
							} else {
								respData.put("title", "Error");
								respData.put("msg", "Invalid quantity!");
								respData.put("status", "error");
							}
							break;
						default:
							respData.put("msg", "Error! An error occurred. Please try again later");
							respData.put("status", "error");
							break;
					}
				}

				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				String respDataJsonString = new Gson().toJson(respData);
				resp.getWriter().write(respDataJsonString);
			} catch (NumberFormatException ex) {
				Logger.getLogger(CartController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	protected void emptyCart(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("user");

		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		long userId = user.getId();
		boolean isClear = cartService.deleteCartByUserId(userId);
		if (isClear) {
			List<CartDTO> cart = cartService.getCartByUserId(userId);
			session.setAttribute("cart", cart);
			resp.sendRedirect(req.getContextPath() + "/cart");
		}
	}

}
