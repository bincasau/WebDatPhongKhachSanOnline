package controll;

import java.io.IOException;
import java.sql.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ThongTinDatPhongDao;
import model.ThongTinDatPhong;

@WebServlet("/HuyDatPhongServlet")
public class HuyDatPhongServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HuyDatPhongServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maDatPhong = request.getParameter("maDatPhong");
        String message = "";

        if (maDatPhong == null || maDatPhong.trim().isEmpty()) {
            response.getWriter().write("Không tìm thấy thông tin đặt phòng vì mã rỗng.");
            return;
        }

        ThongTinDatPhong ttdp = ThongTinDatPhongDao.getInstance().layTheoId(maDatPhong);
        if (ttdp == null) {
            response.getWriter().write("Không tìm thấy thông tin đặt phòng với mã: " + maDatPhong);
            return;
        }

        Date ngayDatPhong = ttdp.getNgayDatPhong();
        Date ngayHienTai = new Date(System.currentTimeMillis());

        long diffInMillies = Math.abs(ngayHienTai.getTime() - ngayDatPhong.getTime());
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        if (diffInDays > 1) {
            message = "Không thể hủy phòng khi đã quá 24h.";
        } else {
            int row = ThongTinDatPhongDao.getInstance().capNhatTrangThaiHuy(maDatPhong);
            message = (row > 0) ? "Hủy đặt phòng thành công!" : "Hủy đặt phòng thất bại!";
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/LichSuDatPhongServlet").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
