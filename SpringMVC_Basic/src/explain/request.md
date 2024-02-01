## 요청 매핑

```java
	@RequestMapping({"/hello-basic", "hello-go"})
	public String helloBasic() {
		log.info("hellobasic");
		return "ok";
	}
```
매핑안에 배열로 { } 이렇게 넣어도 두개의 맵핑이 가능하다<br>
**or** 조건으로 처리함


### PathVariable Style
```java
	@GetMapping("/mapping/{userId}")
	public String mappingPath(@PathVariable("userId") String userId) {
		log.info("mapppingPath userId={}",userId);
		return "ok";
	}
```
```java
	@GetMapping("/mapping/{userId}")
	public String mappingPath(@PathVariable String userId) {
		log.info("mapppingPath userId={}",userId);
		return "ok";
	}
	//PathVariable 변수 이름이랑 맵핑 변수랑 같은 이름이면 명시한것지워두됌
```
- HTTP API는 다음과 같이 리소스 경로에 식별자를 넣는 스타일을 추구

다중 PathVariable
```java
	@GetMapping("/mapping/users/{userId}/orders/{orderId}")
	public String mappingPath(
		@PathVariable("userId") String userId,
		@PathVariable Long orderId) {
		log.info("mapppingPath userId={}",userId);
		log.info("orderId={}",orderId);
		return "ok";
	}
```

쿼리파라미터 조건 매핑
```java
@GetMapping(value = "/mapping-param", params = "mode-debug") 
		public String mappingParam() {
			log.info("hi");
			return "ok";
		}
```

미디어 타입


### 요청 매핑
1) get : 조회
2) post : 등록
3) patch : 수정
4) delete : 수정