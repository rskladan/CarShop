<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping cart</title>
    <link href='<c:url value="/WEB-INF/css/styles.css"/>' rel="stylesheet">
</head>
<body>

<h2>Store</h2>
<jsp:include page="/WEB-INF/views/header.jsp"/>
<h3>List of items</h3>

<table>

    <tr>
        <td>Item id</td>
        <td>Item name</td>
        <td>Item description</td>
        <td>Item price</td>
        <td>Item product code</td>
        <td>Item manufacturer</td>
        <td> </td>
    </tr>

    <c:forEach var="item" items="${itemsList}">
    <tr>
        <td>${item.id}</td>
        <td>${item.name}</td>
        <td>${item.description}</td>
        <td>${item.price}</td>
        <td>${item.productCode}</td>
        <td>${item.manufacturer.name}</td>
        <td><a href="/addToCart/${item.id}">Add to cart</a></td>

        

    </tr>
    </c:forEach>


</table>


<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body>
</html>