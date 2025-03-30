package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import Util.JDBCUtil;
import model.HoaDon;

public class HoaDonDao implements InterfaceDao<HoaDon>{

	public static HoaDonDao getInstance() {
		return new HoaDonDao();
	}
	
	@Override
	public int themDoiTuong(HoaDon t) {
		Connection conn = JDBCUtil.connect();
		String sql = "INSERT INTO hoadon values (?, ?, ?, ?, ?, ?)";
		int row = 0;
		if(conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, t.getMaHoaDon());
				stmt.setDouble(2, t.getThanhTien());
				stmt.setString(3, t.getTrangThai());
				stmt.setDate(4, t.getNgayHoaDon());
				stmt.setInt(5, t.getTgSuDung());
				stmt.setString(6, t.getMaDatPhong());
				row = stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}

	@Override
	public int xoaDoiTuong(HoaDon t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int capNhatDoiTuong(HoaDon t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<HoaDon> layDanhSach() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HoaDon> layDanhSachTheoDK(String dk) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getNextMaHoaDon() {
	    Connection conn = JDBCUtil.connect();
	    String nextMaHoaDon = "HD001"; 
	    String sql = "SELECT MAX(maHoaDon) FROM hoadon";

	    if (conn != null) {
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                String lastMaHoaDon = rs.getString(1);
	                if (lastMaHoaDon != null) {
	                    int num = Integer.parseInt(lastMaHoaDon.substring(2)) + 1;
	                    nextMaHoaDon = String.format("KH%03d", num);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            JDBCUtil.closeConnection();
	        }
	    }
	    return nextMaHoaDon;
	}
}
