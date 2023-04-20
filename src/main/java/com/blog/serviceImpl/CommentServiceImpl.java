package com.blog.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.requestDTO.CommentRequestDTO;
import com.blog.responseDTO.CommentResponseDTO;
import com.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public CommentResponseDTO save(Long postId, CommentRequestDTO commentRequestDTO) {
		
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));		
		Comment comment = mapCommentRequestDTOToCommentEntity(commentRequestDTO);	
		comment.setPost(post);	
		Comment newComment = commentRepository.save(comment);
		return mapCommentToCommentResponseDTO(newComment);
	}	
	private Comment mapCommentRequestDTOToCommentEntity(CommentRequestDTO commentRequestDTO) {
		Comment comment = new Comment();
		comment.setBody(commentRequestDTO.getBody());
		comment.setEmail(commentRequestDTO.getEmail());
		comment.setName(commentRequestDTO.getName());		
		return comment;		
	}
	
	private CommentResponseDTO mapCommentToCommentResponseDTO(Comment comment) {
		CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
		commentResponseDTO.setId(comment.getId());
		commentResponseDTO.setBody(comment.getBody());
		commentResponseDTO.setEmail(comment.getEmail());
		commentResponseDTO.setName(comment.getName());
		return commentResponseDTO;		
	}


}
