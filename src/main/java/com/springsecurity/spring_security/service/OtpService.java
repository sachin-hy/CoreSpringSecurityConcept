package com.springsecurity.spring_security.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.springsecurity.spring_security.model.OtpSchema;
import com.springsecurity.spring_security.repository.OptRepository;

import jakarta.transaction.Transactional;

@Service
public class OtpService {

	@Autowired
	private OptRepository otpRepository;
	
	
	
	@Transactional
	public OtpSchema saveOtp(String otp,String email) {
		OtpSchema otpSchema = new OtpSchema();
		otpSchema.setEmail(email);
		otpSchema.setOtp(otp);
		otpSchema.setCreated_at(LocalDateTime.now());
		otpSchema.setExpire_at(LocalDateTime.now().plusMinutes(10));
		
		return otpRepository.save(otpSchema) ;
	}



	@Transactional
	public String getOtpFromDb(String email) {
		
		List<OtpSchema> listOfOtp = otpRepository.findByEmail(email);
		
		listOfOtp.sort((obj1,obj2)->obj2.getCreated_at().compareTo(obj1.getCreated_at()));
		LocalDateTime expiretime= listOfOtp.get(0).getExpire_at();
		
		if(expiretime.isBefore(LocalDateTime.now()))
		{
			return null;
		}
		return listOfOtp.get(0).getOtp();
	}

}
