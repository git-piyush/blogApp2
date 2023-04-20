package com.blog.requestDTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDTO {

	@NotEmpty
	@Size(min=2, message="Post title should be at least 2 charecters")
	private String name;
	
	@NotEmpty
	private String email;
	
	private String body;
	
}
