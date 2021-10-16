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
<h3>Cart: ${shoppingCart.id}</h3>

<a href="/shoppingCart">Powrót do sklepu</a>

<br>
<p>Dodano cartItem o id: ${cartItemAdded.item.name}</p>

<table>
    <tr>
        <td>NO</td>
        <td>Name</td>
        <td>Quantity</td>
        <td>Price</td>
    </tr>

    <c:forEach var="item" items="${cartItems}" varStatus="number">
        <tr>
            <td>${number.count}</td>
            <td>${item.item.name}</td>
            <td>${item.quantity}</td>
            <td>${item.price}</td>
        </tr>
    </c:forEach>
    <tr>
        <td>TOTAL:</td>
        <td>${cartValue}</td>
    </tr>
</table>
</body>
</html>