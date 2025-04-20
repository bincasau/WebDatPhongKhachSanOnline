<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.ThongTinDatPhong"%>
<%@ page import="model.KhachSan"%>
<%@ page import="Dao.KhachSanDao"%>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.text.DecimalFormat"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Lịch sử đặt phòng</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
	rel="stylesheet">
<style>
body {
	font-family: 'Arial', sans-serif;
	background-color: #f8f9fa;
}

.card {
	transition: transform 0.2s ease-in-out;
	border-radius: 10px;
}

.card:hover {
	transform: translateY(-10px);
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.gray-bg {
	background-color: #f0f0f0;
}

.green-bg {
	background-color: #d4edda;
}

.orange-bg {
	background-color: #ffcc99;
}

.card-title {
	font-size: 1.25rem;
	font-weight: bold;
}

.card-body {
	padding: 1.5rem;
}

.badge-info {
	background-color: #17a2b8;
}

.alert-warning {
	background-color: #ffeeba;
	color: #856404;
	font-weight: bold;
}

.container {
	max-width: 1200px;
}

.section-title {
	font-size: 2rem;
	margin-bottom: 20px;
	color: #333;
}

.card-body p {
	font-size: 1rem;
	color: #333;
}

.card-body p strong {
	color: #007bff;
}

.color-legend {
	margin-bottom: 30px;
}

.status-icon {
	font-size: 1.5rem;
	margin-right: 10px;
	color: #333;
	vertical-align: middle;
}

.status-icon::before {
	display: inline-block;
	width: 1.5rem;
	text-align: center;
}
</style>
</head>
<body>
	<%
	String message = String.valueOf(request.getAttribute("message"));
	if (message != null && !"null".equals(message)) {
	%>
	<div id="alert-message" class="alert alert-success text-center"
		role="alert">
		<%=message%>
	</div>

	<script>
		setTimeout(function() {
			var alert = document.getElementById('alert-message');
			if (alert) {
				alert.style.display = 'none';
			}
		}, 5000);
	</script>
	<%
	}
	%>
	<div class="text-start mb-4">
		<a href="TrangChu.jsp" class="btn btn-secondary"> <i
			class="bi bi-house-door-fill"></i> Về trang chủ
		</a>
	</div>

	<div class="container mt-5">
		<h3 class="section-title text-center">Lịch sử đặt phòng của bạn</h3>

		<div class="color-legend mb-4">
			<div>
				<strong>Màu sắc chú thích:</strong>
			</div>
			<ul>
				<li><span class="badge" style="background-color: #d4edda;">   </span>
					Phòng chưa trả (Ngày trả phòng chưa đến) - <strong>Xanh lá</strong></li>
				<li><span class="badge" style="background-color: #f0f0f0;">   </span>
					Phòng đã trả (Ngày trả phòng đã qua) - <strong>Xám</strong></li>
				<li><span class="badge" style="background-color: #ffcc99;">   </span>
					Phòng đang sử dụng (Ngày hiện tại giữa ngày nhận và trả) - <strong>Cam</strong></li>
			</ul>
		</div>



		<%
		List<ThongTinDatPhong> danhSach = (List<ThongTinDatPhong>) request.getAttribute("lichSuDatPhong");
		if (danhSach == null || danhSach.isEmpty()) {
		%>
		<div class="alert alert-warning text-center">Bạn chưa có lịch sử
			đặt phòng nào.</div>
		<%
		} else {
		for (int i = danhSach.size() - 1; i >= 0; i--) {
			ThongTinDatPhong dp = danhSach.get(i);

			LocalDate ngayTraPhong = dp.getNgayTraPhong().toLocalDate();
			LocalDate ngayNhanPhong = dp.getNgayNhanPhong().toLocalDate();
			LocalDate currentDate = LocalDate.now();

			String bgClass = "";
			if (ngayTraPhong.isBefore(currentDate)) {
				bgClass = "gray-bg";
			} else if (currentDate.isBefore(ngayNhanPhong)) {
				bgClass = "green-bg";
			} else {
				bgClass = "orange-bg";
			}

			DecimalFormat formatter = new DecimalFormat("#,###");
			String formattedThanhTien = formatter.format(dp.getThanhTien());

			// Lấy tên khách sạn
			List<KhachSan> dsks = KhachSanDao.getInstance().layDanhSachTheoID(dp.getMaKhachSan());
			String tenKhachSan = (dsks != null && !dsks.isEmpty()) ? dsks.get(0).getTenKhachSan() : "Không xác định";
		%>

		<div class="row mb-4">
			<div class="col-md-12">
				<div class="card <%=bgClass%>">
					<div class="card-header">
						<h5 class="card-title text-center">
							Mã đặt phòng:
							<%=dp.getMaDatPhong()%></h5>
					</div>
					<div class="card-body">
						<div class="d-flex align-items-center mb-3">
							<i class="bi bi-building-fill status-icon"></i>
							<p>
								<strong>Khách sạn:</strong>
								<%=tenKhachSan%></p>
						</div>
						<div class="d-flex align-items-center mb-3">
							<i class="bi bi-door-closed status-icon"></i>
							<p>
								<strong>Phòng:</strong>
								<%=dp.getMaPhong()%></p>
						</div>
						<div class="d-flex align-items-center mb-3">
							<i class="bi bi-calendar status-icon"></i>
							<p>
								<strong>Ngày đặt:</strong>
								<%=dp.getNgayDatPhong()%></p>
						</div>
						<div class="d-flex align-items-center mb-3">
							<i class="bi bi-clock status-icon"></i>
							<p>
								<strong>Nhận phòng:</strong>
								<%=dp.getNgayNhanPhong()%>
								<strong>—</strong> <strong>Trả phòng:</strong>
								<%=dp.getNgayTraPhong()%></p>
						</div>
						<div class="mb-3">
							<p>
								<strong>Thời gian sử dụng:</strong>
								<%=dp.getTgSuDung()%>
								ngày
							</p>
							<p>
								<strong>Ghi chú:</strong>
								<%=dp.getGhiChu() == null ? "Không có" : dp.getGhiChu()%></p>
						</div>
						<div class="d-flex justify-content-between">
							<p>
								<strong>Thành tiền:</strong>
								<%=formattedThanhTien%>
								VND
							</p>
							<span class="badge bg-info">Trạng thái: <%=dp.getTrangThai()%></span>
						</div>
					</div>
					<div class="card-footer text-center">
						<a
							href="DanhGia.jsp?&TenKhachSan=<%=java.net.URLEncoder.encode(tenKhachSan, "UTF-8")%>&maKhachSan=<%=dp.getMaKhachSan()%>"
							class="btn btn-primary">Đánh giá</a>
					</div>
				</div>
			</div>
		</div>

		<%
		}
		}
		%>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>