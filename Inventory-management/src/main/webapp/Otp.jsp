<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>OTP Verification</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-5">

            <div class="card shadow">
                <div class="card-header text-center">
                    <h4>User Login</h4>
                </div>

                <div class="card-body">

                    <form action="${pageContext.request.contextPath}/register/validateOtp"
                          method="post">

                        <div class="mb-3">
                            <label for="email" class="form-label">
                                Enter Email
                            </label>

                            <input type="email"
                                   class="form-control"
                                   id="email"
                                   name="email"
                                   value="${email}"
                                   readonly>
                        </div>

                        <div class="mb-3">
                            <label for="otp" class="form-label">
                                Enter OTP
                            </label>

                            <input type="text"
                                   class="form-control"
                                   id="otp"
                                   name="otp"
                                   placeholder="Enter OTP"
                                   maxlength="6"
                                   pattern="[0-9]{6}"
                                   required>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">
                                Submit
                            </button>
                        </div>

                    </form>

                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>