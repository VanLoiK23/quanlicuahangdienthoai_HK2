package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nhacungcap")
public class Nhacungcap {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "manhacungcap")
	private int mancc;
	@Column(name = "tennhacungcap")
    private String tenncc;
	@Column(name = "diachi")
    private String diachi;
	@Column(name = "email")
    private String email;
	@Column(name = "sdt")
    private String sdt;
	@Column(name = "trangthai")
    private int trangthai;

    public Nhacungcap() {
    }
    public Nhacungcap(int mancc, String tenncc, String diachi, String email, String sdt,int trangthai) {
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.diachi = diachi;
        this.email = email;
        this.sdt = sdt;
        this.trangthai=trangthai;
    }
    public Nhacungcap(String tenncc, String diachi, String email, String sdt) {
        this.tenncc = tenncc;
        this.diachi = diachi;
        this.email = email;
        this.sdt = sdt;
    }
    public Nhacungcap(int mancc,String tenncc, String diachi, String email, String sdt) {
    	this.mancc=mancc;
        this.tenncc = tenncc;
        this.diachi = diachi;
        this.email = email;
        this.sdt = sdt;
    }

    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return tenncc;
    }
}
