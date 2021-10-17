<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order history</title>
</head>
<body>

<c:if test="${orderList.size()<1}">
    <h3>Brak zamówień</h3>
</c:if>

<c:if test="${orderList.size()>0}">
    <table>
        <tr>
            <td>Order id</td>
            <td>Order value</td>
            <td>Order date</td>
            <td> </td>
        </tr>
        <c:forEach var="order" items="${orderList}">
            <tr>
                <td>${order.id}</td>
                <td>${order.cart.totalValue}</td>
                <td>${order.dateTime.toLocalDate()}</td>
                <td><a href="/orderDetails/${order.id}">Order details</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<a href="/welcome">Back to main page</a>

</body>
</html>