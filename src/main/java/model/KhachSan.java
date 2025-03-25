package model;

public class KhachSan {
	private String maKhachSan;
	private String tenKhachSan;
	private String diaChi;
	private String khuVuc;
	private String moTa;
	private double danhGiaTrungBinh;
	private String maQuanLy;
	private String hinhAnh;
	public KhachSan() {
		super();
	}
	public KhachSan(String maKhachSan, String tenKhachSan, String diaChi, String khuVuc, String moTa,
			double danhGiaTrungBinh, String maQuanLy, String hinhAnh) {
		super();
		this.maKhachSan = maKhachSan;
		this.tenKhachSan = tenKhachSan;
		this.diaChi = diaChi;
		this.khuVuc = khuVuc;
		this.moTa = moTa;
		this.danhGiaTrungBinh = danhGiaTrungBinh;
		this.maQuanLy = maQuanLy;
		this.hinhAnh = hinhAnh;
	}
	public String getMaKhachSan() {
		return maKhachSan;
	}
	public void setMaKhachSan(String maKhachSan) {
		this.maKhachSan = maKhachSan;
	}
	public String getTenKhachSan() {
		return tenKhachSan;
	}
	public void setTenKhachSan(String tenKhachSan) {
		this.tenKhachSan = tenKhachSan;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getKhuVuc() {
		return khuVuc;
	}
	public void setKhuVuc(String khuVuc) {
		this.khuVuc = khuVuc;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public double getDanhGiaTrungBinh() {
		return danhGiaTrungBinh;
	}
	public void setDanhGiaTrungBinh(double danhGiaTrungBinh) {
		this.danhGiaTrungBinh = danhGiaTrungBinh;
	}
	public String getMaQuanLy() {
		return maQuanLy;
	}
	public void setMaQuanLy(String maQuanLy) {
		this.maQuanLy = maQuanLy;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	@Override
	public String toString() {
		return "KhachSan [maKhachSan=" + maKhachSan + ", tenKhachSan=" + tenKhachSan + ", diaChi=" + diaChi
				+ ", khuVuc=" + khuVuc + ", moTa=" + moTa + ", danhGiaTrungBinh=" + danhGiaTrungBinh + ", maQuanLy="
				+ maQuanLy + ", hinhAnh=" + hinhAnh + "]";
	}
	
}
