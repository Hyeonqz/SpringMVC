package com.example.springbootaws.controller;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.springbootaws.domain.posts.Posts;
import com.example.springbootaws.domain.posts.PostsRepository;
import com.example.springbootaws.web.dto.PostsSaveRequestDto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private PostsRepository postsRepository;
	@After("")
	public void tearDown() throws Exception {
		postsRepository.deleteAll();
	}

	@Test
	public void Posts_등록() throws Exception {
		//given
		String title = "title";
		String content = "content";

		PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
			.title(title)
			.content(content)
			.author("author")
			.build();
		String url = "http://localhost:" + port + "/api/v1/posts";

		//when
		ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

		//then
		assertThat(responseEntity.getStatusCode().equals(HttpStatus.OK));
		assertThat(responseEntity.getBody()).isGreaterThan(0L);

		List<Posts> all = postsRepository.findAll();
		assertThat(all.get(0).getTitle()).isEqualTo(title);
		assertThat(all.get(0).getContent()).isEqualTo(content);

	}
}
