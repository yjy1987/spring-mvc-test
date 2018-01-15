package com.spring.mvc.user.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mvc.user.dao.LoginDao;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao LoginDao;
	
	@Override
	public void upsertLoginUser(HashMap<String, Object> map) throws Exception{
	}

}
