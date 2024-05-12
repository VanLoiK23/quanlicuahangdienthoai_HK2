package Model;

public class ChitietSanpham {
	private String imei;
    private int maphienbansp;
    private int maphieunhap;
    private int maphieuxuat;
    private int tinhtrang;

    public ChitietSanpham() {
    }

    public ChitietSanpham(String imei, int maphienbansp, int maphieunhap, int maphieuxuat, int tinhtrang) {
        this.imei = imei;
        this.maphienbansp = maphienbansp;
        this.maphieunhap = maphieunhap;
        this.maphieuxuat = maphieuxuat;
        this.tinhtrang = tinhtrang;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
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

    @Override
    public String toString() {
        return "ChitietSanpham{" + "imei=" + imei + ", maphienbansp=" + maphienbansp + ", maphieunhap=" + maphieunhap + ", maphieuxuat=" + maphieuxuat + ", tinhtrang=" + tinhtrang + '}';
    }
}
