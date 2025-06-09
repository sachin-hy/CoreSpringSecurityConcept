package com.springsecurity.spring_security.service;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import com.springsecurity.spring_security.dto.OtpRequest;
import com.springsecurity.spring_security.dto.SignUpRequest;
import com.springsecurity.spring_security.model.OtpSchema;
import com.springsecurity.spring_security.model.Users;
import com.springsecurity.spring_security.repository.OptRepository;
import com.springsecurity.spring_security.repository.UserRepsoitory;
import com.springsecurity.spring_security.service.exception.UserAlreadyExistsException;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepsoitory userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	@Transactional
	public Users getUser(String email)
	{
		return userRepository.findByEmail(email);
	}

	
	
	
	
	@Transactional
	public Users saveUser(SignUpRequest signupRequest) throws UserAlreadyExistsException {
		
		 Users user1 = userRepository.findByEmail(signupRequest.getEmail());
		 
		 if(user1 != null)
		 {
			 Set<String> roles = user1.getRole();
			 
			 for(String role : roles)
			 {
				 if(role.equals(signupRequest.getRole()))
				 {
					 throw new UserAlreadyExistsException("User with this role already exists");
				 }
			 }
			 
			 roles.add(signupRequest.getRole());
			 user1.setRole(roles);
			 return userRepository.save(user1);
		 }else
		 {
			    Users user = new Users();
				user.setEmail(signupRequest.getEmail());
				user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
				user.setRole(Set.of(signupRequest.getRole()));
				return userRepository.save(user);
		 }
		 
		
	}




	    
}         
