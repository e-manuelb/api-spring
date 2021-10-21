package com.example.mv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class WebServerConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
//	{
//	    auth.parentAuthenticationManager(authenticationManagerBean());
//	        .userDetailsService();
//	}

}
