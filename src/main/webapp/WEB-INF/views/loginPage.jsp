<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>Login page</h2>

<form:form method="post" action="/login" modelAttribute="user">


    <form:input path="email" type="text" placeholder="Email"/>
    <form:input path="password" type="password" placeholder="Password"/>

    <button type="submit">Log in</button>
    <br>

    <h2>${error}</h2>

</form:form>

</body>
</html>