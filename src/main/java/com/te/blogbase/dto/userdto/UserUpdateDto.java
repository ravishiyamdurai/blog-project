package com.te.blogbase.dto.userdto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class UserUpdateDto {
	private Long id;
	private String fisrtName;
	private String middleName;
	private String lastName;
	private String mobile;
	private String email;
	private String password;
	private Date registeredAt;
	private Date lastLogin;
	private String intro;
	private String profile;

}
