package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taikhoan")
public class Taikhoan {
	    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
	    private int id;
	    @Column(name = "username")
	    private String username;
	    @Column(name = "matkhau")
	    private String matkhau;
	    @Column(name = "diachi")
	    private String address;
	    @Column(name = "dienthoai")
	    private String sdt;
	    @Column(name = "vaitro")
	    private String vaitro;
	    @Column(name = "trangthai")
	    private int trangthai;
	    public Taikhoan() {
	        
	    }
        public Taikhoan(String username,String matkhau) {
        	super();
			this.username = username;
			this.matkhau = matkhau;
	    }
        public Taikhoan(int id,String username,String matkhau,String address,String dienthoai,String vaitro,int trangthai) {
        	super();
        	this.id=id;
			this.username = username;
			this.matkhau = matkhau;
			this.address=address;
			this.sdt=dienthoai;
			this.vaitro=vaitro;
			this.trangthai=trangthai;
	    }
		public Taikhoan(String username, String matkhau, String address, String sdt,int trangthai) {
			super();
			this.username = username;
			this.matkhau = matkhau;
			this.address = address;
			this.sdt = sdt;
			this.trangthai=trangthai;
		}
		public Taikhoan(String username, String matkhau, String address, String sdt,int trangthai,String vaitro) {
			super();
			this.username = username;
			this.matkhau = matkhau;
			this.address = address;
			this.sdt = sdt;
			this.trangthai=trangthai;
			this.vaitro=vaitro;
		}
		public Taikhoan(String username, String matkhau,String vaitro) {
			super();
			this.username = username;
			this.matkhau = matkhau;
			this.vaitro=vaitro;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getMatkhau() {
			return matkhau;
		}
		public void setMatkhau(String matkhau) {
			this.matkhau = matkhau;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getSdt() {
			return sdt;
		}
		public void setSdt(String sdt) {
			this.sdt = sdt;
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getTrangthai() {
			return trangthai;
		}
		public void setTrangthai(int trangthai) {
			this.trangthai = trangthai;
		}
		public String getVaitro() {
			return vaitro;
		}
		public void setVaitro(String vaitro) {
			this.vaitro = vaitro;
		}
	    
	   
	}
