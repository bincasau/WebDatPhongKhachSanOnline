package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Util.JDBCUtil;
import model.KhachSan;

public class KhachSanDao implements InterfaceDao<KhachSan>{

	public static KhachSanDao getInstance() {
		return new KhachSanDao();
	}
	
	public static String getNextMaKS() {
		Connection conn = JDBCUtil.connect();
		String nextMaKS = "KS001"; // Mã mặc định nếu bảng rỗng
		String sql = "SELECT MAX(maKhachSan) FROM khachsan";

		if (conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					String lastMaKS = rs.getString(1);
					if (lastMaKS != null) {
						int num = Integer.parseInt(lastMaKS.substring(2)) + 1;
						nextMaKS = String.format("KS%03d", num);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.closeConnection();
			}
		}
		return nextMaKS;
	}
	
	@Override
	public int themDoiTuong(KhachSan ks) {
	    int ketQua = 0;
	    Connection conn = JDBCUtil.connect();
	    String sql = "INSERT INTO khachsan (maKhachSan, tenKhachSan, diaChi, khuVuc, moTa, danhGiaTrungBinh, maQuanLy, hinhAnh) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    
	    if (conn != null) {
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, ks.getMaKhachSan());
	            stmt.setString(2, ks.getTenKhachSan());
	            stmt.setString(3, ks.getDiaChi());
	            stmt.setString(4, ks.getKhuVuc());
	            stmt.setString(5, ks.getMoTa());
	            stmt.setDouble(6, ks.getDanhGiaTrungBinh());
	            stmt.setString(7, ks.getMaQuanLy());
	            stmt.setString(8, ks.getHinhAnh());

	            ketQua = stmt.executeUpdate(); // trả về số dòng bị ảnh hưởng
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            JDBCUtil.closeConnection();
	        }
	    }

	    return ketQua;
	}

	@Override
	public int xoaDoiTuong(KhachSan ks) {
	    int ketQua = 0;
	    Connection conn = JDBCUtil.connect();
	    String sql = "DELETE FROM khachsan WHERE maKhachSan = ?";

	    if (conn != null) {
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, ks.getMaKhachSan());

	            ketQua = stmt.executeUpdate(); // Trả về số dòng bị ảnh hưởng
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            JDBCUtil.closeConnection();
	        }
	    }

	    return ketQua;
	}


	@Override
	public int capNhatDoiTuong(KhachSan ks) {
	    int ketQua = 0;
	    Connection conn = JDBCUtil.connect();
	    String sql = "UPDATE khachsan SET tenKhachSan = ?, diaChi = ?, khuVuc = ?, moTa = ?, danhGiaTrungBinh = ?, maQuanLy = ?, hinhAnh = ? WHERE maKhachSan = ?";

	    if (conn != null) {
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, ks.getTenKhachSan());
	            stmt.setString(2, ks.getDiaChi());
	            stmt.setString(3, ks.getKhuVuc());
	            stmt.setString(4, ks.getMoTa());
	            stmt.setDouble(5, ks.getDanhGiaTrungBinh());
	            stmt.setString(6, ks.getMaQuanLy());
	            stmt.setString(7, ks.getHinhAnh());
	            stmt.setString(8, ks.getMaKhachSan()); // WHERE

	            ketQua = stmt.executeUpdate(); // Trả về số dòng bị ảnh hưởng
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            JDBCUtil.closeConnection();
	        }
	    }

	    return ketQua;
	}


	@Override
	public List<KhachSan> layDanhSach() {
		List<KhachSan> ds = new ArrayList<>();
		Connection conn = JDBCUtil.connect();
		String sql = "select * from khachsan";
		if(conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					KhachSan ks = new KhachSan(rs.getString("maKhachSan"), 
												rs.getString("tenKhachSan"), 
												rs.getString("diaChi"), 
												rs.getString("khuVuc"), 
												rs.getString("moTa"), 
												rs.getDouble("danhGiaTrungBinh"), 
												rs.getString("maQuanLy"), 
												rs.getString("hinhAnh"));
					ds.add(ks);
				}
				JDBCUtil.closeConnection();
				return ds;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<KhachSan> layDanhSachTheoDK(String diaChi) {
		List<KhachSan> ds = new ArrayList<>();
		Connection conn = JDBCUtil.connect();
		String sql = "select * from khachsan where khuVuc = ?" ;
		if(conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, diaChi);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					KhachSan ks = new KhachSan(rs.getString("maKhachSan"), 
												rs.getString("tenKhachSan"), 
												rs.getString("diaChi"), 
												rs.getString("khuVuc"), 
												rs.getString("moTa"), 
												rs.getDouble("danhGiaTrungBinh"), 
												rs.getString("maQuanLy"), 
												rs.getString("hinhAnh"));
					ds.add(ks);
				}
				JDBCUtil.closeConnection();
				return ds;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public List<KhachSan> layDanhSachTheoID(String id){
		List<KhachSan> ds = new ArrayList<>();
		Connection conn = JDBCUtil.connect();
		String sql = "select * from khachsan where maKhachSan = ?" ;
		if(conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					KhachSan ks = new KhachSan(rs.getString("maKhachSan"), 
												rs.getString("tenKhachSan"), 
												rs.getString("diaChi"), 
												rs.getString("khuVuc"), 
												rs.getString("moTa"), 
												rs.getDouble("danhGiaTrungBinh"), 
												rs.getString("maQuanLy"), 
												rs.getString("hinhAnh"));
					ds.add(ks);
				}
				JDBCUtil.closeConnection();
				return ds;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public List<KhachSan> layDanhSachTheoMaQL(String id){
		List<KhachSan> ds = new ArrayList<>();
		Connection conn = JDBCUtil.connect();
		String sql = "select * from khachsan where maQuanLy = ?" ;
		if(conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					KhachSan ks = new KhachSan(rs.getString("maKhachSan"), 
												rs.getString("tenKhachSan"), 
												rs.getString("diaChi"), 
												rs.getString("khuVuc"), 
												rs.getString("moTa"), 
												rs.getDouble("danhGiaTrungBinh"), 
												rs.getString("maQuanLy"), 
												rs.getString("hinhAnh"));
					ds.add(ks);
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
