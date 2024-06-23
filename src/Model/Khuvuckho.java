package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "khuvuckho")
public class Khuvuckho {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "makhuvuc")
    private int makhuvuc;
	@Column(name = "tenkhuvuc")
    private String tenkhuvuc;
	@Column(name = "ghichu")
    private String ghichu;
	@Column(name = "trangthai")
    private int trangthai;

    public Khuvuckho() {
    }

    public Khuvuckho(int makhuvuc, String tenkhuvuc, String ghichu,int trangthai) {
        this.makhuvuc = makhuvuc;
        this.tenkhuvuc = tenkhuvuc;
        this.ghichu = ghichu;
        this.trangthai=trangthai;
    }
    public Khuvuckho(int makhuvuc, String tenkhuvuc, String ghichu) {
        this.makhuvuc = makhuvuc;
        this.tenkhuvuc = tenkhuvuc;
        this.ghichu = ghichu;
    }
    public Khuvuckho(String tenkhuvuc, String ghichu) {
        this.tenkhuvuc = tenkhuvuc;
        this.ghichu = ghichu;
    }
    public Khuvuckho(String tenkhuvuc) {
        this.tenkhuvuc = tenkhuvuc;
    }

    public int getMakhuvuc() {
        return makhuvuc;
    }

    public void setMakhuvuc(int makhuvuc) {
        this.makhuvuc = makhuvuc;
    }

    public String getTenkhuvuc() {
        return tenkhuvuc;
    }

    public void setTenkhuvuc(String tenkhuvuc) {
        this.tenkhuvuc = tenkhuvuc;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}

	@Override
    public String toString() {
        return tenkhuvuc;
    }
    
}
