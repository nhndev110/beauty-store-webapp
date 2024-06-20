package com.nhndev110.beautystore.controller.user;

import com.nhndev110.beautystore.core.Constants;
import com.nhndev110.beautystore.dto.UserDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.nhndev110.beautystore.utils.HashUtils;
import com.nhndev110.beautystore.service.ICartService;
import jakarta.inject.Inject;
import com.nhndev110.beautystore.service.IUserService;
import com.nhndev110.beautystore.utils.CookieUtils;
import com.nhndev110.beautystore.utils.GenerateUtils;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login/*"})
public class LoginController extends HttpServlet {

	@Inject
	private IUserService userService;

	@Inject
	private ICartService cartService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		HttpSession session = req.getSession();

		req.setAttribute("title", "Đăng Nhập");

		req.getRequestDispatcher("/views/user/login.jsp").forward(req, resp);

		session.removeAttribute("msgError");
		session.removeAttribute("emailOrPhone");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		String emailOrPhone = req.getParameter("emailOrPhone");
		String password = req.getParameter("password");
		boolean isRememberMe = req.getParameter("rememberMe") != null;
		
    UserDTO user = userService.getUserByEmailOrPhoneAndPassword(
			emailOrPhone,
			HashUtils.getMd5(password)
		);
		
		if (user == null) {
			session.setAttribute("msgError", "Thông tin người dùng không hợp lệ !");
			session.setAttribute("emailOrPhone", emailOrPhone);

			resp.sendRedirect(Constants.APP_URL + "/login");
		} else {
			session.setAttribute("user", user);

			session.setAttribute("cart", cartService.getCartByUserId(user.getId()));

			if (isRememberMe) {
				String rememberToken = GenerateUtils.generateToken(user.getId());
				user.setRememberToken(rememberToken);
				userService.updateUserOnlyRememberToken(user);
				CookieUtils.setCookie(resp, "_r_tk", rememberToken, 60 * 24 * 365);
			}

			if (user.getRole().equals("admin")) {
				session.setAttribute("isAdmin", true);
				resp.sendRedirect(Constants.APP_URL + "/admin/dashboard");
				return;
			}

			resp.sendRedirect(Constants.APP_URL + "/home");
		}
	}

}
