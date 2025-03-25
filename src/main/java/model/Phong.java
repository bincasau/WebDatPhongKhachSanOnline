package model;

public class Phong {
	private String maPhong;
	private String loaiPhong;
	private double gia;
	private String tinhTrang;
	private String loaiGiuong;
	private int soLuong;
	private String moTa;
	private String maKhachSan;
	private String hinhAnh;
	public Phong() {
		super();
	}
	public Phong(String maPhong, String loaiPhong, double gia, String tinhTrang, String loaiGiuong, int soLuong,
			String moTa, String maKhachSan, String hinhAnh) {
		super();
		this.maPhong = maPhong;
		this.loaiPhong = loaiPhong;
		this.gia = gia;
		this.tinhTrang = tinhTrang;
		this.loaiGiuong = loaiGiuong;
		this.soLuong = soLuong;
		this.moTa = moTa;
		this.maKhachSan = maKhachSan;
		this.hinhAnh = hinhAnh;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getLoaiPhong() {
		return loaiPhong;
	}
	public void setLoaiPhong(String loaiPhong) {
		this.loaiPhong = loaiPhong;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public String getLoaiGiuong() {
		return loaiGiuong;
	}
	public void setLoaiGiuong(String loaiGiuong) {
		this.loaiGiuong = loaiGiuong;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getMaKhachSan() {
		return maKhachSan;
	}
	public void setMaKhachSan(String maKhachSan) {
		this.maKhachSan = maKhachSan;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	
}
