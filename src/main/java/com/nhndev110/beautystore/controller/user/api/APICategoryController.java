package com.nhndev110.beautystore.controller.user.api;

import com.google.gson.Gson;
import com.nhndev110.beautystore.dto.CategoryDTO;
import com.nhndev110.beautystore.dto.UserDTO;
import com.nhndev110.beautystore.model.CategoryModel;
import com.nhndev110.beautystore.service.impl.CategoryService;
import com.nhndev110.beautystore.utils.IOUtils;
import com.nhndev110.beautystore.utils.RespUtils;
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

@WebServlet(name = "APICategoryController", urlPatterns = {"/api/v1/categories/*"})
public class APICategoryController extends HttpServlet {

	@Inject
	private CategoryService categoryService;

	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);

		// Xay ra loi o day
		String categoryId = req.getPathInfo().split("/")[1];

//		if (categoryId)
		CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);

		Gson gson = new Gson();

		resp.setStatus(200);

		resp.getWriter().print(gson.toJson(categoryDTO));

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);

		Map<String, Object> respData = new HashMap<>();
		Gson gson = new Gson();
		RespUtils respUtils = new RespUtils();

		HttpSession session = req.getSession(false);
		UserDTO user = (UserDTO) session.getAttribute("user");

		if (user != null && user.getRole().equals("admin")) {
			String jsonString = IOUtils.toString(req.getReader());
			CategoryModel categoryModel = gson.fromJson(jsonString, CategoryModel.class);
			CategoryDTO categoryDTO = categoryService.insertCategory(categoryModel);
			resp.setStatus(201);
			respData.put("data", categoryDTO);
			respData.put("message", "ok");

//			respUtils
//				.status(resp, 0)
//				.add("data", categoryDTO)
//				.add("message", "ok")
//				.json(resp);
		} else {
			resp.setStatus(403);
			respData.put("message", "you do not have permission to use it");
		}

		resp.getWriter().print(gson.toJson(respData));

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequest(req, resp);

		RespUtils respUtils = new RespUtils();

		String accessToken = req.getHeader("Authorization");

		respUtils
			.status(resp, 202)
			.add("Authorization 1", accessToken)
			.add("Authorization 2", accessToken)
			.add("Authorization 3", accessToken)
			.json(resp);
	}

}
