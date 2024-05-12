package Model;

public class ChitietPhieuXuat {
	    private int maphieuxuat;
	    private int maphienbansp;
	    private int soluong;
	    private int dongia;

	    public ChitietPhieuXuat() {
	    }

	    public ChitietPhieuXuat(int maphieuxuat, int maphienbansp, int soluong, int dongia) {
	        this.maphieuxuat = maphieuxuat;
	        this.maphienbansp = maphienbansp;
	        this.soluong = soluong;
	        this.dongia = dongia;
	    }

	    public int getMaphieuxuat() {
	        return maphieuxuat;
	    }

	    public void setMaphieuxuat(int maphieuxuat) {
	        this.maphieuxuat = maphieuxuat;
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

