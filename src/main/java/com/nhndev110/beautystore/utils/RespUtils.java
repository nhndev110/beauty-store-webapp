package com.nhndev110.beautystore.utils;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class RespUtils {
	
	private final Map<String, Object> respData = new HashMap<>();
	private static final Gson gson = new Gson();
	
	public RespUtils status(HttpServletResponse resp, int status) {
		resp.setStatus(status);
		return this;
	}
	
	public RespUtils add(String key, Object value) {
		this.respData.put(key, value);
		return this;
	}
	
	public Map<String, Object> getRespData() {
		return this.respData;
	}
	
	public void json(HttpServletResponse resp) throws IOException {
		gson.toJson(this.respData, resp.getWriter());
	}
	
}
