package com.springsecurity.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.spring_security.model.Users;

@Repository
public interface UserRepsoitory extends JpaRepository<Users,Long>{

     Users findByEmail(String email);

}
