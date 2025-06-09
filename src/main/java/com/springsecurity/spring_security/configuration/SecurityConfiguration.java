package com.springsecurity.spring_security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.springsecurity.spring_security.customService.SecurityCustomAdminDetailService;


@Configuration
public class SecurityConfiguration {

	@Autowired
	private SecurityCustomAdminDetailService securityCustomAdminDetailService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http
		.csrf().disable()
		.authorizeHttpRequests(
				auth -> auth
				.requestMatchers("/api/signup","/api/sendotp").permitAll()
				.requestMatchers("/api/test").hasRole("ADMIN")
				.anyRequest().authenticated()
				
				).formLogin(
				          form-> form
				          .loginProcessingUrl("/api/login")
				          .usernameParameter("email")
				          .passwordParameter("password")
				          .defaultSuccessUrl("/api/home")
				          .failureForwardUrl("/api/error")
				         
						);
		
		return http.build();
		
	}
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationManager()
	{
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(securityCustomAdminDetailService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider;
		
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
