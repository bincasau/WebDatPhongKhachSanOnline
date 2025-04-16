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

public class ThongTinDatPhongDao implements InterfaceDao<ThongTinDatPhong>{

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
	        // Ghi log hoặc xử lý lỗi chi tiết hơn
	        throw new SQLException("Lỗi khi tạo mã đặt phòng: " + e.getMessage(), e);
	    }
	    
	    return nextMaDP;
	}
	
	@Override
	public int themDoiTuong(ThongTinDatPhong t) {
	    if (t == null || t.getMaPhong() == null || t.getMaKhachHang() == null) {
	        return 0;
	    }
	    String sql = "INSERT INTO thongtindatphong (maDatPhong, ngayDatPhong, ngayNhanPhong, ngayTraPhong, ghiChu, tgSuDung, thanhTien, trangThai, ngayHoaDon, maPhong, maKhachHang) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    
	    try (Connection conn = JDBCUtil.connect();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        System.out.println("heloo ttdp");
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
	        stmt.setString(11, t.getMaKhachHang());
	        
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
	public List<ThongTinDatPhong> layDanhSachTheoDK(String dk) {
	    List<ThongTinDatPhong> ds = new ArrayList<>();
	    Connection conn = JDBCUtil.connect();
	    String sql = "SELECT * FROM thongtindatphong WHERE maKhachHang = ?";

	    if (conn != null) {
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, dk);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                ThongTinDatPhong dp = new ThongTinDatPhong();
	                dp.setMaDatPhong(rs.getString("maDatPhong"));
	                dp.setNgayDatPhong(rs.getDate("ngayDatPhong"));
	                dp.setNgayNhanPhong(rs.getDate("ngayNhanPhong"));
	                dp.setNgayTraPhong(rs.getDate("ngayTraPhong"));
	                dp.setGhiChu(rs.getString("ghiChu"));
	                dp.setTgSuDung(rs.getInt("tgSuDung"));
	                dp.setThanhTien(rs.getFloat("thanhTien"));
	                dp.setTrangThai(rs.getString("trangThai"));
	                dp.setNgayHoaDon(rs.getDate("ngayHoaDon"));
	                dp.setMaPhong(rs.getString("maPhong"));
	                dp.setMaKhachHang(rs.getString("maKhachHang"));
	                ds.add(dp);
	            }

	            rs.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            JDBCUtil.closeConnection();
	        }
	    }

	    return ds;
	}

}
