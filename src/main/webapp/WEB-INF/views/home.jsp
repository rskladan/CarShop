<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>Car Shop Online</title>
    <link href='<c:url value="/WEB-INF/css/styles.css"/>' rel="stylesheet">

</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"/>

<br>
<a href="/registration">Register!!!</a>
<br>
<a href="/login">Login!!!</a>

<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body>
</html>