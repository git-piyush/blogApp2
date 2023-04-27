package com.blog.service;

import org.springframework.stereotype.Service;

import com.blog.requestDTO.LoginDTO;
import com.blog.requestDTO.RegisterDTO;

@Service
public interface AuthService {

	String login(LoginDTO loginDTO);
	
	String register(RegisterDTO registerDTO);
	
}
