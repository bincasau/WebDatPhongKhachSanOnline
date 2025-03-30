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
	    // Lấy thông tin khách hàng từ session
	    KhachHang kh = (KhachHang) session.getAttribute("khachHang");
	    String hoTen = (kh != null) ? kh.getTenKhachHang() : "";
	    String soDienThoai = (kh != null) ? kh.getSoDienThoai() : "";
	    double giaPhong = (double) request.getAttribute("giaPhong"); 
	    String maPhong = request.getAttribute("maPhong") + "";
	    request.setAttribute("maPhong", maPhong);
	%>
    <div class="container mt-5">
    <%
    	String e_message = request.getAttribute("e_message") + "";
    	if(e_message.equals("null")) e_message = "";
    %>
    	<p><%=e_message %></p>
        <h2 class="text-center">Đặt Phòng</h2>
        
        <!-- Form gửi dữ liệu đến XacNhanDatPhongServlet -->
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
                <input type="datetime-local" class="form-control" id="ngayNhan" name="ngayNhan" required>
            </div>

            <div class="mb-3">
                <label>Ngày trả phòng:</label>
                <input type="datetime-local" class="form-control" id="ngayTra" name="ngayTra" required>
            </div>

            <div class="mb-3">
                <label>Thời gian sử dụng (giờ):</label>
                <input type="text" class="form-control" id="tgSuDung" name="tgSuDung" readonly> Giờ
            </div>

            <div class="mb-3">
                <label>Tổng tiền:</label>
                <input type="text" class="form-control" id="tongTien" name="tongTien" readonly>
                <input type="hidden" name="maPhong" value="<%= maPhong %>"> VND
                
            </div>

            <!-- Ghi chú -->
            <div class="mb-3">
                <label>Ghi chú:</label>
                <textarea class="form-control" id="ghiChu" name="ghiChu" rows="3" placeholder="Nhập ghi chú (nếu có)..."></textarea>
            </div>

            <!-- Nút mở modal thanh toán -->
            <button type="button" class="btn btn-success w-100 mt-3" data-bs-toggle="modal" data-bs-target="#confirmModal">
                Thanh Toán
            </button>

            <!-- Bootstrap Modal - Xác nhận thanh toán -->
            <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="confirmModalLabel">Xác nhận Thanh Toán</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                        	<p><strong>Mã phòng: </strong><span value = "<%= request.getAttribute("maPhong") + ""%>" name = "maPhong"></span><%= request.getAttribute("maPhong") + ""%></p>
                            <p><strong>Họ tên:</strong> <span id="modalHoTen"></span></p>
                            <p><strong>Số điện thoại:</strong> <span id="modalSoDienThoai"></span></p>
                            <p><strong>Thời gian sử dụng:</strong> <span id="modalTgSuDung"></span> Giờ</p>
                            <p><strong>Tổng tiền:</strong> <span id="modalTongTien"></span> VND</p>
                            <p><strong>Ghi chú:</strong> <span id="modalGhiChu"></span></p>

                            <!-- Mã QR thanh toán -->
                            <div class="text-center mt-4">
                                <h5>Quét mã QR để thanh toán</h5>
                                <img src="<%=request.getContextPath()%>/pic/qr.jpg" alt="QR Code Thanh Toán" class="img-fluid" style="max-width: 200px;">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                            <!-- Gửi form khi nhấn "Xác Nhận" -->
                            <button type="submit" class="btn btn-primary">Xác Nhận</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <script>
        document.getElementById('ngayTra').addEventListener('change', function() {
            let ngayNhan = new Date(document.getElementById('ngayNhan').value);
            let ngayTra = new Date(this.value);
            
            if (ngayTra > ngayNhan) {
                let diffMs = ngayTra - ngayNhan;
                let tgSuDung = diffMs / (1000 * 60 * 60); // Chuyển mili giây sang giờ
                let giaPhong = parseFloat(document.getElementById('giaPhong').value);
                let tongTien = tgSuDung * giaPhong;

                document.getElementById('tgSuDung').value = tgSuDung.toFixed(2);
                document.getElementById('tongTien').value = tongTien.toLocaleString();
            } else {
                alert("Ngày trả phải lớn hơn ngày nhận!");
                this.value = "";
            }
        });

        // Khi mở modal, điền thông tin vào modal
        document.getElementById('confirmModal').addEventListener('show.bs.modal', function () {
            document.getElementById('modalHoTen').innerText = document.getElementsByName('hoTen')[0].value;
            document.getElementById('modalSoDienThoai').innerText = document.getElementsByName('soDienThoai')[0].value;
            document.getElementById('modalTgSuDung').innerText = document.getElementById('tgSuDung').value;
            document.getElementById('modalTongTien').innerText = document.getElementById('tongTien').value;
            document.getElementById('modalGhiChu').innerText = document.getElementById('ghiChu').value || "Không có ghi chú";
        });
    </script>

</body>
</html>
