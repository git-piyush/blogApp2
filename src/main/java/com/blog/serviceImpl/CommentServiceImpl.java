package com.blog.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceMismatchException;
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
	@Override
	public List<CommentResponseDTO> getCommentByPostId(Long postId) {
		List<CommentResponseDTO> result = new ArrayList<CommentResponseDTO>();
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		List<Comment> comments = commentRepository.findByPostId(postId);
		result = comments.stream().map(comment -> mapCommentToCommentResponseDTO(comment)).collect(Collectors.toList());
		return result;
	}
	@Override
	public CommentResponseDTO getCommentByCommentId(Long postId, Long commentId) {
		
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));		
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
		
		if(!(comment.getPost().getId().equals(post.getId()))) {
			throw new ResourceMismatchException(HttpStatus.BAD_REQUEST, "Comment do not belong to Post");
		}		
		return mapCommentToCommentResponseDTO(comment);
	}
	@Override
	public CommentResponseDTO updateCommentById(Long postId, Long commentId, CommentRequestDTO commentRequestDTO) {
		
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));		
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
		if(!(comment.getPost().getId().equals(post.getId()))) {
			throw new ResourceMismatchException(HttpStatus.BAD_REQUEST, "Comment do not belong to Post");
		}
		comment.setName(commentRequestDTO.getName());
		comment.setEmail(commentRequestDTO.getEmail());
		comment.setBody(commentRequestDTO.getBody());
		comment.setPost(post);
		Comment updatedComment = commentRepository.save(comment);
		return mapCommentToCommentResponseDTO(updatedComment);
	}
	@Override
	public String deleteByCommentId(Long postId, Long commentId) {
		
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));		
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
		if(!(comment.getPost().getId().equals(post.getId()))) {
			throw new ResourceMismatchException(HttpStatus.BAD_REQUEST, "Comment do not belong to Post");
		}
		commentRepository.deleteById(commentId);
		return "Comment has been deleted Sucessfully";
	}


}
