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
        String nextMaDG = "D0001"; // Mã mặc định nếu bảng rỗng
        String sql = "SELECT MAX(maDanhGia) FROM thongtindanhgia";

        try (Connection conn = JDBCUtil.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                String lastMaDG = rs.getString(1);
                if (lastMaDG != null && lastMaDG.matches("D\\d{4}")) {
                    int num = Integer.parseInt(lastMaDG.substring(1)) + 1;
                    nextMaDG = String.format("D%04d", num);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Lỗi khi tạo mã đánh giá: " + e.getMessage(), e);
        }

        return nextMaDG;
    }

    @Override
    public int themDoiTuong(ThongTinDanhGia t) {
        String sql = "INSERT INTO thongtindanhgia (maDanhGia, soSao, moTa, maKhachHang, maKhachSan) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        int rowsAffected = 0;

        try {
            conn = JDBCUtil.connect();
            if (conn != null) {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, t.getMaDanhGia());
                stmt.setInt(2, t.getSoSao());
                stmt.setString(3, t.getMoTa());
                stmt.setString(4, t.getMaKhachHang());
                stmt.setString(5, t.getMaKhachSan());

                rowsAffected = stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JDBCUtil.closeConnection();
        }

        return rowsAffected;
    }

    @Override
    public int xoaDoiTuong(ThongTinDanhGia t) {
        String sql = "DELETE FROM thongtindanhgia WHERE maDanhGia = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        int rowsAffected = 0;

        try {
            conn = JDBCUtil.connect();
            if (conn != null) {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, t.getMaDanhGia());

                rowsAffected = stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JDBCUtil.closeConnection();
        }

        return rowsAffected;
    }

    @Override
    public int capNhatDoiTuong(ThongTinDanhGia t) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<ThongTinDanhGia> layDanhSach() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ThongTinDanhGia> layDanhSachTheoDK(String dk) {
        List<ThongTinDanhGia> ds = new ArrayList<>();
        String sql = "SELECT * FROM thongtindanhgia WHERE maKhachSan = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.connect();
            if (conn != null) {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, dk);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    String maDanhGia = rs.getString("maDanhGia");
                    int soSao = rs.getInt("soSao");
                    String moTa = rs.getString("moTa");
                    String maKhachHang = rs.getString("maKhachHang");
                    String maKhachSan = rs.getString("maKhachSan");

                    ThongTinDanhGia danhGia = new ThongTinDanhGia(maDanhGia, soSao, moTa, maKhachHang, maKhachSan);
                    ds.add(danhGia);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return ds;
    }

    public int tinhSoLuongDanhGia(String maKhachSan) {
        int soLuong = 0;
        String sql = "SELECT COUNT(*) as total FROM thongtindanhgia WHERE maKhachSan = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.connect();
            if (conn != null) {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, maKhachSan);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    soLuong = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JDBCUtil.closeConnection();
        }

        return soLuong;
    }

    public double tinhDiemTrungBinh(String maKhachSan) {
        double diemTrungBinh = 0;
        String sql = "SELECT AVG(soSao) as avg FROM thongtindanhgia WHERE maKhachSan = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.connect();
            if (conn != null) {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, maKhachSan);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    diemTrungBinh = rs.getDouble("avg");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JDBCUtil.closeConnection();
        }

        return diemTrungBinh;
    }

    public Map<Integer, Integer> tinhPhanBoDanhGia(String maKhachSan) {
        Map<Integer, Integer> phanBo = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            phanBo.put(i, 0); // Initialize counts for 1-5 stars
        }

        String sql = "SELECT soSao, COUNT(*) as count FROM thongtindanhgia WHERE maKhachSan = ? GROUP BY soSao";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.connect();
            if (conn != null) {
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, maKhachSan);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    int soSao = rs.getInt("soSao");
                    int count = rs.getInt("count");
                    phanBo.put(soSao, count);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JDBCUtil.closeConnection();
        }

        return phanBo;
    }
}