<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>Reset Password</title>
</head>
<body>

<h2>Reset Password</h2>

<form action="${pageContext.request.contextPath}/register/resetPassword"
      method="post">

    <input type="hidden" name="token" value="${token}">

    <label>New Password:</label>
    <input type="password" name="password"required>

    <br><br>

    <label>Confirm Password:</label>
    <input type="password"name="confirmPassword"required>

    <br><br>
    <button type="submit">Reset Password</button>

</form>

<p style="color:red;">${msg}</p>

</body>
</html>