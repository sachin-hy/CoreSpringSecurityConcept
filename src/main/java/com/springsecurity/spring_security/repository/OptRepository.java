package com.springsecurity.spring_security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.spring_security.model.OtpSchema;

public interface OptRepository extends JpaRepository<OtpSchema,Long>{

   List<OtpSchema> findByEmail(String email);

}
