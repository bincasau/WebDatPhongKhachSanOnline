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
	
	@Override
	public int themDoiTuong(KhachSan t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int xoaDoiTuong(KhachSan t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int capNhatDoiTuong(KhachSan t) {
		// TODO Auto-generated method stub
		return 0;
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

}
