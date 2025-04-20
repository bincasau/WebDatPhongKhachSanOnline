<%@ page import="java.util.*, model.KhachSan, Dao.KhachSanDao, model.QuanLy, Dao.QuanLyDao, model.KhachHang, Dao.KhachHangDao, model.QuanTriVien" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    QuanTriVien qtv = (QuanTriVien) session.getAttribute("quanTriVien");
    if (qtv == null) {
        response.sendRedirect("DangNhap.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Quản lý Hệ thống</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
body {
    background-color: #f8f9fa;
    font-family: 'Segoe UI', sans-serif;
    margin: 30px;
}
h1, h2 {
    color: #343a40;
}
.table thead th {
    background-color: #dee2e6;
}
.hotel-img {
    width: 120px;
    height: 80px;
    object-fit: cover;
    border-radius: 8px;
}
.button {
    padding: 8px 15px;
    background-color: #0d6efd;
    color: white;
    border-radius: 5px;
    text-decoration: none;
}
.button:hover {
    background-color: #0b5ed7;
}
.action-buttons a {
    margin: 0 5px;
}
</style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-custom">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Trang Quản lý</a>
    <div class="d-flex ms-auto">
      <span class="navbar-text me-3">
        Xin chào, <strong><%= qtv.getHoTen() %></strong>
      </span>
      <a class="btn btn-danger btn-sm" href="DangXuatServlet">Đăng xuất</a>
    </div>
  </div>
</nav>

<!-- Tabs -->
<ul class="nav nav-tabs mb-4" id="systemTab" role="tablist">
    <li class="nav-item">
        <button class="nav-link active" id="hotel-tab" data-bs-toggle="tab" data-bs-target="#hotel" type="button" role="tab">Khách sạn</button>
    </li>
    <li class="nav-item">
        <button class="nav-link" id="manager-tab" data-bs-toggle="tab" data-bs-target="#manager" type="button" role="tab">Quản lý</button>
    </li>
    <li class="nav-item">
        <button class="nav-link" id="customer-tab" data-bs-toggle="tab" data-bs-target="#customer" type="button" role="tab">Khách hàng</button>
    </li>
</ul>

<div class="tab-content" id="systemTabContent">

    <!-- Khách sạn -->
    <div class="tab-pane fade show active" id="hotel" role="tabpanel" aria-labelledby="hotel-tab">
        <%
        List<KhachSan> dsKhachSan = KhachSanDao.getInstance().layDanhSach();
        %>

        <a href="addHotel.jsp" class="button mb-3 d-inline-block">+ Thêm Khách sạn</a>

        <div class="mb-3">
            <input type="text" id="searchHotel" class="form-control" placeholder="Tìm kiếm Khách sạn theo tên...">
        </div>

        <% if (dsKhachSan != null && !dsKhachSan.isEmpty()) { %>
        <div class="table-responsive">
        <table class="table table-hover table-striped align-middle text-center">
            <thead>
                <tr>
                    <th>Mã</th>
                    <th>Ảnh</th>
                    <th>Tên</th>
                    <th>Địa chỉ</th>
                    <th>Khu vực</th>
                    <th>Mô tả</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody id="hotelTable">
                <% for (KhachSan ks : dsKhachSan) { %>
                <tr>
                    <td><%= ks.getMaKhachSan() %></td>
                    <td><img src="<%=request.getContextPath()%>/pic/KS/<%=ks.getHinhAnh()%>" alt="Ảnh" class="hotel-img"></td>
                    <td><%= ks.getTenKhachSan() %></td>
                    <td><%= ks.getDiaChi() %></td>
                    <td><%= ks.getKhuVuc() %></td>
                    <td><%= ks.getMoTa() %></td>
                    <td class="action-buttons">
                        <a href="editHotel.jsp?id=<%=ks.getMaKhachSan()%>" class="btn btn-sm btn-warning">Sửa</a>
                        <a href="deleteHotel.jsp?id=<%=ks.getMaKhachSan()%>" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa khách sạn này?');">Xóa</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        </div>
        <% } else { %>
        <div class="text-center text-muted">Chưa có Khách sạn nào!</div>
        <% } %>
    </div>

    <!-- Quản lý -->
    <div class="tab-pane fade" id="manager" role="tabpanel" aria-labelledby="manager-tab">
        <%
        List<QuanLy> dsQuanLy = QuanLyDao.getInstance().layDanhSach();
        %>

        <a href="addManager.jsp" class="button mb-3 d-inline-block">+ Thêm Quản lý</a>

        <div class="mb-3">
            <input type="text" id="searchManager" class="form-control" placeholder="Tìm kiếm Quản lý theo tên...">
        </div>

        <% if (dsQuanLy != null && !dsQuanLy.isEmpty()) { %>
        <div class="table-responsive">
        <table class="table table-hover table-striped align-middle text-center">
            <thead>
                <tr>
                    <th>Mã</th>
                    <th>Tên</th>
                    <th>Điện thoại</th>
                    <th>Email</th>
                    <th>Tài khoản</th>
                    <th>Ngày sinh</th>
                    <th>Giới tính</th>
                    <th>CCCD</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody id="managerTable">
                <% for (QuanLy ql : dsQuanLy) { %>
                <tr>
                    <td><%= ql.getMaQuanLy() %></td>
                    <td><%= ql.getTenQuanLy() %></td>
                    <td><%= ql.getSoDienThoai() %></td>
                    <td><%= ql.getEmail() %></td>
                    <td><%= ql.getTaiKhoan() %></td>
                    <td><%= ql.getNgaySinh() %></td>
                    <td><%= ql.isGioiTinh() ? "Nam" : "Nữ" %></td>
                    <td><%= ql.getSoCCCD() %></td>
                    <td class="action-buttons">
                        <a href="editManager.jsp?id=<%=ql.getMaQuanLy()%>" class="btn btn-sm btn-primary">Sửa</a>
                        <a href="deleteManager.jsp?id=<%=ql.getMaQuanLy()%>" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa quản lý này?');">Xóa</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        </div>
        <% } else { %>
        <div class="text-center text-muted">Chưa có Quản lý nào!</div>
        <% } %>
    </div>

    <!-- Khách hàng -->
    <div class="tab-pane fade" id="customer" role="tabpanel" aria-labelledby="customer-tab">
        <%
        List<KhachHang> dsKhachHang = KhachHangDao.getInstance().layDanhSach();
        %>

        <a href="addCustomer.jsp" class="button mb-3 d-inline-block">+ Thêm Khách hàng</a>

        <div class="mb-3">
            <input type="text" id="searchCustomer" class="form-control" placeholder="Tìm kiếm Khách hàng theo tên...">
        </div>

        <% if (dsKhachHang != null && !dsKhachHang.isEmpty()) { %>
        <div class="table-responsive">
        <table class="table table-hover table-striped align-middle text-center">
            <thead>
                <tr>
                    <th>Mã</th>
                    <th>Tên</th>
                    <th>Tài khoản</th>
                    <th>Điện thoại</th>
                    <th>Ngày sinh</th>
                    <th>Giới tính</th>
                    <th>CCCD</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody id="customerTable">
                <% for (KhachHang kh : dsKhachHang) { %>
                <tr>
                    <td><%= kh.getMaKhachHang() %></td>
                    <td><%= kh.getTenKhachHang() %></td>
                    <td><%= kh.getTaiKhoan() %></td>
                    <td><%= kh.getSoDienThoai() %></td>
                    <td><%= kh.getNgaySinh() %></td>
                    <td><%= kh.isGioiTinh() ? "Nam" : "Nữ" %></td>
                    <td><%= kh.getSoCCCD() %></td>
                    <td class="action-buttons">
                        <a href="editCustomer.jsp?id=<%=kh.getMaKhachHang()%>" class="btn btn-sm btn-warning">Sửa</a>
                        <a href="deleteCustomer.jsp?id=<%=kh.getMaKhachHang()%>" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa khách hàng này?');">Xóa</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        </div>
        <% } else { %>
        <div class="text-center text-muted">Chưa có Khách hàng nào!</div>
        <% } %>
    </div>

</div>

<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- Search chỉ theo tên -->
<script>
function searchTableByName(inputId, tableId, nameColIndex) {
    var input = document.getElementById(inputId);
    input.addEventListener("keyup", function() {
        var filter = input.value.toUpperCase();
        var rows = document.getElementById(tableId).getElementsByTagName("tr");
        for (var i = 0; i < rows.length; i++) {
            var cols = rows[i].getElementsByTagName("td");
            if (cols.length > nameColIndex) {
                var nameCell = cols[nameColIndex];
                if (nameCell.innerText.toUpperCase().indexOf(filter) > -1) {
                    rows[i].style.display = "";
                } else {
                    rows[i].style.display = "none";
                }
            }
        }
    });
}

// Gọi hàm cho từng bảng
searchTableByName("searchHotel", "hotelTable", 2);    // Tên khách sạn ở cột 2
searchTableByName("searchManager", "managerTable", 1); // Tên quản lý ở cột 1
searchTableByName("searchCustomer", "customerTable", 1); // Tên khách hàng ở cột 1
</script>

</body>
</html>
