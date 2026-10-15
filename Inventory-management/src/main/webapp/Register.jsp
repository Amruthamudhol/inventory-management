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
         method="post">

       Name: <input type="text" <br><br>
       Email: <input type="email" n<br><br>
       Password: <input type="password" <br><br>
       Phone Number: <input type="text" <br><br>
       Address: <input type="text" <br><br>

       Role:
       <select name="role" required>
           <option value="">Select Role</option>
           <option value="ADMIN">Admin</option>
           <option value="STAFF">Staff</option>
       </select><br><br>

       <input type="submit" value="Register">

   </form>
</body>
</html>