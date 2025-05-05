package controll;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.PhongDao;
import Dao.ThongTinDanhGiaDao;
import Dao.ThongTinDatPhongDao;
import model.Phong;

/**
 * Servlet implementation class QLThongKeServlet
 */
@WebServlet("/QLThongKeServlet")
public class QLThongKeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QLThongKeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String maKhachSan = request.getParameter("maKhachSan");

        // Get room data
        List<Phong> dsPhong = PhongDao.getInstance().layDanhSachTheoDK(maKhachSan);
        
        // Count total rooms
        int soPhong = dsPhong.size();

        // Count rooms by type
        Map<String, Integer> phongTheoLoai = new HashMap<>();
        for (Phong p : dsPhong) {
            String loaiPhong = p.getLoaiPhong();
            phongTheoLoai.put(loaiPhong, phongTheoLoai.getOrDefault(loaiPhong, 0) + 1);
        }

        // Get revenue data
        ThongTinDatPhongDao datPhongDao = ThongTinDatPhongDao.getInstance();
        double doanhThu = datPhongDao.tinhDoanhThu(maKhachSan);
        List<Object[]> doanhThuTheoLoai = datPhongDao.tinhDoanhThuTheoLoaiPhong(maKhachSan);

        // Get review data
        ThongTinDanhGiaDao danhGiaDao = ThongTinDanhGiaDao.getInstance();
        int soLuongDanhGia = danhGiaDao.tinhSoLuongDanhGia(maKhachSan);
        double diemTrungBinh = danhGiaDao.tinhDiemTrungBinh(maKhachSan);
        Map<Integer, Integer> phanBoDanhGia = danhGiaDao.tinhPhanBoDanhGia(maKhachSan);

        // Set attributes for JSP
        request.setAttribute("maKhachSan", maKhachSan);
        request.setAttribute("soPhong", soPhong);
        request.setAttribute("phongTheoLoai", phongTheoLoai);
        request.setAttribute("doanhThu", doanhThu);
        request.setAttribute("doanhThuTheoLoai", doanhThuTheoLoai);
        request.setAttribute("soLuongDanhGia", soLuongDanhGia);
        request.setAttribute("diemTrungBinh", diemTrungBinh);
        request.setAttribute("phanBoDanhGia", phanBoDanhGia);

        // Forward to JSP
        request.getRequestDispatcher("/ThongKe.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
