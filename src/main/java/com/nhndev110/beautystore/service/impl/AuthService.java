package com.nhndev110.beautystore.service.impl;

import com.nhndev110.beautystore.dao.impl.UserDAO;
import com.nhndev110.beautystore.service.IAuthService;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpSession;

public class AuthService implements IAuthService {

	@Inject
	private UserDAO userDAO;

	@Override
	public boolean checkLogin(HttpSession session) {
		return session != null && session.getAttribute("user") != null;
	}

	@Override
	public boolean checkAdmin(HttpSession session) {
		return checkLogin(session) && (session.getAttribute("isAdmin") != null);
	}

}
