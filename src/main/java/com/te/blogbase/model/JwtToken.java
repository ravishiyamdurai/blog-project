package com.te.blogbase.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken {

	@Value("${app.secret}")
	private String secret;

	public String generateToken(String subject) {
		return Jwts.builder().setSubject(subject).setIssuer("shyamravi")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toDays(10)))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();

	}

	public Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(secret.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
	
	public Date getExpDate(String token) {
		return getClaims(token).getExpiration();	
	}
	
	public String getUserName(String token) {
		return getClaims(token).getSubject();
	}
	
	public Boolean isTokenExp(String token) {
		Date expDate = getExpDate(token);
		return expDate.before(new Date(System.currentTimeMillis()));
	}
	
	public 	Boolean validateToken(String token,String fisrtName) {
		String tokenUserName=getUserName(token);
		return(fisrtName.equals(tokenUserName)&& !isTokenExp(token));
	}
	
	
	
	
	

}
