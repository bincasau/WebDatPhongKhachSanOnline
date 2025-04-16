package model;

import java.sql.Date;

public class QuanLy {
	private String maQuanLy;
	private String tenQuanLy;
	private String soDienThoai;
	private String email;
	private String taiKhoan;
	private String matKhau;
	private Date ngaySinh;
	private boolean gioiTinh;
	private String soCCCD;
	public QuanLy() {
		super();
	}
	public QuanLy(String maQuanLy, String tenQuanLy, String soDienThoai, String email, String taiKhoan, String matKhau,
			Date ngaySinh, boolean gioiTinh, String soCCCD) {
		super();
		this.maQuanLy = maQuanLy;
		this.tenQuanLy = tenQuanLy;
		this.soDienThoai = soDienThoai;
		this.email = email;
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.soCCCD = soCCCD;
	}
	public String getMaQuanLy() {
		return maQuanLy;
	}
	public void setMaQuanLy(String maQuanLy) {
		this.maQuanLy = maQuanLy;
	}
	public String getTenQuanLy() {
		return tenQuanLy;
	}
	public void setTenQuanLy(String tenQuanLy) {
		this.tenQuanLy = tenQuanLy;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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