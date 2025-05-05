<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ThongTinDatPhong" %>
<%@ page import="Dao.ThongTinDatPhongDao" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>  <%-- ⭐ Changed --%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh Sách Đặt Phòng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <style>
        body { background-color: #f8f9fa; padding: 30px; }
        .table-responsive { margin-top: 20px; }
        .filter-form { background-color: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); margin-bottom: 20px; }
        .pagination { justify-content: center; }
        .status-chua-nhan { color: red; font-weight: bold; }
        .status-da-tra { color: green; font-weight: bold; }
        .status-dang-su-dung { color: #ffc107; font-weight: bold; }
    </style>
</head>
<body>
<%
    String maKhachSan = request.getParameter("maKhachSan");
    String ngayDat = request.getParameter("ngayDat");
    String maKhachHang = request.getParameter("maKhachHang");
    String trangThaiPhong = request.getParameter("trangThaiPhong");
    String pageStr = request.getParameter("page");
    int currentPage = (pageStr != null) ? Integer.parseInt(pageStr) : 1;
    int pageSize = 100;

    List<ThongTinDatPhong> dsDatPhong = new ArrayList<>();
    int totalRecords = 0;
    ThongTinDatPhongDao dao = ThongTinDatPhongDao.getInstance();
    LocalDate today = LocalDate.now();

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // ⭐ Changed

    if (maKhachSan != null) {
        dsDatPhong = dao.layDanhSachTheoMaKhachSan(maKhachSan);
        List<ThongTinDatPhong> filteredList = new ArrayList<>();

        // Filter by ngayDat
        if (ngayDat != null && !ngayDat.isEmpty() && ngayDat.matches("\\d{4}-\\d{2}-\\d{2}")) {
            LocalDate filterDate = LocalDate.parse(ngayDat);
            for (ThongTinDatPhong dp : dsDatPhong) {
                if (dp.getNgayDatPhong() != null && dp.getNgayDatPhong().toLocalDate().isEqual(filterDate)) {
                    filteredList.add(dp);
                }
            }
        } else {
            filteredList.addAll(dsDatPhong);
        }

        // Filter by maKhachHang
        dsDatPhong = new ArrayList<>(filteredList);
        filteredList.clear();
        if (maKhachHang != null && !maKhachHang.isEmpty()) {
            for (ThongTinDatPhong dp : dsDatPhong) {
                if (dp.getMaKhachHang() != null && dp.getMaKhachHang().toLowerCase().contains(maKhachHang.toLowerCase())) {
                    filteredList.add(dp);
                }
            }
        } else {
            filteredList.addAll(dsDatPhong);
        }

        // Filter by trangThaiPhong
        dsDatPhong = new ArrayList<>(filteredList);
        filteredList.clear();
        if (trangThaiPhong != null && !trangThaiPhong.isEmpty() && !trangThaiPhong.equals("TatCa")) {
            for (ThongTinDatPhong dp : dsDatPhong) {
                if (dp.getNgayNhanPhong() != null && dp.getNgayTraPhong() != null) {
                    LocalDate checkInDate = dp.getNgayNhanPhong().toLocalDate();
                    LocalDate checkOutDate = dp.getNgayTraPhong().toLocalDate();
                    String status = "";
                    if (today.isBefore(checkInDate)) {
                        status = "Chưa nhận";
                    } else if (today.isAfter(checkOutDate)) {
                        status = "Đã trả";
                    } else {
                        status = "Đang sử dụng";
                    }
                    if (status.equals(trangThaiPhong)) {
                        filteredList.add(dp);
                    }
                }
            }
        } else {
            filteredList.addAll(dsDatPhong);
        }

        dsDatPhong = filteredList;
        totalRecords = dsDatPhong.size();

        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, dsDatPhong.size());
        dsDatPhong = dsDatPhong.subList(start, end);
    }
    int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
%>

