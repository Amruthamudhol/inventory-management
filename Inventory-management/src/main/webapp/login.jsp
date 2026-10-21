
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Inventory Management - Login</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f4f7;
        }

        .login-container {
            width: 350px;
            margin: 80px auto;
            padding: 25px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 0 10px #cccccc;
        }

        h2, h3 {
            text-align: center;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            margin-bottom: 5px;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .error {
            color: red;
            font-size: 13px;
        }

        .register-link {
            text-align: center;
            margin-top: 15px;
        }
    </style>
</head>

<body>

<div class="login-container">

    <h2>Inventory Management System</h2>
    <h3>Login Page</h3>

    <p style="color: green;">${successMessage}</p>
    <p style="color: red;">${errorMessage}</p>

    <c:if test="${not empty validationErrors}">
        <c:forEach items="${validationErrors}" var="error">
            <p class="error">${error.defaultMessage}</p>
        </c:forEach>
    </c:if>

    <form action="${pageContext.request.contextPath}/login"
          method="post"
          onsubmit="return validateLogin()">

        Email:
        <input type="text"
               id="email"
               name="email"
               value="${loginDto.email}"
               placeholder="Enter your email">

        <span id="emailError" class="error"></span>

        <br><br>

        Password:
        <input type="password"
               id="password"
               name="password"
               placeholder="Enter your password">

        <span id="passwordError" class="error"></span>

        <br><br>

        <button type="submit">Login</button>

    </form>

    <div class="register-link">
        Don't have an account?
        <a href="${pageContext.request.contextPath}/register">Register here</a>
    </div>

</div>

<script>
    function validateLogin() {

        let email = document.getElementById("email").value.trim();
        let password = document.getElementById("password").value;

        let isValid = true;

        document.getElementById("emailError").innerText = "";
        document.getElementById("passwordError").innerText = "";

        // Email validation
        if (email === "") {
            document.getElementById("emailError").innerText =
                "Email cannot be empty";
            isValid = false;
        } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
            document.getElementById("emailError").innerText =
                "Enter a valid email address";
            isValid = false;
        }

        // Password validation
        if (password === "") {
            document.getElementById("passwordError").innerText =
                "Password cannot be empty";
            isValid = false;
        } else if (password.length < 8) {
            document.getElementById("passwordError").innerText =
                "Password must contain at least 8 characters";
            isValid = false;
        }

        return isValid;
    }
</script>

</body>
</html>
