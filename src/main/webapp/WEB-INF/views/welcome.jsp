<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>

<h2>Welcome ${user.name}</h2>

<h2>List of items</h2>

<c:forEach var="item" items="${allItems}">
    Item id: ${item.id}
    Item name: ${item.name}
    Item description: ${item.description}
    Item price: ${item.price}
    Item productCode: ${item.productCode}
    Item manufacturer: ${item.manufacturer.name}
    <br>
</c:forEach>
</body>
</html>