package com.spring.mvc.user.service;

import java.util.HashMap;

public interface LoginService {

	void upsertLoginUser (HashMap<String, Object> map) throws Exception;
}
