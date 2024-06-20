package com.nhndev110.beautystore.filter;

import com.nhndev110.beautystore.service.impl.AuthService;
import jakarta.inject.Inject;
import java.io.IOException;
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

@WebFilter(
	filterName = "PermissionFilter",
	urlPatterns = {"/admin/*", "/cart/*", "/login", "/register", "/checkout", "/logout"}
)
public class PermissionFilter implements Filter {

	@Inject
	private AuthService authService;

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession(false);

		String servletPath = httpRequest.getServletPath();

		if ((servletPath.startsWith("/cart") || servletPath.startsWith("/checkout")) && !authService.checkLogin(session)) {
			httpRequest.getSession().setAttribute("msgError", "Please log in!");
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
			return;
		}

		if (servletPath.startsWith("/logout") && !authService.checkLogin(session)) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
			return;
		}

		if ((servletPath.startsWith("/login") || servletPath.startsWith("/register")) && authService.checkLogin(session)) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
			return;
		}

		if (servletPath.startsWith("/admin") && !authService.checkAdmin(session)) {
			httpRequest.getSession().setAttribute("msgError", "Please log in!");
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
			return;
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
