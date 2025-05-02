<%@page import="Dao.ThongTinDanhGiaDao"%>
<%@page import="model.ThongTinDanhGia"%>
<%@page import="Dao.PhongDao"%>
<%@page import="model.Phong"%>
<%@page import="java.util.List"%>
<%@page import="Dao.KhachSanDao"%>
<%@page import="model.KhachSan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Xem khách sạn</title>
<link rel="stylesheet"
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<style>
body {
    background-color: #f8f9fa;
    padding: 30px;
}

.card {
    border-radius: 8px;
    overflow: hidden;
    transition: transform 0.2s;
}

.card:hover {
    transform: scale(1.03);
}

.room-img {
    height: 200px;
    object-fit: cover;
}
</style>
</head>
<body>

    <%
    String maKhachSan = request.getParameter("id");
    List<KhachSan> dsKhachSan = KhachSanDao.getInstance().layDanhSachTheoID(maKhachSan);
    List<ThongTinDanhGia> dsDanhGia = ThongTinDanhGiaDao.getInstance().layDanhSachTheoDK(maKhachSan);
    KhachSan ks = (dsKhachSan != null && !dsKhachSan.isEmpty()) ? dsKhachSan.get(0) : null;

    List<Phong> dsPhong = PhongDao.getInstance().layDanhSachTheoDK(maKhachSan);
    %>

    <div class="container mt-4">
        <%
        if (ks != null) {
        %>
        <h1 class="fw-bold"><%=ks.getTenKhachSan()%></h1>
        <a href="QTVGiaoDien.jsp" class="btn btn-primary mb-4">Quay lại</a>

        <!-- Danh sách phòng -->
        <h2 class="fw-bold mt-4">Danh sách phòng</h2>
        <%
        if (dsPhong != null && !dsPhong.isEmpty()) {
        %>
        <div class="row">
            <%
            for (Phong p : dsPhong) {
            %>
            <div class="col-md-4 mb-4">
                <div class="card shadow-sm">
                    <img
                        src="<%=request.getContextPath()%>/pic/Phong/<%=p.getHinhAnh()%>"
                        class="card-img-top room-img" alt="Hình ảnh phòng">
                    <div class="card-body">
                        <h5 class="card-title fw-bold"><%=p.getLoaiPhong()%></h5>
                        <p><strong>Loại giường:</strong> <%=p.getLoaiGiuong()%></p>
                        <p><strong>Số lượng:</strong> <%=p.getSoLuong()%></p>
                        <p><strong>Giá:</strong> <%=String.format("%,.0f", p.getGia())%> VND</p>
                        <p><strong>Mô tả:</strong> <%=p.getMoTa()%></p>
                    </div>
                </div>
            </div>
            <%
            }
            %>
        </div>
        <%
        } else {
        %>
        <div class="alert alert-secondary">Chưa có phòng nào cho khách sạn này.</div>
        <%
        }
        %>

        <!-- Đánh giá khách sạn -->
        <h2 class="fw-bold mt-5">Đánh giá từ khách hàng</h2>
        <%
        if (dsDanhGia != null && !dsDanhGia.isEmpty()) {
        %>
        <div class="row">
            <%
            for (ThongTinDanhGia dg : dsDanhGia) {
            %>
            <div class="col-md-6 mb-3">
                <div class="card border-success shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title text-success"><%=dg.getSoSao()%> ★</h5>
                        <p class="text-muted mb-0"><strong>Khách hàng:</strong> <%=dg.getMaKhachHang()%></p>
                        <p class="card-text"><%=dg.getMoTa()%></p>
                    </div>
                </div>
            </div>
            <%
            }
            %>
        </div>
        <%
        } else {
        %>
        <div class="alert alert-secondary">Chưa có đánh giá nào cho khách sạn này.</div>
        <%
        }
        %>
        <%
        } else {
        %>
        <div class="container mt-4 text-center">
            <p class="text-danger">Không tìm thấy khách sạn!</p>
            <a href="QuanLyHeThong.jsp" class="btn btn-primary">Quay lại</a>
        </div>
        <%
        }
        %>
    </div>

    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>