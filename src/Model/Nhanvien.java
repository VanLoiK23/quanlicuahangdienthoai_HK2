package Model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nhanvien")
public class Nhanvien {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "manv")
    private int manv;
	@Column(name = "gioitinh")
    private String sex;
	@Column(name = "ngaysinh")
    private LocalDate ngaysinh;
	@Column(name = "email")
    private String email;
	    @Column(name = "hoten")
	    private String username;
	    @Column(name = "sdt")
	    private String sdt;
	    @Column(name = "trangthai")
	    private int trangthai;
	    
	    public Nhanvien() {
	    	
	    }
	    
	    public Nhanvien(int manv, String username, String sex, Date ngaysinh, String sdt,int trangthai,String email) {
			super();
			this.manv = manv;
			this.sex = sex;
			LocalDate localDate = ngaysinh.toInstant()
	                   .atZone(ZoneId.systemDefault())
	                   .toLocalDate();;
			this.ngaysinh= localDate;
			this.email = email;
			this.username = username;
			this.sdt = sdt;
			this.trangthai=trangthai;
		}
		public Nhanvien(int manv, String username, String sex, LocalDate ngaysinh, String email, String sdt) {
			super();
			this.manv = manv;
			this.sex = sex;
			this.ngaysinh = ngaysinh;
			this.email = email;
			this.username = username;
			this.sdt = sdt;
		}
		public Nhanvien(String username, String sex, LocalDate localDate, String email, String sdt) {
			super();
			this.sex = sex;
			this.ngaysinh = localDate;
			this.email = email;
			this.username = username;
			this.sdt = sdt;
		}
		public int getManv() {
			return manv;
		}
		public void setManv(int manv) {
			this.manv = manv;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public LocalDate getNgaysinh() {
			return ngaysinh;
		}
		public void setNgaysinh(LocalDate ngaysinh) {
			this.ngaysinh = ngaysinh;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getSdt() {
			return sdt;
		}
		public void setSdt(String sdt) {
			this.sdt = sdt;
		}
		public int getTrangthai() {
			return trangthai;
		}
		public void setTrangthai(int trangthai) {
			this.trangthai = trangthai;
		}
	   
		@Override
	    public String toString() {
	        return username;
	    }
   
}
