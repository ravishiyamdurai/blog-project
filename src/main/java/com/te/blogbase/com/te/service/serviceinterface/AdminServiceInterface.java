package com.te.blogbase.com.te.service.serviceinterface;

import com.te.blogbase.entity.Users;
import com.te.blogbase.model.UserRequest;

public interface AdminServiceInterface {

	boolean register(Users dto);

	Long deleteprofile(Long id);

	Long countPost();

	String Login(UserRequest request);

}
