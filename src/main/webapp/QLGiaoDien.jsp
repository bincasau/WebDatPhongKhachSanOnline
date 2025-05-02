<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.KhachSan" %>
<%@ page import="model.QuanLy" %>
<%@ page import="Dao.KhachSanDao" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Khách Sạn</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .navbar {
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .hotel-img {
            height: 200px;
            object-fit: cover;
        }
        .container {
            margin-top: 30px;
        }
        .welcome-message {
            margin-bottom: 30px;
        }
        .card {
            transition: transform 0.2s;
        }
        .card:hover {
            transform: translateY(-5px);
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Quản Lý Khách Sạn</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <%
                            QuanLy quanLy = (QuanLy) session.getAttribute("quanLy");
                            String tenQuanLy = (quanLy != null) ? quanLy.getTenQuanLy() : "Quản Lý";
                        %>
                        <span class="nav-link">Xin chào, <%=tenQuanLy%></span>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="DangXuatServlet">Đăng Xuất</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container">
        <h2 class="welcome-message text-center">Danh Sách Khách Sạn Quản Lý Bởi <%=tenQuanLy%></h2>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <%
                if (quanLy != null) {
                    KhachSanDao ksDao = new KhachSanDao();
                    List<KhachSan> danhSachKS = ksDao.layDanhSachTheoMaQL(quanLy.getMaQuanLy());
                    if (danhSachKS != null && !danhSachKS.isEmpty()) {
                        for (KhachSan ks : danhSachKS) {
            %>
            <div class="col">
                <div class="card shadow-sm">
                    <img src="<%=request.getContextPath()%>/pic/KS/<%=ks.getHinhAnh()%>" class="card-img-top hotel-img" alt="Hình ảnh khách sạn">
                    <div class="card-body">
                        <h5 class="card-title fw-bold"><%=ks.getTenKhachSan()%></h5>
                        <p class="card-text"><i class="bi bi-geo-alt"></i> <%=ks.getDiaChi()%></p>
                        <p class="card-text"><strong>Khu vực:</strong> <%=ks.getKhuVuc()%></p>
                        <p class="card-text"><%=ks.getMoTa()%></p>
                        <div class="d-flex justify-content-between align-items-center">
                            <p class="card-text text-warning mb-0">⭐ Đánh giá: <strong><%=ks.getDanhGiaTrungBinh()%></strong></p>
                            <a href="QLXemPhong.jsp?maKhachSan=<%=ks.getMaKhachSan()%>" class="btn btn-info btn-sm">Xem</a>
                        </div>
                    </div>
                </div>
            </div>
            <%
                        }
                    } else {
            %>
            <div class="col-12">
                <div class="alert alert-warning text-center" role="alert">
                    Hiện tại không có khách sạn nào thuộc quản lý của bạn.
                </div>
            </div>
            <%
                    }
                } else {
            %>
            <div class="col-12">
                <div class="alert alert-danger text-center" role="alert">
                    Vui lòng đăng nhập để xem danh sách khách sạn.
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>