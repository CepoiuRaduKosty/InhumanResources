<%@tag description="base page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="pageTitle"%>

<!DOCTYPE html>
<html>
<head>
    <title>${pageTitle}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <style>
        .my-card {
            background-color: white;
            color: #0170ab;
            transition: background-color 0.3s, color 0.3s;
        }

        .my-card:hover {
            background-color: #0170ab;
            color: white;
            transition: background-color 0.3s, color 0.3s;
        }

        .my-card:hover .card-body {
            animation: fadeIn 0.3s ease-in-out;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }

        .navbar {
            transition: background-color 0.3s ease;
        }

        .navbar-toggler {
            border-color: white;
        }

        .navbar:hover {
            background: linear-gradient(to right, #192a56, #0097e6);
        }

        .navbar-nav .nav-link:hover {
            color: #7ec8e3  !important;
        }

        .navbar-nav .nav-link {
            position: relative;
            transition: color 0.3s ease;
        }

        .navbar-nav .nav-link::before {
            content: '';
            position: absolute;
            bottom: -2px; /* Adjust as needed */
            left: 0;
            width: 0;
            height: 2px; /* Bar height */
            background-color: #7ec8e3;
            transition: width 0.3s ease;
        }

        .navbar-nav .nav-link:hover::before {
            width: 100%;
        }

        .navbar-brand {
            background: linear-gradient(to right, #7accee, #ffffff);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            padding: 5px 10px;
            border: 2px solid white;
            border-radius: 5px;
            transition: background 0.7s ease, -webkit-text-fill-color 0.7s ease;
        }

        /* Hover effect */
        .navbar-brand:hover {
            background: linear-gradient(to right, #192a56, #0097e6);
            -webkit-text-fill-color: white;
            transition: background 0.7s ease, -webkit-text-fill-color 0.7s ease;
            animation: gradientInvert 0.5s ease;
        }

        /* Optional: Add animation */
        @keyframes gradientInvert {
            from {
                -webkit-text-fill-color: transparent;
            }
            to {
                -webkit-text-fill-color: white;
            }
        }

        .login-btn-container .login-btn .nav-link {
            transition: color 0.3s ease;
        }

        .login-btn-container .login-btn .nav-link:hover {
            color: #192a56 !important;
        }

        .login-btn-container .login-btn .nav-link[href*="Login"] {
            background-color: white;
            color: #000000 !important; /* Set the default text color to black */
            padding: 8px 15px;
            border-radius: 5px;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .login-btn-container .login-btn .nav-link[href*="Login"]:hover {
            background-color: #000000;
            color: white !important;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .login-title {
            background: linear-gradient(to right, #192a56, #0097e6);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            font-weight: bolder;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
            }
            to {
                opacity: 1;
            }
        }

        .fade-in {
            animation: fadeIn 0.5s ease-out;
        }
    </style>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;margin: 0;width: 100%;padding: 0;">
    <t:navbar/>
    <main class="flex-grow-1 mt-5" style="width: 100%;margin: 0;padding-top: 56px;">
        <jsp:doBody />
    </main>
    <footer class="footer mt-auto py-4" style="background: linear-gradient(to right, #192a56, #0097e6); color: white;">
        <div class="container text-center">
            <div style="font-size: 16px;margin-bottom: 3px;">Created by:</div>
            <div class="row justify-content-center mt-2">
                <div class="col-auto mb-1">
                    <div class="text-center">
                        <p style="font-size: 14px; margin-bottom: 3px;"><strong>Cepoiu Radu</strong></p>
                    </div>
                </div>
                <div class="col-auto mb-1">
                    <div class="text-center">
                        <p style="font-size: 14px; margin-bottom: 3px;"><strong>Darius Onofrei</strong></p>
                    </div>
                </div>
                <div class="col-auto mb-1">
                    <div class="text-center">
                        <p style="font-size: 14px; margin-bottom: 3px;"><strong>Radu Cruceat</strong></p>
                    </div>
                </div>
                <div class="col-auto mb-1">
                    <div class="text-center">
                        <p style="font-size: 14px; margin-bottom: 3px;"><strong>Edi Tira</strong></p>
                    </div>
                </div>
                <div class="col-auto mb-1">
                    <div class="text-center">
                        <p style="font-size: 14px; margin-bottom: 3px;"><strong>Marian Alexandru</strong></p>
                    </div>
                </div>
            </div>
        </div>
    </footer>
</body>
</html>