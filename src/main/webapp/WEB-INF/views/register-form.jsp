<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<form:form method="post" action="/register" modelAttribute="user">
    Name: <form:input path="name"/></br>
    <form:errors path="name"/></br>

    Surname: <form:input path="surname"/></br>
    <form:errors path="surname"/></br>

    E-mail: <form:input path="email"/></br>
    <form:errors path="email"/></br>

    Password: <form:input type="password" path="password" /></br>
    <form:errors path="password"/></br>

    <input type="submit" value="register">

</form:form>



</body>
</html>