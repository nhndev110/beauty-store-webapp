package com.nhndev110.beautystore.controller.user;

import com.nhndev110.beautystore.dto.ProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.nhndev110.beautystore.model.ProductModel;
import com.nhndev110.beautystore.service.IProductService;
import jakarta.inject.Inject;

@WebServlet(name = "ShopServlet", urlPatterns = {"/shop/*"})
public class ShopController extends HttpServlet {
	
	@Inject
	IProductService productService;

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String action = req.getPathInfo();

		if (action == null) {
			action = "/";
		}

		switch (action) {
			case "/" -> {
        req.setAttribute("title", "Shop Page");
        showAll(req, resp);
      }
			case "/search" -> {
        req.setAttribute("title", "Search Product");
        search(req, resp);
      }
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

	protected void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ProductDTO> products = productService.getProducts();
		req.setAttribute("productList", products);
		req.getRequestDispatcher("/views/user/shop.jsp").forward(req, resp);
	}

	protected void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String query = req.getParameter("q");
		List<ProductModel> products = productService.getProductsByName(query);
		req.setAttribute("productList", products);
		req.getRequestDispatcher("/views/user/shop.jsp").forward(req, resp);
	}

}
