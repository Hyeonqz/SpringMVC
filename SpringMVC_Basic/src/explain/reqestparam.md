## @RequestParam

http요청 파라미터에 사용
```java
	@RequestMapping("/request-param-v2")
	@ResponseBody
	public String requestParamV2(
		@RequestParam("username") String memberName,
			@RequestParam("age") int memberAge) {

		log.info("username={}, age={}", memberName,memberAge);
		return "ok";
	}
```
@RequestParam은 HttpservleReques에. <br>
request.getParameter()랑 같은 효과를 가지고 있다.
- http 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="XX) 생략 가능
- String, int, Integer등의 단순 타입이면 @RequestParam도 생략 가능
- @RequestParam이 있으면 명확하게 요청 파리미터에서 데이터를 읽는 다는 것을 알 수 있다. 


```java
	@RequestParam(required = false) String username,
    @RequestParam(required = true) int age) 
```
#### required = true 일 경우 무조건 값이 들어와 있어야 한다. 
1) 기본일 경우는 필수이다.(true값이다)
> 파라미터 이름만 있고 값이 없는 경우 => 빈문자열로 통과 된다.
2) required = false; => 값을 않넣어두도 된다, 

깨알 팁
```java
int a = null; //안됌
Integer b = null; //가능
null 과 ""(빈값) 는 다르다.
```
#### defaultValue
```java
@RequestParam(required = false, defaultValue = "guset") String username
```
1) 값이 안들어오면 자동으로 guest로 넣어준다는 뜻.
2) defaultValue가 들어가게되면, required false는 들어가지 않는다. 


### Map으로 전체 출력하기

```java

	//Map으로 출력하기.
	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequire1(
		@RequestParam Map<String,Object> paramMap) {

		log.info("username={}, age={}",paramMap.get("username"),paramMap.get("age"));

		return "ok";
	}
```

파라미터를 Map 또는 MultiValueMap으로 조회할 수 있다<br>
