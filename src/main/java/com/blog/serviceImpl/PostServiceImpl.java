package com.blog.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public Post savePost(Post post) {
		if (postRepository != null) {
			try {
				return postRepository.save(post);
			} catch (Exception e) {
				//
			}

		}
		return null;
	}

	@Override
	public List<Post> getAllPost() {
		List<Post> allPost = postRepository.findAll();
		if (allPost != null) {
			return allPost;
		}
		return new ArrayList<Post>();
	}

	@Override
	public Post findPostById(Long postId) {
		//Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "Post Id", postId));
		
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		return post;
	}

	@Override
	public Post updatePost(Long postId, Post post) {
		
		Post dbPost = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		
		dbPost.setTitle(post.getTitle());
		dbPost.setDescription(post.getDescription());
		dbPost.setContent(post.getContent());
		
		Post updatedPost = postRepository.save(dbPost);
		return updatedPost;
	}

	@Override
	public Post deletePost(Long postId) {
		Post dbPost = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		postRepository.deleteById(postId);
		return dbPost;
	}
	
}
