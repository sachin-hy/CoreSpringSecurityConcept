package com.springsecurity.spring_security.customService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.spring_security.model.Users;
import com.springsecurity.spring_security.repository.UserRepsoitory;
import com.springsecurity.spring_security.service.UserService;

@Service
public class SecurityCustomAdminDetailService implements UserDetailsService {

	@Autowired
	private UserRepsoitory userRepsoitory;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("username = " + username);
		Users user = userRepsoitory.findByEmail(username);
		
		return  User.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.roles(user.getRole().toArray(new String[0]))
				.build();
	}

}
