package com.nhndev110.beautystore.filter;

import com.nhndev110.beautystore.dto.UserDTO;
import com.nhndev110.beautystore.service.impl.UserService;
import com.nhndev110.beautystore.utils.CookieUtils;
import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/login"})
public class AuthFilter implements Filter {

	@Inject
	private UserService userService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) req;
		HttpServletResponse httpResponse = (HttpServletResponse) resp;

		HttpSession session = httpRequest.getSession();

		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user == null) {
			String rememberToken;
			if ((rememberToken = CookieUtils.getCookie(httpRequest, "_r_tk")) != null) {
				user = userService.getUserByRememberToken(rememberToken);
				session.setAttribute("user", user);
				session.setAttribute("isAdmin", user.getRole().equals("admin"));
			}
		}

		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
	}

}
