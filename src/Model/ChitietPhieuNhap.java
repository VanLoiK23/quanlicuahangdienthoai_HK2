package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chitietphieunhap")
public class ChitietPhieuNhap {
	   @Id
	   @Column(name = "maphieunhap")
	    private int maphieunhap;
	   @Column(name = "maphienbansp")
	    private int maphienbansp;
	   @Column(name = "soluong")
	    private int soluong;
	   @Column(name = "dongia")
	    private int dongia;

	    public ChitietPhieuNhap() {
	    }

	    public ChitietPhieuNhap(int maphieunhap, int maphienbansp, int soluong, int dongia) {
	        this.maphieunhap = maphieunhap;
	        this.maphienbansp = maphienbansp;
	        this.soluong = soluong;
	        this.dongia = dongia;
	    }

	    public int getMaphieunhap() {
	        return maphieunhap;
	    }

	    public void setMaphieunhap(int maphieu) {
	        this.maphieunhap = maphieu;
	    }

	    public int getMaphienbansp() {
	        return maphienbansp;
	    }

	    public void setMaphienbansp(int maphienbansp) {
	        this.maphienbansp = maphienbansp;
	    }

	    public int getSoluong() {
	        return soluong;
	    }

	    public void setSoluong(int soluong) {
	        this.soluong = soluong;
	    }

	    public int getDongia() {
	        return dongia;
	    }

	    public void setDongia(int dongia) {
	        this.dongia = dongia;
	    }
}
