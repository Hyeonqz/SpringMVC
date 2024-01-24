```java
@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
	private String title;
	private String content;

	@Builder
	public PostsUpdateRequestDto(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
```
1) requestdto 에서 빌더패턴으로 생성자에 주입을 시킨다.
2) 그리고 엔티티에 들어가서 메서드를 하나 만든다.
```java
	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
```
업데이트를 할 수 있는 메소드를 하나 만든다.<br>

나중에 사용할때 서비스 메소드<br>
```java
	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository.findById(id)
			.orElseThrow( () -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id) );

		posts.update(requestDto.getTitle(), requestDto.getContent());
		return id;
	}
```
- id 와 requestDto 객체를 받고
- post 객체를 만들어 저장하고 posts 객체에 만들어둔 update메소드를 활용하여 업데이트 한다. 
