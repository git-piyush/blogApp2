package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.Post;
import com.blog.requestDTO.PostRequestDTO;
import com.blog.responseDTO.PostResponseDTO;
import com.blog.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	@PostMapping("/savePost")
	public ResponseEntity saveNewPost(@RequestBody PostRequestDTO postRequestDTO) {
		
		//covert postRequestDTO to post entity
		Post post = new Post();
		post.setTitle(postRequestDTO.getTitle());
		post.setDescription(postRequestDTO.getDescription());
		post.setContent(postRequestDTO.getContent());
		Post newPost = postService.savePost(post);
		
		//convert new post to postResponseDTO
		
		PostResponseDTO postResponseDTO = new PostResponseDTO();
		postResponseDTO.setId(newPost.getId());
		postResponseDTO.setTitle(newPost.getTitle());
		postResponseDTO.setDescription(newPost.getDescription());
		postResponseDTO.setContent(newPost.getContent());
		
		
		
		return new ResponseEntity<>(postResponseDTO, HttpStatus.CREATED);
	}
	
}
