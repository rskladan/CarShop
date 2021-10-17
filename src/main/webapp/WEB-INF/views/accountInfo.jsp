<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<head>
  <title>Account Info</title>
  <link href='<c:url value="/WEB-INF/css/styles.css"/>' rel="stylesheet">
</head>
<body>

<jsp:include page="/WEB-INF/views/header.jsp"/>

<div class="page-title">Account Info</div>
<div class="account-container">

  <form:form method="post" action="/accountInfo/${loggedUser.id}" modelAttribute="loggedUser">
  <table>

    <tr>
      <td>
        User Name:
      </td>
      <td>
        <form:input path="name" value="${loggedUser.name}"/> <br>
        <form:errors path="name"/> <br>
      </td>
    </tr>

    <tr>
      <td>
        User surname:
      </td>
      <td>
        <form:input path="surname" value="${loggedUser.surname}"/> <br>
        <form:errors path="surname"/> <br>
      </td>
    </tr>

    <tr>
      <td>
        User login:
      </td>
      <td>
        <form:input path="username" value="${loggedUser.username}"/> <br>
        <form:errors path="username"/> <br>
      </td>
    </tr>

    <tr>
      <td>
        User email:
      </td>
      <td>
        <form:input path="email" value="${loggedUser.email}"/> <br>
        <form:errors path="email"/> <br>
      </td>
    </tr>

    <button type="submit">Edit user</button>
    <a href="/welcome">Back</a>

  </table>
  </form:form>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp"/>

</body>
</html>