<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="model.KhachHang" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt Phòng</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<%
    KhachHang kh = (KhachHang) session.getAttribute("khachHang");
    String hoTen = (kh != null) ? kh.getTenKhachHang() : "";
    String soDienThoai = (kh != null) ? kh.getSoDienThoai() : "";

    double giaPhong = 0;
    Object giaPhongObj = request.getAttribute("giaPhong");
    if (giaPhongObj != null) {
        giaPhong = (Double) giaPhongObj;
    }

    String maPhong = "";
    Object maPhongObj = request.getAttribute("maPhong");
    if (maPhongObj != null) {
        maPhong = maPhongObj.toString();
    }

    String e_message = (request.getAttribute("e_message") != null) ? request.getAttribute("e_message").toString() : "";
%>

<div class="container mt-5">
    <p class="text-danger"><%=e_message %></p>
    <h2 class="text-center">Đặt Phòng</h2>

    <form id="datPhongForm" method="post" action="XacNhanDatPhongServlet">
        <input type="hidden" name="giaPhong" id="giaPhong" value="<%= giaPhong %>">

        <div class="mb-3">
            <label>Họ và tên:</label>
            <input type="text" class="form-control" name="hoTen" value="<%= hoTen %>" readonly>
        </div>

        <div class="mb-3">
            <label>Số điện thoại:</label>
            <input type="text" class="form-control" name="soDienThoai" value="<%= soDienThoai %>" readonly>
        </div>

        <div class="mb-3">
            <label>Ngày nhận phòng:</label>
            <input type="date" class="form-control" id="ngayNhan" name="ngayNhan" required>
            <select class="form-control mt-2" id="gioNhan" name="gioNhan" required>
                <option value="">Chọn giờ nhận</option>
                <option value="08:00">08:00 AM</option>
                <option value="10:00">10:00 AM</option>
                <option value="12:00">12:00 PM</option>
                <option value="14:00">02:00 PM</option>
                <option value="16:00">04:00 PM</option>
                <option value="18:00">06:00 PM</option>
            </select>
        </div>

        <div class="mb-3">
            <label>Ngày trả phòng:</label>
            <input type="date" class="form-control" id="ngayTra" name="ngayTra" required>
            <input type="hidden" id="gioTra" name="gioTra">
        </div>

        <div class="mb-3">
            <label>Thời gian sử dụng (ngày):</label>
            <input type="text" class="form-control" id="tgSuDung" name="tgSuDung" readonly>
        </div>

        <div class="mb-3">
            <label>Tổng tiền:</label>
            <input type="text" class="form-control" id="tongTien" name="tongTien" readonly>
            <input type="hidden" name="maPhong" value="<%= maPhong %>">
        </div>

        <div class="mb-3">
            <label>Ghi chú:</label>
            <textarea class="form-control" id="ghiChu" name="ghiChu" rows="3" placeholder="Nhập ghi chú (nếu có)..."></textarea>
        </div>

        <button type="button" class="btn btn-success w-100 mt-3" data-bs-toggle="modal" data-bs-target="#confirmModal">
            Thanh Toán
        </button>

        <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="confirmModalLabel">Xác nhận Thanh Toán</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <p><strong>Mã phòng:</strong> <%= maPhong %></p>
                        <p><strong>Họ tên:</strong> <span id="modalHoTen"></span></p>
                        <p><strong>SĐT:</strong> <span id="modalSoDienThoai"></span></p>
                        <p><strong>Thời gian nhận:</strong> <span id="modalNgayNhan"></span> <span id="modalGioNhan"></span></p>
                        <p><strong>Thời gian trả:</strong> <span id="modalNgayTra"></span> <span id="modalGioTra"></span></p>
                        <p><strong>Thời gian sử dụng:</strong> <span id="modalTgSuDung"></span> Ngày</p>
                        <p><strong>Tổng tiền:</strong> <span id="modalTongTien"></span> VND</p>
                        <p><strong>Ghi chú:</strong> <span id="modalGhiChu"></span></p>

                        <div class="text-center mt-4">
                            <h5>Quét mã QR để thanh toán</h5>
                            <img src="<%=request.getContextPath()%>/pic/qr.jpg" alt="QR Code Thanh Toán" class="img-fluid" style="max-width: 200px;">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <button type="submit" class="btn btn-primary">Xác Nhận</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<script>
    function calculateTimeAndCost() {
        let ngayNhan = document.getElementById('ngayNhan').value;
        let gioNhan = document.getElementById('gioNhan').value;
        let ngayTra = document.getElementById('ngayTra').value;

        if (ngayNhan && gioNhan && ngayTra) {
            let dateNhan = new Date(ngayNhan + 'T' + gioNhan);
            let dateTra = new Date(ngayTra + 'T' + gioNhan);

            if (dateTra > dateNhan) {
                let diffMs = dateTra - dateNhan;
                let tgSuDung = diffMs / (1000 * 60 * 60 * 24);
                let giaPhong = parseFloat(document.getElementById('giaPhong').value);
                let tongTien = tgSuDung * giaPhong;

                document.getElementById('tgSuDung').value = tgSuDung.toFixed(0);
                document.getElementById('tongTien').value = tongTien.toLocaleString();
                document.getElementById('gioTra').value = gioNhan;
            } else {
                alert("Ngày trả phải lớn hơn ngày nhận!");
                document.getElementById('ngayTra').value = "";
                document.getElementById('tgSuDung').value = "";
                document.getElementById('tongTien').value = "";
            }
        }
    }

    document.getElementById('ngayNhan').addEventListener('change', calculateTimeAndCost);
    document.getElementById('gioNhan').addEventListener('change', calculateTimeAndCost);
    document.getElementById('ngayTra').addEventListener('change', calculateTimeAndCost);

    document.getElementById('confirmModal').addEventListener('show.bs.modal', function () {
        document.getElementById('modalHoTen').innerText = document.getElementsByName('hoTen')[0].value;
        document.getElementById('modalSoDienThoai').innerText = document.getElementsByName('soDienThoai')[0].value;
        document.getElementById('modalNgayNhan').innerText = document.getElementById('ngayNhan').value;
        document.getElementById('modalGioNhan').innerText = document.getElementById('gioNhan').value;
        document.getElementById('modalNgayTra').innerText = document.getElementById('ngayTra').value;
        document.getElementById('modalGioTra').innerText = document.getElementById('gioNhan').value;
        document.getElementById('modalTgSuDung').innerText = document.getElementById('tgSuDung').value;
        document.getElementById('modalTongTien').innerText = document.getElementById('tongTien').value;
        document.getElementById('modalGhiChu').innerText = document.getElementById('ghiChu').value || "Không có ghi chú";
    });
</script>

</body>
</html>
