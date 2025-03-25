<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Đăng Nhập - Đặt Phòng Khách Sạn Online</title>
<!-- Import Bootstrap 5 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f8f9fa;
	height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
}

.login-container {
	background: white;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
	max-width: 400px;
	width: 100%;
	text-align: center;
}

.hotel-image {
	width: 100%;
	height: auto;
	border-radius: 10px;
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<!-- Tiêu đề -->
	<h2 class="mb-3">Đặt Phòng Khách Sạn Online</h2>

	<!-- Form đăng nhập -->
	<div class="login-container">
		<h3 class="mb-4">Đăng Nhập</h3>
		<form action="DangNhapServlet" method="POST">
			<div class="mb-3">
				<%
				String message = request.getAttribute("error") + "";
				if (!message.equals("null")) {
				%>
				<p style="color: red; font-style: italic; font-weight: bold;">
					*<%=message%></p>

				<%
				}
				%>
				<input type="text" class="form-control" name="username"
					placeholder="Tài khoản" required>
			</div>
			<div class="mb-3">
				<input type="password" class="form-control" name="password"
					placeholder="Mật khẩu" required>
			</div>
			<button type="submit" class="btn btn-primary w-100">Đăng
				Nhập</button>
		</form>

		<!-- Liên kết đăng ký -->
		<p class="mt-3">
			Chưa có tài khoản? <a href="DangKy.jsp"
				class="text-primary text-decoration-underline">Đăng ký</a>
		</p>
	</div>

	<!-- Import Bootstrap JS (nếu cần) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
