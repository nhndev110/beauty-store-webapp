package com.nhndev110.beautystore.service;

import jakarta.servlet.http.HttpSession;

public interface IAuthService {

	public boolean checkLogin(HttpSession session);

	public boolean checkAdmin(HttpSession session);

}
