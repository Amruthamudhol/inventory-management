<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page isELIgnored="false" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>User Login</title>
</head>
<body>

<h2>User Login</h2>
<form action="${pageContext.request.contextPath}/register/emailLogin"
      method="post">

    <label for="email">Email:</label>
    <input type="email"
           id="email"
           name="email"
           placeholder="Enter your email"
           required>

    <br><br>

    <button type="submit">Send OTP</button>
</form>

<c:if test="${not empty successMessage}">
    <div style="color: green; margin: 10px 0;">
        ${successMessage}
    </div>
</c:if>

</body>
</html>