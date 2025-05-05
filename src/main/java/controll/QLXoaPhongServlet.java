package controll;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.PhongDao;
import model.Phong;

/**
 * Servlet implementation class QLXoaPhongServlet
 */
@WebServlet("/QLXoaPhongServlet")
public class QLXoaPhongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QLXoaPhongServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maPhong = request.getParameter("maPhong");
        String maKhachSan = request.getParameter("maKhachSan");

        String message = "";
        Phong p = new Phong();
        p.setMaPhong(maPhong); p.setMaKhachSan(maKhachSan);
        if (maPhong != null && !maPhong.isEmpty()) {
            int thanhCong = PhongDao.getInstance().xoaDoiTuong(p);
            if (thanhCong > 0) {
                message = "Xóa phòng thành công!";
            } else {
                message = "Xóa phòng thất bại!";
            }
        } else {
            message = "Không tìm thấy mã phòng cần xóa.";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("QLXemPhong.jsp?maKhachSan=" + maKhachSan).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
