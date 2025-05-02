package controll;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.KhachSanDao;
import Dao.QuanLyDao;
import model.KhachSan;
import model.QuanLy;

/**
 * Servlet implementation class QTVXoaQuanLyServlet
 */
@WebServlet("/QTVXoaQuanLyServlet")
public class QTVXoaQuanLyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QTVXoaQuanLyServlet() {
        super();
        // TODO Auto-generated constructor stub              
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String message = "";
		String url ="/QTVGiaoDien.jsp";
		if(KhachSanDao.getInstance().layDanhSachTheoMaQL(id).size() > 0) {
			message = "Xóa Quán Lý Thất Bại Do Quản Lý Đang Quản Lý Khách Sạn!";
		}else {
			QuanLy ql = new QuanLy();
			ql.setMaQuanLy(id);
			int row = QuanLyDao.getInstance().xoaDoiTuong(ql);
			if(row > 0) {
				message = "Xóa Quán Lý Thành Công!";
			}else {
				message = "Xóa Quán Lý Thất Bại!";
			}
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
