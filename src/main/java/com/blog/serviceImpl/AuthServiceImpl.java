package com.blog.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blog.requestDTO.LoginDTO;
import com.blog.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	private AuthenticationManager authenticationManager;
	
	@Autowired
	public AuthServiceImpl(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public String login(LoginDTO loginDTO) {
		Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDTO.getUserNameOrEmail(), loginDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "User Logged-in Successfully.";
	}

}
