## Http 요청 파라미터, @ModelAttribute

실제 개발을 하면 요청 파라미터를 받아, 필요한 객체를 만들고, 그 객체에 값을 넣어주어야 한다.

예시1)<br>
```java
	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV2(@ModelAttribute HelloData helloData) {
		log.info(helloData.getUsername(),helloData.getAge());
		log.info(String.valueOf(helloData));

		return "ok";
	}
```
1) @Data 는 getter,setter,toString,equals,RequiredConstrucotr 을 자동으로 생성해줌
2) HelloData 객체를 생성한다
3) 객체의 프로퍼티를 찾고, 해당 프로퍼티의 setter를 호출해서 파라미터의 값을 입력한다
4) ex) username이면 setUsername()을 찾아서 호출한다. 
5) 변경하면 setUserName() , 조회하면 getUserName()호출

스프링은 생략이 가능하다<br>
무엇을 기준으로 생략을하나?
1) String, int, Integer 단순타입은 @RequestParam 생략 가능
2) 나머지는 @ModelAttribute (argument resolver로 지정해준 타입은 제외)