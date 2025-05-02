<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.QuanLy, Dao.QuanLyDao" %>
<%
    String id = request.getParameter("id");
    QuanLy quanLy = QuanLyDao.getInstance().layDanhSachTheoID(id).get(0);
    if (quanLy == null) {
        response.sendRedirect("QTVGiaoDien.jsp?message=Không tìm thấy quản lý");
        return;
    }
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Sửa thông tin Quản lý</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', sans-serif;
            margin: 30px;
        }

        h2 {
            color: #343a40;
            margin-bottom: 20px;
        }

        .form-container {
            max-width: 700px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .btn-primary {
            background-color: #0d6efd;
            border: none;
        }

        .btn-primary:hover {
            background-color: #0b5ed7;
        }
    </style>
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

<div class="form-container">
    <h2>Sửa thông tin Quản lý</h2>
    <form action="QTVSuaQuanLyServlet" method="post">
        <input type="hidden" name="maQuanLy" value="<%=quanLy.getMaQuanLy()%>">

        <div class="mb-3">
            <label class="form-label">Họ tên</label>
            <input type="text" class="form-control" name="tenQuanLy" value="<%=quanLy.getTenQuanLy()%>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Số điện thoại</label>
            <input type="text" class="form-control" name="soDienThoai" value="<%=quanLy.getSoDienThoai()%>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" class="form-control" name="email" value="<%=quanLy.getEmail()%>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Ngày sinh</label>
            <input type="date" class="form-control" name="ngaySinh" value="<%=quanLy.getNgaySinh()%>" required>
        </div>

        <div class="mb-3">
            <label class="form-label">Giới tính</label>
            <select class="form-select" name="gioiTinh">
                <option value="nam" <%=quanLy.isGioiTinh() ? "selected" : ""%>>Nam</option>
                <option value="nu" <%=!quanLy.isGioiTinh() ? "selected" : ""%>>Nữ</option>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Số CCCD</label>
            <input type="text" class="form-control" name="soCCCD" value="<%=quanLy.getSoCCCD()%>" required>
        </div>

        <button type="submit" class="btn btn-primary">Cập nhật</button>
        <a href="QTVGiaoDien.jsp" class="btn btn-secondary">Hủy</a>
    </form>
</div>

</body>
</html>
