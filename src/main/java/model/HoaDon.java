package model;

import java.sql.Date;

public class HoaDon {
	private String maHoaDon;
	private double thanhTien;
	private String trangThai;
	private Date ngayHoaDon;
	private int tgSuDung;
	private String maDatPhong;
	public HoaDon() {
		super();
	}
	public HoaDon(String maHoaDon, double thanhTien, String trangThai, Date ngayHoaDon, int tgSuDung,
			String maDatPhong) {
		super();
		this.maHoaDon = maHoaDon;
		this.thanhTien = thanhTien;
		this.trangThai = trangThai;
		this.ngayHoaDon = ngayHoaDon;
		this.tgSuDung = tgSuDung;
		this.maDatPhong = maDatPhong;
	}
	public String getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
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
	public int getTgSuDung() {
		return tgSuDung;
	}
	public void setTgSuDung(int tgSuDung) {
		this.tgSuDung = tgSuDung;
	}
	public String getMaDatPhong() {
		return maDatPhong;
	}
	public void setMaDatPhong(String maDatPhong) {
		this.maDatPhong = maDatPhong;
	}
	
	
}
