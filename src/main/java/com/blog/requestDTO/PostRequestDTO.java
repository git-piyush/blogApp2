package com.blog.requestDTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostRequestDTO {

	private long id;
	
	@NotEmpty
	@Size(min = 2, message="Post title should be at least 2 charecters")
	private String title;
	
	private String description;
	
	private String content;
	
	
}
