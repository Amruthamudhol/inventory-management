<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>

<h2>Forgot Password</h2>

<form id="forgotPasswordForm">
    <label>Email:</label>
    <input type="email" id="email" name="email" required>
    <button type="submit">Send Reset Link</button>
</form>

<p id="message"></p>

<script>
    $("#forgotPasswordForm").submit(function (event) {
        event.preventDefault();

        $.ajax({
            url: "<%= request.getContextPath() %>/register/sendResetLink",
            type: "POST",
            data: {
                email: $("#email").val()
            },
            success: function (response) {
                $("#message").text(response);
            },
            error: function (xhr) {
                $("#message").text("Request failed: " + xhr.status);
                console.log("Request URL:", this.url);
                console.log("Response:", xhr.responseText);
            }
        });
    });
</script>

</body>
</html>