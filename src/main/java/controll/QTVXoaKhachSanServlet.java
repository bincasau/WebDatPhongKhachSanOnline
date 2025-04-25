package controll;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.KhachSanDao;
import model.KhachSan;

/**
 * Servlet implementation class QTVXoaKhachSanServlet
 */
@WebServlet("/QTVXoaKhachSanServlet")
public class QTVXoaKhachSanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QTVXoaKhachSanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maKhachSan = request.getParameter("id");
		KhachSan ks = new KhachSan();
		ks.setMaKhachSan(maKhachSan);
		String message = "";
		int row = KhachSanDao.getInstance().xoaDoiTuong(ks);
		if(row > 0) {
			message = "Xóa Khách Sạn Thành Công!";
		}else {
			message = "Xóa Khách Sạn Thất Bại!";
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
