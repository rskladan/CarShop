<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>Login page</h2>

<a href="/registration">Back to registration</a>

<form:form method="post" action="/login" modelAttribute="user">

    <input name="username" id="username" type="text" placeholder="Username"/>
    <input name="password" id="password" type="password" placeholder="Password"/>

    <button type="submit">Log in</button>
    <br>
    <div align="center" th:if="${param.error}">
        <p style="font-size: 20px; color: #FF1C19;">User Name or Password invalid, please verify</p>
    </div>

</form:form>

</body>
</html>