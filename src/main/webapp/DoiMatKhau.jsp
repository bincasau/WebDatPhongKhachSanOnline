<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.KhachHang"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đổi mật khẩu</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css"
	rel="stylesheet">

<style>
body {
	background-color: #f8f9fa;
}

.change-password-form {
	max-width: 500px;
	margin: 60px auto;
	background: white;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.form-title {
	margin-bottom: 25px;
	text-align: center;
}

.input-group-text {
	cursor: pointer;
}
</style>
</head>
<body>

	<div class="text-start mb-4">
		<a href="TrangChu.jsp" class="btn btn-secondary"> <i
			class="bi bi-house-door-fill"></i> Về trang chủ
		</a>
	</div>
	<%
	KhachHang kh = (KhachHang) session.getAttribute("khachHang");
	if (kh == null) {
		response.sendRedirect("DangNhap.jsp");
		return;
	}
	%>

	<div class="container">
		<div class="change-password-form">
			<h3 class="form-title">Đổi mật khẩu</h3>

			<%
			String msg = (String) request.getAttribute("message");
			if (msg != null) {
			%>
			<div class="alert alert-info text-center"><%=msg%></div>
			<%
			}
			%>

			<form action="DoiMatKhauServlet" method="post">
				<!-- Mật khẩu hiện tại -->
				<div class="mb-3">
					<label for="matKhauCu" class="form-label">Mật khẩu hiện tại</label>
					<div class="input-group">
						<input type="password" class="form-control" id="matKhauCu"
							name="matKhauCu" required> <span class="input-group-text"
							onclick="togglePassword('matKhauCu', this)"> <i
							class="bi bi-eye-slash"></i>
						</span>
					</div>
				</div>

				<!-- Mật khẩu mới -->
				<div class="mb-3">
					<label for="matKhauMoi" class="form-label">Mật khẩu mới</label>
					<div class="input-group">
						<input type="password" class="form-control" id="matKhauMoi"
							name="matKhauMoi" required> <span
							class="input-group-text"
							onclick="togglePassword('matKhauMoi', this)"> <i
							class="bi bi-eye-slash"></i>
						</span>
					</div>
				</div>

				<!-- Nhập lại mật khẩu -->
				<div class="mb-3">
					<label for="xacNhanMatKhauMoi" class="form-label">Nhập lại
						mật khẩu mới</label>
					<div class="input-group">
						<input type="password" class="form-control" id="xacNhanMatKhauMoi"
							name="xacNhanMatKhauMoi" required> <span
							class="input-group-text"
							onclick="togglePassword('xacNhanMatKhauMoi', this)"> <i
							class="bi bi-eye-slash"></i>
						</span>
					</div>
				</div>

				<div class="d-flex gap-2">
					<button type="submit" class="btn btn-primary flex-fill">Cập
						nhật mật khẩu</button>
				</div>


			</form>
		</div>
	</div>

	<!-- Script toggle password visibility -->
	<script>
		function togglePassword(fieldId, iconElement) {
			const input = document.getElementById(fieldId);
			const icon = iconElement.querySelector("i");

			if (input.type === "password") {
				input.type = "text";
				icon.classList.remove("bi-eye-slash");
				icon.classList.add("bi-eye");
			} else {
				input.type = "password";
				icon.classList.remove("bi-eye");
				icon.classList.add("bi-eye-slash");
			}
		}
	</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
