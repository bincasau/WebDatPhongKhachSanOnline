<%@page import="Dao.ThongTinDanhGiaDao"%>
<%@page import="model.ThongTinDanhGia"%>
<%@page import="Dao.PhongDao"%>
<%@page import="Dao.QuanLyDao"%>
<%@page import="model.QuanLy"%>
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
<title>Chi tiết khách sạn</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<style>
body {
	background-color: #f8f9fa;
	padding-bottom: 120px;
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
	List<ThongTinDanhGia> dsDanhGia = ThongTinDanhGiaDao.getInstance().layDanhSachTheoDK(maKhachSan);
	KhachSan ks = (dsKhachSan != null && !dsKhachSan.isEmpty()) ? dsKhachSan.get(0) : null;

	List<Phong> dsPhong = PhongDao.getInstance().layDanhSachTheoDK(maKhachSan);

	// Lọc dữ liệu nếu có
	String strGiaTu = request.getParameter("giaTu");
	String strGiaDen = request.getParameter("giaDen");
	String loaiGiuong = request.getParameter("loaiGiuong");

	double giaTu = (strGiaTu != null && !strGiaTu.isEmpty()) ? Double.parseDouble(strGiaTu) : 0;
	double giaDen = (strGiaDen != null && !strGiaDen.isEmpty()) ? Double.parseDouble(strGiaDen) : Double.MAX_VALUE;

	List<Phong> dsPhongLoc = new java.util.ArrayList<>();
	for (Phong p : dsPhong) {
		boolean hopGia = p.getGia() >= giaTu && p.getGia() <= giaDen;
		boolean hopGiuong = (loaiGiuong == null || loaiGiuong.isEmpty()) || p.getLoaiGiuong().equalsIgnoreCase(loaiGiuong);
		if (hopGia && hopGiuong) {
			dsPhongLoc.add(p);
		}
	}
	dsPhong = dsPhongLoc;
	List<QuanLy> dsQuanLy = QuanLyDao.getInstance().layDanhSachTheoMaKhachSan(maKhachSan);
	%>

	<div class="container mt-4">
		<%
		if (ks != null) {
		%>
		<h1 class="fw-bold"><%=ks.getTenKhachSan()%></h1>
		<a href="TrangChu.jsp" class="btn btn-primary mb-4">Quay lại</a>

		<!-- Bộ lọc phòng -->
		<form method="get" class="row g-3 mb-4">
			<input type="hidden" name="id" value="<%=maKhachSan%>">
			<div class="col-md-4">
				<label class="form-label">Giá từ:</label> <input type="number"
					class="form-control" name="giaTu" placeholder="VND"
					value="<%=strGiaTu != null ? strGiaTu : ""%>">
			</div>
			<div class="col-md-4">
				<label class="form-label">Giá đến:</label> <input type="number"
					class="form-control" name="giaDen" placeholder="VND"
					value="<%=strGiaDen != null ? strGiaDen : ""%>">
			</div>
			<div class="col-md-4">
				<label class="form-label">Loại giường:</label> <select
					class="form-select" name="loaiGiuong">
					<option value="">Tất cả</option>
					<option value="Đơn"
						<%="Đơn".equals(loaiGiuong) ? "selected" : ""%>>Đơn</option>
					<option value="Đôi"
						<%="Đôi".equals(loaiGiuong) ? "selected" : ""%>>Đôi</option>

				</select>
			</div>
			<div class="col-12">
				<button type="submit" class="btn btn-primary">Lọc</button>
				<a href="ChiTietPhongKhachSan.jsp?id=<%=maKhachSan%>"
					class="btn btn-secondary ms-2">Xóa lọc</a>
			</div>
		</form>

		<!-- Danh sách phòng -->
		<h2 class="fw-bold mt-4">Danh sách phòng</h2>
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
						<p>
							<strong>Loại giường:</strong>
							<%=p.getLoaiGiuong()%></p>
						<p>
							<strong>Số lượng:</strong>
							<%=p.getSoLuong()%></p>
						<p>
							<strong>Giá:</strong>
							<%=String.format("%,.0f", p.getGia())%>
							VND
						</p>
						<p>
							<strong>Mô tả:</strong>
							<%=p.getMoTa()%></p>
						<a href="DatPhongServlet?maPhong=<%=p.getMaPhong()%>&giaPhong=<%=p.getGia()%>&maKhachSan=<%=ks.getMaKhachSan()%>"
							class="btn btn-success"> Đặt ngay </a>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>

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
						<h5 class="card-title text-success"><%=dg.getSoSao()%>
							★
						</h5>
						<p class="text-muted mb-0">
							<strong>Khách hàng:</strong>
							<%=dg.getMaKhachHang()%></p>
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
		<div class="alert alert-secondary">Chưa có đánh giá nào cho
			khách sạn này.</div>
		<%
		}
		%>
		<%
		} else {
		%>
		<div class="container mt-4 text-center">
			<p class="text-danger">Không tìm thấy khách sạn!</p>
			<a href="DanhSachKhachSan.jsp" class="btn btn-primary">Quay lại</a>
		</div>
		<%
		}
		%>
	</div>

	<!-- Footer hiển thị người quản lý -->
	<footer class="custom-footer">
		<h5 class="fw-bold">Người quản lý</h5>
		<%
		if (dsQuanLy != null && !dsQuanLy.isEmpty()) {
		%>
		<%
		for (QuanLy ql : dsQuanLy) {
		%>
		<p>
			<strong>Mã:</strong>
			<%=ql.getMaQuanLy()%>
			| <strong>Email:</strong>
			<%=ql.getEmail()%>
			| <strong>SĐT:</strong>
			<%=ql.getSoDienThoai()%></p>
		<%
		}
		%>
		<%
		} else {
		%>
		<p class="text-muted">Không có người quản lý nào.</p>
		<%
		}
		%>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>