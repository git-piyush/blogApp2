package com.blog.responseDTO;

import java.util.List;

import com.blog.entity.Comment;

import lombok.Data;

@Data
public class PostResponseDTO {

	private long id;
	
	private String title;
	
	private String description;
	
	private String content;
	
	private List<CommentResponseDTO> comment;
	
}
