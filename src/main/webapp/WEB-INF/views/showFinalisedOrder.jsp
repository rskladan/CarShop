<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order</title>
</head>
<body>

<h2>Final order summary:</h2>
<h3>Finalised order id: ${order.id}</h3>
<h3>Finalised order cart value: ${order.cart.totalValue}</h3>

<a href="/welcome">Back to main page</a>
</body>
</html>