package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Util.JDBCUtil;
import model.KhachHang;

public class KhachHangDao implements InterfaceDao<KhachHang> {

	public static KhachHangDao getInstance() {
		return new KhachHangDao();
	}

	public static String getNextMaKhachHang() {
		Connection conn = JDBCUtil.connect();
		String nextMaKH = "KH001"; // Mã mặc định nếu bảng rỗng
		String sql = "SELECT MAX(maKhachHang) FROM khachhang";

		if (conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					String lastMaKH = rs.getString(1);
					if (lastMaKH != null) {
						int num = Integer.parseInt(lastMaKH.substring(2)) + 1;
						nextMaKH = String.format("KH%03d", num);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.closeConnection();
			}
		}
		return nextMaKH;
	}

	@Override
	public int themDoiTuong(KhachHang t) {
		Connection conn = JDBCUtil.connect();
		int row = 0;
		String sql = "INSERT INTO khachhang VALUES (?, ? ,? ,? ,? ,? ,? ,?)";

		if (conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, t.getMaKhachHang()); // Gán mã tự động
				stmt.setString(2, t.getTenKhachHang());
				stmt.setString(3, t.getTaiKhoan());
				stmt.setString(4, t.getMatKhau());
				stmt.setString(5, t.getSoDienThoai());
				stmt.setDate(6, t.getNgaySinh());
				stmt.setBoolean(7, t.isGioiTinh());
				stmt.setString(8, t.getSoCCCD());

				row = stmt.executeUpdate();
				System.out.println(row);
			} catch (Exception e) {
				System.out.println(row);
				e.printStackTrace();
			} finally {
				JDBCUtil.closeConnection();
			}
		}
		return row;
	}

	@Override
	public int xoaDoiTuong(KhachHang t) {
		Connection conn = JDBCUtil.connect();
		String sql = "DELETE FROM khachhang WHERE maKhachHang = ?";
		int row = 0;
		if (conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, t.getMaKhachHang());
				row = stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JDBCUtil.closeConnection();
		return row;
	}

	@Override
	public int capNhatDoiTuong(KhachHang t) {
		Connection conn = JDBCUtil.connect();
		int row = 0;
		String sql = "UPDATE khachhang SET tenKhachHang = ?, taiKhoan = ?, matKhau = ?, soDienThoai = ?, ngaySinh = ?, gioiTinh = ?, soCCCD = ? WHERE maKhachHang = ?";

		if (conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, t.getTenKhachHang());
				stmt.setString(2, t.getTaiKhoan());
				stmt.setString(3, t.getMatKhau());
				stmt.setString(4, t.getSoDienThoai());
				stmt.setDate(5, t.getNgaySinh());
				stmt.setBoolean(6, t.isGioiTinh());
				stmt.setString(7, t.getSoCCCD());
				stmt.setString(8, t.getMaKhachHang());

				row = stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.closeConnection();
			}
		}
		return row; // Trả về số dòng bị ảnh hưởng
	}

	@Override
	public List<KhachHang> layDanhSach() {
		List<KhachHang> ds = new ArrayList<>();
		Connection conn = JDBCUtil.connect(); // Kết nối với cơ sở dữ liệu
		String sql = "SELECT * FROM khachhang"; // Truy vấn SQL để lấy tất cả các khách hàng

		if (conn != null) {
			try {
				PreparedStatement stmt = conn.prepareStatement(sql); // Chuẩn bị câu lệnh SQL
				ResultSet rs = stmt.executeQuery(); // Thực thi truy vấn và nhận kết quả
				while (rs.next()) {
					// Chuyển kết quả từ ResultSet thành đối tượng KhachHang
					KhachHang kh = new KhachHang();
					kh.setMaKhachHang(rs.getString("maKhachHang"));
					kh.setTenKhachHang(rs.getString("tenKhachHang"));
					kh.setTaiKhoan(rs.getString("taiKhoan"));
					kh.setMatKhau(rs.getString("matKhau"));
					kh.setSoDienThoai(rs.getString("soDienThoai"));
					kh.setNgaySinh(rs.getDate("ngaySinh"));
					kh.setGioiTinh(rs.getBoolean("gioiTinh"));
					kh.setSoCCCD(rs.getString("soCCCD"));

					ds.add(kh); // Thêm đối tượng KhachHang vào danh sách
				}
				conn.close(); // Đóng kết nối
			} catch (Exception e) {
				e.printStackTrace(); // In lỗi nếu có
			}
		}
		return ds; // Trả về danh sách khách hàng
	}

	@Override
	public List<KhachHang> layDanhSachTheoDK(String dk) {
		return null;
	}

}
