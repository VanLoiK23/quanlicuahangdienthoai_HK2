package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ctsanpham")
public class ChitietSanpham {
	 @Id
	 @Column(name = "maimei")
	private long imei;
	 @Column(name = "maphienbansp")
    private int maphienbansp;
	 @Column(name = "maphieunhap")
    private int maphieunhap;
	 @Column(name = "maphieuxuat")
    private Integer maphieuxuat;
	 @Column(name = "tinhtrang")
    private int tinhtrang;

	 @Transient
	 private String tt;
    public ChitietSanpham() {
    }

    public ChitietSanpham(long imei, int maphienbansp, int maphieunhap, int maphieuxuat, int tinhtrang) {
        this.imei = imei;
        this.maphienbansp = maphienbansp;
        this.maphieunhap = maphieunhap;
        this.maphieuxuat = maphieuxuat;
        this.tinhtrang = tinhtrang;
        if(tinhtrang==1) {
        	this.tt="Chưa bán";
        }
        else {
        	this.tt="Đã bán";
        }
    }
    
    public ChitietSanpham(long imei, int maphienbansp, int maphieunhap, int tinhtrang) {
        this.imei = imei;
        this.maphienbansp = maphienbansp;
        this.maphieunhap = maphieunhap;
        this.maphieuxuat=  null;
        this.tinhtrang = tinhtrang;
    }
    public ChitietSanpham(long imei) {
        this.imei = imei;
      
    }

    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
        this.imei = imei;
    }

    public int getMaphienbansp() {
        return maphienbansp;
    }

    public void setMaphienbansp(int maphienbansp) {
        this.maphienbansp = maphienbansp;
    }

    public int getMaphieunhap() {
        return maphieunhap;
    }

    public void setMaphieunhap(int maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public int getMaphieuxuat() {
        return maphieuxuat;
    }

    public void setMaphieuxuat(int maphieuxuat) {
        this.maphieuxuat = maphieuxuat;
    }

    public int getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(int tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
    

    public String getTt() {
		return tt;
	}

	public void setTt(String tt) {
		this.tt = tt;
	}

	public void setMaphieuxuat(Integer maphieuxuat) {
		this.maphieuxuat = maphieuxuat;
	}

	@Override
    public String toString() {
        return "ChitietSanpham{" + "imei=" + imei + ", maphienbansp=" + maphienbansp + ", maphieunhap=" + maphieunhap + ", maphieuxuat=" + maphieuxuat + ", tinhtrang=" + tinhtrang + '}';
    }
}
