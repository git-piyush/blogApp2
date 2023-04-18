package com.blog.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Post;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;


@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public Post savePost(Post post) {
		if(postRepository != null) {
			try {
				return postRepository.save(post);
			} catch (Exception e) {
				//
			}
			
		}
		return null;
	}

}
