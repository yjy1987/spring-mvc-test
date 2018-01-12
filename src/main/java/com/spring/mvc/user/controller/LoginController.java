package com.spring.mvc.user.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;


import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.common.web.controller.BaseController;
import com.spring.mvc.user.service.LoginService;

@RestController("loginController")
@RequestMapping(value = "/user", method = { RequestMethod.POST, RequestMethod.GET })
public class LoginController extends BaseController {

	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;

	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;

	@Autowired
	private LoginService loginService;
	
	// 로그인 첫 화면 요청 메소드
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView sample(@RequestParam HashMap<String, Object> map, HttpServletRequest request) throws Exception {

		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String redirectUrl = new URL(request.getRequestURL().toString()).toString().replaceAll("login",
				"oauth2callback");

		googleOAuth2Parameters.set("redirect_uri", redirectUrl);
		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);

		logger.info("구글 : {} ", url);
		map.put("url", url);
		return new ModelAndView("user/login", map);
	}

	// 구글 Callback호출 메소드
	@RequestMapping(value = "/oauth2callback", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView googleCallback(@RequestParam HashMap<String, Object> map) throws IOException {

		logger.info("여기는 googleCallback");
		logger.info(map.toString());
		
		if(!MapUtils.isEmpty(map)) {
			String code = map.get("code").toString();
			OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
			AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, googleOAuth2Parameters.getRedirectUri(), null);
			String accessToken = accessGrant.getAccessToken();
			Long expireTime = accessGrant.getExpireTime();
			if (expireTime != null && expireTime < System.currentTimeMillis()) {
				accessToken = accessGrant.getRefreshToken();
				logger.info("accessToken is expired. refresh token = {}", accessToken);
			}
			Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
			Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();
			PlusOperations plusOperations = google.plusOperations();
			Person person = plusOperations.getGoogleProfile();
			
			logger.info("getAboutMe  : {}", person.getAboutMe());
			logger.info("getAccountEmail  : {}", person.getAccountEmail());
			logger.info("getBirthday  : {}", person.getBirthday() );
			logger.info("getDisplayName  : {}", person.getDisplayName());
			logger.info("getEmailAddresses  : {}", person.getEmailAddresses());
			logger.info("getEmails  : {}", person.getEmails());
			logger.info("getEtag  : {}", person.getEtag());
			logger.info("getFamilyName  : {}", person.getFamilyName());
			logger.info("getGender  : {}", person.getGender());
			logger.info("getGivenName  : {}", person.getGivenName());
			logger.info("getId  : {}", person.getId());
			logger.info("getImageUrl  : {}", person.getImageUrl());
			logger.info("getOccupation  : {}", person.getOccupation());
			logger.info("getOrganizations  : {}", person.getOrganizations());
			logger.info("getPlacesLived  : {}", person.getPlacesLived());
			logger.info("getRelationshipStatus  : {}", person.getRelationshipStatus());
			logger.info("getUrl  : {}", person.getUrl());
			logger.info("getUrls  : {}", person.getUrls());
			
			
			
			
		}
		return new ModelAndView("user/temp", map);
	}
	
	@RequestMapping(value = "/oauth2callbackResult", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView googleCallbackResult(@RequestParam HashMap<String, Object> map) throws IOException {

		

		return new ModelAndView("user/googleSuccessResult", map);
	}
}
