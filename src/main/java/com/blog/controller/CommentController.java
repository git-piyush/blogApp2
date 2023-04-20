package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.requestDTO.CommentRequestDTO;
import com.blog.responseDTO.CommentResponseDTO;
import com.blog.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/comment")
	public ResponseEntity<CommentResponseDTO> save(@RequestBody CommentRequestDTO commentRequestDTO, @PathVariable Long postId) {
		
		CommentResponseDTO commentResponseDTO = commentService.save(postId, commentRequestDTO);
		return new ResponseEntity<CommentResponseDTO>(commentResponseDTO, HttpStatus.CREATED);
		
	}
	
}
