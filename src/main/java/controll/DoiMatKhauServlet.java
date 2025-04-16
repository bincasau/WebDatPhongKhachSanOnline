package controll;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.KhachHangDao;
import Dao.SHA1;
import model.KhachHang;

/**
 * Servlet implementation class DoiMatKhauServlet
 */
@WebServlet("/DoiMatKhauServlet")
public class DoiMatKhauServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoiMatKhauServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		    KhachHang kh = (KhachHang) session.getAttribute("khachHang");
		    String message = "";

		    String matKhauCu = request.getParameter("matKhauCu");
		    String matKhauMoi = request.getParameter("matKhauMoi");
		    String xacNhanMatKhauMoi = request.getParameter("xacNhanMatKhauMoi");
		    System.out.println(matKhauCu);
		    try {
				if (!SHA1.toSHA1(matKhauCu.trim()).equals(kh.getMatKhau())) {
				    message = "Mật khẩu hiện tại không chính xác!";
				    request.setAttribute("message", message);
				    request.getRequestDispatcher("DoiMatKhau.jsp").forward(request, response);
				    return;
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		    if (!matKhauMoi.trim().equals(xacNhanMatKhauMoi.trim())) {
		        message = "Mật khẩu mới và xác nhận không trùng khớp!";
		        request.setAttribute("message", message);
		        request.getRequestDispatcher("DoiMatKhau.jsp").forward(request, response);
		        return;
		    }
		    KhachHang hkm = new KhachHang(kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getTaiKhoan(), matKhauMoi, kh.getSoDienThoai(), kh.getNgaySinh(), kh.isGioiTinh(), kh.getSoCCCD());
		    int row = KhachHangDao.getInstance().capNhatDoiTuong(hkm);
		    if(row > 0) {
		    	message = "Đổi mật khẩu thành công!";
			    request.setAttribute("suaThongTin_message", message);
			    request.getRequestDispatcher("TrangChu.jsp").forward(request, response);
		    }
		    else {
		    	 message = "Đổi mật khẩu thất bại!";
			     request.setAttribute("message", message);
			     request.getRequestDispatcher("DoiMatKhau.jsp").forward(request, response);
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
