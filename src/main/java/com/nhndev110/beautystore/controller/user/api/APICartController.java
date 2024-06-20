package com.nhndev110.beautystore.controller.user.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nhndev110.beautystore.dto.UserDTO;
import com.nhndev110.beautystore.model.CartModel;
import com.nhndev110.beautystore.service.impl.CartService;
import com.nhndev110.beautystore.service.impl.CategoryService;
import com.nhndev110.beautystore.utils.IOUtils;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "APICartController", urlPatterns = {"/api/v1/cart/*"})
public class APICartController extends HttpServlet {

	@Inject
	private CategoryService categoryService;

	@Inject
	private CartService cartService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("checked GET");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		Gson gson = new Gson();
		Map<String, Object> respJson = new HashMap<>();
		if (user != null) {
			long userId = user.getId();

			String jsonString = IOUtils.toString(req.getReader());
			JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
			jsonObject.addProperty("userId", userId);
			jsonString = jsonObject.toString();

			CartModel cartModel = gson.fromJson(jsonString, CartModel.class);
			cartModel = cartService.insertCartItem(
        cartModel.getUserId(),
        cartModel.getProductId(),
        cartModel.getQuantity()
      );
			respJson.put("data", cartModel);
		}
    
		resp.getWriter().write(gson.toJson(respJson));
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPut(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doDelete(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
	}

}
