package controll;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ThongTinDanhGiaDao;
import model.ThongTinDanhGia;

/**
 * Servlet implementation class XuLyDanhGiaServlet
 */
@WebServlet("/XuLyDanhGiaServlet")
public class XuLyDanhGiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XuLyDanhGiaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");

		    String maKhachHang = request.getParameter("maKhachHang");
		    String maKhachSan = request.getParameter("maKhachSan");
		    int soSao = Integer.parseInt(request.getParameter("soSao"));
		    String moTa = request.getParameter("moTa");
		    
		    String message = "";
		    try {
				ThongTinDanhGia ttdg = new ThongTinDanhGia(ThongTinDanhGiaDao.getNextMaDanhGia(), soSao, moTa, maKhachHang, maKhachSan);
				int row = ThongTinDanhGiaDao.getInstance().themDoiTuong(ttdg);
				if(row > 0) {
					message = "Thêm đánh giá thành công!";
				}else {
					message = "Thêm đánh giá thất bại!";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Có lỗi xảy ra!";
			}
		    request.setAttribute("message", message);
		    request.getRequestDispatcher("LichSuDatPhongServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
