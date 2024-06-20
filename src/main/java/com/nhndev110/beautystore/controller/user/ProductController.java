package com.nhndev110.beautystore.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.nhndev110.beautystore.model.ProductModel;
import com.nhndev110.beautystore.service.IProductService;
import jakarta.inject.Inject;

@WebServlet(name = "ProductServlet", urlPatterns = {"/product/*"})
public class ProductController extends HttpServlet {

	@Inject
	private IProductService productService;

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String action = req.getPathInfo();

		if (action == null) {
			action = "/";
		}

		switch (action) {
			case "/show" -> {
        show(req, resp);
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

	protected void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String productId = req.getParameter("product_id");
		ProductModel product = productService.getProductById(productId);
		req.setAttribute("product", product);
		req.getRequestDispatcher("/views/user/product/product.jsp").forward(req, resp);
	}

}
