package com.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.requestDTO.LoginDTO;
import com.blog.requestDTO.RegisterDTO;
import com.blog.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}
	
	@PostMapping({"/login", "/signin"})
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
		String msg = authService.login(loginDTO);
		
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@PostMapping({"/register", "/signup"})
	public ResponseEntity<String> userRegister(@RequestBody RegisterDTO registerDTO){
		String msg = authService.register(registerDTO);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}
	
}
