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
 * Servlet implementation class QLSuaPhongServlet
 */
@WebServlet("/QLSuaPhongServlet")
@MultipartConfig
public class QLSuaPhongServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QLSuaPhongServlet() {
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

	        String maPhong = request.getParameter("maPhong");
	        String maKhachSan = request.getParameter("maKhachSan");
	        String loaiPhong = request.getParameter("loaiPhong");
	        String loaiGiuong = request.getParameter("loaiGiuong");
	        int soLuong = Integer.parseInt(request.getParameter("soLuong"));
	        double gia = Double.parseDouble(request.getParameter("gia"));
	        String moTa = request.getParameter("moTa");
	        String tinhTrang = request.getParameter("tinhTrang");
	        String hinhAnhCu = request.getParameter("hinhAnhCu");

	        // Xử lý hình ảnh
	        Part filePart = request.getPart("hinhAnh");
	        String fileName = filePart.getSubmittedFileName();
	        String hinhAnh;

	        if (fileName != null && !fileName.isEmpty()) {
	            fileName = Paths.get(fileName).getFileName().toString();
	            String uploadPath = getServletContext().getRealPath("") + File.separator + "pic/Phong";
	            File uploadDir = new File(uploadPath);
	            if (!uploadDir.exists()) uploadDir.mkdir();

	            filePart.write(uploadPath + File.separator + fileName);
	            hinhAnh = fileName;
	        } else {
	            // Nếu không chọn ảnh mới, giữ ảnh cũ
	            hinhAnh = hinhAnhCu;
	        }

	        // Cập nhật phòng
	        Phong phong = new Phong(maPhong, loaiPhong, gia, tinhTrang, loaiGiuong, soLuong, moTa, maKhachSan, hinhAnh);
	        int result = PhongDao.getInstance().capNhatDoiTuong(phong);

	        if (result > 0) {
	            request.setAttribute("message", "Cập nhật phòng thành công!");
	        } else {
	            request.setAttribute("message", "Cập nhật phòng thất bại!");
	        }

	        RequestDispatcher rd = request.getRequestDispatcher("QLXemPhong.jsp?maKhachSan=" + maKhachSan);
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
