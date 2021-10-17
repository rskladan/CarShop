<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>OrderDetails</title>
</head>
<body>

<h2>Order details</h2>

<table>
    <tr>
        <td>NO</td>
        <td>Item name</td>
        <td>Quantity</td>
        <td>Price</td>
    </tr>

    <c:forEach items="${cartItemList}" var="cartItem" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td>${cartItem.item.name}</td>
            <td>${cartItem.quantity}</td>
            <td>${cartItem.totalPrice}</td>
        </tr>
    </c:forEach>

    <tr>
        <td>Total price: </td>
        <td>${orderDetails.cart.totalValue} PLN</td>
    </tr>

</table>

<a href="/orderHistory">Back to order history</a>

</body>
</html>