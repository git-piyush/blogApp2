package com.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.PostRepository;
import com.blog.requestDTO.PostRequestDTO;
import com.blog.responseDTO.PostResponse;
import com.blog.responseDTO.PostResponseDTO;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public PostResponseDTO savePost(PostRequestDTO postRequestDTO) {
		Post newPost = mapPostRequestDTOToPostEntity(postRequestDTO);

		Post post = postRepository.save(newPost);
		
		return mapPostToPostResponseDTO(post);

	}

	@Override
	public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String ascDir) {
		
		Sort sort = ascDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():
			Sort.by(sortBy).descending();
		
		Pageable page = PageRequest.of(pageNo, pageSize, sort);
		Page<Post> allPost = postRepository.findAll(page);
		if (allPost != null) {
			List<PostResponseDTO> allPosts = allPost.getContent().stream().map(post -> mapPostToPostResponseDTO(post)).collect(Collectors.toList());
			PostResponse postResponse = new PostResponse();
			postResponse.setPostResponse(allPosts);
			postResponse.setPageNo(allPost.getNumber());
			postResponse.setPageSize(allPost.getSize());
			postResponse.setTotalElements(allPost.getTotalElements());
			postResponse.setTotalPages(allPost.getTotalPages());
			postResponse.setLast(allPost.isLast());
			return postResponse;
		}
		return new PostResponse();
	}

	@Override
	public PostResponseDTO findPostById(Long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		return mapPostToPostResponseDTO(post);
	}

	@Override
	public PostResponseDTO updatePost(Long postId, PostRequestDTO post) {
		
		Post dbPost = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		
		dbPost.setTitle(post.getTitle());
		dbPost.setDescription(post.getDescription());
		dbPost.setContent(post.getContent());
		Post updatedPost = postRepository.save(dbPost);
		return mapPostToPostResponseDTO(updatedPost);
	}

	@Override
	public Post deletePost(Long postId) {
		Post dbPost = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		postRepository.deleteById(postId);
		return dbPost;
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
