<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  MemberRepository memberRepository = MemberRepository.getInstance();
  List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
  <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
  </thead>
  <tbody>
    <td>${member.id}</td>
    <td>${member.username}</td>
    <td>${member.age}</td>
  </tbody>
</table>

</body>
</html>
