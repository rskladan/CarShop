<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart Details</title>
</head>
<body>

<h2>Welcome, ${loggedUser.name} ${loggedUser.surname}</h2>
<h3>Cart: ${currentCart.id}</h3>

<a href="/shoppingCart">Powrót do sklepu</a>

<br>
<p>Dodano cartItem o id: {cartItem.id}</p>

<table>
    <c:forEach var="item" items="${cartItems}">
        <tr>
            <td>${item.id}</td>
            <td>${item.quantity}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>