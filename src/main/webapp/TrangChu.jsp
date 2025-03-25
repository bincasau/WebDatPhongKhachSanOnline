<%@page import="model.KhachHang"%>
<%@page import="java.util.Arrays"%>
<%@page import="Dao.KhachSanDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.KhachSan"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách khách sạn</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

<style>
body {
	background-color: #f8f9fa;
}

.navbar {
	margin-bottom: 20px;
}

.filter-container {
	padding: 15px;
	background: white;
	border-radius: 8px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.hotel-img {
	height: 200px; /* Điều chỉnh chiều cao ảnh */
	object-fit: cover; /* Giữ tỉ lệ ảnh và cắt nếu cần */
	border-top-left-radius: 8px;
	border-top-right-radius: 8px;
}

.card {
	border-radius: 8px;
	overflow: hidden;
	transition: transform 0.2s;
}

.card:hover {
	transform: scale(1.03);
}
</style>
</head>
<body>

	<!-- Thanh điều hướng -->
	<nav class="navbar navbar-light bg-light px-3">
		<div
			class="container d-flex justify-content-between align-items-center">
			<a class="navbar-brand fw-bold d-flex align-items-center" href="#">
				🏨 Đạt Phòng Khách Sạn </a>
			<div class="d-flex align-items-center">
				<%
				Object s = session.getAttribute("khachHang");
				KhachHang kh = (KhachHang) s;
				if (kh == null) {
				%>
				<a href="DangNhap.jsp" class="btn btn-primary">Đăng nhập</a>
				<%
				} else {
				%>
				<span class="fw-bold me-2">Xin chào, <%=kh.getTenKhachHang()%></span>
				<a href="DangXuatServlet" class="btn btn-primary btn-sm">Đăng xuất</a>
				<%
				}
				%>
			</div>
		</div>
	</nav>


	<!-- Bộ lọc khu vực -->
	<div class="container">
		<div class="filter-container mb-4">
			<form action="HotelServlet" method="GET">
				<div class="row align-items-center">
					<div class="col-md-4">
						<label class="fw-bold">Chọn khu vực:</label> <select name="khuVuc"
							class="form-select">
							<option value="">Tất cả khu vực</option>
							<%
							List<String> ds = Arrays.asList("An Giang", "Bà Rịa - Vũng Tàu", "Bắc Giang", "Bắc Kạn", "Bạc Liêu", "Bắc Ninh",
									"Bến Tre", "Bình Định", "Bình Dương", "Bình Phước", "Bình Thuận", "Cà Mau", "Cần Thơ", "Cao Bằng", "Đà Nẵng",
									"Đắk Lắk", "Đắk Nông", "Điện Biên", "Đồng Nai", "Đồng Tháp", "Gia Lai", "Hà Giang", "Hà Nam", "Hà Nội",
									"Hà Tĩnh", "Hải Dương", "Hải Phòng", "Hậu Giang", "Hòa Bình", "Hưng Yên", "Khánh Hòa", "Kiên Giang", "Kon Tum",
									"Lai Châu", "Lâm Đồng", "Lạng Sơn", "Lào Cai", "Long An", "Nam Định", "Nghệ An", "Ninh Bình", "Ninh Thuận",
									"Phú Thọ", "Phú Yên", "Quảng Bình", "Quảng Nam", "Quảng Ngãi", "Quảng Ninh", "Quảng Trị", "Sóc Trăng", "Sơn La",
									"Tây Ninh", "Thái Bình", "Thái Nguyên", "Thanh Hóa", "Thừa Thiên Huế", "Tiền Giang", "TP. Hồ Chí Minh",
									"Trà Vinh", "Tuyên Quang", "Vĩnh Long", "Vĩnh Phúc", "Yên Bái");
							for (String t : ds) {
							%>
							<option value="tinhThanh"><%=t%></option>
							<%
							}
							%>
						</select>
					</div>
					<div class="col-md-2">
						<button type="submit" class="btn btn-success mt-3">Lọc</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- Danh sách khách sạn -->
	<div class="container">
		<div class="row">
			<%
			List<KhachSan> dsKhachSan = KhachSanDao.getInstance().layDanhSach();
			if (dsKhachSan != null && !dsKhachSan.isEmpty()) {
				for (KhachSan ks : dsKhachSan) {
			%>
			<div class="col-md-4 mb-4">
				<div class="card shadow-sm">
					<img
						src="<%=request.getContextPath()%>/pic/KS/<%=ks.getHinhAnh()%>.jpg"
						class="card-img-top hotel-img" alt="Hình ảnh khách sạn">
					<div class="card-body">
						<h5 class="card-title fw-bold"><%=ks.getTenKhachSan()%></h5>
						<p class="card-text">
							<i class="bi bi-geo-alt"></i>
							<%=ks.getDiaChi()%></p>
						<p class="card-text">
							<strong>Khu vực:</strong>
							<%=ks.getKhuVuc()%></p>
						<p class="card-text"><%=ks.getMoTa()%></p>
						<p class="card-text text-warning">
							⭐ Đánh giá: <strong><%=ks.getDanhGiaTrungBinh()%></strong>
						</p>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			%>
			<div class="col-12 text-center">
				<p class="text-muted">Không tìm thấy khách sạn nào!</p>
			</div>
			<%
			}
			%>
		</div>
	</div>
	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
