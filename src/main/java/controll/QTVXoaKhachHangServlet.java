package controll;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.KhachHangDao;
import model.KhachHang;

/**
 * Servlet implementation class QTVXoaKhachHangServlet
 */
@WebServlet("/QTVXoaKhachHangServlet")
public class QTVXoaKhachHangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QTVXoaKhachHangServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maKhachHang = request.getParameter("id");
		KhachHang kh = new KhachHang();
		kh.setMaKhachHang(maKhachHang);
		String message = "";
		int row = KhachHangDao.getInstance().xoaDoiTuong(kh);
		if(row > 0) {
			message = "Xóa Khách Hàng Thành Công!";
		}else {
			message = "Xóa Khách Hàng Thất Bại!";
		}
		request.setAttribute("message", message);
        request.getRequestDispatcher("/QTVGiaoDien.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
