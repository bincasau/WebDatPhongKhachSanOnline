package controll;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.QuanLyDao;
import Dao.SHA1;
import model.QuanLy;

/**
 * Servlet implementation class QTVThemQuanLyServlet
 */
@WebServlet("/QTVThemQuanLyServlet")
public class QTVThemQuanLyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QTVThemQuanLyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String message = "";
		String url = "";
		try {
			String tenQuanLy = request.getParameter("tenQuanLy");
			String soDienThoai = request.getParameter("soDienThoai");
			String email = request.getParameter("email");
			String taiKhoan = request.getParameter("taiKhoan");
			String matKhau = request.getParameter("matKhau");
			java.sql.Date ngaySinh = java.sql.Date.valueOf(request.getParameter("ngaySinh"));
			boolean gioiTinh = request.getParameter("gioiTinh").equals("true");
			String soCCCD = request.getParameter("soCCCD");
			
			
			
			List<QuanLy> ds = QuanLyDao.getInstance().layDanhSachTheoDK(taiKhoan);
			if(ds.size() > 0) {
				message = "Tài Khoản Đã Tồn Tại!";
				url = "/ThemQuanLy.jsp";
			}else{
				int row = QuanLyDao.getInstance().themDoiTuong(new QuanLy(QuanLyDao.getNextMaQuanLy(), tenQuanLy, soDienThoai, email, taiKhoan, SHA1.toSHA1(matKhau), ngaySinh, gioiTinh, soCCCD));
				if(row > 0) {
					message = "Thêm Quản Lý Thành Công!";
					url = "/QTVGiaoDien.jsp";
				}else {
					message = "Thêm Không Thành Công";
					url = "/ThemQuanLy.jsp";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
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
