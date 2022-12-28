package com.te.blogbase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.te.blogbase.exceptionpackage.BlogException;

@ControllerAdvice
public class BlogExceptionController {
	
	@ExceptionHandler
	public ResponseEntity<String> exception(BlogException blogException){
		return ResponseEntity.ok(blogException.getMessage());
		
	}

}
