<%--<%@ page isELIgnored="false" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Shopping Cart Details</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<h2>Welcome, ${loggedUser.name} ${loggedUser.surname}</h2>--%>
<%--<h3>Cart: ${shoppingCart.id}</h3>--%>

<%--<a href="/shoppingCart">Powrót do sklepu</a>--%>

<%--<table>--%>
<%--    <tr>--%>
<%--        <td>NO</td>--%>
<%--        <td>Name</td>--%>
<%--        <td> </td>--%>
<%--        <td>Quantity</td>--%>
<%--        <td> </td>--%>
<%--        <td>Price</td>--%>
<%--    </tr>--%>

<%--    <c:forEach var="item" items="${cartItems}" varStatus="number">--%>
<%--        <tr>--%>
<%--            <td>${number.count}</td>--%>
<%--            <td>${item.item.name}</td>--%>
<%--            <c:if test = "${item.quantity>0}">--%>
<%--            <td>--%>
<%--                <a href="decreaseAmount/${item.id}">-</a>--%>
<%--            </td>--%>
<%--            </c:if>--%>
<%--            <td>${item.quantity}</td>--%>
<%--            <td><a href="increaseAmount/${item.id}">+</a></td>--%>
<%--            <td>${item.totalPrice}</td>--%>
<%--            <td><a href="removeItemFromCart/${item.id}">Usuń</a></td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>

<%--    <tr>--%>
<%--        <td>TOTAL:</td>--%>
<%--        <td>${cartValue} PLN</td>--%>
<%--    </tr>--%>
<%--    <br>--%>

<%--    <c:if test = "${cartItems.size()>0}">--%>
<%--    <form action="/finaliseOrder" method="post">--%>
<%--        <button type="submit">Finalise order</button>--%>
<%--    </form>--%>
<%--    </c:if>--%>

<%--</table>--%>
<%--</body>--%>
<%--</html>--%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Car Parts Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Charmonman:400,700|Open+Sans:400,600,700&amp;subset=latin-ext"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css" />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
</head>

<body>
<jsp:include page="/WEB-INF/views/appMenu.jsp"/>

<div class="m-4 p-3 width-medium text-color-darker">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="mt-4 ml-4 mr-4">
            <div class="row border-bottom border-3">
                <div class="col"><h3 class="color-header text-uppercase">Shopping cart</h3></div>
                <c:if test="${cartItemsAmount>0}">
                <div class="col noPadding d-flex justify-content-end mb-2"><a href="/finaliseOrder" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Finalise order</a></div>
                </c:if>
            </div>

            <table class="table borderless">
                <tbody>
                <tr class="d-flex">
                    <th scope="row" class="col-2">Items in shopping cart: </th>
                    <td class="col-7">
                        ${cartItemsAmount}
                    </td>
                </tr>
                <tr class="d-flex">
                    <th scope="row" class="col-2">Total value: </th>
                    <td class="col-7">${cartValue}</td>
                </tr>
                </tbody>
            </table>

            <div class="row d-flex">
                <div class="col-12 border-bottom border-3"><h3 class="text-uppercase">Items</h3></div>

            </div>
            <div class="row d-flex">
                <div class="col-12 p-4">
                    <table class="table border-bottom schedules-content">
                    <thead>
                    <tr class="d-flex text-color-darker">
                        <th scope="col">NO</th>
                        <th scope="col" class="col-1">Name</th>
                        <th scope="col" class="col-3">Description</th>
                        <th scope="col" class="col-2">Item product code</th>
                        <th scope="col" class="col-1">Item manufacturer</th>
                        <th scope="col" class="col-2 center">Price</th>
                        <th scope="col" class="col-1">Amount</th>
                        <th scope="col" class="col-2"> </th>
                    </tr>
                    </thead>
                    <c:forEach var="cartItem" items="${cartItems}" varStatus="status">
                        <tr class="d-flex">
                            <th scope="row">${status.count}</th>
                            <td class="col-1">${cartItem.item.name}</td>
                            <td class="col-3">${cartItem.item.description}</td>
                            <td class="col-2">${cartItem.item.productCode}</td>
                            <td class="col-1">${cartItem.item.manufacturer.name}</td>
                            <td class="col-2 center">${cartItem.totalPrice} PLN <br>
                            <c:if test="${cartItem.quantity>1}">
                                (${cartItem.item.price} PLN)
                            </c:if>
                            </td>
                            <c:if test = "${cartItem.quantity>0}">
                            <td>
                                <a href="decreaseAmount/${cartItem.id}">-</a>
                            </td>
                            </c:if>
                            <c:if test = "${cartItem.quantity<=0}">
                                <td>
                                    -
                                </td>
                            </c:if>
                            <td>${cartItem.quantity}</td>
                            <td><a href="increaseAmount/${cartItem.id}">+</a></td>
                            <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                                <a href="removeItemFromCart/${cartItem.id}" class="btn btn-danger rounded-0 text-light m-1" onclick="return confirm('Are you sure?')">Remove</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </table>
                </div>


        </div>
    </div>
</div>
</div>
</section>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

</body>
</html>