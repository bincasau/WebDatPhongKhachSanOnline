<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.KhachSan"%>
<%@ page import="model.Phong"%>
<%@ page import="model.ThongTinDanhGia"%>
<%@ page import="Dao.KhachSanDao"%>
<%@ page import="Dao.PhongDao"%>
<%@ page import="Dao.ThongTinDanhGiaDao"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<%
String message = (String) request.getAttribute("message");
if (message != null) {
%>
<div id="messageBox"
	class="alert alert-info text-center position-fixed top-0 start-50 translate-middle-x mt-3"
	style="z-index: 1050;">
	<%=message%>
</div>
<script>
        setTimeout(function() {
            document.getElementById("messageBox").style.display = "none";
        }, 5000);
    </script>
<%
}
%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Xem Khách Sạn</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<!-- SweetAlert2 CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
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

.btn-sm {
	margin-right: 5px;
}
</style>
</head>
<body>
	<%
	String maKhachSan = request.getParameter("maKhachSan");
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
		<div class="d-flex justify-content-between mb-4">
			<div>
				<a href="QLGiaoDien.jsp" class="btn btn-primary">Quay lại</a> <a
					href="ThemPhong.jsp?maKhachSan=<%=maKhachSan%>"
					class="btn btn-success">Thêm phòng</a>
			</div>
			<div>
				<a href="QLThongKeServlet?maKhachSan=<%=maKhachSan%>"
					class="btn btn-info">Xem thống kê</a> <a
					href="QLXemDanhSachDatPhong.jsp?maKhachSan=<%=maKhachSan%>"
					class="btn btn-warning">Xem danh sách đặt phòng</a>
			</div>
		</div>

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
						<div class="d-flex justify-content-end">
							<button type="button" class="btn btn-primary btn-sm"
								data-bs-toggle="modal" data-bs-target="#suaPhongModal"
								onclick="fillSuaPhongForm('<%=p.getMaPhong()%>', '<%=p.getLoaiPhong()%>', '<%=p.getLoaiGiuong()%>', '<%=p.getSoLuong()%>', '<%=p.getGia()%>', '<%=p.getMoTa()%>', '<%=p.getHinhAnh()%>', '<%=p.getTinhTrang()%>')">Sửa</button>
							<button class="btn btn-danger btn-sm"
								onclick="confirmDeletePhong('<%=p.getMaPhong()%>', '<%=maKhachSan%>')">Xóa</button>

						</div>
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
		<div class="alert alert-secondary">Chưa có phòng nào cho khách
			sạn này.</div>
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
						<div class="d-flex justify-content-between align-items-center">
							<h5 class="card-title text-success mb-0"><%=dg.getSoSao()%>
								★
							</h5>
							<button class="btn btn-danger btn-sm"
								onclick="confirmDeleteDanhGia('<%=dg.getMaDanhGia()%>', '<%=maKhachSan%>')">Xóa</button>
						</div>
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
			<a href="QLGiaoDien.jsp" class="btn btn-primary">Quay lại</a>
		</div>
		<%
		}
		%>
	</div>

	<!-- Modal Sửa Phòng -->
	<div class="modal fade" id="suaPhongModal" tabindex="-1"
		aria-labelledby="suaPhongModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="suaPhongModalLabel">Sửa Phòng</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="QLSuaPhongServlet" method="post"
						enctype="multipart/form-data">
						<input type="hidden" name="maPhong" id="suaMaPhong"> <input
							type="hidden" name="maKhachSan" value="<%=maKhachSan%>">
						<div class="mb-3">
							<label for="suaLoaiPhong" class="form-label">Loại phòng</label> <input
								type="text" class="form-control" id="suaLoaiPhong"
								name="loaiPhong" required>
						</div>
						<div class="mb-3">
							<label for="suaLoaiGiuong" class="form-label">Loại giường</label>
							<input type="text" class="form-control" id="suaLoaiGiuong"
								name="loaiGiuong" required>
						</div>
						<div class="mb-3">
							<label for="suaSoLuong" class="form-label">Số lượng</label> <input
								type="number" class="form-control" id="suaSoLuong"
								name="soLuong" required>
						</div>
						<div class="mb-3">
							<label for="suaGia" class="form-label">Giá (VND)</label> <input
								type="number" class="form-control" id="suaGia" name="gia"
								required>
						</div>
						<div class="mb-3">
							<label for="suaTinhTrang" class="form-label">Tình trạng</label> <select
								class="form-select" id="suaTinhTrang" name="tinhTrang" required>
								<option value="Tốt">Tốt</option>
								<option value="Đang sửa chữa">Đang sửa chữa</option>
							</select>
						</div>

						<div class="mb-3">
							<label for="suaMoTa" class="form-label">Mô tả</label>
							<textarea class="form-control" id="suaMoTa" name="moTa" rows="4"
								required></textarea>
						</div>
						<div class="mb-3">
							<label for="suaHinhAnh" class="form-label">Hình ảnh</label> <input
								type="file" class="form-control" id="suaHinhAnh" name="hinhAnh"
								accept="image/*"> <input type="hidden" id="suaHinhAnhCu"
								name="hinhAnhCu">
						</div>
						<button type="submit" class="btn btn-primary">Lưu</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<!-- SweetAlert2 JS -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
	function fillSuaPhongForm(maPhong, loaiPhong, loaiGiuong, soLuong, gia, moTa, hinhAnh, tinhTrang) {
	    document.getElementById('suaMaPhong').value = maPhong;
	    document.getElementById('suaLoaiPhong').value = loaiPhong;
	    document.getElementById('suaLoaiGiuong').value = loaiGiuong;
	    document.getElementById('suaSoLuong').value = soLuong;
	    document.getElementById('suaGia').value = gia;
	    document.getElementById('suaMoTa').value = moTa;
	    document.getElementById('suaHinhAnhCu').value = hinhAnh;
	    document.getElementById('suaTinhTrang').value = tinhTrang;
	}


        function confirmDeleteDanhGia(maDanhGia, maKhachSan) {
            Swal.fire({
                title: 'Xác nhận xóa',
                text: 'Bạn có chắc muốn xóa đánh giá này?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#d33',
                cancelButtonColor: '#3085d6',
                confirmButtonText: 'Xóa',
                cancelButtonText: 'Hủy'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = 'QLXoaDanhGiaServlet?maDanhGia=' + maDanhGia + '&maKhachSan=' + maKhachSan;
                }
            });
        }
        function confirmDeletePhong(maPhong, maKhachSan) {
            Swal.fire({
                title: 'Xác nhận xóa phòng',
                text: 'Bạn có chắc chắn muốn xóa phòng này? Thao tác này không thể hoàn tác.',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#d33',
                cancelButtonColor: '#3085d6',
                confirmButtonText: 'Xóa phòng',
                cancelButtonText: 'Hủy'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = 'QLXoaPhongServlet?maPhong=' + maPhong + '&maKhachSan=' + maKhachSan;
                }
            });
        }
                
    </script>
</body>
</html>