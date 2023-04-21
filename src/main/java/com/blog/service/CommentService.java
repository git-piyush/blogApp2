package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.requestDTO.CommentRequestDTO;
import com.blog.responseDTO.CommentResponseDTO;

@Service
public interface CommentService {

	public CommentResponseDTO save(Long postId, CommentRequestDTO comment);
	
	public List<CommentResponseDTO> getCommentByPostId(Long postId);
	
	public CommentResponseDTO getCommentByCommentId(Long postId, Long commentId);
	
	public CommentResponseDTO updateCommentById(Long postId, Long commentId, CommentRequestDTO commentRequestDTO);

	public String deleteByCommentId(Long postId, Long commentId);
}
