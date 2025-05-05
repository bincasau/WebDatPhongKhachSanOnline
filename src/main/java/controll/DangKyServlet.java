package controll;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.KhachHangDao;
import Util.JDBCUtil;
import model.KhachHang;

/**
 * Servlet implementation class DangKyServlet
 */
@WebServlet("/DangKyServlet")
public class DangKyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangKyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		Date ngaySinh = null; 	
		String taiKhoan = request.getParameter("taiKhoan");
		String matKhau = request.getParameter("matKhau");
		String tenKhachHang = request.getParameter("tenKhachHang");
		String soDienThoai = request.getParameter("soDienThoai");
		try {
		    ngaySinh = (Date) dateFormat.parse(request.getParameter("ngaySinh"));
		} catch (Exception e) {
		    e.printStackTrace();
		}
		boolean gioiTinh = request.getParameter("gioiTinh").equals("true");
		String soCCCD = request.getParameter("soCCCD");
		Connection conn = JDBCUtil.connect();
		String url = "";
		String sql = "select * from khachhang where taiKhoan = ?";
		if(conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, taiKhoan);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					url = "/DangKy.jsp";
					request.setAttribute("thongBaoLoiDangKy", "Tài Khoản đã tồn tại!");
				}else {
					KhachHang kh = new KhachHang(KhachHangDao.getInstance().getNextMaKhachHang(), tenKhachHang, taiKhoan, matKhau, soDienThoai, new java.sql.Date(ngaySinh.getTime()), gioiTinh, soCCCD);
					int row = KhachHangDao.getInstance().themDoiTuong(kh);
					HttpSession session = request.getSession();
					if(row > 0) {
						session.setAttribute("khachHang", kh);
						url = "/TrangChu.jsp";
					}else {
						url = "/DangKy.jsp";
						request.setAttribute("thongBaoLoiDangKy", "Đăng ký thất bại!");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
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
