package model;

public class QuanLy {
	private String maQuanLy;
	private String soDienThoai;
	private String tenQuanLy;
	private String email;
	public QuanLy() {
		super();
	}
	public QuanLy(String maQuanLy, String soDienThoai, String tenQuanLy, String email) {
		super();
		this.maQuanLy = maQuanLy;
		this.soDienThoai = soDienThoai;
		this.tenQuanLy = tenQuanLy;
		this.email = email;
	}
	public String getMaQuanLy() {
		return maQuanLy;
	}
	public void setMaQuanLy(String maQuanLy) {
		this.maQuanLy = maQuanLy;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getTenQuanLy() {
		return tenQuanLy;
	}
	public void setTenQuanLy(String tenQuanLy) {
		this.tenQuanLy = tenQuanLy;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
