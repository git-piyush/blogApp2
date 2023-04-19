package com.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entity.Post;
import com.blog.requestDTO.PostRequestDTO;
import com.blog.responseDTO.PostResponse;
import com.blog.responseDTO.PostResponseDTO;
import com.blog.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	@PostMapping("/savePost")
	public ResponseEntity<PostResponseDTO> saveNewPost(@Valid @RequestBody PostRequestDTO postRequestDTO) {
		PostResponseDTO newPost = postService.savePost(postRequestDTO);
		return new ResponseEntity<>(newPost, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/getAllPost",method = RequestMethod.GET)
	public ResponseEntity<PostResponse> getALLPost(
			@RequestParam(value="pageNo", defaultValue = "0", required = false) int pageNo,
			@RequestParam(value="pageSize", defaultValue = "5", required = false) int pageSize
			){
		PostResponse allPost = postService.getAllPost(pageNo, pageSize);
		 return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
	}
	
	@GetMapping("/getPost/{postId}")
	public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long postId) {
		PostResponseDTO post = postService.findPostById(postId);
		return new ResponseEntity<PostResponseDTO>(post, HttpStatus.OK);
	}
	
	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostResponseDTO> updatePost(@RequestBody PostRequestDTO post, @PathVariable Long postId) {
		PostResponseDTO updatePost = postService.updatePost(postId, post);
		return new ResponseEntity<PostResponseDTO>(updatePost, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/deletePost/{postId}",method = RequestMethod.DELETE)
	public String deletePost(@PathVariable Long postId) {
		Post post = postService.deletePost(postId);
		return "Post with Id "+postId+" has been deleted sucessfully";
	}
}
