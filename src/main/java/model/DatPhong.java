package model;

import java.sql.Date;

public class DatPhong {
	private String maDatPhong;
	private Date ngayDatPhong;
	private Date ngayNhanPhong;
	private Date ngayTraPhong;
	private String ghiChu;
	private String maPhong;
	private String maKhachHang;
	public DatPhong() {
		super();
	}
	public DatPhong(String maDatPhong, Date ngayDatPhong, Date ngayNhanPhong, Date ngayTraPhong, String ghiChu,
			String maPhong, String maKhachHang) {
		super();
		this.maDatPhong = maDatPhong;
		this.ngayDatPhong = ngayDatPhong;
		this.ngayNhanPhong = ngayNhanPhong;
		this.ngayTraPhong = ngayTraPhong;
		this.ghiChu = ghiChu;
		this.maPhong = maPhong;
		this.maKhachHang = maKhachHang;
	}
	public String getMaDatPhong() {
		return maDatPhong;
	}
	public void setMaDatPhong(String maDatPhong) {
		this.maDatPhong = maDatPhong;
	}
	public Date getNgayDatPhong() {
		return ngayDatPhong;
	}
	public void setNgayDatPhong(Date ngayDatPhong) {
		this.ngayDatPhong = ngayDatPhong;
	}
	public Date getNgayNhanPhong() {
		return ngayNhanPhong;
	}
	public void setNgayNhanPhong(Date ngayNhanPhong) {
		this.ngayNhanPhong = ngayNhanPhong;
	}
	public Date getNgayTraPhong() {
		return ngayTraPhong;
	}
	public void setNgayTraPhong(Date ngayTraPhong) {
		this.ngayTraPhong = ngayTraPhong;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	
}
