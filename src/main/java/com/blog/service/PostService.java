package com.blog.service;

import org.springframework.stereotype.Service;

import com.blog.entity.Post;

@Service
public interface PostService {

	public Post savePost(Post post);
	
}
