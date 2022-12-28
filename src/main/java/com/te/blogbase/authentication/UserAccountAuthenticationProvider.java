package com.te.blogbase.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class UserAccountAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{
	@Autowired
	private  UserDetailsService detailsService;
	private PasswordEncoder encoder;
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if(authentication.getCredentials()==null||userDetails.getPassword()==null) {
			throw new BadCredentialsException("credentials may not be null");
		}
		if(!encoder.matches((String)authentication.getCredentials(),userDetails.getPassword())) {
			throw new BadCredentialsException("invalid credentials");
		}
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		return detailsService.loadUserByUsername(username);
	}
	

}
