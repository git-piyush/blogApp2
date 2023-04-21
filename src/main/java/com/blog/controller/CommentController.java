package com.blog.controller;

import java.util.List;

import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(value = "/post/{postId}/comment", method = RequestMethod.GET)
	public ResponseEntity<List<CommentResponseDTO>> getAllCommentByPostId(@PathVariable Long postId){		
		List<CommentResponseDTO> comments = commentService.getCommentByPostId(postId);		
		return new ResponseEntity<List<CommentResponseDTO>>(comments, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/post/{postId}/comment/{commentId}", method = RequestMethod.GET)
	public ResponseEntity<CommentResponseDTO> getCommentByCommentId(@PathVariable Long postId, @PathVariable Long commentId){		
		CommentResponseDTO comment = commentService.getCommentByCommentId(postId, commentId);	
		return new ResponseEntity<CommentResponseDTO>(comment, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/post/{postId}/comment/{commentId}", method = RequestMethod.PUT)
	public ResponseEntity<CommentResponseDTO> updateCommentByCommentId( @RequestBody CommentRequestDTO commentRequestDTO ,@PathVariable Long postId, @PathVariable Long commentId){		
		CommentResponseDTO comment = commentService.updateCommentById(postId, commentId, commentRequestDTO);	
		return new ResponseEntity<CommentResponseDTO>(comment, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/post/{postId}/comment/{commentId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCommentByCommentId(@PathVariable Long postId, @PathVariable Long commentId){		
		String msg = commentService.deleteByCommentId(postId, commentId);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	
}
