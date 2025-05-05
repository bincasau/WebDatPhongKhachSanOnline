package controll;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ThongTinDanhGiaDao;
import model.ThongTinDanhGia;

/**
 * Servlet implementation class QLXoaDanhGiaServlet
 */
@WebServlet("/QLXoaDanhGiaServlet")
public class QLXoaDanhGiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QLXoaDanhGiaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maDanhGia = request.getParameter("maDanhGia");
		ThongTinDanhGia dg = new ThongTinDanhGia();
		dg.setMaDanhGia(maDanhGia);
		int row = ThongTinDanhGiaDao.getInstance().xoaDoiTuong(dg);
		String message = "";
		if(row > 0) {
			message = "Xóa Đánh Giá Thành Công!";
		}else {
			message = "Xóa Đánh Giá Không Thành Công!";
		}
		request.setAttribute("message", message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/QLXemPhong.jsp");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
