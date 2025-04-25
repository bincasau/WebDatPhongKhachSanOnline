package test;

import java.sql.SQLException;

import Dao.KhachSanDao;
import Dao.QuanLyDao;
import Dao.ThongTinDanhGiaDao;
import Dao.ThongTinDatPhongDao;
import model.ThongTinDatPhong;

public class test {
	public static void main(String[] args) {
		System.out.println(KhachSanDao.getNextMaKS());
	}
}
