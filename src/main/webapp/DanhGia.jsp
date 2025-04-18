<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đánh giá khách sạn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .star-rating {
            direction: rtl;
            display: inline-flex;
            font-size: 2rem;
        }
        .star-rating input[type="radio"] {
            display: none;
        }
        .star-rating label {
            color: #ddd;
            cursor: pointer;
        }
        .star-rating input[type="radio"]:checked ~ label {
            color: #ffc107;
        }
        .star-rating label:hover,
        .star-rating label:hover ~ label {
            color: #ffc107;
        }
    </style>
</head>
<body>

<%
    String tenKhachSan = request.getParameter("TenKhachSan") != null ? URLDecoder.decode(request.getParameter("TenKhachSan"), "UTF-8") : "";
    String maKhachSan = request.getParameter("maKhachSan");
    Object s = session.getAttribute("khachHang");
	KhachHang kh = (KhachHang) s;
	String maKhachHang = kh.getMaKhachHang();
%>

<div class="container mt-5">
    <h3 class="text-center mb-4">Đánh giá khách sạn</h3>

    <div class="card p-4">
        <h5 class="mb-3">Khách sạn: <strong><%= tenKhachSan %></strong></h5>

        <form action="XuLyDanhGiaServlet" method="post">
            <!-- Gửi kèm các thông tin cần thiết -->
            <input type="hidden" name="maKhachSan" value="<%= maKhachSan %>">
            <input type="hidden" name="maKhachHang" value="<%= maKhachHang %>">

            <div class="mb-4">
                <label class="form-label">Chọn số sao:</label>
                <div class="star-rating">
                    <input type="radio" id="5-stars" name="soSao" value="5" required><label for="5-stars">&#9733;</label>
                    <input type="radio" id="4-stars" name="soSao" value="4"><label for="4-stars">&#9733;</label>
                    <input type="radio" id="3-stars" name="soSao" value="3"><label for="3-stars">&#9733;</label>
                    <input type="radio" id="2-stars" name="soSao" value="2"><label for="2-stars">&#9733;</label>
                    <input type="radio" id="1-star"  name="soSao" value="1"><label for="1-star">&#9733;</label>
                </div>
            </div>

            <div class="mb-4">
                <label for="moTa" class="form-label">Mô tả chi tiết:</label>
                <textarea class="form-control" id="moTa" name="moTa" rows="5" placeholder="Viết cảm nhận của bạn..." required></textarea>
            </div>

            <div class="d-flex justify-content-between">
                <a href="LichSuDatPhongServlet" class="btn btn-secondary">← Quay về</a>
                <button type="submit" class="btn btn-success">Gửi đánh giá</button>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
