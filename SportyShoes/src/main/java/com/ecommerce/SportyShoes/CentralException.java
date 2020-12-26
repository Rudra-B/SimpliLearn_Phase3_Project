package com.ecommerce.SportyShoes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class CentralException {
	
	Logger logger=LoggerFactory.getLogger(CentralException.class);

	@ExceptionHandler(value=NullPointerException.class)
	public String nullPointer(Model model)
	{
		
		logger.error("due to null pointer exception program terminated");
		model.addAttribute("error", "This Exception raised becuse of null pointer");
		return "exception";
		
	}
	
	@ExceptionHandler(value=Exception.class)
	public String globalExceptionHandler(Model model)
	{
		logger.error("due to generic exception program terminated");
		model.addAttribute("error", "Generic exception raised");
		return "exception";
	}
	
}
