<%@page import="Dao.PhongDao"%>
<%@page import="Dao.QuanLyDao"%>
<%@page import="model.QuanLy"%>
<%@page import="model.Phong"%>
<%@page import="java.util.List"%>
<%@page import="Dao.KhachSanDao"%>
<%@page import="model.KhachSan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết khách sạn</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body { background-color: #f8f9fa; padding-bottom: 80px; } /* Đệm để không bị che footer */
        .card { border-radius: 8px; overflow: hidden; transition: transform 0.2s; }
        .card:hover { transform: scale(1.03); }
        .room-img { height: 200px; object-fit: cover; }

        /* Footer cố định */
        .custom-footer {
            position: fixed;
            bottom: 0;
            left: 0;
            width: 100%;
            background-color: #004085;
            color: white;
            text-align: center;
            padding: 10px 0;
            font-weight: bold;
            box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>

<%
    String maKhachSan = request.getParameter("id");
    List<KhachSan> dsKhachSan = KhachSanDao.getInstance().layDanhSachTheoID(maKhachSan);
    KhachSan ks = (dsKhachSan != null && !dsKhachSan.isEmpty()) ? dsKhachSan.get(0) : null;
    List<Phong> dsPhong = PhongDao.getInstance().layDanhSachTheoDK(maKhachSan);
    List<QuanLy> dsQuanLy = QuanLyDao.getInstance().layDanhSachTheoMaKhachSan(maKhachSan);
%>

<div class="container mt-4">
    <% if (ks != null) { %>
        <h1 class="fw-bold"><%= ks.getTenKhachSan() %></h1>
        <a href="TrangChu.jsp" class="btn btn-primary">Quay lại</a>

        <!-- Danh sách phòng -->
        <h2 class="fw-bold mt-5">Danh sách phòng</h2>
        <div class="row">
            <% for (Phong p : dsPhong) { %>
                <div class="col-md-4 mb-4">
                    <div class="card shadow-sm">
                        <img src="<%= request.getContextPath() %>/pic/Phong/<%= p.getHinhAnh() %>" class="card-img-top room-img" alt="Hình ảnh phòng">
                        <div class="card-body">
                            <h5 class="card-title fw-bold"><%= p.getLoaiPhong() %></h5>
                            <p><strong>Loại giường:</strong> <%= p.getLoaiGiuong() %></p>
                            <p><strong>Số lượng:</strong> <%= p.getSoLuong() %></p>
                            <p><strong>Giá:</strong> <%= String.format("%,.0f", p.getGia()) %> VND</p>
                            <p><strong>Mô tả:</strong> <%= p.getMoTa() %></p>
                            <a href="DatPhongServlet?maPhong=<%= p.getMaPhong() %>&giaPhong=<%= p.getGia() %>" class="btn btn-success">Đặt ngay</a>
                        </div>
                    </div>
                </div>
            <% } %>
        </div>

    <% } else { %>
        <div class="container mt-4 text-center">
            <p class="text-danger">Không tìm thấy khách sạn!</p>
            <a href="DanhSachKhachSan.jsp" class="btn btn-primary">Quay lại</a>
        </div>
    <% } %>
</div>

<!-- Footer hiển thị người quản lý -->
<footer class="custom-footer">
    <h5 class="fw-bold">Người quản lý</h5>
    <% if (dsQuanLy != null && !dsQuanLy.isEmpty()) { %>
        <% for (QuanLy ql : dsQuanLy) { %>
            <p><strong>Mã:</strong> <%= ql.getMaQuanLy() %> | <strong>Email:</strong> <%= ql.getEmail() %> | <strong>SĐT:</strong> <%= ql.getSoDienThoai() %></p>
        <% } %>
    <% } else { %>
        <p class="text-muted">Không có người quản lý nào.</p>
    <% } %>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
