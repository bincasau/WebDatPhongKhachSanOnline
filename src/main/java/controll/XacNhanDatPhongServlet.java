package controll;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.DatPhongDao;
import Dao.HoaDonDao;
import Dao.KhachHangDao;
import model.DatPhong;
import model.HoaDon;
import model.KhachHang;

/**
 * Servlet implementation class XacNhanDatPhongServlet
 */
@WebServlet("/XacNhanDatPhongServlet")
public class XacNhanDatPhongServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public XacNhanDatPhongServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"); 
        String maDatPhong = DatPhongDao.getInstance().getNextMaDatPhong();
        Date ngayDatPhong = new Date(System.currentTimeMillis());

        Date ngayNhan = null;
        Date ngayTra = null;

        try {
            ngayNhan = new Date(dateFormat.parse(request.getParameter("ngayNhan")).getTime());
            ngayTra = new Date(dateFormat.parse(request.getParameter("ngayTra")).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String ghiChu = request.getParameter("ghiChu");
        String maPhong = request.getParameter("maPhong")+ "";
        System.out.println(maPhong);
        HttpSession session = request.getSession();
        KhachHang kh = (KhachHang) session.getAttribute("khachHang");
        int row = 0;
        if (kh != null) {
            String maKhachHang = kh.getMaKhachHang();
            DatPhong dp = new DatPhong(maDatPhong, ngayDatPhong, ngayNhan, ngayTra, ghiChu, maPhong, maKhachHang);
            row = DatPhongDao.getInstance().themDoiTuong(dp);
            if(row == 0) {
            	request.setAttribute("e_message", "Lỗi đạt phòng xin thử lại!");
            	request.getRequestDispatcher("/FormDatPhong.jsp").forward(request, response);
            }
            ////
            String maHoaDon = HoaDonDao.getNextMaHoaDon();
            String tongTienStr = request.getParameter("tongTien");
            if (tongTienStr != null) {
                tongTienStr = tongTienStr.replace(",", "").trim(); // Loại bỏ dấu phẩy nếu có
            }
            double thanhTien = tongTienStr.isEmpty() ? 0 : Double.parseDouble(tongTienStr);
            String trangThai = "Đã thanh toán";
            Date ngayHoaDon = ngayDatPhong;
            String tgSuDungStr = request.getParameter("tgSuDung");
            if (tgSuDungStr != null) {
                tgSuDungStr = tgSuDungStr.replace(".", "").trim(); // Loại bỏ dấu phẩy nếu có
            }
            int thoiGianSuDung = tgSuDungStr.isEmpty() ? 0 : Integer.parseInt(tgSuDungStr);
            HoaDon hd = new HoaDon(maHoaDon, thanhTien, trangThai, ngayHoaDon, (thoiGianSuDung / 100), maDatPhong) ;
            row = HoaDonDao.getInstance().themDoiTuong(hd);
            if(row == 0) { 
            	DatPhongDao.getInstance().xoaDoiTuong(dp);
            	request.setAttribute("e_message", "Lỗi đạt phòng xin thử lại!");
            	request.getRequestDispatcher("/FormDatPhong.jsp").forward(request, response);
            }
            row = 0;
            ///
            
        }
        
        
        request.getRequestDispatcher("/TrangChu.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
