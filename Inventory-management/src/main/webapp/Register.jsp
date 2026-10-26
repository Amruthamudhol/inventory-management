<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory Management - Register</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <style>
        body {
            background: #f1f5f9;
            font-family: Arial, sans-serif;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .navbar {
            background: #102a43;
        }

        .navbar-brand {
            font-size: 23px;
            font-weight: bold;
        }

        .register-section {
            flex: 1;
            padding: 45px 15px;
        }

        .register-card {
            max-width: 650px;
            margin: auto;
            background: #fff;
            border-radius: 16px;
            overflow: hidden;
            box-shadow: 0 8px 30px rgba(15, 40, 70, 0.10);
        }

        .register-header {
            background: #1769e0;
            color: white;
            padding: 28px 25px;
            text-align: center;
        }

        .register-header h2 {
            font-weight: bold;
            margin-bottom: 8px;
        }

        .register-body {
            padding: 30px;
        }

        .form-label {
            font-weight: 600;
            color: #334155;
        }

        .form-control,
        .form-select {
            padding: 11px 12px;
            border-radius: 7px;
        }

        .form-control:focus,
        .form-select:focus {
            border-color: #1769e0;
            box-shadow: 0 0 0 0.2rem rgba(23, 105, 224, 0.12);
        }

        .btn-register {
            background: #1769e0;
            color: white;
            border: none;
            padding: 12px;
            font-weight: bold;
            border-radius: 7px;
        }

        .btn-register:hover {
            background: #1053b8;
            color: white;
        }

        footer {
            background: #102a43;
            color: #dbe7f3;
            text-align: center;
            padding: 18px;
            margin-top: auto;
        }

        footer p {
            margin: 4px;
        }
    </style>
</head>

<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
            📦 InventoryPro
        </a>

        <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarMenu"
                aria-controls="navbarMenu"
                aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarMenu">
            <ul class="navbar-nav ms-auto align-items-lg-center">
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/index.jsp">Home</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link active"
                       href="${pageContext.request.contextPath}/Register.jsp">Register</a>
                </li>

                <li class="nav-item">
                    <a class="btn btn-outline-light ms-lg-3 mt-2 mt-lg-0"
                       href="${pageContext.request.contextPath}/login.jsp">Login</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Registration Form -->
<main class="register-section">
    <div class="register-card">

        <div class="register-header">
            <h2>Create Your Account</h2>
            <p class="mb-0">Register to access the Inventory Management System</p>
        </div>

        <div class="register-body">

            <c:if test="${not empty successMessage}">
                <div class="alert alert-success alert-dismissible fade show"
                     role="alert">
                    <c:out value="${successMessage}"/>
                    <button type="button" class="btn-close"
                            data-bs-dismiss="alert"
                            aria-label="Close"></button>
                </div>
            </c:if>

            <c:if test="${not empty validationErrors}">
                <div class="alert alert-danger" role="alert">
                    <strong>Please correct the following errors:</strong>
                    <ul class="mb-0 mt-2">
                        <c:forEach items="${validationErrors}" var="error">
                            <li><c:out value="${error.defaultMessage}"/></li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/register/saveRegisterForm"
                  method="post" onsubmit="return validateForm()">

                <div class="mb-3">
                    <label for="name" class="form-label">Full Name</label>
                    <input type="text" class="form-control" id="name"
                           name="name" value="${RegisterDto.name}"
                           placeholder="Enter your full name">
                    <div id="nameError" class="text-danger small mt-1"></div>
                </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email Address</label>

                <input type="email"
                       class="form-control"
                       id="email"
                       name="email"
                       placeholder="example@gmail.com"
                       onblur="checkEmailExit()">

                <div id="EmailError" class="text-danger small mt-1"></div>
            </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password"
                           name="password" placeholder="Minimum 8 characters">
                    <div id="passwordError" class="text-danger small mt-1"></div>
                </div>

                <div class="mb-3">
                    <label for="phoneNumber" class="form-label">Phone Number</label>
                    <input type="text" class="form-control" id="phoneNumber"
                           name="phoneNumber" value="${RegisterDto.phoneNumber}"
                           placeholder="Enter 10-digit phone number">
                    <div id="phoneNumberError" class="text-danger small mt-1"></div>
                </div>

                <div class="mb-3">
                    <label for="address" class="form-label">Address</label>
                    <textarea class="form-control" id="address" name="address"
                              rows="3" placeholder="Enter your full address">${RegisterDto.address}</textarea>
                    <div id="addressError" class="text-danger small mt-1"></div>
                </div>

                <div class="mb-4">
                    <label for="role" class="form-label">Account Role</label>
                    <select class="form-select" id="role" name="role">
                        <option value="">Select your role</option>
                        <option value="Admin"
                            ${RegisterDto.role == 'Admin' ? 'selected' : ''}>Admin</option>
                        <option value="User"
                            ${RegisterDto.role == 'User' ? 'selected' : ''}>User</option>
                    </select>
                    <div id="roleError" class="text-danger small mt-1"></div>
                </div>

                <button type="submit" class="btn btn-register w-100">
                    Create Account
                </button>

                <p class="text-center mt-3 mb-0">
                    Already have an account?
                    <a href="${pageContext.request.contextPath}/login.jsp"
                       class="text-primary text-decoration-none fw-semibold">
                        Login here
                    </a>
                </p>

            </form>
        </div>
    </div>
</main>

<!-- Footer -->
<footer>
    <p><strong>InventoryPro</strong> | Inventory Management System</p>
    <p>&copy; 2026 Inventory Management System. All rights reserved.</p>
</footer>

<!-- Bootstrap JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>


<script>
    function validateForm() {
        let name = document.getElementById("name").value.trim();
        let email = document.getElementById("email").value.trim();
        let password = document.getElementById("password").value;
        let phoneNumber = document.getElementById("phoneNumber").value.trim();
        let address = document.getElementById("address").value.trim();
        let role = document.getElementById("role").value;

        let isValid = true;

        document.getElementById("nameError").innerText = "";
        document.getElementById("EmailError").innerText = "";
        document.getElementById("passwordError").innerText = "";
        document.getElementById("phoneNumberError").innerText = "";
        document.getElementById("addressError").innerText = "";
        document.getElementById("roleError").innerText = "";

        if (name === "") {
            document.getElementById("nameError").innerText =
                "Name cannot be empty";
            isValid = false;
        } else if (name.length < 3 || name.length > 50) {
            document.getElementById("nameError").innerText =
                "Name must contain 3 to 50 characters";
            isValid = false;
        }

        if (email === "") {
            document.getElementById("EmailError").innerText =
                "Email cannot be empty";
            isValid = false;
        } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
            document.getElementById("EmailError").innerText =
                "Enter a valid email address";
            isValid = false;
        }

        if (password === "") {
            document.getElementById("passwordError").innerText =
                "Password cannot be empty";
            isValid = false;
        } else if (password.length < 8) {
            document.getElementById("passwordError").innerText =
                "Password must contain at least 8 characters";
            isValid = false;
        }

        if (!/^[0-9]{10}$/.test(phoneNumber)) {
            document.getElementById("phoneNumberError").innerText =
                "Enter exactly 10 digits";
            isValid = false;
        }

        if (address.length < 5) {
            document.getElementById("addressError").innerText =
                "Address must contain at least 5 characters";
            isValid = false;
        }

        if (role === "") {
            document.getElementById("roleError").innerText =
                "Please select a role";
            isValid = false;
        }

        return isValid;
    }


function checkEmailExit() {
    const emailId = document.getElementById("email").value.trim();

    console.log("Email entered:", emailId);

    const url =
        "${pageContext.request.contextPath}/api/inventory/checkEmailExit/"
        + encodeURIComponent(emailId);

    console.log("Request URL:", url);

    const request = new XMLHttpRequest();

    request.open("GET", url, true);

    request.onload = function () {
        console.log("HTTP Status:", request.status);
        console.log("Server Response:", request.responseText);

        if (request.status === 200) {
            document.getElementById("EmailError").innerText =
                request.responseText;
        }
    };

    request.onerror = function () {
        console.log("Request failed");
    };

    request.send();
}
 </script>





</body>
</html>
