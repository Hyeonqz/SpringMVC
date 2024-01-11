# JSP

#### JSP 사용하려면 build.gradle에 라이브러리 추가

``` 코드
//JSP 추가 시작
implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
implementation 'javax.servlet:jstl'
//JSP 추가 끝
```
라이브러리 추가 후, 코끼리 버튼 **reload** 하기

JSP에서 비즈니스 로직은<br>
<% ~ %> 을 사용해서 View에서 로직 처리도 가능 하게함.

``` 코드
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    //request, response 바로 사용 가능( servlet으로 변환되서 사용된다고 함) 문법상 지원이 됨.
    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("MemberSaveServlet.service");
    String username=  request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username,age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id = <%=member.getId()%></li>
    <li>username = <%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
```

위 코드 처럼 JSP안에 비즈니스 로직을 처리할 수 있게 만들 수 있다.

### 정리

1️⃣ 
``` 코드
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
```
위 코드는 JSP문서라는 뜻이다. 이렇게 시작해야 함.

2️⃣ JSP는 서버 내부에서 서블릿으로 변환되는데, 우리가 만들었던 MemberFormServlet과 비슷한 모습

3️⃣ JSP는 자바 코드를 그대로 사용할 수 있다.<br>
- <% %> 자바 코드 입력<br>
- <%= %> 자바 코드 출력

### JSP와 서블릿의 한계
1️⃣ 서블릿으로 개발 시 View화면을 위한 HTML을 만드는 작업이 자바 코드에 섞여서 복잡함<br>
2️⃣ JSP를 사용함으로 View를 생성하는 파일을 가져가고, 중간 중간 동적으로 변경 가능함.<br>

하지만 회원 저장 JSP를 보면 절반은 비즈니스 로직, 절반은 View 코드 이다.<br>

즉 JSP가 너무 많은 역할을 한다.<br>

그래서 MVC 패턴의 등장으로 종결이 되었다. <br>
비즈니스 로직 -> Model<br>
보이는 곳(UI) -> View<br>
DB 전달 -> Model<br>

