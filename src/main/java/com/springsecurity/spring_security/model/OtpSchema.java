package com.springsecurity.spring_security.model;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class OtpSchema {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  Long id;
	private String email;
	private String otp;
	private LocalDateTime created_at;
	private LocalDateTime expire_at;
}
