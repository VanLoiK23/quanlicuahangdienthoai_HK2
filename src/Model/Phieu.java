package Model;

import java.sql.Timestamp;

public class Phieu {
	    private int maphieu;
	    private int manguoitao;
	    private Timestamp thoigiantao;
	    private long tongTien;

	    public Phieu() {
	    }

	    public Phieu(int maphieu, int manguoitao, Timestamp thoigiantao, long tongTien) {
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

	    
	}
