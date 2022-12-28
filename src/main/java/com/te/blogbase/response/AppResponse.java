package com.te.blogbase.response;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class AppResponse {
	private int status;
	private boolean error;
	private Object data;
	private String message;

}
