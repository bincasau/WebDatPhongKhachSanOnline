package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Util.JDBCUtil;
import model.QuanLy;

public class QuanLyDao implements InterfaceDao<QuanLy> {

	public static QuanLyDao getInstance() {
		return new QuanLyDao();
	}

	@Override
	public int themDoiTuong(QuanLy t) {
		return 0;
	}

	@Override
	public int xoaDoiTuong(QuanLy t) {
		return 0;
	}

	@Override
	public int capNhatDoiTuong(QuanLy t) {
		return 0;
	}

	@Override
	public List<QuanLy> layDanhSach() {
		return null;
	}

	@Override
	public List<QuanLy> layDanhSachTheoDK(String dk) {
		return null;
	}

	// Lấy danh sách quản lý theo mã khách sạn
	public List<QuanLy> layDanhSachTheoMaKhachSan(String maKS) {
		List<QuanLy> ds = new ArrayList<>();
		Connection conn = JDBCUtil.connect();
		String sql = "SELECT * FROM quanly as ql JOIN khachsan as ks ON ql.maQuanLy = ks.maQuanLy WHERE ks.maKhachSan = ?";

		if (conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, maKS);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					QuanLy ql = new QuanLy();
					ql.setMaQuanLy(rs.getString("maQuanLy"));
					ql.setTenQuanLy(rs.getString("tenQuanLy"));
					ql.setSoDienThoai(rs.getString("soDienThoai"));
					ql.setEmail(rs.getString("email")); // Thêm dòng này để lấy email
					ql.setTaiKhoan(rs.getString("taiKhoan"));
					ql.setMatKhau(rs.getString("matKhau"));
					ql.setNgaySinh(rs.getDate("ngaySinh"));
					ql.setGioiTinh(rs.getBoolean("gioiTinh"));
					ql.setSoCCCD(rs.getString("soCCCD"));

					ds.add(ql);
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ds;
	}

}
