package com.spring.common.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
