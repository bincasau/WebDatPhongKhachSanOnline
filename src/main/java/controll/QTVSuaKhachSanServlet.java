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

@WebServlet("/QTVSuaKhachSanServlet")
@MultipartConfig
public class QTVSuaKhachSanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public QTVSuaKhachSanServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String message = "";
        String url = "";

        try {
        	// Lấy giá trị các trường văn bản từ Part
            String maKhachSan = getSafeParam(request, "maKhachSan");
            String tenKhachSan = getSafeParam(request,"tenKhachSan");
            String diaChi = getSafeParam(request, "diaChi");
            String khuVuc = getSafeParam(request, "khuVuc");
            String moTa = getSafeParam(request, "moTa"); // Có thể rỗng
            String danhGiaStr = request.getParameter("danhGiaTrungBinh");
            double danhGiaTrungBinh = Double.parseDouble(danhGiaStr);
            String maQuanLy = getSafeParam(request, "maQuanLy");

            KhachSanDao khachSanDao = KhachSanDao.getInstance();
            KhachSan currentKhachSan = khachSanDao.layDanhSachTheoID(maKhachSan).isEmpty() ? null : khachSanDao.layDanhSachTheoID(maKhachSan).get(0);

            String fileName = currentKhachSan.getHinhAnh();
            Part filePart = request.getPart("hinhAnh");
            if(filePart != null) {
            	String fileName_2 = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

    			if (fileName_2 != null && !fileName_2.isEmpty()) {
    				String uploadPath = getServletContext().getRealPath("/pic/KS");
    				File uploadDir = new File(uploadPath);
    				fileName = fileName_2;
    				if (!uploadDir.exists()) {
    					uploadDir.mkdirs();
    				}
    			}
            }
			
            KhachSan ks = new KhachSan(maKhachSan, tenKhachSan, diaChi, khuVuc, moTa, danhGiaTrungBinh, maQuanLy, fileName);
            int row = khachSanDao.getInstance().capNhatDoiTuong(ks);
            if(row > 0) {
            	message = "Cập Nhật Khách Sạn Thành Công!";
            	url = "/QTVGiaoDien.jsp";
            }
            else {
            	message = "Cập Nhật Khách Sạn Thất Bại!";
            	url = "/SuaKhachSan.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher(url).forward(request, response);
    }

    private String getSafeParam(HttpServletRequest request, String name) {
		String param = request.getParameter(name);
		return (param != null && !param.trim().isEmpty()) ? param.trim() : null;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}