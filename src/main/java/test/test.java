package test;

import java.sql.SQLException;

import Dao.ThongTinDatPhongDao;
import model.ThongTinDatPhong;

public class test {
	public static void main(String[] args) {
		try {
			ThongTinDatPhong ttdp = new ThongTinDatPhong(ThongTinDatPhongDao.getNextMaDatPhong(), null, null, null, null, 0, 0, null, null, "P193", "KH090");
			ThongTinDatPhongDao.getInstance().themDoiTuong(ttdp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
