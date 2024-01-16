package com.example.springbootaws.jpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springbootaws.domain.posts.Posts;
import com.example.springbootaws.domain.posts.PostsRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.*;

@SpringBootTest
public class PostsRepositoryTest {

	@Autowired
	PostsRepository postsRepository;

	@After("")
	public void cleanup() {
		postsRepository.deleteAll();
	}

	@Test
	public void 게시글_불러오기() {
		//given
		String title = "테스트 게시글";
		String content = "테스트 본문";

		postsRepository.save(Posts.builder()
			.title(title)
			.content(content)
			.author("jinhyeonkyu")
			.build());

		//when
		ArrayList<Posts> postsList = new ArrayList<>();

		//then
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle()).isEqualTo(title);
		assertThat(posts.getContent()).isEqualTo(content);
	}

	@Test
	public void BaseTimeEntit_등록() {
		//given
		LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
		postsRepository.save(Posts.builder()
			.title("title")
			.content("content")
			.author("author")
			.build());

		//when
		List<Posts> postsList = postsRepository.findAll();

		//then
		Posts posts = postsList.get(0);

		System.out.println(">>>>>>>> createDate = " + posts.getCreatedDate()
			+ ", modifiredDate = " + posts.getModifiedDate());

		assertThat(posts.getCreatedDate()).isAfter(now);
		assertThat(posts.getModifiedDate()).isAfter(now);

	}
}
