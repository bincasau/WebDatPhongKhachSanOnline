package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.JDBCUtil;
import model.ThongTinDanhGia;

public class ThongTinDanhGiaDao implements InterfaceDao<ThongTinDanhGia> {

    public static ThongTinDanhGiaDao getInstance() {
        return new ThongTinDanhGiaDao();
    }

    public static String getNextMaDanhGia() throws SQLException {
        String nextMaDG = "DG0001";
        String sql = "SELECT MAX(maDanhGia) FROM thongtindanhgia";

        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                String lastMaDG = rs.getString(1);
                if (lastMaDG != null && lastMaDG.matches("DG\\d{4}")) {
                    int num = Integer.parseInt(lastMaDG.substring(2)) + 1;
                    nextMaDG = String.format("DG%04d", num);
                }
            }
            return nextMaDG;

        } catch (SQLException e) {
            throw new SQLException("Lỗi khi tạo mã đánh giá: " + e.getMessage(), e);
        }
    }


    @Override
    public int themDoiTuong(ThongTinDanhGia t) {
        String sql = "INSERT INTO thongtindanhgia (maDanhGia, soSao, moTa, ngayDanhGia, maKhachHang, maKhachSan) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getMaDanhGia());
            stmt.setInt(2, t.getSoSao());
            stmt.setString(3, t.getMoTa());
            stmt.setDate(4, t.getNgayDanhGia());
            stmt.setString(5, t.getMaKhachHang());
            stmt.setString(6, t.getMaKhachSan());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int xoaDoiTuong(ThongTinDanhGia t) {
        String sql = "DELETE FROM thongtindanhgia WHERE maDanhGia = ?";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getMaDanhGia());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int capNhatDoiTuong(ThongTinDanhGia t) {
        String sql = "UPDATE thongtindanhgia SET soSao = ?, moTa = ?, ngayDanhGia = ?, maKhachHang = ?, maKhachSan = ? WHERE maDanhGia = ?";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, t.getSoSao());
            stmt.setString(2, t.getMoTa());
            stmt.setDate(3, t.getNgayDanhGia());
            stmt.setString(4, t.getMaKhachHang());
            stmt.setString(5, t.getMaKhachSan());
            stmt.setString(6, t.getMaDanhGia());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<ThongTinDanhGia> layDanhSach() {
        List<ThongTinDanhGia> ds = new ArrayList<>();
        String sql = "SELECT * FROM thongtindanhgia";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ThongTinDanhGia danhGia = new ThongTinDanhGia(
                    rs.getString("maDanhGia"),
                    rs.getInt("soSao"),
                    rs.getString("moTa"),
                    rs.getDate("ngayDanhGia"),
                    rs.getString("maKhachHang"),
                    rs.getString("maKhachSan")
                );
                ds.add(danhGia);
            }
            return ds;
        } catch (SQLException e) {
            e.printStackTrace();
            return ds;
        }
    }

    @Override
    public List<ThongTinDanhGia> layDanhSachTheoDK(String maKhachSan) {
        List<ThongTinDanhGia> ds = new ArrayList<>();
        String sql = "SELECT * FROM thongtindanhgia WHERE maKhachSan = ?";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ThongTinDanhGia danhGia = new ThongTinDanhGia(
                        rs.getString("maDanhGia"),
                        rs.getInt("soSao"),
                        rs.getString("moTa"),
                        rs.getDate("ngayDanhGia"),
                        rs.getString("maKhachHang"),
                        rs.getString("maKhachSan")
                    );
                    ds.add(danhGia);
                }
            }
            return ds;
        } catch (SQLException e) {
            e.printStackTrace();
            return ds;
        }
    }

    public int tinhSoLuongDanhGia(String maKhachSan) {
        String sql = "SELECT COUNT(*) as total FROM thongtindanhgia WHERE maKhachSan = ?";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double tinhDiemTrungBinh(String maKhachSan) {
        String sql = "SELECT AVG(soSao) as avg FROM thongtindanhgia WHERE maKhachSan = ?";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("avg");
                }
            }
            return 0.0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public Map<Integer, Integer> tinhPhanBoDanhGia(String maKhachSan) {
        Map<Integer, Integer> phanBo = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            phanBo.put(i, 0);
        }
        String sql = "SELECT soSao, COUNT(*) as count FROM thongtindanhgia WHERE maKhachSan = ? GROUP BY soSao";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int soSao = rs.getInt("soSao");
                    int count = rs.getInt("count");
                    phanBo.put(soSao, count);
                }
            }
            return phanBo;
        } catch (SQLException e) {
            e.printStackTrace();
            return phanBo;
        }
    }

    // Thêm phương thức tính số lượng đánh giá theo tháng
    public List<Object[]> tinhSoLuongDanhGiaTheoThang(String maKhachSan, String year) {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT MONTH(ngayDanhGia) as month, COUNT(*) as total " +
                     "FROM thongtindanhgia WHERE maKhachSan = ? AND YEAR(ngayDanhGia) = ? " +
                     "GROUP BY MONTH(ngayDanhGia) ORDER BY month";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            stmt.setString(2, year);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new Object[]{rs.getInt("month"), rs.getInt("total")});
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
    }

    // Thêm phương thức tính điểm trung bình theo tháng
    public List<Object[]> tinhDiemTrungBinhTheoThang(String maKhachSan, String year) {
        List<Object[]> result = new ArrayList<>();
        String sql = "SELECT MONTH(ngayDanhGia) as month, AVG(soSao) as avg " +
                     "FROM thongtindanhgia WHERE maKhachSan = ? AND YEAR(ngayDanhGia) = ? " +
                     "GROUP BY MONTH(ngayDanhGia) ORDER BY month";
        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maKhachSan);
            stmt.setString(2, year);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    result.add(new Object[]{rs.getInt("month"), rs.getDouble("avg")});
                }
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
    }
}