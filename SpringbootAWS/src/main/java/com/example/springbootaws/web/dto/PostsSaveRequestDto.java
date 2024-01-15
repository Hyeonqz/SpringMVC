package com.example.springbootaws.web.dto;

import com.example.springbootaws.domain.posts.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
	// Request 와 Response 용 클래스
	// View를 위한 클래스라 자주 변경이 필요한 값 및 객체 저장한다.
	// Dto 클래스는 view를 위해 존재하는 것이다
	// 그러므로 DB Layer 와 View Layter를 분리하는 것이 많다.
	private String title;
	private String content;
	private String author;

	@Builder
	public PostsSaveRequestDto(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}

	public Posts toEntity() {
		return Posts.builder()
			.title(title)
			.content(content)
			.author(author)
			.build();
	}
}
