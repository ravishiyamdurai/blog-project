package com.te.blogbase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.te.blogbase.authentication.UserAccountAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {
	@Autowired
	private UserAccountAuthenticationProvider accountAuthenticationProvider;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder=http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(accountAuthenticationProvider);
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/admin/userregister", "/admin/login","/viewer/**").permitAll()
		.anyRequest()
		.hasAnyRole("user","admin")
		.and()
		.httpBasic(Customizer.withDefaults())
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	        return encoder;
	    }
}

//	@Bean  
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.withUsername("user")
//            .password(passwordEncoder.encode("password"))
//            .roles("USER")
//            .build();
//
//        UserDetails admin = User.withUsername("admin")
//            .password(passwordEncoder.encode("admin"))
//            .roles("ADMIN")
//            .build(); 
//
//		UserDetails viewer = User.withUsername("viewer")
//            .password(passwordEncoder.encode("viewer"))
//            .roles("viewer")
//            .build();
//
//        return new InMemoryUserDetailsManager(user, admin,viewer);
//    }

   



