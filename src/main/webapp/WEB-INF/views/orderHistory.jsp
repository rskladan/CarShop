<%--<%@ page isELIgnored="false" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Order history</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<c:if test="${orderList.size()<1}">--%>
<%--    <h3>Brak zamówień</h3>--%>
<%--</c:if>--%>

<%--<c:if test="${orderList.size()>0}">--%>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <td>Order id</td>--%>
<%--            <td>Order value</td>--%>
<%--            <td>Order date</td>--%>
<%--            <td> </td>--%>
<%--        </tr>--%>
<%--        <c:forEach var="order" items="${orderList}">--%>
<%--            <tr>--%>
<%--                <td>${order.id}</td>--%>
<%--                <td>${order.cart.totalValue}</td>--%>
<%--                <td>${order.dateTime.toLocalDate()}</td>--%>
<%--                <td><a href="/orderDetails/${order.id}">Order details</a></td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--    </table>--%>
<%--</c:if>--%>

<%--<a href="/welcome">Back to main page</a>--%>

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

    <div class="m-4 p-4 width-medium">

        <div class="dashboard-content border-dashed p-3 m-4 view-height">
            <div class="row border-bottom border-3 p-1 m-1">
                <div class="col noPadding"><h3 class="color-header text-uppercase">Order history</h3></div>
                <div class="col noPadding d-flex justify-content-end mb-2">
                    <a href="/welcome" class="btn btn-info rounded-0 text-light m-1">Back to main page</a>
                </div>
            </div>
            <c:if test="${orderList.size()<1}">
                <h3>Brak zamówień</h3>
            </c:if>

            <c:if test="${orderList.size()>0}">
                <table class="table border-bottom schedules-content">
                    <thead>
                    <tr class="d-flex text-color-darker">
                        <th scope="col center">NO</th>
                        <th scope="col center" class="col-1" >Order id</th>
                        <th scope="col center" class="col-1">Order value</th>
                        <th scope="col center" class="col-1">Order date</th>
                        <th scope="col center" class="col-1"> </th>
                    </tr>
                    </thead>
                    <tbody class="text-color-lighter">

                    <c:forEach var="order" items="${orderList}" varStatus="status">
                        <tr class="d-flex">
                            <td>${status.count}</td>
                            <td class="col-1 center" >${order.id}</td>
                            <td class="col-1 center">${order.cart.totalValue} PLN</td>
                            <td class="col-1 center">${order.dateTime}</td>
                            <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                                <a href="/orderDetails/${order.id}" class="btn btn-info rounded-0 text-light m-1">Order details</a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </c:if>


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