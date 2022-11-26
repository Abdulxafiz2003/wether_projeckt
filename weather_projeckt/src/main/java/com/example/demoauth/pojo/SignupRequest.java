package com.example.demoauth.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Setter
@Getter
public class SignupRequest {
	private String username;
	private String email;
	private Set<String> roles;
	private String password;
}
