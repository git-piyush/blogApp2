package com.blog.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.exception.BlogApiException;
import com.blog.repository.RoleRepository;
import com.blog.repository.UserRepository;
import com.blog.requestDTO.LoginDTO;
import com.blog.requestDTO.RegisterDTO;
import com.blog.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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

	@Override
	public String register(RegisterDTO registerDTO) {
		
		//check for username exists in database
		if(userRepository.existsByUserName(registerDTO.getUserName())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Username is already exist!!");
		}
		
		//check for email exixts in database
		if(userRepository.existsByEmail(registerDTO.getEmail())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Email is already exist!!");
		}
		
		User user = new User();
		user.setUserName(registerDTO.getUserName());
		user.setName(registerDTO.getName());
		user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
		user.setEmail(registerDTO.getEmail());
		
		Set<Role> roles = new HashSet<>();
		
		Role role = roleRepository.findByName("ROLE_USER").get();
		
		
		roles.add(role);
		
		user.setRoles(roles);
		
		userRepository.save(user);
		return "User Registered Successfully";
	}

}
