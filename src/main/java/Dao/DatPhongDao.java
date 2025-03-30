package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import Util.JDBCUtil;
import model.DatPhong;

public class DatPhongDao implements InterfaceDao<DatPhong>{

	public static DatPhongDao getInstance() {
		return new DatPhongDao();
	}
	
	public static String getNextMaDatPhong() {
	    Connection conn = JDBCUtil.connect();
	    String nextMaDatPhong = "DP001"; 
	    String sql = "SELECT MAX(maDatPhong) FROM datphong";

	    if (conn != null) {
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                String lastMaDatPhong = rs.getString(1);
	                if (lastMaDatPhong != null) {
	                    int num = Integer.parseInt(lastMaDatPhong.substring(2)) + 1;
	                    nextMaDatPhong = String.format("DP%03d", num);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            JDBCUtil.closeConnection();
	        }
	    }
	    return nextMaDatPhong;
	}
	
	@Override
	public int themDoiTuong(DatPhong t) {
		Connection conn = JDBCUtil.connect();
		String sql = "INSERT INTO datPhong VALUES (?, ?, ? ,? ,? ,? ,?)";
		int row = 0;
		if(conn!=null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, t.getMaDatPhong());
				stmt.setDate(2, t.getNgayDatPhong());
				stmt.setDate(3, t.getNgayNhanPhong());
				stmt.setDate(4, t.getNgayTraPhong());
				stmt.setString(5, t.getGhiChu());
				stmt.setString(6, t.getMaPhong());
				stmt.setString(7, t.getMaKhachHang());
				row = stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		return row;
	}

	@Override
	public int xoaDoiTuong(DatPhong t) {
		Connection conn = JDBCUtil.connect();
		String sql = "DELETE FROM datphong where maDatPhong = ? ";
		int row = 0;
		if(conn!=null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, t.getMaDatPhong());
				row = stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		return row;
	}

	@Override
	public int capNhatDoiTuong(DatPhong t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DatPhong> layDanhSach() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DatPhong> layDanhSachTheoDK(String dk) {
		// TODO Auto-generated method stub
		return null;
	}
}
