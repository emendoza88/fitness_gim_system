package com.server.fitnessgym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public static BCryptPasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		
		BCryptPasswordEncoder encode = passwordEncode();
		UserBuilder users = User.builder().passwordEncoder(p -> encode.encode(p));
		
		builder.inMemoryAuthentication()
		.withUser(users.username("admin").password("Angie1$").roles("ADMIN", "USER"));
		
	}
}