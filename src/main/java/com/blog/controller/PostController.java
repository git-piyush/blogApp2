package com.blog.controller;

import java.net.Authenticator.RequestorType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	public ResponseEntity saveNewPost(@Valid @RequestBody PostRequestDTO postRequestDTO) {
		
		//covert postRequestDTO to post entity
		/*
		 * Post post = new Post(); post.setTitle(postRequestDTO.getTitle());
		 * post.setDescription(postRequestDTO.getDescription());
		 * post.setContent(postRequestDTO.getContent());
		 */
		Post newPost = postService.savePost(mapPostRequestDTOToPostEntity(postRequestDTO));
		
		//convert new post to postResponseDTO
		/*
		 * PostResponseDTO postResponseDTO = new PostResponseDTO();
		 * postResponseDTO.setId(newPost.getId());
		 * postResponseDTO.setTitle(newPost.getTitle());
		 * postResponseDTO.setDescription(newPost.getDescription());
		 * postResponseDTO.setContent(newPost.getContent());
		 */
		
		return new ResponseEntity<>(mapPostToPostResponseDTO(newPost), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/getAllPost",method = RequestMethod.GET)
	public List<PostResponseDTO> getALLPost(){
		List<Post> allPost = postService.getAllPost();
		 List<PostResponseDTO> allPostResponse = new ArrayList<PostResponseDTO>();
		 allPostResponse = allPost.stream().map(post -> mapPostToPostResponseDTO(post)).collect(Collectors.toList()); 
		 return allPostResponse;
	}
	
	@GetMapping("/getPost/{postId}")
	public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long postId) {
		Post post = postService.findPostById(postId);
		return new ResponseEntity<>(mapPostToPostResponseDTO(post), HttpStatus.OK);
	}
	
	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostResponseDTO> updatePost(@RequestBody PostRequestDTO post, @PathVariable Long postId) {
		Post updatePost = postService.updatePost(postId, mapPostRequestDTOToPostEntity(post));
		return new ResponseEntity<>(mapPostToPostResponseDTO(updatePost), HttpStatus.OK);		
	}
	
	@RequestMapping(value="/deletePost/{postId}",method = RequestMethod.DELETE)
	public String deletePost(@PathVariable Long postId) {
		Post post = postService.deletePost(postId);
		return "Post with Id "+postId+" has been deleted sucessfully";
	}
	
	//convert postRequestDTO to Post Entity
	private Post mapPostRequestDTOToPostEntity(PostRequestDTO postRequestDTO) {
		Post post = new Post();
		post.setTitle(postRequestDTO.getTitle());
		post.setDescription(postRequestDTO.getDescription());
		post.setContent(postRequestDTO.getContent());
		return post;
	}
	
	//convert Post Entity to postResponseDTO
	private PostResponseDTO mapPostToPostResponseDTO(Post post) {
		PostResponseDTO postResponseDTO = new PostResponseDTO();
		postResponseDTO.setId(post.getId());
		postResponseDTO.setTitle(post.getTitle());
		postResponseDTO.setDescription(post.getDescription());
		postResponseDTO.setContent(post.getContent());
		return postResponseDTO;
	}
	
}
