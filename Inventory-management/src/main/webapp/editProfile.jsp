<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">

            <div class="card shadow">
                <div class="card-header bg-primary text-white">
                    <h3 class="text-center mb-0">Edit Profile</h3>
                </div>

                <div class="card-body">

                    <p class="text-danger">${msg}</p>

                    <form action="${pageContext.request.contextPath}/register/update"
                          method="post"
                          enctype="multipart/form-data">

                        <input type="hidden" name="id"
                               value="${registerDto.id}">

                        <div class="mb-3">
                            <label for="name" class="form-label">Full Name</label>
                            <input type="text" class="form-control"
                                   id="name" name="name"
                                   value="${registerDto.name}" required>
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control"
                                   id="email" name="email"
                                   value="${registerDto.email}" readonly>
                        </div>

                        <div class="mb-3">
                            <label for="phoneNumber" class="form-label">Phone Number</label>
                            <input type="text" class="form-control"
                                   id="phoneNumber" name="phoneNumber"
                                   value="${registerDto.phoneNumber}" required>
                        </div>

                        <div class="mb-3">
                            <label for="address" class="form-label">Address</label>
                            <textarea class="form-control"
                                      id="address" name="address"
                                      rows="3" required>${registerDto.address}</textarea>
                        </div>

                        <div class="mb-3">
                            <label for="file" class="form-label">Profile Picture</label>
                            <input type="file" class="form-control"
                                   id="file" name="file"
                                   accept="image/*">
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">
                                Update Profile
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