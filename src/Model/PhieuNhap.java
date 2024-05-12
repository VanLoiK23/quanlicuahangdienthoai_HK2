package Model;

import java.sql.Timestamp;

public class PhieuNhap extends Phieu{
    private int manhacungcap;

    public PhieuNhap(int manhacungcap) {
        this.manhacungcap = manhacungcap;
    }

    public PhieuNhap(int manhacungcap, int maphieu, int manguoitao, Timestamp thoigiantao, long tongTien) {
        super(maphieu, manguoitao, thoigiantao, tongTien);
        this.manhacungcap = manhacungcap;
    }

    public int getManhacungcap() {
        return manhacungcap;
    }

    public void setManhacungcap(int manhacungcap) {
        this.manhacungcap = manhacungcap;
    }
    
}
