<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thống Kê Khách Sạn</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.0/dist/chart.umd.min.js"></script>
    <style>
        body {
            background-color: #f8f9fa;
            padding: 30px;
        }
        .card {
            border-radius: 8px;
            margin-bottom: 20px;
        }
        .chart-container {
            max-width: 600px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <h1 class="fw-bold">Thống Kê Khách Sạn</h1>
        <a href="QLXemPhong.jsp?maKhachSan=<%=request.getAttribute("maKhachSan")%>" class="btn btn-primary mb-4">Quay lại</a>

        <!-- Filter Form -->
        <div class="card shadow-sm mb-4">
            <div class="card-body">
                <h5 class="card-title">Lọc dữ liệu</h5>
                <form action="QLThongKeServlet" method="get" class="row g-3">
                    <input type="hidden" name="maKhachSan" value="<%=request.getAttribute("maKhachSan")%>">
                    <div class="col-md-4">
                        <label for="monthYear" class="form-label">Tháng-Năm</label>
                        <input type="month" class="form-control" id="monthYear" name="monthYear" value="<%=request.getAttribute("monthYear") != null ? request.getAttribute("monthYear") : ""%>">
                    </div>
                    <div class="col-md-4">
                        <label for="year" class="form-label">Năm</label>
                        <input type="number" class="form-control" id="year" name="year" min="2000" max="2100" value="<%=request.getAttribute("year") != null ? request.getAttribute("year") : ""%>">
                    </div>
                    <div class="col-md-4 align-self-end">
                        <button type="submit" class="btn btn-primary">Lọc</button>
                        <a href="QLThongKeServlet?maKhachSan=<%=request.getAttribute("maKhachSan")%>" class="btn btn-secondary">Xóa lọc</a>
                    </div>
                </form>
            </div>
        </div>

        <!-- Tổng quan -->
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Tổng quan</h5>
                <p><strong>Tổng số phòng:</strong> <%=request.getAttribute("soPhong")%></p>
                <p><strong>Tổng số đặt phòng:</strong> <%=request.getAttribute("soLuongDatPhong")%></p>
                <p><strong>Tổng doanh thu:</strong> <%=String.format("%,.0f", (Double)request.getAttribute("doanhThu"))%> VND</p>
            </div>
        </div>

        <!-- Thống kê đánh giá -->
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Thống kê đánh giá</h5>
                <p><strong>Tổng số đánh giá:</strong> <%=request.getAttribute("soLuongDanhGia")%></p>
                <p><strong>Điểm trung bình:</strong> <%=String.format("%.2f", (Double)request.getAttribute("diemTrungBinh"))%> sao</p>
                <h6>Phân bố đánh giá:</h6>
                <ul>
                    <%
                    Map<Integer, Integer> phanBoDanhGia = (Map<Integer, Integer>) request.getAttribute("phanBoDanhGia");
                    for (int i = 1; i <= 5; i++) {
                    %>
                        <li><strong><%=i%> sao:</strong> <%=phanBoDanhGia.get(i)%> đánh giá</li>
                    <%
                    }
                    %>
                </ul>
            </div>
        </div>

        <!-- Thống kê đánh giá theo tháng (nếu chọn năm) -->
        <% if (request.getAttribute("soLuongDanhGiaTheoThang") != null && request.getAttribute("diemTrungBinhTheoThang") != null) { %>
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Thống kê đánh giá theo tháng</h5>
                <ul>
                    <%
                    List<Object[]> soLuongDanhGiaTheoThang = (List<Object[]>) request.getAttribute("soLuongDanhGiaTheoThang");
                    List<Object[]> diemTrungBinhTheoThang = (List<Object[]>) request.getAttribute("diemTrungBinhTheoThang");
                    for (int i = 0; i < soLuongDanhGiaTheoThang.size(); i++) {
                        Object[] soLuongRow = soLuongDanhGiaTheoThang.get(i);
                        Object[] diemRow = diemTrungBinhTheoThang.get(i);
                    %>
                        <li><strong>Tháng <%=soLuongRow[0]%>:</strong> <%=soLuongRow[1]%> đánh giá, <%=String.format("%.2f", (Double)diemRow[1])%> sao</li>
                    <%
                    }
                    %>
                </ul>
            </div>
        </div>
        <% } %>

        <!-- Phân loại phòng -->
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Phân loại phòng</h5>
                <ul>
                    <%
                    Map<String, Integer> phongTheoLoai = (Map<String, Integer>) request.getAttribute("phongTheoLoai");
                    for (Map.Entry<String, Integer> entry : phongTheoLoai.entrySet()) {
                    %>
                        <li><strong><%=entry.getKey()%>:</strong> <%=entry.getValue()%> phòng</li>
                    <%
                    }
                    %>
                </ul>
            </div>
        </div>

        <!-- Doanh thu theo loại phòng -->
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Doanh thu theo loại phòng</h5>
                <ul>
                    <%
                    List<Object[]> doanhThuTheoLoai = (List<Object[]>) request.getAttribute("doanhThuTheoLoai");
                    if (doanhThuTheoLoai != null) {
                        for (Object[] row : doanhThuTheoLoai) {
                    %>
                        <li><strong><%=row[0]%>:</strong> <%=String.format("%,.0f", (Double)row[1])%> VND</li>
                    <%
                        }
                    }
                    %>
                </ul>
            </div>
        </div>

        <!-- Doanh thu và số lượng đặt phòng theo tháng (nếu chọn năm) -->
        <% if (request.getAttribute("doanhThuTheoThang") != null) { %>
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Doanh thu và số lượng đặt phòng theo tháng</h5>
                <ul>
                    <%
                    List<Object[]> doanhThuTheoThang = (List<Object[]>) request.getAttribute("doanhThuTheoThang");
                    List<Object[]> soLuongDatPhongTheoThang = (List<Object[]>) request.getAttribute("soLuongDatPhongTheoThang");
                    for (int i = 0; i < doanhThuTheoThang.size(); i++) {
                        Object[] doanhThuRow = doanhThuTheoThang.get(i);
                        Object[] soLuongRow = soLuongDatPhongTheoThang.get(i);
                    %>
                        <li><strong>Tháng <%=doanhThuRow[0]%>:</strong> <%=String.format("%,.0f", (Double)doanhThuRow[1])%> VND, <%=soLuongRow[1]%> đặt phòng</li>
                    <%
                    }
                    %>
                </ul>
            </div>
        </div>
        <% } %>

        <!-- Biểu đồ phân bố đánh giá -->
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Biểu đồ phân bố đánh giá</h5>
                <div class="chart-container">
                    <canvas id="reviewChart"></canvas>
                </div>
            </div>
        </div>

        <!-- Biểu đồ đánh giá theo tháng (nếu chọn năm) -->
        <% if (request.getAttribute("soLuongDanhGiaTheoThang") != null) { %>
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Biểu đồ so sánh đánh giá theo tháng</h5>
                <div class="chart-container">
                    <canvas id="monthlyReviewChart"></canvas>
                </div>
            </div>
        </div>
        <% } %>

        <!-- Biểu đồ phân bố phòng -->
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Biểu đồ phân bố phòng</h5>
                <div class="chart-container">
                    <canvas id="roomChart"></canvas>
                </div>
            </div>
        </div>

        <!-- Biểu đồ doanh thu theo tháng (nếu chọn năm) -->
        <% if (request.getAttribute("doanhThuTheoThang") != null) { %>
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Biểu đồ doanh thu theo tháng</h5>
                <div class="chart-container">
                    <canvas id="monthlyRevenueChart"></canvas>
                </div>
            </div>
        </div>
        <% } %>

        <!-- Biểu đồ số lượng đặt phòng theo tháng (nếu chọn năm) -->
        <% if (request.getAttribute("soLuongDatPhongTheoThang") != null) { %>
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Biểu đồ số lượng đặt phòng theo tháng</h5>
                <div class="chart-container">
                    <canvas id="monthlyBookingChart"></canvas>
                </div>
            </div>
        </div>
        <% } %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script>
        // Review Chart
        const reviewLabels = ['1 sao', '2 sao', '3 sao', '4 sao', '5 sao'];
        const reviewData = [<%=phanBoDanhGia.get(1)%>, <%=phanBoDanhGia.get(2)%>, <%=phanBoDanhGia.get(3)%>, <%=phanBoDanhGia.get(4)%>, <%=phanBoDanhGia.get(5)%>];

        const reviewCtx = document.getElementById('reviewChart').getContext('2d');
        new Chart(reviewCtx, {
            type: 'bar',
            data: {
                labels: reviewLabels,
                datasets: [{
                    label: 'Số lượng đánh giá',
                    data: reviewData,
                    backgroundColor: 'rgba(255, 99, 132, 0.6)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        title: { display: true, text: 'Số lượng đánh giá' }
                    },
                    x: {
                        title: { display: true, text: 'Số sao' }
                    }
                },
                plugins: {
                    legend: { display: true, position: 'top' }
                }
            }
        });

        // Monthly Review Chart (nếu chọn năm)
        <% if (request.getAttribute("soLuongDanhGiaTheoThang") != null) { %>
        const monthlyReviewLabels = [<% 
            List<Object[]> soLuongDanhGiaTheoThang = (List<Object[]>) request.getAttribute("soLuongDanhGiaTheoThang");
            for (Object[] row : soLuongDanhGiaTheoThang) { %>'Tháng <%=row[0]%>',<% } %>];
        const monthlyReviewData = [<% 
            for (Object[] row : soLuongDanhGiaTheoThang) { %><%=row[1]%>,<% } %>];
        const monthlyAverageRatingData = [<% 
            List<Object[]> diemTrungBinhTheoThang = (List<Object[]>) request.getAttribute("diemTrungBinhTheoThang");
            for (Object[] row : diemTrungBinhTheoThang) { %><%=row[1]%>,<% } %>];

        const monthlyReviewCtx = document.getElementById('monthlyReviewChart').getContext('2d');
        new Chart(monthlyReviewCtx, {
            type: 'bar',
            data: {
                labels: monthlyReviewLabels,
                datasets: [
                    {
                        label: 'Số lượng đánh giá',
                        data: monthlyReviewData,
                        backgroundColor: 'rgba(255, 99, 132, 0.6)',
                        borderColor: 'rgba(255, 99, 132, 1)',
                        borderWidth: 1
                    },
                    {
                        label: 'Điểm trung bình (sao)',
                        data: monthlyAverageRatingData,
                        backgroundColor: 'rgba(54, 162, 235, 0.6)',
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 1
                    }
                ]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        title: { display: true, text: 'Giá trị' }
                    },
                    x: {
                        title: { display: true, text: 'Tháng' }
                    }
                },
                plugins: {
                    legend: { display: true, position: 'top' }
                }
            }
        });
        <% } %>

        // Room Chart
        const roomLabels = [<% for (String key : phongTheoLoai.keySet()) { %>'<%=key%>',<% } %>];
        const roomData = [<% for (Integer value : phongTheoLoai.values()) { %><%=value%>,<% } %>];

        const roomCtx = document.getElementById('roomChart').getContext('2d');
        new Chart(roomCtx, {
            type: 'bar',
            data: {
                labels: roomLabels,
                datasets: [{
                    label: 'Số lượng phòng',
                    data: roomData,
                    backgroundColor: 'rgba(54, 162, 235, 0.6)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        title: { display: true, text: 'Số lượng phòng' }
                    },
                    x: {
                        title: { display: true, text: 'Loại phòng' }
                    }
                },
                plugins: {
                    legend: { display: true, position: 'top' }
                }
            }
        });

        // Monthly Revenue Chart (nếu chọn năm)
        <% if (request.getAttribute("doanhThuTheoThang") != null) { %>
        const monthlyRevenueLabels = [<% 
            List<Object[]> doanhThuTheoThang = (List<Object[]>) request.getAttribute("doanhThuTheoThang");
            for (Object[] row : doanhThuTheoThang) { %>'Tháng <%=row[0]%>',<% } %>];
        const monthlyRevenueData = [<% 
            for (Object[] row : doanhThuTheoThang) { %><%=row[1]%>,<% } %>];

        const monthlyRevenueCtx = document.getElementById('monthlyRevenueChart').getContext('2d');
        new Chart(monthlyRevenueCtx, {
            type: 'bar',
            data: {
                labels: monthlyRevenueLabels,
                datasets: [{
                    label: 'Doanh thu (VND)',
                    data: monthlyRevenueData,
                    backgroundColor: 'rgba(75, 192, 192, 0.6)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        title: { display: true, text: 'Doanh thu (VND)' }
                    },
                    x: {
                        title: { display: true, text: 'Tháng' }
                    }
                },
                plugins: {
                    legend: { display: true, position: 'top' }
                }
            }
        });
        <% } %>

        // Monthly Booking Chart (nếu chọn năm)
        <% if (request.getAttribute("soLuongDatPhongTheoThang") != null) { %>
        const monthlyBookingLabels = [<% 
            List<Object[]> soLuongDatPhongTheoThang = (List<Object[]>) request.getAttribute("soLuongDatPhongTheoThang");
            for (Object[] row : soLuongDatPhongTheoThang) { %>'Tháng <%=row[0]%>',<% } %>];
        const monthlyBookingData = [<% 
            for (Object[] row : soLuongDatPhongTheoThang) { %><%=row[1]%>,<% } %>];

        const monthlyBookingCtx = document.getElementById('monthlyBookingChart').getContext('2d');
        new Chart(monthlyBookingCtx, {
            type: 'bar',
            data: {
                labels: monthlyBookingLabels,
                datasets: [{
                    label: 'Số lượng đặt phòng',
                    data: monthlyBookingData,
                    backgroundColor: 'rgba(153, 102, 255, 0.6)',
                    borderColor: 'rgba(153, 102, 255, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        title: { display: true, text: 'Số lượng đặt phòng' }
                    },
                    x: {
                        title: { display: true, text: 'Tháng' }
                    }
                },
                plugins: {
                    legend: { display: true, position: 'top' }
                }
            }
        });
        <% } %>
    </script>
</body>
</html>