package controll;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.QuanLyDao;
import model.QuanLy;

/**
 * Servlet implementation class QTVSuaQuanLyServlet
 */
@WebServlet("/QTVSuaQuanLyServlet")
public class QTVSuaQuanLyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QTVSuaQuanLyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // đảm bảo nhận tiếng Việt

	    String maQuanLy = request.getParameter("maQuanLy");
	    String tenQuanLy = request.getParameter("tenQuanLy");
	    String soDienThoai = request.getParameter("soDienThoai");
	    String email = request.getParameter("email");
	    String ngaySinhStr = request.getParameter("ngaySinh");
	    String gioiTinhStr = request.getParameter("gioiTinh");
	    String soCCCD = request.getParameter("soCCCD");
	    String message = "";
	    String url = "";
	    
	    boolean gioiTinh = "nam".equalsIgnoreCase(gioiTinhStr);
	    Date ngaySinh = Date.valueOf(ngaySinhStr); // yyyy-MM-dd
	    
	    QuanLy ql = QuanLyDao.getInstance().layDanhSachTheoID(maQuanLy).get(0);
	    ql.setTenQuanLy(tenQuanLy); ql.setSoDienThoai(soDienThoai); ql.setEmail(email); ql.setNgaySinh(ngaySinh); ql.setGioiTinh(gioiTinh); ql.setSoCCCD(soCCCD);
	    int row = QuanLyDao.getInstance().capNhatDoiTuong(ql);
	    if(row > 0) {
	    	message = "Cập Nhật Thành Công!";
	    	url = "/QTVGiaoDien.jsp";
	    }else {
	    	message = "Cập Nhật Thất Bại!";
	    	url = "/SuaQuanLy.jsp";
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
