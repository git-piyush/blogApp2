package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.entity.Post;

@Service
public interface PostService {

	public Post savePost(Post post);
	
	public List<Post> getAllPost();
	
}
