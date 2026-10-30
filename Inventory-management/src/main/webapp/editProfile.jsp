<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Edit Profile</title>
</head>
<body>

<h2>Edit Profile</h2>

<p style="color:red;">${msg}</p>

<form action="${pageContext.request.contextPath}/register/update" method="post"
      enctype="multipart/form-data">

    <input type="hidden" name="id" value="${registerDto.id}"></br>

    <input type="text" name="name"
           value="${registerDto.name}" required>

    <input type="email" name="email"
           value="${registerDto.email}" readonly>

    <input type="text" name="phoneNumber"
           value="${registerDto.phoneNumber}" required>

    <input type="text" name="address"
           value="${registerDto.address}" required>

    <input type="file" name="file" accept="image/*">

    <button type="submit">Update Profile</button>



</form>

</body>
</html>