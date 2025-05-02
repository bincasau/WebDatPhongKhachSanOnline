package controll;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.ThongTinDatPhongDao;
import model.KhachHang;
import model.QuanTriVien;
import model.ThongTinDatPhong;

/**
 * Servlet implementation class LichSuDatPhongServlet
 */
@WebServlet("/LichSuDatPhongServlet")
public class LichSuDatPhongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LichSuDatPhongServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        QuanTriVien qtv = (QuanTriVien) session.getAttribute("quanTriVien");
        KhachHang kh = (KhachHang) session.getAttribute("khachHang");

        // Trường hợp là quản trị viên
        if (qtv != null) {
            String maKH = request.getParameter("id"); // Lấy mã khách hàng cần xem
            if (maKH != null && !maKH.isEmpty()) {
                List<ThongTinDatPhong> lichSu = ThongTinDatPhongDao.getInstance().layDanhSachTheoDK(maKH);
                request.setAttribute("lichSuDatPhong", lichSu);
                request.getRequestDispatcher("LichSuDatPhongQTV.jsp").forward(request, response);
                return;
            } else {
                response.sendRedirect("QTVGiaoDien.jsp"); // hoặc trang admin nào đó
                return;
            }
        }

        // Trường hợp là khách hàng
        if (kh != null) {
            List<ThongTinDatPhong> lichSu = ThongTinDatPhongDao.getInstance().layDanhSachTheoDK(kh.getMaKhachHang());
            request.setAttribute("lichSuDatPhong", lichSu);

            String message = (String) request.getAttribute("message");
            request.setAttribute("message", message != null ? message : "");

            request.getRequestDispatcher("LichSuDatPhong.jsp").forward(request, response);
        } else {
            response.sendRedirect("DangNhap.jsp");
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
