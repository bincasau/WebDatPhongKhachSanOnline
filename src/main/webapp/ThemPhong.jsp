<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Phòng Mới</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            padding: 30px;
        }
        .form-container {
            max-width: 600px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
    <%
        String maKhachSan = request.getParameter("maKhachSan");
        String message = (String) request.getAttribute("message");
    %>
    <div class="container mt-4">
        <h1 class="fw-bold text-center">Thêm Phòng Mới</h1>
        <%
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
        <div class="form-container mt-4">
            <form action="QLThemPhongServlet" method="post" enctype="multipart/form-data">
                <input type="hidden" name="maKhachSan" value="<%=maKhachSan%>">
                <div class="mb-3">
                    <label for="loaiPhong" class="form-label">Loại phòng</label>
                    <input type="text" class="form-control" id="loaiPhong" name="loaiPhong" placeholder="VD: Thường, VIP" required>
                </div>
                <div class="mb-3">
                    <label for="loaiGiuong" class="form-label">Loại giường</label>
                    <input type="text" class="form-control" id="loaiGiuong" name="loaiGiuong" placeholder="VD: Đơn, Đôi" required>
                </div>
                <div class="mb-3">
                    <label for="soLuong" class="form-label">Số lượng</label>
                    <input type="number" class="form-control" id="soLuong" name="soLuong" min="1" required>
                </div>
                <div class="mb-3">
                    <label for="gia" class="form-label">Giá (VND)</label>
                    <input type="number" class="form-control" id="gia" name="gia" min="0" step="1000" required>
                </div>
                <div class="mb-3">
                    <label for="tinhTrang" class="form-label">Tình trạng</label>
                    <select class="form-control" id="tinhTrang" name="tinhTrang" required>
                        <option value="Trống">Tốt</option>
                        <option value="Đã đặt">Đang sửa chửa</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="moTa" class="form-label">Mô tả</label>
                    <textarea class="form-control" id="moTa" name="moTa" rows="4" placeholder="Mô tả chi tiết về phòng" required></textarea>
                </div>
                <div class="mb-3">
                    <label for="hinhAnh" class="form-label">Hình ảnh</label>
                    <input type="file" class="form-control" id="hinhAnh" name="hinhAnh" accept="image/*" required>
                </div>
                <div class="d-flex justify-content-between">
                    <a href="QLXemPhong.jsp?maKhachSan=<%=maKhachSan%>" class="btn btn-secondary">Quay lại</a>
                    <button type="submit" class="btn btn-primary">Thêm phòng</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>