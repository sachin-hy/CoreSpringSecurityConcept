package com.springsecurity.spring_security.controller;


import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.spring_security.customService.EmailService;

import com.springsecurity.spring_security.dto.OtpRequest;
import com.springsecurity.spring_security.dto.SignUpRequest;
import com.springsecurity.spring_security.model.OtpSchema;
import com.springsecurity.spring_security.model.Users;
import com.springsecurity.spring_security.service.OtpService;
import com.springsecurity.spring_security.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;


@RestController
public class ControllerClass {
	
	
	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private OtpService otpService;
	
	
	
	
	
	
	
	@RequestMapping("/api/sendotp")
	public ResponseEntity<?> sendMail(@RequestParam("email") String email)
	{
		
		
		try {
		   // check if user already exist
	            Users checkUserPresent = userService.getUser(email);
		
		   if (checkUserPresent != null) {
		      return new ResponseEntity<>("User Already Registered", HttpStatus.CONFLICT);
		    }
	
		 String otp = RandomStringUtils.randomNumeric(6);
	        //sned otp 
    	        emailService.sendEmail(email, "otp verification email", otp);
	       // check unique otp or not
	
	       //save otp
	       OtpSchema savedOtp = otpService.saveOtp(otp,email);
			
	     return new ResponseEntity<>("Otp sent Successfully",HttpStatus.OK);
	   }catch(Exception e)
		{
		      return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

	@RequestMapping("/api/signup")
	public ResponseEntity<?> signup(@RequestBody SignUpRequest signupRequest)
	{
		try {
			
			 String dbotp = otpService.getOtpFromDb(signupRequest.getEmail());
			 
			 if(dbotp == null || !dbotp.equals(signupRequest.getOtp()))
			 {
				 return new ResponseEntity<>("Invalid Otp",HttpStatus.BAD_REQUEST); 
			 }
			
			 Users user = userService.saveUser(signupRequest);
			 
			 return new ResponseEntity<>(user,HttpStatus.CREATED);
		}catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping("/api/test")
	public ResponseEntity<String> testFunction(Principal principal)
	{
		String email = principal.getName();
		
		System.out.println("user email  = " + email);
		
		
		return new ResponseEntity<>("controller called successfully",HttpStatus.OK);
	}

	@RequestMapping("/api/home")
	public ResponseEntity<String> homePage()
	{
		return new ResponseEntity<>("Home page url called successfully",HttpStatus.OK);
	}
	
	@RequestMapping("/api/error")
	public ResponseEntity<String> errorPage()
	{
		return new ResponseEntity<>("error page url is called",HttpStatus.OK);
	}

	@RequestMapping("/api/logout")
	public ResponseEntity<String> logoutPage()
	{
		
		SecurityContextHolder.getContext().setAuthentication(null);

		
		return new ResponseEntity<>("Logout successfull",HttpStatus.OK);
	}
}
