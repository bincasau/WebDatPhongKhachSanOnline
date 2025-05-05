package controll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Dao.PhongDao;
import model.Phong;

/**
 * Servlet implementation class QLThemPhongServlet
 */
@WebServlet("/QLThemPhongServlet")
@MultipartConfig
public class QLThemPhongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QLThemPhongServlet() {
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
		
		String maKhachSan = request.getParameter("maKhachSan");
        String loaiPhong = request.getParameter("loaiPhong");
        String loaiGiuong = request.getParameter("loaiGiuong");
        int soLuong = Integer.parseInt(request.getParameter("soLuong"));
        double gia = Double.parseDouble(request.getParameter("gia"));
        String tinhTrang = request.getParameter("tinhTrang");
        String moTa = request.getParameter("moTa");

        // Xử lý file hình ảnh
        Part filePart = request.getPart("hinhAnh");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "pic/Phong";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        filePart.write(uploadPath + File.separator + fileName);
        String maPhong = PhongDao.getNextMaPh(maKhachSan);
        Phong phong = new Phong(maPhong, loaiPhong, gia, tinhTrang, loaiGiuong, soLuong, moTa, maKhachSan, fileName);

        PhongDao phongDao = PhongDao.getInstance();
        int row = phongDao.themDoiTuong(phong);

        if (row > 0) {
            request.setAttribute("message", "Thêm phòng thành công!");
        } else {
            request.setAttribute("message", "Thêm phòng thất bại!");
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/QLXemPhong.jsp?maKhachSan=" + maKhachSan);
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
