package controll;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.SHA1;
import Util.JDBCUtil;
import model.KhachHang;

/**
 * Servlet implementation class DangNhapServlet
 */
@WebServlet("/DangNhapServlet")
public class DangNhapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangNhapServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String taiKhoan = request.getParameter("username");
		String matKhau = request.getParameter("password");
		Connection conn = JDBCUtil.connect();
		String url = "";
		if(conn!=null) {
			try {
				String sql = "select * from khachhang where taiKhoan = ? and matKhau = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, taiKhoan);
                stmt.setString(2, SHA1.toSHA1(matKhau));
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                	HttpSession session = request.getSession();
                	KhachHang kh = new KhachHang(rs.getString("maKhachHang"),
                								rs.getString("tenKhachHang"),
                								rs.getString("taiKhoan"), 
                								rs.getString("matKhau"), 
                								rs.getString("soDienThoai"), 
                								rs.getDate("ngaySinh"), 
                								rs.getBoolean("gioiTinh"), 
                								rs.getString("soCCCD"));
                	session.setAttribute("khachHang", kh);
                	url="/TrangChu.jsp";
       
                }else {
                	request.setAttribute("error", "Tài Khoản hoặc mật khẩu éo đúng");
                	url="/DangNhap.jsp";
                }
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
	        rd.forward(request, response);
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
