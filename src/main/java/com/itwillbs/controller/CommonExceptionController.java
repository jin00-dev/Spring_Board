package com.itwillbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice : 컨트롤러의 보조기능(예외처리)을 수행

@ControllerAdvice
public class CommonExceptionController {
	// 공통의 예외를 처리하는 클래스(컨트롤러의 예외를 처리)

	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionController.class);

	//@ExceptionHandler(NullPointerException.class)
	//@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	@ExceptionHandler(Exception.class)
	public String commonException(Exception e,Model model) {
		logger.debug(" Exception 타입의 예외가 발생할때마다 실행하는 메서드");
		logger.debug(" 모든 예외를 처리하는 메서드 ");
		
		logger.debug(""+e.getMessage());
		e.printStackTrace();
		model.addAttribute("e", e);
		
		return "common_err";
	}
	
//	try {
//		
//	}catch(Exception e) {
//		e.printStackTrace();	
//	}
	
	
	
}
