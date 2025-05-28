package controll;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.KhachHangDao;
import model.KhachHang;

/**
 * Servlet implementation class SuaThongTinServlet
 */
@WebServlet("/SuaThongTinServlet")
public class SuaThongTinServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuaThongTinServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        // Lấy thông tin từ request
        String tenKhachHang = request.getParameter("tenKhachHang");
        String soDienThoai = request.getParameter("soDienThoai");
        String ngaySinhStr = request.getParameter("ngaySinh");
        boolean gioiTinh = "true".equals(request.getParameter("gioiTinh"));
        String soCCCD = request.getParameter("soCCCD");

        // Chuyển đổi ngày sinh từ String sang Date
        Date ngaySinh = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = dateFormat.parse(ngaySinhStr);
            ngaySinh = new Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Lấy session hiện tại
        HttpSession session = request.getSession();
        KhachHang kh = (KhachHang) session.getAttribute("khachHang");

        if (kh != null && ngaySinh != null) {
            // Tạo đối tượng khách hàng mới với thông tin cập nhật
            KhachHang newKhachHang = new KhachHang(
                kh.getMaKhachHang(), tenKhachHang, kh.getTaiKhoan(),
                kh.getMatKhau(), soDienThoai, ngaySinh, gioiTinh, soCCCD
            );
            System.out.println(kh.getMatKhau());

            // Cập nhật thông tin trong database
            int row = KhachHangDao.getInstance().capNhatDoiTuong(newKhachHang);
            String suaThongTin_message = "Sửa thông tin thất bại";

            if (row == 1) {
                suaThongTin_message = "Sửa thông tin thành công";
                session.setAttribute("khachHang", newKhachHang); // Cập nhật session
            }

            // Đặt thông báo vào request
            request.setAttribute("suaThongTin_message", suaThongTin_message);

            // Chuyển hướng đến TrangChu.jsp
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/TrangChu.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("SuaThongTinTaiKhoan.jsp?error=invalidData");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
