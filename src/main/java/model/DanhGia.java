package model;

public class DanhGia {
	private String maDanhGia;
	private int soSao;
	private String moTa;
	private String maKhachHang;
	private String maKhachSan;
	public DanhGia() {
		super();
	}
	public DanhGia(String maDanhGia, int soSao, String moTa, String maKhachHang, String maKhachSan) {
		super();
		this.maDanhGia = maDanhGia;
		this.soSao = soSao;
		this.moTa = moTa;
		this.maKhachHang = maKhachHang;
		this.maKhachSan = maKhachSan;
	}
	public String getMaDanhGia() {
		return maDanhGia;
	}
	public void setMaDanhGia(String maDanhGia) {
		this.maDanhGia = maDanhGia;
	}
	public int getSoSao() {
		return soSao;
	}
	public void setSoSao(int soSao) {
		this.soSao = soSao;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getMaKhachHang() {
		return maKhachHang;
	}
	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	public String getMaKhachSan() {
		return maKhachSan;
	}
	public void setMaKhachSan(String maKhachSan) {
		this.maKhachSan = maKhachSan;
	}
	
}
