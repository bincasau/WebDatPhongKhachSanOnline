package controll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Dao.KhachSanDao;
import model.KhachSan;

@WebServlet("/QTVThemKhachSanServlet")
@MultipartConfig // Cần thiết để upload file
public class QTVThemKhachSanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public QTVThemKhachSanServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String message = "";
		String url = "";

		try {
			// Lấy và kiểm tra dữ liệu đầu vào
			String tenKhachSan = getSafeParam(request, "tenKhachSan");
			String diaChi = getSafeParam(request, "diaChi");
			String khuVuc = getSafeParam(request, "khuVuc");
			String moTa = getSafeParam(request, "moTa");
			String maQuanLy = getSafeParam(request, "maQuanLy");
			String danhGiaStr = request.getParameter("danhGiaTrungBinh");
			
			if (tenKhachSan == null || diaChi == null || khuVuc == null || moTa == null || maQuanLy == null || danhGiaStr == null) {
				message = "Vui lòng điền đầy đủ thông tin!";
				url = "/ThemKhachSan.jsp";
			} else {
				double danhGiaTrungBinh = Double.parseDouble(danhGiaStr);

				// Xử lý file hình ảnh
				Part filePart = request.getPart("hinhAnh");
				String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

				if (fileName == null || fileName.isEmpty()) {
					message = "Vui lòng chọn hình ảnh!";
					url = "/ThemKhachSan.jsp";
				} else {
					String uploadPath = getServletContext().getRealPath("/pic/KS");
					File uploadDir = new File(uploadPath);
					if (!uploadDir.exists()) {
						uploadDir.mkdirs();
					}

					// Lưu ảnh
					filePart.write(uploadPath + File.separator + fileName);

					// Tạo đối tượng Khách Sạn và lưu vào DB
					KhachSan ks = new KhachSan(
						KhachSanDao.getNextMaKS(),
						tenKhachSan,
						diaChi,
						khuVuc,
						moTa,
						danhGiaTrungBinh,
						maQuanLy,
						fileName
					);

					int row = KhachSanDao.getInstance().themDoiTuong(ks);

					if (row > 0) {
						message = "Thêm Khách Sạn Thành Công!";
						url = "/QTVGiaoDien.jsp";
					} else {
						message = "Thêm Khách Sạn Thất Bại!";
						url = "/ThemKhachSan.jsp";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "Đã xảy ra lỗi: " + e.getMessage();
			url = "/ThemKhachSan.jsp";
		}

		request.setAttribute("message", message);
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// Hàm tiện ích: trả về chuỗi đã trim hoặc null nếu không tồn tại
	private String getSafeParam(HttpServletRequest request, String name) {
		String param = request.getParameter(name);
		return (param != null && !param.trim().isEmpty()) ? param.trim() : null;
	}
}
