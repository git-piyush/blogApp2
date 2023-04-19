package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;

@Service
public interface PostService {

	public Post savePost(Post post);
	
	public List<Post> getAllPost();
	
	public Post findPostById(Long postId);
	
	public Post updatePost(Long postId, Post post);

	public Post deletePost(Long postId);
	
}
