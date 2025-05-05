package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Util.JDBCUtil;
import model.Phong;

public class PhongDao implements InterfaceDao<Phong> {

	public static PhongDao getInstance() {
		return new PhongDao();
	}
	public static String getNextMaPh(String maKS) {
		Connection conn = JDBCUtil.connect();
		String nextMaPh = "P001"; // Mã mặc định nếu bảng rỗng
		String sql = "SELECT MAX(maPhong) FROM phong where maKhachSan = ?";

		if (conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, maKS);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					String lastMaPh = rs.getString(1);
					if (lastMaPh != null) {
						int num = Integer.parseInt(lastMaPh.substring(1)) + 1;
						nextMaPh = String.format("P%03d", num);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.closeConnection();
			}
		}
		return nextMaPh;
	}
	 @Override
	    public int themDoiTuong(Phong t) {
	        Connection conn = JDBCUtil.connect();
	        String sql = "INSERT INTO phong (maPhong, loaiPhong, gia, tinhTrang, loaiGiuong, soLuong, moTa, maKhachSan, hinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        int result = 0;

	        if (conn != null) {
	            try {
	                PreparedStatement stmt = conn.prepareStatement(sql);
	                stmt.setString(1, t.getMaPhong());
	                stmt.setString(2, t.getLoaiPhong());
	                stmt.setDouble(3, t.getGia());
	                stmt.setString(4, t.getTinhTrang());
	                stmt.setString(5, t.getLoaiGiuong());
	                stmt.setInt(6, t.getSoLuong());
	                stmt.setString(7, t.getMoTa());
	                stmt.setString(8, t.getMaKhachSan());
	                stmt.setString(9, t.getHinhAnh());

	                result = stmt.executeUpdate();
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                JDBCUtil.closeConnection();
	            }
	        }
	        return result;
	    }

	 @Override
	 public int xoaDoiTuong(Phong t) {
	     Connection conn = JDBCUtil.connect();
	     String sql = "DELETE FROM phong WHERE maPhong = ? AND maKhachSan = ?";
	     int result = 0;

	     if (conn != null) {
	         try {
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             stmt.setString(1, t.getMaPhong());
	             stmt.setString(2, t.getMaKhachSan());

	             result = stmt.executeUpdate();
	         } catch (Exception e) {
	             e.printStackTrace();
	         } finally {
	             JDBCUtil.closeConnection();
	         }
	     }

	     return result;
	 }


	@Override
	public int capNhatDoiTuong(Phong t) {
	    Connection conn = JDBCUtil.connect();
	    String sql = "UPDATE phong SET loaiPhong = ?, gia = ?, tinhTrang = ?, loaiGiuong = ?, soLuong = ?, moTa = ?, hinhAnh = ? WHERE maPhong = ? AND maKhachSan = ?";
	    int result = 0;

	    if (conn != null) {
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, t.getLoaiPhong());
	            stmt.setDouble(2, t.getGia());
	            stmt.setString(3, t.getTinhTrang());
	            stmt.setString(4, t.getLoaiGiuong());
	            stmt.setInt(5, t.getSoLuong());
	            stmt.setString(6, t.getMoTa());
	            stmt.setString(7, t.getHinhAnh());
	            stmt.setString(8, t.getMaPhong());
	            stmt.setString(9, t.getMaKhachSan());

	            result = stmt.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            JDBCUtil.closeConnection();
	        }
	    }

	    return result;
	}


	@Override
	public List<Phong> layDanhSach() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Phong> layDanhSachTheoDK(String id) {
		List<Phong> ds = new ArrayList<>();
		Connection conn = JDBCUtil.connect();
		String sql = "select * from phong where maKhachSan = ?" ;
		if(conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					Phong phong = new Phong(rs.getString("maPhong"),
											rs.getString("loaiPhong"),
											rs.getDouble("gia"),
											rs.getString("tinhTrang"),
											rs.getString("loaiGiuong"),
											rs.getInt("soLuong"),
											rs.getString("moTa"),
											rs.getString("maKhachSan"),
											rs.getString("hinhAnh"));
					ds.add(phong);
				}
				JDBCUtil.closeConnection();
				return ds;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
