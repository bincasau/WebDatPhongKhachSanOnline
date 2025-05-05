<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thống Kê Khách Sạn</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- Chart.js -->
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

        <!-- Tổng quan -->
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Tổng quan</h5>
                <p><strong>Tổng số phòng:</strong> <%=request.getAttribute("soPhong")%></p>
                <p><strong>Tổng doanh thu:</strong> <%=String.format("%,.0f", (Double)request.getAttribute("doanhThu"))%> VND</p>
                <p><strong>Tổng số đánh giá:</strong> <%=request.getAttribute("soLuongDanhGia")%></p>
                <p><strong>Điểm đánh giá trung bình:</strong> <%=String.format("%.2f", (Double)request.getAttribute("diemTrungBinh"))%> sao</p>
            </div>
        </div>

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
                    for (Object[] row : doanhThuTheoLoai) {
                    %>
                        <li><strong><%=row[0]%>:</strong> <%=String.format("%,.0f", (Double)row[1])%> VND</li>
                    <%
                    }
                    %>
                </ul>
            </div>
        </div>

        <!-- Phân bố đánh giá -->
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Phân bố đánh giá</h5>
                <ul>
                    <%
                    Map<Integer, Integer> phanBoDanhGia = (Map<Integer, Integer>) request.getAttribute("phanBoDanhGia");
                    for (int i = 1; i <= 5; i++) {
                    %>
                        <li><strong><%=i%> sao:</strong> <%=phanBoDanhGia.getOrDefault(i, 0)%> đánh giá</li>
                    <%
                    }
                    %>
                </ul>
            </div>
        </div>

        <!-- Biểu đồ phân bố phòng -->
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Biểu đồ phân bố phòng</h5>
                <div class="chart-container">
                    <canvas id="roomChart"></canvas>
                </div>
            </div>
        </div>

        <!-- Biểu đồ phân bố đánh giá -->
        <div class="card shadow-sm">
            <div class="card-body">
                <h5 class="card-title">Biểu đồ phân bố đánh giá</h5>
                <div class="chart-container">
                    <canvas id="reviewChart"></canvas>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script>
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
                        title: {
                            display: true,
                            text: 'Số lượng phòng'
                        }
                    },
                    x: {
                        title: {
                            display: true,
                            text: 'Loại phòng'
                        }
                    }
                },
                plugins: {
                    legend: {
                        display: true,
                        position: 'top'
                    }
                }
            }
        });

        // Review Chart
        const reviewLabels = ['1 sao', '2 sao', '3 sao', '4 sao', '5 sao'];
        const reviewData = [<% for (int i = 1; i <= 5; i++) { %><%=phanBoDanhGia.getOrDefault(i, 0)%>,<% } %>];

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
                        title: {
                            display: true,
                            text: 'Số lượng đánh giá'
                        }
                    },
                    x: {
                        title: {
                            display: true,
                            text: 'Số sao'
                        }
                    }
                },
                plugins: {
                    legend: {
                        display: true,
                        position: 'top'
                    }
                }
            }
        });
    </script>
</body>
</html>