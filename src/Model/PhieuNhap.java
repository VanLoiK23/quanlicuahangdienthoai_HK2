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
@Table(name = "phieunhap")
public class PhieuNhap {
	@Column(name = "manhacungcap")
    private int manhacungcap;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maphieunhap")
    private int maphieu;
    @Column(name = "nguoitaophieunhap")
    private int manguoitao;
    @Column(name = "tongtien")
    private long tongTien;
    
    @Transient
    private Timestamp thoigiantao;
    @Transient
    private String tennv;
	 @Transient
    private String tenncc;

    public PhieuNhap(int manhacungcap) {
        this.manhacungcap = manhacungcap;
    }
    public PhieuNhap() {
    	
    }
    public PhieuNhap( int maphieu,int manhacungcap, int manguoitao, long tongTien) {
		super();
		this.manhacungcap = manhacungcap;
		this.maphieu = maphieu;
		this.manguoitao = manguoitao;
		this.tongTien = tongTien;
	}
    public PhieuNhap(int maphieu,String tenncc, String tennv, Timestamp thoigiantao, long tongTien) {
		super();
		this.tennv = tennv;
		this.maphieu = maphieu;
		this.tenncc = tenncc;
		this.thoigiantao = thoigiantao;
		this.tongTien = tongTien;
	}
    public PhieuNhap(int manhacungcap,int maphieu, int manguoitao, Timestamp thoigiantao, long tongTien) {
		super();
		this.manhacungcap = manhacungcap;
		this.maphieu = maphieu;
		this.manguoitao = manguoitao;
		this.thoigiantao = thoigiantao;
		this.tongTien = tongTien;
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
	public int getManhacungcap() {
        return manhacungcap;
    }

    public void setManhacungcap(int manhacungcap) {
        this.manhacungcap = manhacungcap;
    }
	public String getTennv() {
		return tennv;
	}
	public void setTennv(String tennv) {
		this.tennv = tennv;
	}
	public String getTenncc() {
		return tenncc;
	}
	public void setTenncc(String tenncc) {
		this.tenncc = tenncc;
	}
    
    
}
