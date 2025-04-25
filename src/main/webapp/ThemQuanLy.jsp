<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Thêm Quản Lý</title>
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
		<h2 class="text-center mb-4">Thêm Quản Lý</h2>
		<form action="QTVThemQuanLyServlet" method="post">
			<div class="mb-3">
				<label for="tenQuanLy" class="form-label">Tên Quản Lý</label> <input
					type="text" class="form-control" id="tenQuanLy" name="tenQuanLy"
					required>
			</div>

			<div class="mb-3">
				<label for="soDienThoai" class="form-label">Số Điện Thoại</label> <input
					type="text" class="form-control" id="soDienThoai"
					name="soDienThoai" required>
			</div>

			<div class="mb-3">
				<label for="email" class="form-label">Email</label> <input
					type="email" class="form-control" id="email" name="email" required>
			</div>

			<div class="mb-3">
				<label for="taiKhoan" class="form-label">Tài Khoản</label> <input
					type="text" class="form-control" id="taiKhoan" name="taiKhoan"
					required>
			</div>

			<div class="mb-3">
				<label for="matKhau" class="form-label">Mật Khẩu</label> <input
					type="password" class="form-control" id="matKhau" name="matKhau"
					required>
			</div>

			<div class="mb-3">
				<label for="nhapLaiMatKhau" class="form-label">Nhập Lại Mật
					Khẩu</label> <input type="password" class="form-control"
					id="nhapLaiMatKhau" name="nhapLaiMatKhau" required>
			</div>

			<div class="mb-3">
				<label for="ngaySinh" class="form-label">Ngày Sinh</label> <input
					type="date" class="form-control" id="ngaySinh" name="ngaySinh"
					required>
			</div>

			<div class="mb-3">
				<label class="form-label">Giới Tính</label><br>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="gioiTinh"
						id="nam" value="true" checked> <label
						class="form-check-label" for="nam">Nam</label>
				</div>
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="gioiTinh"
						id="nu" value="false"> <label class="form-check-label"
						for="nu">Nữ</label>
				</div>
			</div>

			<div class="mb-3">
				<label for="soCCCD" class="form-label">Số CCCD</label> <input
					type="text" class="form-control" id="soCCCD" name="soCCCD" required>
			</div>

			<div class="text-center">
				<button type="submit" class="btn btn-primary">Thêm Quản Lý</button>
			</div>
		</form>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
