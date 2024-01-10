<%@tag description="base page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="pageTitle"%>

<!DOCTYPE html>
<html>
<head>
    <title>${pageTitle}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body style="display: flex; flex-direction: column; min-height: 100vh;margin: 0;width: 100%;padding: 0;">
    <t:navbar/>
    <main class="flex-grow-1 mt-5" style="width: 100%;margin: 0;">
        <jsp:doBody />
    </main>
    <footer class="footer mt-auto py-4" style="background: linear-gradient(to right, #192a56, #0097e6); color: white;">
        <div class="container text-center">
            <p style="font-size: 16px;margin-bottom: 3px;">Created by:</p>
            <div class="row justify-content-center">
                <div class="col-auto mb-1">
                    <div class="text-center">
                        <p style="font-size: 14px; margin-top: 3px;"><strong>Cepoiu Radu</strong></p>
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