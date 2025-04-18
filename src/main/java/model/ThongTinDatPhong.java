package model;

import java.sql.Date;

public class ThongTinDatPhong {
	private String maDatPhong;
	private Date ngayDatPhong;
	private Date ngayNhanPhong;
	private Date ngayTraPhong;
	private String ghiChu;
	private int tgSuDung;
	private float thanhTien;
	private String trangThai;
	private Date ngayHoaDon;
	private String maPhong;
	private String maKhachSan;
	private String maKhachHang;
	public ThongTinDatPhong() {
		super();
	}
	public ThongTinDatPhong(String maDatPhong, Date ngayDatPhong, Date ngayNhanPhong, Date ngayTraPhong, String ghiChu,
			int tgSuDung, float thanhTien, String trangThai, Date ngayHoaDon, String maPhong, String maKhachSan,
			String maKhachHang) {
		super();
		this.maDatPhong = maDatPhong;
		this.ngayDatPhong = ngayDatPhong;
		this.ngayNhanPhong = ngayNhanPhong;
		this.ngayTraPhong = ngayTraPhong;
		this.ghiChu = ghiChu;
		this.tgSuDung = tgSuDung;
		this.thanhTien = thanhTien;
		this.trangThai = trangThai;
		this.ngayHoaDon = ngayHoaDon;
		this.maPhong = maPhong;
		this.maKhachSan = maKhachSan;
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
	public int getTgSuDung() {
		return tgSuDung;
	}
	public void setTgSuDung(int tgSuDung) {
		this.tgSuDung = tgSuDung;
	}
	public float getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(float thanhTien) {
		this.thanhTien = thanhTien;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public Date getNgayHoaDon() {
		return ngayHoaDon;
	}
	public void setNgayHoaDon(Date ngayHoaDon) {
		this.ngayHoaDon = ngayHoaDon;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getMaKhachSan() {
		return maKhachSan;
	}
	public void setMaKhachSan(String maKhachSan) {
		this.maKhachSan = maKhachSan;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	
}
