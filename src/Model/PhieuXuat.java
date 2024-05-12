package Model;

import java.sql.Timestamp;

public class PhieuXuat  extends Phieu{
    private int makh;

    public PhieuXuat(int makh) {
        this.makh = makh;
    }

    public PhieuXuat(int makh, int maphieu, int manguoitao, Timestamp thoigiantao, long tongTien) {
        super(maphieu, manguoitao, thoigiantao, tongTien);
        this.makh = makh;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }
    

    
}
