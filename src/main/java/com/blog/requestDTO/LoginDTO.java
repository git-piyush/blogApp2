package com.blog.requestDTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

	@NotEmpty(message = "Username should be at least 2 charecters")
	@Size(min = 2, message="Username should be at least 2 charecters")
	private String userNameOrEmail;
	
	@NotEmpty(message = "Password should be at least 2 charecters")
	@Size(min = 2, message="Password should be at least 2 charecters")
	private String password;
	
}
