package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Util.JDBCUtil;
import model.KhachSan;
import model.Phong;

public class PhongDao implements InterfaceDao<Phong> {

	public static PhongDao getInstance() {
		return new PhongDao();
	}
	
	@Override
	public int themDoiTuong(Phong t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int xoaDoiTuong(Phong t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int capNhatDoiTuong(Phong t) {
		// TODO Auto-generated method stub
		return 0;
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
