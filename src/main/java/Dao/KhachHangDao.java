package Dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import Util.JDBCUtil;
import model.KhachHang;

public class KhachHangDao implements InterfaceDao<KhachHang>{

	@Override
	public int themDoiTuong(KhachHang t) {
		
		return 0;
	}

	@Override
	public int xoaDoiTuong(KhachHang t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int capNhatDoiTuong(KhachHang t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<KhachHang> layDanhSach() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KhachHang> layDanhSachTheoDK(String dk) {
		return null;
	}

}
