<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<div class="header-container">
    <div class="site-name">Online Shop</div>

    <div class="header-bar">
        <div>
        <c:if test = "${loggedUser != null}">
            Hello
            <a href="/accountInfo"> ${loggedUser.name}</a>
            <a href="/logout">Logout</a>
        </c:if>

        <c:if test = "${loggedUser == null}">
            <a href="/login">Login</a>
        </c:if>
        </div>
    </div>

</div>

