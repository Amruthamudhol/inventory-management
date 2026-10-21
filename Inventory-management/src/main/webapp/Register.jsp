<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Inventory Management - Register</title>
</head>
<body>

<h2>Inventory Management System</h2>
<h3>Register Form</h3>

<p style="color: green;">${successMessage}</p>

<c:if test="${not empty validationErrors}">
    <c:forEach items="${validationErrors}" var="error">
        <p style="color: red;">${error.defaultMessage}</p>
    </c:forEach>
</c:if>

<form action="${pageContext.request.contextPath}/register/saveRegisterForm"
      method="post" onsubmit="return validateForm()">

    Name:
    <input type="text" id="name" name="name" value="${RegisterDto.name}">
    <span id="nameError" style="color:red;"></span>
    <br><br>

    Email:
    <input type="text" id="email" name="email" value="${RegisterDto.email}">
    <span id="emailError" style="color:red;"></span>
    <br><br>

    Password:
    <input type="password" id="password" name="password">
    <span id="passwordError" style="color:red;"></span>
    <br><br>

    Phone Number:
    <input type="text" id="phoneNumber" name="phoneNumber"value="${RegisterDto.phoneNumber}">
    <span id="phoneNumberError" style="color:red;"></span>
    <br><br>

    Address:
    <input type="text" id="address" name="address" value="${RegisterDto.address}">
    <span id="addressError" style="color:red;"></span>
    <br><br>

    Role:
    <select id="role" name="role">
        <option value="">Select Role</option>
        <option value="Admin">Admin</option>
        <option value="User">User</option>
    </select>
    <span id="roleError" style="color:red;"></span>
    <br><br>

    <button type="submit">Register</button>

</form>

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
        document.getElementById("emailError").innerText = "";
        document.getElementById("passwordError").innerText = "";
        document.getElementById("phoneNumberError").innerText = "";
        document.getElementById("addressError").innerText = "";
        document.getElementById("roleError").innerText = "";


        if (name === "") {
           document.getElementById("nameError").innerText = "Name cannot be empty";
            isValid = false;
        } else if (name.length < 3 || name.length > 50) {
            document.getElementById("nameError").innerText = "Name must contain 3 to 50 characters";
            isValid = false;
        }

        if (email === "") {
            document.getElementById("emailError").innerText ="Email cannot be empty";
            isValid = false;
        } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
            document.getElementById("emailError").innerText =  "Enter a valid email address";
            isValid = false;
        }


        if (password === "") {
            document.getElementById("passwordError").innerText = "Password cannot be empty";
            isValid = false;
        } else if (password.length < 8) {
            document.getElementById("passwordError").innerText = "Password must contain at least 8 characters";
            isValid = false;
        }

        if (phoneNumber === "") {
            document.getElementById("phoneNumberError").innerText =  "Phone number cannot be empty";
            isValid = false;
        } else if (!/^[0-9]{10}$/.test(phoneNumber)) {
            document.getElementById("phoneNumberError").innerText = "Enter exactly 10 digits";
            isValid = false;
        }

        if (address === "") {
            document.getElementById("addressError").innerText ="Address cannot be empty";
            isValid = false;
        } else if (address.length < 5) {
            document.getElementById("addressError").innerText ="Address must contain at least 5 characters";
            isValid = false;
        }

        if (role === "") {
            document.getElementById("roleError").innerText =  "Please select a role";
            isValid = false;
        }

        return isValid;
    }
</script>

</body>
</html>
