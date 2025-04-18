package test;

import java.sql.SQLException;

import Dao.ThongTinDanhGiaDao;
import Dao.ThongTinDatPhongDao;
import model.ThongTinDatPhong;

public class test {
	public static void main(String[] args) {
		try {
			System.out.println(ThongTinDanhGiaDao.getNextMaDanhGia());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
