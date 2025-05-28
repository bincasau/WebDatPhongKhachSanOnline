package controll;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.ThongTinDanhGiaDao;
import model.ThongTinDanhGia;

/**
 * Servlet implementation class XuLyDanhGiaServlet
 */
@WebServlet("/XuLyDanhGiaServlet")
public class XuLyDanhGiaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public XuLyDanhGiaServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String maKhachHang = request.getParameter("maKhachHang");
        String maKhachSan = request.getParameter("maKhachSan");
        int soSao;
        String moTa = request.getParameter("moTa");
        String message = "";
        try {
            soSao = Integer.parseInt(request.getParameter("soSao"));
        } catch (NumberFormatException e) {
            message = "Số sao không hợp lệ!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("LichSuDatPhongServlet").forward(request, response);
            return;
        }

        Date ngayDanhGia = new Date(System.currentTimeMillis());

        try {
            ThongTinDanhGia ttdg = new ThongTinDanhGia(
                ThongTinDanhGiaDao.getNextMaDanhGia(),
                soSao,
                moTa,
                ngayDanhGia,
                maKhachHang,
                maKhachSan
            );
            int row = ThongTinDanhGiaDao.getInstance().themDoiTuong(ttdg);
            if (row > 0) {
                message = "Thêm đánh giá thành công!";
            } else {
                message = "Thêm đánh giá thất bại!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = "Có lỗi xảy ra: " + e.getMessage();
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("LichSuDatPhongServlet").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}