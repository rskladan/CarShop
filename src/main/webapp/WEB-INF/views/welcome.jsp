<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/views/header.jsp"/>
    <body>
        <h2>Welcome, ${loggedUser.name} ${loggedUser.surname}</h2>
        <br>
        <a href="/shoppingCart">Go to store</a>
        <a href="/accountInfo">Edit user</a>
        <a href="/orderHistory">Order history</a>
        <jsp:include page="/WEB-INF/views/footer.jsp"/>
    </body>
</html>