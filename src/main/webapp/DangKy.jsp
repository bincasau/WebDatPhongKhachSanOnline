<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký tài khoản</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<style>
    body {
        background: linear-gradient(135deg, #ff9a9e, #fad0c4);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .register-container {
        background: white;
        padding: 30px;
        border-radius: 15px;
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
        max-width: 450px;
        width: 100%;
    }
    .form-control:focus {
        border-color: #ff758c;
        box-shadow: 0 0 5px rgba(255, 117, 140, 0.5);
    }
    .btn-register {
        background: #ff758c;
        border: none;
        transition: 0.3s;
    }
    .btn-register:hover {
        background: #ff5770;
    }
    .form-label {
        font-weight: bold;
        color: #4a4a4a;
    }
</style>
</head>
<body>

    <div class="register-container">
        <h3 class="text-center text-danger fw-bold">Đăng ký tài khoản</h3>
        <form action="DangKyServlet" method="POST">
            <div class="mb-3">
                <label class="form-label">Tài khoản</label>
                <input type="text" class="form-control" name="username" placeholder="Nhập tài khoản" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Mật khẩu</label>
                <input type="password" class="form-control" name="password" placeholder="Nhập mật khẩu" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Tên khách hàng</label>
                <input type="text" class="form-control" name="fullname" placeholder="Nhập họ và tên" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Số điện thoại</label>
                <input type="text" class="form-control" name="phone" placeholder="Nhập số điện thoại" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Ngày sinh</label>
                <input type="date" class="form-control" name="birthdate" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Giới tính</label>
                <select class="form-select" name="gender">
                    <option value="true">Nam</option>
                    <option value="false">Nữ</option>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">Số CCCD</label>
                <input type="text" class="form-control" name="cccd" placeholder="Nhập số CCCD" required>
            </div>
            <button type="submit" class="btn btn-register w-100 text-white fw-bold">Đăng ký</button>
        </form>
        <div class="text-center mt-3">
            <a href="DangNhap.jsp" class="text-decoration-none text-secondary">Đã có tài khoản? Đăng nhập ngay</a>
        </div>
    </div>

</body>
</html>