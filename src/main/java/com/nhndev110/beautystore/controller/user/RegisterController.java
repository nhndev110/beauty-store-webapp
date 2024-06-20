package com.nhndev110.beautystore.controller.user;

import com.nhndev110.beautystore.dto.UserDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import jakarta.inject.Inject;
import com.nhndev110.beautystore.model.CategoryModel;
import com.nhndev110.beautystore.model.UserModel;
import com.nhndev110.beautystore.service.ICategoryService;
import com.nhndev110.beautystore.service.IUserService;
import com.nhndev110.beautystore.utils.HashUtils;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

	@Inject
	ICategoryService categoryService;

	@Inject
	IUserService userService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setAttribute("title", "Đăng Ký");
		List<CategoryModel> categories = categoryService.getCategories();
		request.setAttribute("listCategory", categories);
		request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);

		request.getSession().removeAttribute("message");
		request.getSession().removeAttribute("userInput");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("name").trim();
		String userEmail = request.getParameter("email").trim();
		String userPhone = request.getParameter("phone").trim();
		String userPassword = request.getParameter("password").trim();
		String userRePassword = request.getParameter("repassword").trim();

		HashMap<String, String> message = new HashMap<>();

		String emailRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		String phoneRegex = "^\\d{10}$";

		// Check userName
		if (userName.isEmpty()) {
			message.putIfAbsent("nameError", "Tên người dùng không được để trống!");
		}

		// Check userEmail
		if (userEmail.isEmpty()) {
			message.putIfAbsent("emailError", "Email không được để trống!");
		}

		if (!userEmail.matches(emailRegex)) {
			message.putIfAbsent("emailError", "Email không hợp lệ!");
		}

		if (userService.getUserByEmailOrPhone(userEmail) != null) {
			message.putIfAbsent("emailError", "Email đã tồn tại!");
		}

		// Check userPhone
		if (userPhone.isEmpty()) {
			message.putIfAbsent("phoneError", "Số điện thoại không được để trống!");
		}

		if (!userPhone.matches(phoneRegex)) {
			message.putIfAbsent("phoneError", "Số điện thoại không hợp lệ!");
		}

		if (userService.getUserByEmailOrPhone(userPhone) != null) {
			message.putIfAbsent("phoneError", "Số điện thoại đã tồn tại!");
		}

		// Check userPassword
		if (userPassword.isEmpty()) {
			message.putIfAbsent("passwordError", "Mật khẩu không được để trống!");
		}

		if (userPassword.length() < 8) {
			message.putIfAbsent("passwordError", "Mật khẩu không hợp lệ!");
		}

		// Check user repassword
		if (userRePassword.isEmpty()) {
			message.putIfAbsent("repasswordError", "Mật khẩu không hợp lệ");
		}

		if (!userRePassword.equals(userPassword)) {
			message.putIfAbsent("repasswordError", "Mật khẩu không khớp!");
		}

		if (!message.isEmpty()) {
			HashMap<String, String> userInput = new HashMap<>();
			userInput.put("userName", userName);
			userInput.put("userEmail", userEmail);
			userInput.put("userPhone", userPhone);
			userInput.put("userPassword", userPassword);
			userInput.put("userRePassword", userRePassword);

			request.getSession().setAttribute("message", message);
			request.getSession().setAttribute("userInput", userInput);

			response.sendRedirect(request.getContextPath() + "/register");
		} else {
			userService.insertUser(new UserModel(
				userName,
				userEmail,
				userPhone,
				HashUtils.getMd5(userPassword)
			)
			);
			UserDTO user = userService.getUserByEmailOrPhone(userEmail);
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("statusRegister", true);

			response.sendRedirect(request.getContextPath() + "/home");
		}
	}

}
