package com.nhndev110.beautystore.controller.user;

import com.nhndev110.beautystore.utils.CookieUtils;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("cart");
		request.getSession().removeAttribute("isAdmin");
		CookieUtils.removeCookie(request, response, "_r_tk");

		response.sendRedirect(request.getContextPath() + "/home");
	}

}
