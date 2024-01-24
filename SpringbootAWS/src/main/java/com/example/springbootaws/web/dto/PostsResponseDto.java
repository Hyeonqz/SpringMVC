package com.example.springbootaws.web.dto;

import com.example.springbootaws.domain.posts.Posts;

import lombok.Getter;

@Getter
public class PostsResponseDto {
	//Response는 Entity의 필드 중 일부만 사용한다.

	private Long id;
	private String title;
	private String content;
	private String author;

	public PostsResponseDto(Posts entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getContent();
		this.author = entity.getAuthor();
	}
}
