package Model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "phieuxuat")
public class PhieuXuat {
	@Column(name = "makh")
    private int makh;    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maphieuxuat")
    private int maphieu;
    @Column(name = "nguoitaophieuxuat")
    private int manguoitao;
    private Timestamp thoigiantao;
    @Column(name = "tongtien")
    private long tongTien;
    
    @Transient
    private String tennv;
	 @Transient
    private String tenkh;

    public PhieuXuat(int makh) {
        this.makh = makh;
    }

    public PhieuXuat() {
    	
    }
    public PhieuXuat(int makh, int maphieu, int manguoitao, Timestamp thoigiantao, long tongTien) {
		super();
		this.makh = makh;
		this.maphieu = maphieu;
		this.manguoitao = manguoitao;
		this.thoigiantao = thoigiantao;
		this.tongTien = tongTien;
	}

    public PhieuXuat(int maphieu,String tenkh, String tennv, Timestamp thoigiantao, long tongTien) {
		super();
		this.tennv = tennv;
		this.maphieu = maphieu;
		this.tenkh = tenkh;
		this.thoigiantao = thoigiantao;
		this.tongTien = tongTien;
	}

	public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

	public int getMaphieu() {
		return maphieu;
	}

	public void setMaphieu(int maphieu) {
		this.maphieu = maphieu;
	}

	public int getManguoitao() {
		return manguoitao;
	}

	public void setManguoitao(int manguoitao) {
		this.manguoitao = manguoitao;
	}

	public Timestamp getThoigiantao() {
		return thoigiantao;
	}

	public void setThoigiantao(Timestamp thoigiantao) {
		this.thoigiantao = thoigiantao;
	}

	public long getTongTien() {
		return tongTien;
	}

	public void setTongTien(long tongTien) {
		this.tongTien = tongTien;
	}

	public String getTennv() {
		return tennv;
	}

	public void setTennv(String tennv) {
		this.tennv = tennv;
	}

	public String getTenkh() {
		return tenkh;
	}

	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
	}
    

    
}
