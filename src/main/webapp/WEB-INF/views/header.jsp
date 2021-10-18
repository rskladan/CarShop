<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="utf-8">
    <title>Car shop</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/dashboard/">

<%--    <!-- Bootstrap core CSS -->--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<%--    <!-- FontAwesome -->--%>
    <script src="https://kit.fontawesome.com/fcb2a487a2.js" crossorigin="anonymous"></script>
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap" rel="stylesheet">


        <div class="header-container">
            <div class="site-name">Online Shop</div>

            <div class="header-bar">
                <div>
                <c:if test = "${loggedUser != null}">
                    Hello
                    <c:if test = "${shoppingCart != null}">
                    <a href="/shoppingCartDetails">Shopping Cart</a>
                    </c:if>
                    <a href="/accountInfo"> ${loggedUser.name}</a>
                    <a href="/logout">Logout</a>
                </c:if>

                <c:if test = "${loggedUser == null}">
                    <a href="/login">Login</a>
                </c:if>
                </div>
            </div>

        </div>
</head>