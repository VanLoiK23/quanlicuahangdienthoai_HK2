package Main;


import AtttributeSanPham.Ram;
import DAO.Account_DAO;
import DAO.Hedieuhanh_DAO;
import DAO.Kho_DAO;
import DAO.Thuonghieu_DAO;
import DAO.Xuatxu_DAO;
import DAO.ram_DAO;
import Model.Khachhang;
import Model.Taikhoan;

import java.lang.String;

public class Test2 {
    
    	
    	public static void main(String[] args) {
    		
			try {
				Account_DAO a=new Account_DAO();
				Taikhoan taikhoan = new Taikhoan(8,"Gia Heo","heo321","Điện Biên","0912345676","khách hàng",1);
				Khachhang k=new Khachhang(8);
		        int result = a.delete(k);
		        
		        if(result!=0) {
		        	System.out.println("OK");
		        }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		

		}
}

