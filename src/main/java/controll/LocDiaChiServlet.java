package controll;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.KhachSanDao;
import model.KhachSan;

/**
 * Servlet implementation class LocDiaChiServlet
 */
@WebServlet("/LocDiaChiServlet")
public class LocDiaChiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocDiaChiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String tinhThanh = request.getParameter("khuVuc");
			List<KhachSan> ds;
			if(tinhThanh.equals("tatCaKhuVuc")) {
				ds = KhachSanDao.getInstance().layDanhSach();
			}
			else ds = KhachSanDao.getInstance().layDanhSachTheoDK(tinhThanh);
			request.setAttribute("DK", ds);
			request.getRequestDispatcher("/TrangChu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
