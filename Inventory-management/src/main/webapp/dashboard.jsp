
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inventory Management - Dashboard</title>

    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
        }

        .navbar {
            background-color: #243447;
            color: white;
            padding: 18px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .navbar h2 {
            margin: 0;
        }

        .navbar a {
            color: white;
            text-decoration: none;
            background-color: #e74c3c;
            padding: 10px 16px;
            border-radius: 5px;
        }

        .container {
            width: 80%;
            max-width: 700px;
            margin: 40px auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 3px 12px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #243447;
            text-align: center;
        }

        .details {
            margin-top: 25px;
        }

        .detail-row {
            display: flex;
            border-bottom: 1px solid #ddd;
            padding: 14px 8px;
        }

        .label {
            width: 40%;
            font-weight: bold;
            color: #333;
        }

        .value {
            width: 60%;
            color: #555;
            overflow-wrap: anywhere;
        }

        .welcome {
            text-align: center;
            color: #198754;
        }

        .footer {
            text-align: center;
            color: #777;
            margin-top: 25px;
        }
    </style>
</head>

<body>

<div class="navbar">
    <h2>Inventory Management</h2>
    <a href="${pageContext.request.contextPath}/login.jsp">Logout</a>
</div>

<div class="container">

    <h2>User Dashboard</h2>

    <p class="welcome">
        Welcome, <c:out value="${registerDto.name}" />
    </p>

    <div class="details">

        <div class="detail-row">
            <div class="label">Name</div>
            <div class="value">
                <c:out value="${registerDto.name}" />
            </div>
        </div>

        <div class="detail-row">
            <div class="label">Email</div>
            <div class="value">
                <c:out value="${registerDto.email}" />
            </div>
        </div>

        <div class="detail-row">
            <div class="label">Phone Number</div>
            <div class="value">
                <c:out value="${registerDto.phoneNumber}" />
            </div>
        </div>

        <div class="detail-row">
            <div class="label">Address</div>
            <div class="value">
                <c:out value="${registerDto.address}" />
            </div>
        </div>

    </div>

    <div class="footer">
        Inventory Management System
    </div>

   <a href="${pageContext.request.contextPath}/register/edit?email=${registerDto.email}">
       Edit Profile
   </a>

</div>

</body>
</html>
