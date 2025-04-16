<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt Phòng Thành Công</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Optional Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        body {
            background-color: #f1f4f9;
            font-family: Arial, sans-serif;
        }

        .success-container {
            margin-top: 80px;
            padding: 40px;
            background-color: #fff;
            border-radius: 20px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.05);
            max-width: 600px;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
        }

        .checkmark-icon {
            font-size: 70px;
            color: #28a745;
            margin-bottom: 20px;
        }

        .btn-home {
            margin-top: 25px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="success-container">
        <div class="checkmark-icon">✔</div>
        <h2 class="mb-3">Đặt phòng thành công!</h2>
        <p class="lead">Cảm ơn bạn đã tin tưởng và sử dụng dịch vụ của chúng tôi.</p>
        
        <a href="TrangChu.jsp" class="btn btn-primary btn-home">
            Quay về Trang Chủ
        </a>
    </div>
</div>

</body>
</html>
