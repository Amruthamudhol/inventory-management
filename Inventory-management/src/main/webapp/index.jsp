
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory Management System</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet">

    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f5f8fc;
            color: #1e293b;
        }

        .navbar {
            background: #102a43;
            padding: 16px 0;
        }

        .navbar-brand {
            color: white !important;
            font-size: 23px;
            font-weight: bold;
        }

        .nav-link {
            color: #e2e8f0 !important;
            margin: 0 8px;
        }

        .nav-link:hover {
            color: #60a5fa !important;
        }


        .hero-content {
            padding-top: 65px;
            padding-bottom: 65px;
        }

        .hero h1 {
            font-size: clamp(36px, 5vw, 54px);
            font-weight: 800;
            color: #102a43;
            line-height: 1.15;
        }

        .hero p {
            font-size: 18px;
            line-height: 1.8;
            color: #475569;
            max-width: 530px;
            margin-top: 20px;
        }

        .btn-primary-custom {
            background: #1769e0;
            color: white;
            padding: 13px 25px;
            border-radius: 7px;
            text-decoration: none;
            display: inline-block;
            margin: 8px 8px 0 0;
            font-weight: bold;
        }

        .btn-primary-custom:hover {
            background: #0e4fae;
            color: white;
        }

        .btn-outline-custom {
            border: 2px solid #1769e0;
            color: #1769e0;
            padding: 11px 25px;
            border-radius: 7px;
            text-decoration: none;
            display: inline-block;
            margin-top: 8px;
            font-weight: bold;
        }

        .btn-outline-custom:hover {
            background: #1769e0;
            color: white;
        }

        .features {
            padding: 55px 0;
        }

        .feature-card {
            background: white;
            border-radius: 12px;
            padding: 28px 22px;
            height: 100%;
            box-shadow: 0 4px 18px rgba(15, 40, 70, 0.07);
            border: 1px solid #e5edf5;
        }

        .feature-icon {
            font-size: 34px;
            margin-bottom: 14px;
        }

        .feature-card h5 {
            color: #102a43;
            font-weight: bold;
        }

        .feature-card p {
            color: #64748b;
            line-height: 1.7;
            margin-bottom: 0;
        }

        footer {
            background: #102a43;
            color: #dbe7f3;
            text-align: center;
            padding: 22px 12px;
        }

        footer p {
            margin: 4px 0;
        }

        @media (max-width: 767px) {
            .hero {
                background-position: 60% center;
            }

            .hero-content {
                padding-top: 55px;
                padding-bottom: 55px;
            }
        }
    </style>
</head>

<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">
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
                    <a class="nav-link" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#about">About</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="Register.jsp">Register</a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-primary ms-lg-2 mt-2 mt-lg-0"
                       href="login.jsp">Login</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Header / Hero Section -->
<header class="hero" id="about">
    <div class="container">
        <div class="hero-content">
            <span class="badge text-bg-primary mb-3 p-2">
                SMART STOCK MANAGEMENT
            </span>

            <h1>
                Inventory Management<br>
                Made Simple
            </h1>

            <p>
                Manage your products, monitor stock levels and organize
                your inventory efficiently — all in one place.
                A smarter way to keep your business running smoothly.
            </p>

            <a href="Register.jsp" class="btn-primary-custom">
                Get Started →
            </a>

            <a href="login.jsp" class="btn-outline-custom">
                Login to Account
            </a>
        </div>
    </div>
</header>

<!-- Footer -->
<footer>
    <p><strong>InventoryPro — Inventory Management System</strong></p>
    <p>Manage your inventory. Simplify your business.</p>
    <p>&copy; 2026 Inventory Management System. All rights reserved.</p>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
</script>

</body>
</html>
