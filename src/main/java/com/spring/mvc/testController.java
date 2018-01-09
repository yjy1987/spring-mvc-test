package com.spring.mvc;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class testController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/test")
	public ModelAndView test(Map<String,Object> map) {
		logger.info("인터셉터 테스트");
		return new ModelAndView("test");
	}
}
