package com.blog.service;

import org.springframework.stereotype.Service;

import com.blog.requestDTO.CommentRequestDTO;
import com.blog.responseDTO.CommentResponseDTO;

@Service
public interface CommentService {

	public CommentResponseDTO save(Long postId, CommentRequestDTO comment);
	
}
