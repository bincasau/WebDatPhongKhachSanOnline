package model;

import java.sql.Date;

public class QuanTriVien {
	private String maQuanTriVien;
	private String taiKhoan;
	private String matKhau;
	private String hoTen;
	private String soDienThoai;
	private Date ngaySinh;
	private boolean gioiTinh;
	private String soCCCD;
	public QuanTriVien() {
		super();
	}
	public QuanTriVien(String maQuanTriVien, String taiKhoan, String matKhau, String hoTen, String soDienThoai,
			Date ngaySinh, boolean gioiTinh, String soCCCD) {
		super();
		this.maQuanTriVien = maQuanTriVien;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.hoTen = hoTen;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.soCCCD = soCCCD;
	}
	public String getMaQuanTriVien() {
		return maQuanTriVien;
	}
	public void setMaQuanTriVien(String maQuanTriVien) {
		this.maQuanTriVien = maQuanTriVien;
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
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
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
