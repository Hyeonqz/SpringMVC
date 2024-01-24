package com.example.springbootaws.web.posts;

import org.springframework.stereotype.Service;

import com.example.springbootaws.domain.posts.Posts;
import com.example.springbootaws.domain.posts.PostsRepository;
import com.example.springbootaws.web.dto.PostsResponseDto;
import com.example.springbootaws.web.dto.PostsSaveRequestDto;
import com.example.springbootaws.web.dto.PostsUpdateRequestDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
	private final PostsRepository postsRepository;

	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository.findById(id)
			.orElseThrow( () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id) );

		posts.update(requestDto.getTitle(), requestDto.getContent());
		return id;
	}

	//JPA핵심 내용은 JPA의 영속성 컨텍스트 때문이다.
	//영속성 컨텍스트란 -> 엔티티를 저장하는 환경이다.

	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id)
			.orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

		return new PostsResponseDto(entity);
	}
}