<div class="container">
    <h1 class="fw-bold mb-4">Danh Sách Đặt Phòng</h1>
    <a href="QLXemPhong.jsp?maKhachSan=<%=maKhachSan%>" class="btn btn-primary mb-3">Quay lại</a>

    <!-- Filter Form -->
    <div class="filter-form">
        <form action="QLXemDanhSachDatPhong.jsp" method="get">
            <input type="hidden" name="maKhachSan" value="<%=maKhachSan%>">
            <div class="row">
                <div class="col-md-3 mb-3">
                    <label for="ngayDat" class="form-label">Lọc theo ngày đặt</label>
                    <input type="date" class="form-control" id="ngayDat" name="ngayDat" value="<%=ngayDat != null ? ngayDat : ""%>">
                </div>
                <div class="col-md-3 mb-3">
                    <label for="maKhachHang" class="form-label">Lọc theo mã khách hàng</label>
                    <input type="text" class="form-control" id="maKhachHang" name="maKhachHang" value="<%=maKhachHang != null ? maKhachHang : ""%>" placeholder="Nhập mã khách hàng">
                </div>
                <div class="col-md-3 mb-3">
                    <label for="trangThaiPhong" class="form-label">Lọc theo tình trạng phòng</label>
                    <select class="form-control" id="trangThaiPhong" name="trangThaiPhong">
                        <option value="TatCa" <%= "TatCa".equals(trangThaiPhong) || trangThaiPhong == null ? "selected" : "" %>>Tất cả</option>
                        <option value="Chưa nhận" <%= "Chưa nhận".equals(trangThaiPhong) ? "selected" : "" %>>Chưa nhận</option>
                        <option value="Đang sử dụng" <%= "Đang sử dụng".equals(trangThaiPhong) ? "selected" : "" %>>Đang sử dụng</option>
                        <option value="Đã trả" <%= "Đã trả".equals(trangThaiPhong) ? "selected" : "" %>>Đã trả</option>
                    </select>
                </div>
                <div class="col-md-3 d-flex align-items-end mb-3">
                    <button type="submit" class="btn btn-primary">Lọc</button>
                    <a href="QLXemDanhSachDatPhong.jsp?maKhachSan=<%=maKhachSan%>" class="btn btn-secondary ms-2">Xóa bộ lọc</a>
                </div>
            </div>
        </form>
    </div>

    <!-- Booking Table -->
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>Mã Đặt Phòng</th>
                    <th>Ngày Đặt</th>
                    <th>Ngày Nhận</th>
                    <th>Ngày Trả</th>
                    <th>Mã Khách Hàng</th>
                    <th>Mã Phòng</th>
                    <th>Thành Tiền</th>
                    <th>Trạng Thái</th>
                    <th>Ghi Chú</th>
                    <th>Tình Trạng Phòng</th>
                </tr>
            </thead>
            <tbody>
                <%
                if (dsDatPhong != null && !dsDatPhong.isEmpty()) {
                    for (ThongTinDatPhong dp : dsDatPhong) {
                        String roomStatus = "N/A";
                        String statusClass = "";
                        if (dp.getNgayNhanPhong() != null && dp.getNgayTraPhong() != null) {
                            LocalDate checkInDate = dp.getNgayNhanPhong().toLocalDate();
                            LocalDate checkOutDate = dp.getNgayTraPhong().toLocalDate();
                            if (today.isBefore(checkInDate)) {
                                roomStatus = "Chưa nhận";
                                statusClass = "status-chua-nhan";
                            } else if (today.isAfter(checkOutDate)) {
                                roomStatus = "Đã trả";
                                statusClass = "status-da-tra";
                            } else {
                                roomStatus = "Đang sử dụng";
                                statusClass = "status-dang-su-dung";
                            }
                        }
                %>
                <tr>
                    <td><%=dp.getMaDatPhong()%></td>
                    <td><%=dp.getNgayDatPhong() != null ? dp.getNgayDatPhong().toLocalDate().format(dateFormatter) : "N/A"%></td> <%-- ⭐ Changed --%>
                    <td><%=dp.getNgayNhanPhong() != null ? dp.getNgayNhanPhong().toLocalDate().format(dateFormatter) : "N/A"%></td> <%-- ⭐ Changed --%>
                    <td><%=dp.getNgayTraPhong() != null ? dp.getNgayTraPhong().toLocalDate().format(dateFormatter) : "N/A"%></td> <%-- ⭐ Changed --%>
                    <td><%=dp.getMaKhachHang()%></td>
                    <td><%=dp.getMaPhong()%></td>
                    <td><%=String.format("%,.0f", dp.getThanhTien())%> VND</td>
                    <td><%=dp.getTrangThai() != null ? dp.getTrangThai() : "N/A"%></td>
                    <td><%=dp.getGhiChu() != null ? dp.getGhiChu() : "N/A"%></td>
                    <td class="<%=statusClass%>"><%=roomStatus%></td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="10" class="text-center">Không có dữ liệu đặt phòng.</td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
    </div>

    <!-- Pagination -->
    <%
    if (totalPages > 1) {
    %>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li class="page-item <%=currentPage == 1 ? "disabled" : ""%>">
                <a class="page-link" href="QLXemDanhSachDatPhong.jsp?maKhachSan=<%=maKhachSan%>&page=<%=currentPage - 1%><%=ngayDat != null ? "&ngayDat=" + ngayDat : ""%><%=maKhachHang != null ? "&maKhachHang=" + maKhachHang : ""%><%=trangThaiPhong != null && !trangThaiPhong.equals("TatCa") ? "&trangThaiPhong=" + trangThaiPhong : ""%>">Trước</a>
            </li>
            <%
            for (int i = 1; i <= totalPages; i++) {
            %>
            <li class="page-item <%=currentPage == i ? "active" : ""%>">
                <a class="page-link" href="QLXemDanhSachDatPhong.jsp?maKhachSan=<%=maKhachSan%>&page=<%=i%><%=ngayDat != null ? "&ngayDat=" + ngayDat : ""%><%=maKhachHang != null ? "&maKhachHang=" + maKhachHang : ""%><%=trangThaiPhong != null && !trangThaiPhong.equals("TatCa") ? "&trangThaiPhong=" + trangThaiPhong : ""%>"><%=i%></a>
            </li>
            <%
            }
            %>
            <li class="page-item <%=currentPage == totalPages ? "disabled" : ""%>">
                <a class="page-link" href="QLXemDanhSachDatPhong.jsp?maKhachSan=<%=maKhachSan%>&page=<%=currentPage + 1%><%=ngayDat != null ? "&ngayDat=" + ngayDat : ""%><%=maKhachHang != null ? "&maKhachHang=" + maKhachHang : ""%><%=trangThaiPhong != null && !trangThaiPhong.equals("TatCa") ? "&trangThaiPhong=" + trangThaiPhong : ""%>">Sau</a>
            </li>
        </ul>
    </nav>
    <%
    }
    %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
