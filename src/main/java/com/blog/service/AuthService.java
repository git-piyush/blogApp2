package com.blog.service;

import org.springframework.stereotype.Service;

import com.blog.requestDTO.LoginDTO;

@Service
public interface AuthService {

	String login(LoginDTO loginDTO);
	
}
