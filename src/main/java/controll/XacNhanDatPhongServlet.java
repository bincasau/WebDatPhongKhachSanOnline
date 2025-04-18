package controll;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ThongTinDatPhongDao;
import model.ThongTinDatPhong;

@WebServlet("/XacNhanDatPhongServlet")
public class XacNhanDatPhongServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public XacNhanDatPhongServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy dữ liệu từ form
            String maDatPhong = ThongTinDatPhongDao.getNextMaDatPhong();
            java.sql.Date ngayDatPhong = new java.sql.Date(System.currentTimeMillis());

            String ngayNhanStr = request.getParameter("ngayNhan");
            String gioNhanStr = request.getParameter("gioNhan");
            String ngayTraStr = request.getParameter("ngayTra");
            String gioTraStr = request.getParameter("gioTra");

            java.sql.Date ngayNhanPhong = java.sql.Date.valueOf(ngayNhanStr);
            java.sql.Date ngayTraPhong = java.sql.Date.valueOf(ngayTraStr);

            String ghiChu = request.getParameter("ghiChu");
            int tgSuDung = Integer.parseInt(request.getParameter("tgSuDung"));
            float thanhTien = Float.parseFloat(request.getParameter("tongTien").replace(",", ""));

            String trangThai = "Đã thanh toán";
            java.sql.Date ngayHoaDon = ngayDatPhong;

            String maPhong = request.getParameter("maPhong");
            String maKhachSan = request.getParameter("maKhachSan") + "";
            String maKhachHang = ((model.KhachHang) request.getSession().getAttribute("khachHang")).getMaKhachHang();

            // Debug: Log parameters
            System.out.println("maKhachSan: " + maKhachSan);
            System.out.println("maPhong: " + maPhong);
            System.out.println("maKhachHang: " + maKhachHang);

            // Tạo đối tượng đầy đủ 12 trường
            ThongTinDatPhong ttdp = new ThongTinDatPhong(
                maDatPhong, ngayDatPhong, ngayNhanPhong, ngayTraPhong,
                ghiChu, tgSuDung, thanhTien, trangThai, ngayHoaDon,
                maPhong, maKhachSan, maKhachHang
            );

            // Gọi DAO để lưu
            int result = ThongTinDatPhongDao.getInstance().themDoiTuong(ttdp);

            if (result > 0) {
                response.sendRedirect("ThanhCong.jsp");
            } else {
                request.setAttribute("e_message", "Đặt phòng thất bại, vui lòng thử lại.");
                request.getRequestDispatcher("FormDatPhong.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("e_message", "Có lỗi xảy ra: " + e.getMessage());
            request.getRequestDispatcher("FormDatPhong.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}