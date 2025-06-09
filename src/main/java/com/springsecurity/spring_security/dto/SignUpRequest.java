package com.springsecurity.spring_security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
	private String email;
	private String password;
	private String role;
	private String otp;
}
