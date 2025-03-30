<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa thông tin cá nhân</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<style>
    body {
        background: linear-gradient(135deg, #ff9a9e, #fad0c4);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .edit-container {
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
    .btn-save {
        background: #ff758c;
        border: none;
        transition: 0.3s;
    }
    .btn-save:hover {
        background: #ff5770;
    }
    .form-label {
        font-weight: bold;
        color: #4a4a4a;
    }
</style>
</head>
<body>
    <%-- Lấy thông tin khách hàng từ session --%>
    <% 
        KhachHang kh = (KhachHang) session.getAttribute("khachHang");
        if (kh == null) {
            response.sendRedirect("DangNhap.jsp");
            return;
        }
    %>
    
    <div class="edit-container">
        <h3 class="text-center text-danger fw-bold">Sửa thông tin cá nhân</h3>
        
        <form action="SuaThongTinServlet" method="POST">
            <div class="mb-3">
                <label class="form-label">Tên khách hàng</label>
                <input type="text" class="form-control" name="tenKhachHang" value="<%= kh.getTenKhachHang() %>" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Số điện thoại</label>
                <input type="text" class="form-control" name="soDienThoai" value="<%= kh.getSoDienThoai() %>" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Ngày sinh</label>
                <input type="date" class="form-control" name="ngaySinh" value="<%= kh.getNgaySinh() %>" required>
            </div>
            <div class="mb-3">
                <label class="form-label">Giới tính</label>
                <select class="form-select" name="gioiTinh">
                    <option value="true" <%= kh.isGioiTinh() ? "selected" : "" %>>Nam</option>
                    <option value="false" <%= !kh.isGioiTinh() ? "selected" : "" %>>Nữ</option>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">Số CCCD</label>
                <input type="text" class="form-control" name="soCCCD" value="<%= kh.getSoCCCD() %>" required>
            </div>
            <button type="submit" class="btn btn-save w-100 text-white fw-bold">Lưu thay đổi</button>
        </form>
        <div class="text-center mt-3">
            <a href="TrangChu.jsp" class="text-decoration-none text-secondary">Quay lại trang chủ</a>
        </div>
    </div>
</body>
</html>
