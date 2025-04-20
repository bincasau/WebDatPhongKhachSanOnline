package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Util.JDBCUtil;
import model.QuanTriVien;

public class QuanTriVienDao implements InterfaceDao<QuanTriVien> {

	public static QuanTriVienDao getInstance() {
		return new QuanTriVienDao();
	}
	@Override
	public int themDoiTuong(QuanTriVien t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int xoaDoiTuong(QuanTriVien t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int capNhatDoiTuong(QuanTriVien t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<QuanTriVien> layDanhSach() {
		List<QuanTriVien> ds = new ArrayList<>();
		String sql = "SELECT * FROM quantrivien";

		try (Connection conn = JDBCUtil.connect(); // Giả sử bạn có class Database để lấy Connection
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			while (rs.next()) {
				String maQuanTriVien = rs.getString("maQuanTriVien");
				String taiKhoan = rs.getString("taiKhoan");
				String matKhau = rs.getString("matKhau");
				String hoTen = rs.getString("hoTen");
				String soDienThoai = rs.getString("soDienThoai");
				Date ngaySinh = rs.getDate("ngaySinh");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				String soCCCD = rs.getString("soCCCD");

				QuanTriVien qtv = new QuanTriVien(maQuanTriVien, taiKhoan, matKhau, hoTen, soDienThoai, ngaySinh,
						gioiTinh, soCCCD);
				ds.add(qtv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	@Override
	public List<QuanTriVien> layDanhSachTheoDK(String dk) {
		// TODO Auto-generated method stub
		return null;
	}

}
