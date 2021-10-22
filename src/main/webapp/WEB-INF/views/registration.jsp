<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<jsp:include page="/WEB-INF/views/header.jsp"/>

<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-50">

                <form:form class="padding-small text-center" action="/registration" method="post" modelAttribute="user">
                    <h1 class="text-color-darker">Register</h1>
                    <div class="form-group">
                        <form:input type="text" path="name" id="name" placeholder="Name"/>
                        <form:errors path="name" style="font-size: 15px; color: #FF1C19;"/>
                    </div>
                    <div class="form-group">
                        <form:input type="text" path="surname" id="surname" name="surname" placeholder="Surname"/>
                        <form:errors path="surname" style="font-size: 15px; color: #FF1C19;"/>
                    </div>
                    <div class="form-group">
                        <form:input type="text" path="username" id="username" name="username" placeholder="Username"/>
                        <form:errors path="username" style="font-size: 15px; color: #FF1C19;"/>
                    </div>
                    <div class="form-group">
                        <form:input type="text" path="email" id="email" name="email" placeholder="Email"/>
                        <form:errors path="email" style="font-size: 15px; color: #FF1C19;"/>
                    </div>
                    <div class="form-group">
                        <form:input type="password" path="password" id="password" name="password" placeholder="Password"/>
                        <form:errors path="password" style="font-size: 15px; color: #FF1C19;"/>
                    </div>
                    <div class="form-group">
                        <form:select path="userRole" items="${userRoles}" id="role"/>
                    </div>


                    <button class="btn btn-color rounded-0" type="submit">Register</button>
                </form:form>
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