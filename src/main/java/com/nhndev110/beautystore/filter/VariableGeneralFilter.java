package com.nhndev110.beautystore.filter;

import com.nhndev110.beautystore.core.Constants;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@WebFilter(filterName = "VariableGeneralFilter", urlPatterns = {"*"})
public class VariableGeneralFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		HttpSession session = httpRequest.getSession();
		
		session.setAttribute("servletPath", httpRequest.getServletPath());
		session.setAttribute("currentTime", new Date().getTime());
		session.setAttribute("APP_PATH", new File(httpRequest.getServletContext().getRealPath("\\")).getParentFile().getParentFile());
		
    Constants.APP_PATH = new File(httpRequest.getServletContext().getRealPath("\\")).getParentFile().getParentFile().toString();
    Constants.APP_URL = httpRequest.getContextPath();
    
		chain.doFilter(req, resp);
	}

}
