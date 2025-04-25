<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List, model.KhachSan, Dao.KhachSanDao, model.QuanLy, Dao.QuanLyDao"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Sửa Khách Sạn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
    <div id="messageBox" class="alert alert-info text-center position-fixed top-0 start-50 translate-middle-x mt-3" style="z-index: 1050;">
        <%=message%>
    </div>
    <script>
        setTimeout(function() {
            document.getElementById("messageBox").style.display = "none";
        }, 5000);
    </script>
    <%
        }

        // Lấy mã khách sạn từ tham số yêu cầu
        String maKhachSan = request.getParameter("id");
        KhachSan khachSan = null;
        String errorMessage = null;
        if (maKhachSan != null && !maKhachSan.isEmpty()) {
            try {
                List<KhachSan> khachSanList = KhachSanDao.getInstance().layDanhSachTheoID(maKhachSan);
                if (!khachSanList.isEmpty()) {
                    khachSan = khachSanList.get(0);
                } else {
                    errorMessage = "Không tìm thấy khách sạn với mã: " + maKhachSan;
                }
            } catch (Exception e) {
                errorMessage = "Lỗi khi lấy thông tin khách sạn: " + e.getMessage();
            }
        } else {
            errorMessage = "Mã khách sạn không hợp lệ.";
        }

        if (errorMessage != null) {
    %>
    <div class="container mt-5">
        <div class="alert alert-danger text-center">
            <%=errorMessage%>
            <a href="QTVGiaoDien.jsp" class="btn btn-secondary mt-3">Quay lại</a>
        </div>
    </div>
    <%
            return;
        }
    %>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Sửa Khách Sạn</h2>

        <form action="QTVSuaKhachSanServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="maKhachSan" value="<%=khachSan.getMaKhachSan()%>">

            <div class="mb-3">
                <label for="tenKhachSan" class="form-label">Tên Khách Sạn</label>
                <input type="text" class="form-control" id="tenKhachSan" name="tenKhachSan" value="<%=khachSan.getTenKhachSan() != null ? khachSan.getTenKhachSan() : ""%>" required>
            </div>

            <div class="mb-3">
                <label for="diaChi" class="form-label">Địa Chỉ</label>
                <input type="text" class="form-control" id="diaChi" name="diaChi" value="<%=khachSan.getDiaChi() != null ? khachSan.getDiaChi() : ""%>" required>
            </div>

            <div class="mb-3">
                <label for="khuVuc" class="form-label">Khu Vực</label>
                <input type="text" class="form-control" id="khuVuc" name="khuVuc" value="<%=khachSan.getKhuVuc() != null ? khachSan.getKhuVuc() : ""%>" required>
            </div>

            <div class="mb-3">
                <label for="moTa" class="form-label">Mô Tả</label>
                <textarea class="form-control" id="moTa" name="moTa" rows="4"><%=khachSan.getMoTa() != null ? khachSan.getMoTa() : ""%></textarea>
            </div>

            <div class="mb-3">
                <label for="danhGiaTrungBinh" class="form-label">Đánh Giá Trung Bình</label>
                <input type="number" step="0.1" min="0" max="5" class="form-control" id="danhGiaTrungBinh" name="danhGiaTrungBinh" value="<%=khachSan.getDanhGiaTrungBinh()%>" required>
            </div>

            <div class="mb-3">
                <label for="maQuanLy" class="form-label">Mã Quản Lý</label>
                <select class="form-select" id="maQuanLy" name="maQuanLy" required>
                    <option value="">-- Chọn Quản Lý --</option>
                    <%
                        List<QuanLy> listQL = QuanLyDao.getInstance().layDanhSach();
                        for (QuanLy ql : listQL) {
                            String selected = ql.getMaQuanLy().equals(khachSan.getMaQuanLy()) ? "selected" : "";
                    %>
                    <option value="<%=ql.getMaQuanLy()%>" <%=selected%>>
                        <%=ql.getMaQuanLy()%> - <%=ql.getTenQuanLy()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="mb-3">
                <label for="hinhAnh" class="form-label">Hình Ảnh</label>
                <input type="file" class="form-control" id="hinhAnh" name="hinhAnh" accept="image/*">
                <small class="form-text text-muted">Hình ảnh hiện tại: <%=khachSan.getHinhAnh() != null ? khachSan.getHinhAnh() : "Không có"%></small>
                <% if (khachSan.getHinhAnh() != null && !khachSan.getHinhAnh().isEmpty()) { %>
                <img src="<%=request.getContextPath()%>/pic/KS/<%=khachSan.getHinhAnh()%>" alt="Hình ảnh khách sạn" class="mt-2" style="max-width: 200px; border-radius: 8px;">
                <% } %>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-primary">Cập nhật Khách Sạn</button>
                <a href="QTVGiaoDien.jsp" class="btn btn-secondary ms-2">Hủy</a>
            </div>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>