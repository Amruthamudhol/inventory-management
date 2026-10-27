<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Edit Profile</title>
</head>
<body>

<h2>Edit Profile</h2>

<p style="color:red;">${msg}</p>

<form action="${pageContext.request.contextPath}/register/update"
      method="post">

    <label>Name:</label>
    <input type="text" name="name"
           value="${registerDto.name}" required>
    <br><br>

    <label>Email:</label>
    <input type="email" name="email"
           value="${registerDto.email}" readonly>
    <br><br>

    <label>Phone Number:</label>
    <input type="text" name="phoneNumber"
           value="${registerDto.phoneNumber}" required>
    <br><br>

    <label>Address:</label>
    <input type="text" name="address"
           value="${registerDto.address}" required>
    <br><br>

    <button type="submit">Update Profile</button>

    <a href="${pageContext.request.contextPath}/register/edit?email=${registerDto.email}">
        Cancel
    </a>

</form>

</body>
</html>