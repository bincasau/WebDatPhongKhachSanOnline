<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.ThongTinDatPhong" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.text.DecimalFormat" %> <!-- Import DecimalFormat để định dạng số -->
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Lịch sử đặt phòng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
            transform: translateY(-10px); /* Hiệu ứng khi di chuột */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Tạo bóng cho thẻ */
        }
        .gray-bg { background-color: #f0f0f0; } /* Đã trả phòng */
        .green-bg { background-color: #d4edda; } /* Phòng chưa trả - xanh lá */
        .orange-bg { background-color: #ffcc99; } /* Phòng đang sử dụng - cam */
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
            width: 25px;
            height: 25px;
            margin-right: 10px;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <h3 class="section-title text-center">Lịch sử đặt phòng của bạn</h3>

    <!-- Chú thích màu sắc -->
    <div class="color-legend mb-4">
        <div><strong>Màu sắc chú thích:</strong></div>
        <ul>
            <li><span class="badge" style="background-color: #d4edda;">&nbsp;&nbsp;&nbsp;</span> Phòng chưa trả (Ngày trả phòng chưa đến) - <strong>Xanh lá</strong></li>
            <li><span class="badge" style="background-color: #f0f0f0;">&nbsp;&nbsp;&nbsp;</span> Phòng đã trả (Ngày trả phòng đã qua) - <strong>Xám</strong></li>
            <li><span class="badge" style="background-color: #ffcc99;">&nbsp;&nbsp;&nbsp;</span> Phòng đang sử dụng (Ngày hiện tại giữa ngày nhận và trả) - <strong>Cam</strong></li>
        </ul>
    </div>

    <%
        List<ThongTinDatPhong> danhSach = (List<ThongTinDatPhong>) request.getAttribute("lichSuDatPhong");
        if (danhSach == null || danhSach.isEmpty()) {
    %>
        <div class="alert alert-warning text-center">Bạn chưa có lịch sử đặt phòng nào.</div>
    <%
        } else {
            for (int i = danhSach.size() - 1; i >= 0; i--) {  // Hiển thị từ mới nhất
                ThongTinDatPhong dp = danhSach.get(i);
                
                // Lấy ngày trả phòng và ngày hiện tại
                LocalDate ngayTraPhong = dp.getNgayTraPhong().toLocalDate();
                LocalDate ngayNhanPhong = dp.getNgayNhanPhong().toLocalDate();
                LocalDate currentDate = LocalDate.now();

                // Xác định màu nền
                String bgClass = "";
                if (ngayTraPhong.isBefore(currentDate)) {
                    bgClass = "gray-bg"; // Ngày trả phòng đã qua
                } else if (currentDate.isBefore(ngayNhanPhong)) {
                    bgClass = "green-bg"; // Phòng chưa trả, ngày trả chưa đến
                } else {
                    bgClass = "orange-bg"; // Phòng đang sử dụng (giữa ngày nhận và trả)
                }

                // Định dạng số thành tiền
                DecimalFormat formatter = new DecimalFormat("#,###");  // Định dạng số với dấu phẩy
                String formattedThanhTien = formatter.format(dp.getThanhTien());  // Định dạng số tiền
    %>

    <div class="row mb-4">
        <div class="col-md-12">
            <div class="card <%= bgClass %>">
                <div class="card-header">
                    <h5 class="card-title text-center">Mã đặt phòng: <%= dp.getMaDatPhong() %></h5>
                </div>
                <div class="card-body">
                    <div class="d-flex align-items-center mb-3">
                        <img src="https://img.icons8.com/ios/50/000000/bed.png" class="status-icon" alt="Phòng"/>
                        <p><strong>Phòng:</strong> <%= dp.getMaPhong() %></p>
                    </div>

                    <div class="d-flex align-items-center mb-3">
                        <img src="https://img.icons8.com/ios/50/000000/calendar.png" class="status-icon" alt="Ngày đặt"/>
                        <p><strong>Ngày đặt:</strong> <%= dp.getNgayDatPhong() %></p>
                    </div>

                    <div class="d-flex align-items-center mb-3">
                        <img src="https://img.icons8.com/ios/50/000000/clock.png" class="status-icon" alt="Nhận phòng"/>
                        <p><strong>Nhận phòng:</strong> <%= dp.getNgayNhanPhong() %> <strong>—</strong> <strong>Trả phòng:</strong> <%= dp.getNgayTraPhong() %></p>
                    </div>

                    <div class="mb-3">
                        <p><strong>Thời gian sử dụng:</strong> <%= dp.getTgSuDung() %> giờ</p>
                        <p><strong>Ghi chú:</strong> <%= dp.getGhiChu() == null ? "Không có" : dp.getGhiChu() %></p>
                    </div>

                    <div class="d-flex justify-content-between">
                        <p><strong>Thành tiền:</strong> <%= formattedThanhTien %> VND</p>
                        <span class="badge bg-info">Trạng thái: <%= dp.getTrangThai() %></span>
                    </div>
                </div>
                <div class="card-footer text-center">
                    <a href="#" class="btn btn-primary">Đánh giá</a>
                </div>
            </div>
        </div>
    </div>

    <%
            }
        }
    %>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
