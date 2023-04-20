package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.entity.Post;
import com.blog.requestDTO.PostRequestDTO;
import com.blog.responseDTO.PostResponse;
import com.blog.responseDTO.PostResponseDTO;

@Service
public interface PostService {

	public PostResponseDTO savePost(PostRequestDTO postRequestDTO);
	
	public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String ascDir);
	
	public PostResponseDTO findPostById(Long postId);
	
	public PostResponseDTO updatePost(Long postId, PostRequestDTO post);

	public Post deletePost(Long postId);
	
}
