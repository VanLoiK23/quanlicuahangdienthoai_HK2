package Model;

import java.sql.Date;

public class Nhanvien extends Taikhoan{
   private int masv;
   private String sex;
   private Date ngaysinh;
   private String email;
   
public Nhanvien(int masv,String username,String password, String sex, Date ngaysinh, String sdt,String email, int trangthai, String address) {
	super(username, password, address, sdt,trangthai);
	this.masv = masv;
	this.sex = sex;
	this.ngaysinh = ngaysinh;
	this.email=email;
}

public int getMasv() {
	return masv;
}

public void setMasv(int masv) {
	this.masv = masv;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getSex() {
	return sex;
}

public void setSex(String sex) {
	this.sex = sex;
}

public Date getNgaysinh() {
	return ngaysinh;
}

public void setNgaysinh(Date ngaysinh) {
	this.ngaysinh = ngaysinh;
}
   
}
