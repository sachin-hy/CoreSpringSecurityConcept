package com.springsecurity.spring_security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class OtpRequest {

	
	private String otp;
	private String email;
}
