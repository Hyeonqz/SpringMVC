<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<%--상대경로 사용, [현재URL이 속한 계층 경로 + /save--%>
<form action="save" method="post">
    username : <input type="text" name="username"/>
    age : <input type="text" name="age"/>
    <button type="submit">전송</button>
</form>
<%--이후 코드에서 해당 jsp를 계속 사용하기 때문에 상대경로를 사용한 부분을 그대로 유지 해야함.--%>

</body>
</html>
