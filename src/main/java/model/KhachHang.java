package model;

import java.sql.Date;

public class KhachHang {
	private String maKhachHang;
	private String tenKhachHang;
	private String taiKhoan;
	private String matKhau;
	private String soDienThoai;
	private Date ngaySinh;
	private boolean gioiTinh;
	private String soCCCD;
	public KhachHang() {
		super();
	}
	public KhachHang(String maKhachHang, String tenKhachHang, String taiKhoan, String matKhau, String soDienThoai,
			Date ngaySinh, boolean gioiTinh, String soCCCD) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.soCCCD = soCCCD;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public String getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getSoCCCD() {
		return soCCCD;
	}
	public void setSoCCCD(String soCCCD) {
		this.soCCCD = soCCCD;
	}
	
	
}
