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

@WebServlet("/QLThongKeServlet")
public class QLThongKeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public QLThongKeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String maKhachSan = request.getParameter("maKhachSan");
        String monthYear = request.getParameter("monthYear");
        String year = request.getParameter("year");

        // Get room data
        List<Phong> dsPhong = PhongDao.getInstance().layDanhSachTheoDK(maKhachSan);
        int soPhong = dsPhong.size();
        Map<String, Integer> phongTheoLoai = new HashMap<>();
        for (Phong p : dsPhong) {
            String loaiPhong = p.getLoaiPhong();
            phongTheoLoai.put(loaiPhong, phongTheoLoai.getOrDefault(loaiPhong, 0) + 1);
        }

        // Initialize DAOs
        ThongTinDatPhongDao datPhongDao = ThongTinDatPhongDao.getInstance();
        ThongTinDanhGiaDao danhGiaDao = ThongTinDanhGiaDao.getInstance();

        // Data variables
        double doanhThu = 0;
        int soLuongDatPhong = 0;
        List<Object[]> doanhThuTheoLoai = null;
        List<Object[]> doanhThuTheoThang = null;
        List<Object[]> soLuongDatPhongTheoThang = null;
        List<Object[]> soLuongDanhGiaTheoThang = null;
        List<Object[]> diemTrungBinhTheoThang = null;

        // Fetch booking data based on filter
        if (monthYear != null && !monthYear.isEmpty()) {
            doanhThu = datPhongDao.tinhDoanhThu(maKhachSan, monthYear);
            soLuongDatPhong = datPhongDao.tinhSoLuongDatPhong(maKhachSan, monthYear);
            doanhThuTheoLoai = datPhongDao.tinhDoanhThuTheoLoaiPhong(maKhachSan, monthYear);
        } else if (year != null && !year.isEmpty()) {
            doanhThu = datPhongDao.tinhDoanhThuNam(maKhachSan, year);
            soLuongDatPhong = datPhongDao.tinhSoLuongDatPhongNam(maKhachSan, year);
            doanhThuTheoLoai = datPhongDao.tinhDoanhThuTheoLoaiPhongNam(maKhachSan, year);
            doanhThuTheoThang = datPhongDao.tinhDoanhThuTheoThang(maKhachSan, year);
            soLuongDatPhongTheoThang = datPhongDao.tinhSoLuongDatPhongTheoThang(maKhachSan, year);
            soLuongDanhGiaTheoThang = danhGiaDao.tinhSoLuongDanhGiaTheoThang(maKhachSan, year);
            diemTrungBinhTheoThang = danhGiaDao.tinhDiemTrungBinhTheoThang(maKhachSan, year);
        } else {
            doanhThu = datPhongDao.tinhDoanhThu(maKhachSan);
            soLuongDatPhong = datPhongDao.tinhSoLuongDatPhong(maKhachSan);
            doanhThuTheoLoai = datPhongDao.tinhDoanhThuTheoLoaiPhong(maKhachSan);
        }

        // Fetch review data
        int soLuongDanhGia = danhGiaDao.tinhSoLuongDanhGia(maKhachSan);
        double diemTrungBinh = danhGiaDao.tinhDiemTrungBinh(maKhachSan);
        Map<Integer, Integer> phanBoDanhGia = danhGiaDao.tinhPhanBoDanhGia(maKhachSan);

        // Set attributes for JSP
        request.setAttribute("maKhachSan", maKhachSan);
        request.setAttribute("soPhong", soPhong);
        request.setAttribute("phongTheoLoai", phongTheoLoai);
        request.setAttribute("doanhThu", doanhThu);
        request.setAttribute("soLuongDatPhong", soLuongDatPhong);
        request.setAttribute("doanhThuTheoLoai", doanhThuTheoLoai);
        request.setAttribute("doanhThuTheoThang", doanhThuTheoThang);
        request.setAttribute("soLuongDatPhongTheoThang", soLuongDatPhongTheoThang);
        request.setAttribute("soLuongDanhGiaTheoThang", soLuongDanhGiaTheoThang);
        request.setAttribute("diemTrungBinhTheoThang", diemTrungBinhTheoThang);
        request.setAttribute("soLuongDanhGia", soLuongDanhGia);
        request.setAttribute("diemTrungBinh", diemTrungBinh);
        request.setAttribute("phanBoDanhGia", phanBoDanhGia);
        request.setAttribute("monthYear", monthYear);
        request.setAttribute("year", year);

        // Forward to JSP
        request.getRequestDispatcher("/ThongKe.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}