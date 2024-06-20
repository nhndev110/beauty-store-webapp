package com.nhndev110.beautystore.controller.user;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.inject.Inject;
import com.nhndev110.beautystore.service.ICategoryService;
import com.nhndev110.beautystore.service.IProductService;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

  @Inject
  ICategoryService categoryService;

  @Inject
  IProductService productService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

		req.setAttribute("title", "Trang Chủ");

		req.setAttribute("categories", categoryService.getCategories());

		String categoryId = req.getParameter("category_id");
		req.setAttribute("productsByCategory", productService.getProductsByCategory(categoryId));

		req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);

		req.getSession().removeAttribute("statusRegister");
    
  }

}
