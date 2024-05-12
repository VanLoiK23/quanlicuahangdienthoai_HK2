package Model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "khachhang")
public class Khachhang {

	    @Id
	    @Column(name = "makh")
	    private int maKH;
	    @Column(name = "tenkhachhang")
	    private String hoten;
	    @Column(name = "diachi")
	    private String diachi;
	    @Column(name = "sdt")
	    private String sdt;
	    @Column(name = "trangthai")
	    private int trangthai;
	    @Column(name = "ngaythamgia")
	    private LocalDateTime ngaythamgia;

	    public Khachhang() {
	    }
        public Khachhang(int maKH) {
        	this.maKH=maKH;
        }
	    public Khachhang(int maKH, String hoten, String sdt, String diachi,int trangthai) {
	        this.maKH = maKH;
	        this.hoten = hoten;
	        this.sdt = sdt;
	        this.diachi = diachi;
	        this.trangthai=trangthai;
	    }
	    
	    public Khachhang(int maKH, String hoten, String diachi, String sdt,LocalDateTime ngaythamgia) {
	        this.maKH = maKH;
	        this.hoten = hoten;
	        this.sdt = sdt;
	        this.diachi = diachi;
	        this.ngaythamgia = ngaythamgia;
	    }

	    public LocalDateTime getNgaythamgia() {
	        return ngaythamgia;
	    }

	    public void setNgaythamgia(LocalDateTime localDateTime) {
	        this.ngaythamgia = localDateTime;
	    }

	    public int getMaKH() {
	        return maKH;
	    }

	    public void setMaKH(int maKH) {
	        this.maKH = maKH;
	    }

	    public String getHoten() {
	        return hoten;
	    }

	    public void setHoten(String hoten) {
	        this.hoten = hoten;
	    }

	    public String getSdt() {
	        return sdt;
	    }

	    public void setSdt(String sdt) {
	        this.sdt = sdt;
	    }

	    public String getDiachi() {
	        return diachi;
	    }

	    public void setDiachi(String diachi) {
	        this.diachi = diachi;
	    }

	    
	    public int getTrangthai() {
			return trangthai;
		}

		public void setTrangthai(int trangthai) {
			this.trangthai = trangthai;
		}

		@Override
	    public String toString() {
	        return "KhachHang{" + "maKH=" + maKH + ", hoten=" + hoten + ", sdt=" + sdt + ", diachi=" + diachi + '}';
	    }

	}
