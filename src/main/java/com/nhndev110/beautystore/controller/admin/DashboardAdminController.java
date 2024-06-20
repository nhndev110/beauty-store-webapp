package com.nhndev110.beautystore.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DashboardAdminController", urlPatterns = {"/admin/", "/admin/dashboard"})
public class DashboardAdminController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("title", "Trang Chủ");
		req.getRequestDispatcher("/views/admin/dashboard.jsp").forward(req, resp);
	}
	
}
