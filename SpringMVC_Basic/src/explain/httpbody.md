## http 요청 메시지 - 단순 텍스트
- http api 에서 주로 사용 JSON, XML, TEXT
- 데이터 형식은 주로 JSON사용
- POST,PUT,PATCH,GET

요청 파라미터와 다르게 http메시지 바디를 통해 데이터가 직접 데이터가 넘어오는 경우<br>
@RequestParam, @ModelAttrubute를 사용할 수 없다.
```java
	@PostMapping("/request-body-string-v2")
	public HttpEntity<String> requestBodyStringV1(HttpEntity<String> httpEntity) throws IOException {
		//메시지 컨버터 작동
		String body = httpEntity.getBody();
		log.info("messageBody={}", body);
		
		return new HttpEntity<>("ok");
	}
```
- 1) HttpEntity : http헤더와 body 정보를 편리하게 조회할 수 있다.
  - 메시지 바디 정보를 직접 조회
  - 요청 파라미터를 조회하는 기능과 관계가 없다. ( get방식 or  html폼전송하는(post)일 떄 => requestparam, modelAttribute)
- 2) 응답에도 사용 가능
    - 메시지 바디 정보 직접 반환
    - 헤더 정보 포함 가능
    - View 조회 X -> Rest API이다

-1 : RequestEntity<><br>
🖐 httpmethod, url 정보 추가 요청에 사용

-2 : ResponseEntity<> <br>
-> http상태 코드를 넣을 수 있다<br>
```
return new ResponseEntity<String>("ok",HttpstatusCode.CREATED)<br>
```

> 스프링 MVC 내부에서 http 메시지 바디를 읽어서 문자나 객체로 변환이 가능하다
> > 바로이게 http 메시지 컨버터 이다.
> >> HttpMessageConverter

위 과정들이 다 귀찮아서 만들어진 Annotation이 바로 @**RequestBody** 이다

### @Requestbody
1) http 메시지 바디 정보를 편리하게 조회할 수 있다.
2) 헤더 정보가 필요하면, httpentity나 reqeustController를 사용하면됨
3) 이 messsagebody는 요청 파라미터(requestparam, modelattribtue) 랑 관계가 없다

#### 요청 파라미터 vs http메시지 바디
   - 요청 파라미터(@Requestparam, @Modelattribtue)
   - http메시지 바디를 직접 조회하는 기능 @RequestBody

### @ResponeBody
1) 응답결과를 http메시지 바디에 직접 담아서 전달한다.
2) 이 경우에는 view를 사용하지 않는다.
즉 -> Responsebody는 View를 사용하지 않는다.


---

