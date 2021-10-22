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

<div class="m-4 p-4 width-medium">
    <div class="dashboard-header m-4">

        <div class="dashboard-alerts">
            <div class="alert-item alert-info">
                <i class="fas icon-circle fa-info-circle"></i>
                <span class="font-weight-bold">Orders counter: ${orderList.size()}</span>
            </div>
            <div class="alert-item alert-light">
                <i class="far icon-calendar fa-calendar-alt"></i>
                <span class="font-weight-bold">Last order: ${lastOrderDate}</span>
            </div>
            <div class="alert-item alert-cart">
                <i class="fas fa-shopping-cart"></i>
                <span class="font-weight-bold">Items in cart: ${cartItemsAmount}</span>
            </div>
        </div>
    </div>

</div>



</div>
</section>

<section>
    <div class="m-4 p-3 width-medium">
        <div class="dashboard-content border-dashed p-3 m-4 view-height">
            <div class="row border-bottom border-3 p-1 m-1">
                <div class="col noPadding"><h3 class="color-header text-uppercase">Car Parts</h3></div>
            </div>
            <table class="table border-bottom schedules-content">
                <thead>
                <tr class="d-flex text-color-darker">
                    <th scope="col" class="col-1">ID</th>
                    <th scope="col" class="col-1">Name</th>
                    <th scope="col" class="col-4">Description</th>
                    <th scope="col" class="col-1">Item product code</th>
                    <th scope="col" class="col-1">Item manufacturer</th>
                    <th scope="col" class="col-2 center">Price</th>
                    <th scope="col" class="col-2"> </th>
                </tr>
                </thead>
                <tbody class="text-color-lighter">

                <c:forEach var="item" items="${itemsList}" varStatus="status">
                    <tr class="d-flex">
                        <th scope="row" class="col-1">${status.count}</th>
                        <td class="col-1">${item.name}</td>
                        <td class="col-4">${item.description}</td>
                        <td class="col-1">${item.productCode}</td>
                        <td class="col-1">${item.manufacturer.name}</td>
                        <td class="col-2 center">${item.price} PLN</td>
                        <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                            <a href="/addToCart/${item.id}" class="btn btn-info rounded-0 text-light m-1" onclick="buyFunction()">Add to cart</a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
</section>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
<script>
    function buyFunction(){
        alert("Added to shopping cart")
    }
</script>
</body>
</html>