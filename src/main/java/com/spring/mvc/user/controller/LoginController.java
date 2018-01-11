package com.spring.mvc.user.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.common.web.controller.BaseController;

@RestController("loginController")
@RequestMapping(value = "/user", method = {RequestMethod.POST, RequestMethod.GET})
public class LoginController extends BaseController{
	
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;

	// 로그인 첫 화면 요청 메소드
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView sample(@RequestParam HashMap<String, Object> map,  HttpServletRequest  request) throws Exception {

		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String redirectUrl = new URL(request.getRequestURL().toString()).toString().replaceAll("login", "oauth2callback");
		
		
		googleOAuth2Parameters.set("redirect_uri", redirectUrl);
		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		
		logger.info("구글 : {} ", url);
		map.put("url", url);
		return new ModelAndView("user/login",map);
	}
	
	// 구글 Callback호출 메소드
	@RequestMapping(value = "/oauth2callback", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView googleCallback(@RequestParam HashMap<String, Object> map) throws IOException {
		
		logger.info("여기는 googleCallback");
		logger.info(map.toString());
		return new ModelAndView("user/googleSuccess",map);
	}
}
