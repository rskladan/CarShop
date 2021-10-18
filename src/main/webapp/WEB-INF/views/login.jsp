<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
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
    <div>
        <c:if test = "${param.error}">
        <p style="font-size: 20px; color: #FF1C19;">User Name or Password invalid, please verify</p>
        </c:if>
    </div>

</form:form>

</body>
</html>