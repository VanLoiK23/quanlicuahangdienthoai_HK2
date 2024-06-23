package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chitietphieuxuat")
public class ChitietPhieuXuat {
	   @Id
	   @Column(name = "maphieuxuat")
	    private int maphieuxuat;
	   @Column(name = "maphienbansp")
	    private int maphienbansp;
	   @Column(name = "soluong")
	    private int soluong;
	   @Column(name = "dongia")
	    private int dongia;

	    public ChitietPhieuXuat() {
	    }

	    public ChitietPhieuXuat(int maphieuxuat, int maphienbansp, int soluong, int dongia) {
	        this.maphieuxuat = maphieuxuat;
	        this.maphienbansp = maphienbansp;
	        this.soluong = soluong;
	        this.dongia = dongia;
	    }

	    public int getMaphieuxuat() {
	        return maphieuxuat;
	    }

	    public void setMaphieuxuat(int maphieuxuat) {
	        this.maphieuxuat = maphieuxuat;
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

