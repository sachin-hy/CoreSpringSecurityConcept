package com.springsecurity.spring_security.model;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Users {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long userid;
		private String email;
		private String password;
		private Set<String> role;
	
}
