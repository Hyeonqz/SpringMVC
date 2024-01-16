package com.example.springbootaws.domain.posts;

import com.example.springbootaws.domain.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class Posts extends BaseTimeEntity {
//Entity 클래스에는 절대 Setter 메소드를 만들지 않습니다.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 500, nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	private String author;

	@Builder
	public Posts(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}
	//생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
