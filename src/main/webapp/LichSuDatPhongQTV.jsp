<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.ThongTinDatPhong"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.DecimalFormat"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lịch sử đặt phòng (Admin)</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4 text-center">Lịch sử đặt phòng (Quản trị viên)</h2>
    <a href="QTVGiaoDien.jsp" class="btn btn-secondary mb-3">← Quay lại bảng điều khiển</a>

    <%
        List<ThongTinDatPhong> lichSu = (List<ThongTinDatPhong>) request.getAttribute("lichSuDatPhong");
        if (lichSu == null || lichSu.isEmpty()) {
    %>
        <div class="alert alert-warning text-center">Chưa có lịch sử đặt phòng nào.</div>
    <%
        } else {
            DecimalFormat formatter = new DecimalFormat("#,###");
    %>
        <table class="table table-bordered table-hover">
            <thead class="table-dark">
                <tr>
                    <th>Mã ĐP</th>
                    <th>Khách hàng</th>
                    <th>Khách sạn</th>
                    <th>Phòng</th>
                    <th>Ngày đặt</th>
                    <th>Nhận - Trả</th>
                    <th>Thành tiền (VND)</th>
                    <th>Trạng thái</th>
                </tr>
            </thead>
            <tbody>
            <% for (ThongTinDatPhong dp : lichSu) { %>
                <tr>
                    <td><%= dp.getMaDatPhong() %></td>
                    <td><%= dp.getMaKhachHang() %></td>
                    <td><%= dp.getMaKhachSan() %></td>
                    <td><%= dp.getMaPhong() %></td>
                    <td><%= dp.getNgayDatPhong() %></td>
                    <td><%= dp.getNgayNhanPhong() %> - <%= dp.getNgayTraPhong() %></td>
                    <td><%= formatter.format(dp.getThanhTien()) %></td>
                    <td><%= dp.getTrangThai() %></td>
                </tr>
            <% } %>
            </tbody>
        </table>
    <%
        }
    %>
</div>
</body>
</html>
