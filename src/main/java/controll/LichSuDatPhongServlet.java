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
        KhachHang kh = (KhachHang) session.getAttribute("khachHang");
        if (kh == null) {
            response.sendRedirect("DangNhap.jsp");
            return;
        }

        List<ThongTinDatPhong> lichSu = ThongTinDatPhongDao.getInstance().layDanhSachTheoDK(kh.getMaKhachHang());
        request.setAttribute("lichSuDatPhong", lichSu);
        String message = request.getAttribute("message")+"";
        request.setAttribute("message", message);
        request.getRequestDispatcher("LichSuDatPhong.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
