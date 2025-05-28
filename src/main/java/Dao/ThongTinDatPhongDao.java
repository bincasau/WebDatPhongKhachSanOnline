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
        String nextMaDP = "DP0001";
        String sql = "SELECT MAX(maDatPhong) FROM thongtindatphong";

        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String lastMaDP = rs.getString(1);
                if (lastMaDP != null && lastMaDP.matches("DP\\d{4}")) {
                    int num = Integer.parseInt(lastMaDP.substring(2)) + 1;
                    nextMaDP = String.format("DP%04d", num);
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
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            JDBCUtil.closeConnection();
        }
    }

    @Override
    public int xoaDoiTuong(ThongTinDatPhong t) {
        String sql = "DELETE FROM thongtindatphong WHERE maDatPhong = ?";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getMaDatPhong());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            JDBCUtil.closeConnection();
        }
    }

    @Override
    public int capNhatDoiTuong(ThongTinDatPhong t) {
        String sql = "UPDATE thongtindatphong SET ngayDatPhong = ?, ngayNhanPhong = ?, ngayTraPhong = ?, ghiChu = ?, "
                   + "tgSuDung = ?, thanhTien = ?, trangThai = ?, ngayHoaDon = ?, maPhong = ?, maKhachSan = ?, maKhachHang = ? "
                   + "WHERE maDatPhong = ?";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, t.getNgayDatPhong() != null ? t.getNgayDatPhong() : new Date(System.currentTimeMillis()));
            stmt.setDate(2, t.getNgayNhanPhong());
            stmt.setDate(3, t.getNgayTraPhong());
            stmt.setString(4, t.getGhiChu());
            stmt.setInt(5, t.getTgSuDung());
            stmt.setFloat(6, t.getThanhTien());
            stmt.setString(7, t.getTrangThai() != null ? t.getTrangThai() : "Đã thanh toán");
            stmt.setDate(8, t.getNgayHoaDon());
            stmt.setString(9, t.getMaPhong());
            stmt.setString(10, t.getMaKhachSan());
            stmt.setString(11, t.getMaKhachHang());
            stmt.setString(12, t.getMaDatPhong());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            JDBCUtil.closeConnection();
        }
    }

    @Override
    public List<ThongTinDatPhong> layDanhSach() {
        List<ThongTinDatPhong> ds = new ArrayList<>();
        String sql = "SELECT * FROM thongtindatphong";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection();
        }
        return ds;
    }

    @Override
    public List<ThongTinDatPhong> layDanhSachTheoDK(String maKhachHang) {
        List<ThongTinDatPhong> ds = new ArrayList<>();
        String sql = "SELECT * FROM thongtindatphong WHERE maKhachHang = ?";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection();
        }
        return ds;
    }

    public List<ThongTinDatPhong> layDanhSachTheoMaKhachSan(String maKhachSan) {
        List<ThongTinDatPhong> ds = new ArrayList<>();
        String sql = "SELECT * FROM thongtindatphong WHERE maKhachSan = ?";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection();
        }
        return ds;
    }

    public double tinhDoanhThu(String maKhachSan) {
        double doanhThu = 0;
        String sql = "SELECT SUM(thanhTien) as total FROM thongtindatphong WHERE maKhachSan = ? AND trangThai = 'Đã thanh toán'";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                doanhThu = rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection();
        }
        return doanhThu;
    }

    public double tinhDoanhThu(String maKhachSan, String monthYear) {
        double doanhThu = 0;
        String sql = "SELECT SUM(thanhTien) as total FROM thongtindatphong WHERE maKhachSan = ? AND trangThai = 'Đã thanh toán' AND DATE_FORMAT(ngayDatPhong, '%Y-%m') = ?";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            stmt.setString(2, monthYear);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                doanhThu = rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection();
        }
        return doanhThu;
    }

    public double tinhDoanhThuNam(String maKhachSan, String year) {
        double doanhThu = 0;
        String sql = "SELECT SUM(thanhTien) as total FROM thongtindatphong WHERE maKhachSan = ? AND trangThai = 'Đã thanh toán' AND YEAR(ngayDatPhong) = ?";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            stmt.setString(2, year);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                doanhThu = rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection();
        }
        return doanhThu;
    }

    public int tinhSoLuongDatPhong(String maKhachSan) {
        int soLuong = 0;
        String sql = "SELECT COUNT(*) as total FROM thongtindatphong WHERE maKhachSan = ? AND trangThai = 'Đã thanh toán'";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection();
        }
        return soLuong;
    }

    public int tinhSoLuongDatPhong(String maKhachSan, String monthYear) {
        int soLuong = 0;
        String sql = "SELECT COUNT(*) as total FROM thongtindatphong WHERE maKhachSan = ? AND trangThai = 'Đã thanh toán' AND DATE_FORMAT(ngayDatPhong, '%Y-%m') = ?";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            stmt.setString(2, monthYear);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection();
        }
        return soLuong;
    }

    public int tinhSoLuongDatPhongNam(String maKhachSan, String year) {
        int soLuong = 0;
        String sql = "SELECT COUNT(*) as total FROM thongtindatphong WHERE maKhachSan = ? AND trangThai = 'Đã thanh toán' AND YEAR(ngayDatPhong) = ?";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            stmt.setString(2, year);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection();
        }
        return soLuong;
    }

    public List<Object[]> tinhDoanhThuTheoLoaiPhong(String maKhachSan) {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT p.loaiPhong, SUM(dp.thanhTien) as total "
                   + "FROM thongtindatphong dp JOIN phong p ON dp.maPhong = p.maPhong "
                   + "WHERE dp.maKhachSan = ? AND dp.trangThai = 'Đã thanh toán' GROUP BY p.loaiPhong";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new Object[]{rs.getString("loaiPhong"), rs.getDouble("total")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection();
        }
        return result;
    }

    public List<Object[]> tinhDoanhThuTheoLoaiPhong(String maKhachSan, String monthYear) {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT p.loaiPhong, SUM(dp.thanhTien) as total "
                   + "FROM thongtindatphong dp JOIN phong p ON dp.maPhong = p.maPhong "
                   + "WHERE dp.maKhachSan = ? AND dp.trangThai = 'Đã thanh toán' AND DATE_FORMAT(dp.ngayDatPhong, '%Y-%m') = ? GROUP BY p.loaiPhong";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            stmt.setString(2, monthYear);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new Object[]{rs.getString("loaiPhong"), rs.getDouble("total")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection();
        }
        return result;
    }

    public List<Object[]> tinhDoanhThuTheoLoaiPhongNam(String maKhachSan, String year) {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT p.loaiPhong, SUM(dp.thanhTien) as total "
                   + "FROM thongtindatphong dp JOIN phong p ON dp.maPhong = p.maPhong "
                   + "WHERE dp.maKhachSan = ? AND dp.trangThai = 'Đã thanh toán' AND YEAR(dp.ngayDatPhong) = ? GROUP BY p.loaiPhong";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            stmt.setString(2, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new Object[]{rs.getString("loaiPhong"), rs.getDouble("total")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection();
        }
        return result;
    }

    public List<Object[]> tinhDoanhThuTheoThang(String maKhachSan, String year) {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT DATE_FORMAT(ngayDatPhong, '%Y-%m') as month, SUM(thanhTien) as total "
                   + "FROM thongtindatphong WHERE maKhachSan = ? AND trangThai = 'Đã thanh toán' AND YEAR(ngayDatPhong) = ? "
                   + "GROUP BY DATE_FORMAT(ngayDatPhong, '%Y-%m')";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            stmt.setString(2, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new Object[]{rs.getString("month"), rs.getDouble("total")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection();
        }
        return result;
    }

    public List<Object[]> tinhSoLuongDatPhongTheoThang(String maKhachSan, String year) {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT DATE_FORMAT(ngayDatPhong, '%Y-%m') as month, COUNT(*) as total "
                   + "FROM thongtindatphong WHERE maKhachSan = ? AND trangThai = 'Đã thanh toán' AND YEAR(ngayDatPhong) = ? "
                   + "GROUP BY DATE_FORMAT(ngayDatPhong, '%Y-%m')";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            stmt.setString(2, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(new Object[]{rs.getString("month"), rs.getInt("total")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection();
        }
        return result;
    }
    public int capNhatTrangThaiHuy(String maDatPhong) {
        String sql = "UPDATE thongtindatphong SET trangThai = 'Đã hủy' WHERE maDatPhong = ?";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maDatPhong);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            JDBCUtil.closeConnection();
        }
    }
    public ThongTinDatPhong layTheoId(String maDatPhong) {
        ThongTinDatPhong dp = null;
        String sql = "SELECT * FROM thongtindatphong WHERE maDatPhong = ?";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maDatPhong);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                dp = new ThongTinDatPhong(
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection();
        }
        return dp;
    }
}