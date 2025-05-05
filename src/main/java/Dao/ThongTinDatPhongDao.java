package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.JDBCUtil;
import model.ThongTinDatPhong;

public class ThongTinDatPhongDao implements InterfaceDao<ThongTinDatPhong> {

    public static ThongTinDatPhongDao getInstance() {
        return new ThongTinDatPhongDao();
    }

    public static String getNextMaDatPhong() throws SQLException {
        String nextMaDP = "D0001"; // Mã mặc định nếu bảng rỗng
        String sql = "SELECT MAX(maDatPhong) FROM thongtindatphong";

        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                String lastMaDP = rs.getString(1);
                if (lastMaDP != null && lastMaDP.matches("D\\d{4}")) {
                    int num = Integer.parseInt(lastMaDP.substring(1)) + 1;
                    nextMaDP = String.format("D%04d", num);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Lỗi khi tạo mã đặt phòng: " + e.getMessage(), e);
        }

        return nextMaDP;
    }

    @Override
    public int themDoiTuong(ThongTinDatPhong t) {
        if (t == null || t.getMaPhong() == null || t.getMaKhachHang() == null || t.getMaKhachSan() == null) {
            return 0;
        }
        String sql = "INSERT INTO thongtindatphong (maDatPhong, ngayDatPhong, ngayNhanPhong, ngayTraPhong, ghiChu, "
                   + "tgSuDung, thanhTien, trangThai, ngayHoaDon, maPhong, maKhachSan, maKhachHang) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = JDBCUtil.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getMaDatPhong());
            stmt.setDate(2, t.getNgayDatPhong() != null ? t.getNgayDatPhong() : new Date(System.currentTimeMillis()));
            stmt.setDate(3, t.getNgayNhanPhong());
            stmt.setDate(4, t.getNgayTraPhong());
            stmt.setString(5, t.getGhiChu());
            stmt.setInt(6, t.getTgSuDung());
            stmt.setFloat(7, t.getThanhTien());
            stmt.setString(8, t.getTrangThai() != null ? t.getTrangThai() : "Đã thanh toán");
            stmt.setDate(9, t.getNgayHoaDon());
            stmt.setString(10, t.getMaPhong());
            stmt.setString(11, t.getMaKhachSan());
            stmt.setString(12, t.getMaKhachHang());

            int rowsAffected = stmt.executeUpdate();
            JDBCUtil.closeConnection();
            return rowsAffected > 0 ? 1 : 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int xoaDoiTuong(ThongTinDatPhong t) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int capNhatDoiTuong(ThongTinDatPhong t) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<ThongTinDatPhong> layDanhSach() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ThongTinDatPhong> layDanhSachTheoDK(String maKhachHang) {
        List<ThongTinDatPhong> ds = new ArrayList<>();
        String sql = "SELECT * FROM thongtindatphong WHERE maKhachHang = ?";

        try (Connection conn = JDBCUtil.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKhachHang);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ThongTinDatPhong dp = new ThongTinDatPhong(
                    rs.getString("maDatPhong"),
                    rs.getDate("ngayDatPhong"),
                    rs.getDate("ngayNhanPhong"),
                    rs.getDate("ngayTraPhong"),
                    rs.getString("ghiChu"),
                    rs.getInt("tgSuDung"),
                    rs.getFloat("thanhTien"),
                    rs.getString("trangThai"),
                    rs.getDate("ngayHoaDon"),
                    rs.getString("maPhong"),
                    rs.getString("maKhachSan"),
                    rs.getString("maKhachHang")
                );
                ds.add(dp);
            }

            rs.close();
            JDBCUtil.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public List<ThongTinDatPhong> layDanhSachTheoMaKhachSan(String maKhachSan) {
        List<ThongTinDatPhong> ds = new ArrayList<>();
        String sql = "SELECT * FROM thongtindatphong WHERE maKhachSan = ?";

        try (Connection conn = JDBCUtil.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKhachSan);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ThongTinDatPhong dp = new ThongTinDatPhong(
                    rs.getString("maDatPhong"),
                    rs.getDate("ngayDatPhong"),
                    rs.getDate("ngayNhanPhong"),
                    rs.getDate("ngayTraPhong"),
                    rs.getString("ghiChu"),
                    rs.getInt("tgSuDung"),
                    rs.getFloat("thanhTien"),
                    rs.getString("trangThai"),
                    rs.getDate("ngayHoaDon"),
                    rs.getString("maPhong"),
                    rs.getString("maKhachSan"),
                    rs.getString("maKhachHang")
                );
                ds.add(dp);
            }

            rs.close();
            JDBCUtil.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public double tinhDoanhThu(String maKhachSan) {
        double doanhThu = 0;
        String sql = "SELECT SUM(thanhTien) as total FROM thongtindatphong WHERE maKhachSan = ? AND trangThai = 'Đã thanh toán'";

        try (Connection conn = JDBCUtil.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKhachSan);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                doanhThu = rs.getDouble("total");
            }

            rs.close();
            JDBCUtil.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doanhThu;
    }

    public List<Object[]> tinhDoanhThuTheoLoaiPhong(String maKhachSan) {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT p.loaiPhong, SUM(dp.thanhTien) as total "
                   + "FROM thongtindatphong dp JOIN phong p ON dp.maPhong = p.maPhong "
                   + "WHERE dp.maKhachSan = ? AND dp.trangThai = 'Đã thanh toán' GROUP BY p.loaiPhong";

        try (Connection conn = JDBCUtil.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKhachSan);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                result.add(new Object[]{rs.getString("loaiPhong"), rs.getDouble("total")});
            }

            rs.close();
            JDBCUtil.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}