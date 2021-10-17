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

<table>
    <tr>
        <td>NO</td>
        <td>Name</td>
        <td> </td>
        <td>Quantity</td>
        <td> </td>
        <td>Price</td>
    </tr>

    <c:forEach var="item" items="${cartItems}" varStatus="number">
        <tr>
            <td>${number.count}</td>
            <td>${item.item.name}</td>
            <c:if test = "${item.quantity>0}">
            <td>
                <a href="decreaseAmount/${item.id}">-</a>
            </td>
            </c:if>
            <td>${item.quantity}</td>
            <td><a href="increaseAmount/${item.id}">+</a></td>
            <td>${item.totalPrice}</td>
            <td><a href="removeItemFromCart/${item.id}">Usuń</a></td>
        </tr>
    </c:forEach>

    <tr>
        <td>TOTAL:</td>
        <td>${cartValue} PLN</td>
    </tr>
    <br>

    <c:if test = "${cartItems.size()>0}">
    <form action="/finaliseOrder" method="post">
        <button type="submit">Finalise order</button>
    </form>
    </c:if>

</table>
</body>
</html>