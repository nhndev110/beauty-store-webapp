package com.nhndev110.beautystore.controller.user;

import com.nhndev110.beautystore.dto.CartDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import com.nhndev110.beautystore.model.UserModel;
import com.nhndev110.beautystore.service.ICartService;
import jakarta.inject.Inject;

@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout/*"})
public class CheckoutController extends HttpServlet {

	@Inject
	ICartService cartService;

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String action = req.getPathInfo();

		if (action == null) {
			action = "/";
		}

		switch (action) {
			case "/" -> {
        req.setAttribute("title", "Checkout Page");
        showCheckout(req, resp);
      }
			case "/order" -> order(req, resp);
			default -> throw new AssertionError();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);
	}

	protected void showCheckout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		UserModel user = (UserModel) session.getAttribute("user");
		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		long userId = user.getId();

		List<CartDTO> cart = cartService.getCartByUserId(userId);

		double subTotal = cartService.getSubTotal(cart);
		req.setAttribute("subtotal", subTotal);

		double VAT = cartService.getVAT(subTotal);
		req.setAttribute("vat", VAT);

		req.setAttribute("total", cartService.getTotal(subTotal, VAT));
		session.setAttribute("cart", cart);

		req.getRequestDispatcher("/views/user/checkout.jsp").forward(req, resp);
	}

	protected void order(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();

		UserModel user = (UserModel) session.getAttribute("user");
		if (user == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		long userId = user.getId();

		boolean isOrderSucces = true;

		if (isOrderSucces) {

//			Mail.sendMail(
//			user.getName(),
//			user.getEmail(),
//			Constants.email,
//			"Congratulations on your successful order",
//			"<h1>Congratulations on your successful order</h1>",
//			true,
//			Constants.emailPassword
//			);
			boolean isClear = cartService.deleteCartByUserId(userId);

			if (isClear) {
				List<CartDTO> cart = cartService.getCartByUserId(userId);
				session.setAttribute("cart", cart);
				resp.sendRedirect(req.getContextPath() + "/home");
			}
		}
	}

}
