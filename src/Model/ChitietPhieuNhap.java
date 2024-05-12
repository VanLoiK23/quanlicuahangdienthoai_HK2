package Model;

public class ChitietPhieuNhap {
	    private int maphieunhap;
	    private int maphienbansp;
	    private int soluong;
	    private int dongia;

	    public ChitietPhieuNhap() {
	    }

	    public ChitietPhieuNhap(int maphieunhap, int maphienbansp, int soluong, int dongia) {
	        this.maphieunhap = maphieunhap;
	        this.maphienbansp = maphienbansp;
	        this.soluong = soluong;
	        this.dongia = dongia;
	    }

	    public int getMaphieunhap() {
	        return maphieunhap;
	    }

	    public void setMaphieunhap(int maphieu) {
	        this.maphieunhap = maphieu;
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
