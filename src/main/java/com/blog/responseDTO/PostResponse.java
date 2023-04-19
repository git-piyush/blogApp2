package com.blog.responseDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostResponse {

	private List<PostResponseDTO> postResponse;

	private int pageNo;

	private int pageSize;

	private long totalElements;

	private int totalPages;

	private boolean last;

}
