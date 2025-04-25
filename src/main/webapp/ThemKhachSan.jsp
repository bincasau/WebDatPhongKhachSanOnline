<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="model.QuanLy"%>
<%@ page import="Dao.QuanLyDao"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Thêm Khách Sạn</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
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
	<div class="container mt-5">
		<div class="container-fluid">
			
			<h2 class="text-center mb-4">Thêm Khách Sạn</h2>

			<form action="QTVThemKhachSanServlet" method="post"
				enctype="multipart/form-data">
				<div class="mb-3">
					<label for="tenKhachSan" class="form-label">Tên Khách Sạn</label> <input
						type="text" class="form-control" id="tenKhachSan"
						name="tenKhachSan" required>
				</div>

				<div class="mb-3">
					<label for="diaChi" class="form-label">Địa Chỉ</label> <input
						type="text" class="form-control" id="diaChi" name="diaChi"
						required>
				</div>

				<div class="mb-3">
					<label for="khuVuc" class="form-label">Khu Vực</label> <input
						type="text" class="form-control" id="khuVuc" name="khuVuc"
						required>
				</div>

				<div class="mb-3">
					<label for="moTa" class="form-label">Mô Tả</label>
					<textarea class="form-control" id="moTa" name="moTa" rows="4"
						required></textarea>
				</div>

				<div class="mb-3">
					<label for="danhGiaTrungBinh" class="form-label">Đánh Giá
						Trung Bình</label> <input type="number" step="0.1" min="0" max="5"
						class="form-control" id="danhGiaTrungBinh" name="danhGiaTrungBinh"
						required>
				</div>

				<div class="mb-3">
					<label for="maQuanLy" class="form-label">Mã Quản Lý</label> <select
						class="form-select" id="maQuanLy" name="maQuanLy" required>
						<option value="">-- Chọn Quản Lý --</option>
						<%
						List<QuanLy> listQL = QuanLyDao.getInstance().layDanhSach();
						for (QuanLy ql : listQL) {
						%>
						<option value="<%=ql.getMaQuanLy()%>">
							<%=ql.getMaQuanLy()%> -
							<%=ql.getTenQuanLy()%>
						</option>
						<%
						}
						%>
					</select>
				</div>

				<div class="mb-3">
					<label for="hinhAnh" class="form-label">Hình Ảnh</label> <input
						type="file" class="form-control" id="hinhAnh" name="hinhAnh"
						accept="image/*" required>
				</div>

				<div class="text-center">
					<button type="submit" class="btn btn-primary">Thêm Khách
						Sạn</button>
				</div>
			</form>
		</div>

		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
