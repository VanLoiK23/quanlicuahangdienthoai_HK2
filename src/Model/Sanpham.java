package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sanpham")
public class Sanpham {

	    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "masp")
	    private int masp;
	    @Column(name = "tensp")
        private String tensp;
	    @Column(name = "hinhanh")
	    private String hinhanh;
		@Column(name = "xuatxu")
	    private int xuatxu;
		@Column(name = "dungluongpin")
	    private int dungluongpin;
		@Column(name = "kichthuocman")
	    private double kichthuocman;
		@Column(name = "hedieuhanh")
	    private int hedieuhanh;
		@Column(name = "phienbanhdh")
	    private int phienbanhdh;
		@Column(name = "camerasau")
	    private String camerasau;
		@Column(name = "cameratruoc")
	    private String cameratruoc;
		@Column(name = "thoigianbaohanh")
	    private int thoigianbaohanh;
		@Column(name = "thuonghieu")
	    private int thuonghieu;
		@Column(name = "khuvuckho")
	    private int khuvuckho;
		@Column(name = "Soluongton")
	    private int soluongton;
		@Column(name = "trangthai")
	    private int trangthai;
		 @Transient
	    private String giaTienkemdonvi;
		 @Transient
	    private String tenthuonghieu;
		 @Transient
		private String tenkhuvuc;
		 @Transient
	    private String tenxuatxu;
		 @Transient
	    private String tenhedieuhanh;

	    public Sanpham() {
	    }
        public Sanpham(String tensp,String hinhanh,String giaTienkemdonvi) {
        	this.tensp=tensp;
        	this.hinhanh=hinhanh;
        	this.giaTienkemdonvi=giaTienkemdonvi;
        }
        public Sanpham(String tensp) {
        	this.tensp=tensp;
        }
        
        //chi de minh hoa
	    public String getGiaTienkemdonvi() {
			return giaTienkemdonvi;
		}
		public void setGiaTienkemdonvi(String giaTienkemdonvi) {
			this.giaTienkemdonvi = giaTienkemdonvi;
		}
		public Sanpham(String tensp,int soluongton,String hinhanh) {
			this.tensp=tensp;
			this.soluongton=soluongton;
			this.hinhanh=hinhanh;
		}
		public Sanpham(int masp) {
        	this.masp=masp;
        }
		
		
		public Sanpham(int masp, String tensp, String hinhanh, int xuatxu, int dungluongpin, double kichthuocman, int hedieuhanh, int phienbanhdh, String camerasau, String cameratruoc, int thoigianbaohanh, int thuonghieu, int khuvuckho, int soluongton) {
	        this.masp = masp;
	        this.tensp = tensp;
	        this.hinhanh = hinhanh;
	        this.xuatxu = xuatxu;
	        this.dungluongpin = dungluongpin;
	        this.kichthuocman = kichthuocman;
	        this.hedieuhanh = hedieuhanh;
	        this.phienbanhdh = phienbanhdh;
	        this.camerasau = camerasau;
	        this.cameratruoc = cameratruoc;
	        this.thoigianbaohanh = thoigianbaohanh;
	        this.thuonghieu = thuonghieu;
	        this.khuvuckho = khuvuckho;
	        this.soluongton = soluongton;
	    }
		public Sanpham(int masp, String tensp, String hinhanh, int xuatxu, int dungluongpin, double kichthuocman, int hedieuhanh, int phienbanhdh, String camerasau, String cameratruoc, int thoigianbaohanh, int thuonghieu, int khuvuckho, int soluongton,int trangthai) {
	        this.masp = masp;
	        this.tensp = tensp;
	        this.hinhanh = hinhanh;
	        this.xuatxu = xuatxu;
	        this.dungluongpin = dungluongpin;
	        this.kichthuocman = kichthuocman;
	        this.hedieuhanh = hedieuhanh;
	        this.phienbanhdh = phienbanhdh;
	        this.camerasau = camerasau;
	        this.cameratruoc = cameratruoc;
	        this.thoigianbaohanh = thoigianbaohanh;
	        this.thuonghieu = thuonghieu;
	        this.khuvuckho = khuvuckho;
	        this.soluongton = soluongton;
	        this.trangthai=trangthai;
	    }
		public Sanpham(String tensp, String hinhanh, int xuatxu, int dungluongpin, double kichthuocman, int hedieuhanh, int phienbanhdh, String camerasau, String cameratruoc, int thoigianbaohanh, int thuonghieu, int khuvuckho) {
	        this.tensp = tensp;
	        this.hinhanh = hinhanh;
	        this.xuatxu = xuatxu;
	        this.dungluongpin = dungluongpin;
	        this.kichthuocman = kichthuocman;
	        this.hedieuhanh = hedieuhanh;
	        this.phienbanhdh = phienbanhdh;
	        this.camerasau = camerasau;
	        this.cameratruoc = cameratruoc;
	        this.thoigianbaohanh = thoigianbaohanh;
	        this.thuonghieu = thuonghieu;
	        this.khuvuckho = khuvuckho;
	    }
		public Sanpham(int masp,String tensp, String hinhanh, int xuatxu, int dungluongpin, double kichthuocman, int hedieuhanh, int phienbanhdh, String camerasau, String cameratruoc, int thoigianbaohanh, int thuonghieu, int khuvuckho) {
	        this.tensp = tensp;
	        this.masp=masp;
	        this.hinhanh = hinhanh;
	        this.xuatxu = xuatxu;
	        this.dungluongpin = dungluongpin;
	        this.kichthuocman = kichthuocman;
	        this.hedieuhanh = hedieuhanh;
	        this.phienbanhdh = phienbanhdh;
	        this.camerasau = camerasau;
	        this.cameratruoc = cameratruoc;
	        this.thoigianbaohanh = thoigianbaohanh;
	        this.thuonghieu = thuonghieu;
	        this.khuvuckho = khuvuckho;
	    }
		public Sanpham(int masp, String tensp, int soluongton,String camerasau,String cameratruoc, int phienbanhdh, int thoigianbaohanh,String tenthuonghieu,String tenhedieuhanh,double kichthuocman, String hinhanh,int dungluongpin, String tenxuatxu, String tenkhuvuc) {
	       this.masp=masp;
	       this.tensp=tensp;
	       this.soluongton=soluongton;
	       this.camerasau = camerasau;
	       this.cameratruoc = cameratruoc;
	       this.thoigianbaohanh = thoigianbaohanh;
	       this.phienbanhdh = phienbanhdh;
	       this.tenthuonghieu=tenthuonghieu;
	       this.tenhedieuhanh=tenhedieuhanh;
	       this.kichthuocman=kichthuocman;
	       this.hinhanh=hinhanh;
	       this.dungluongpin=dungluongpin;
	       this.tenxuatxu=tenxuatxu;
	       this.tenkhuvuc=tenkhuvuc;
	    }

	    public int getMasp() {
	        return masp;
	    }

	    public void setMasp(int masp) {
	        this.masp = masp;
	    }

	    public String getTensp() {
	        return tensp;
	    }

	    public void setTensp(String tensp) {
	        this.tensp = tensp;
	    }

	    public String getHinhanh() {
	        return hinhanh;
	    }

	    public void setHinhanh(String hinhanh) {
	        this.hinhanh = hinhanh;
	    }

	    public int getXuatxu() {
	        return xuatxu;
	    }

	    public void setXuatxu(int xuatxu) {
	        this.xuatxu = xuatxu;
	    }

	    public int getDungluongpin() {
	        return dungluongpin;
	    }

	    public void setDungluongpin(int dungluongpin) {
	        this.dungluongpin = dungluongpin;
	    }

	    public double getKichthuocman() {
	        return kichthuocman;
	    }

	    public void setKichthuocman(double kichthuocman) {
	        this.kichthuocman = kichthuocman;
	    }

	    public int getHedieuhanh() {
	        return hedieuhanh;
	    }

	    public void setHedieuhanh(int hedieuhanh) {
	        this.hedieuhanh = hedieuhanh;
	    }

	    public int getPhienbanhdh() {
	        return phienbanhdh;
	    }

	    public void setPhienbanhdh(int phienbanhdh) {
	        this.phienbanhdh = phienbanhdh;
	    }

	    public String getCamerasau() {
	        return camerasau;
	    }

	    public void setCamerasau(String camerasau) {
	        this.camerasau = camerasau;
	    }

	    public String getCameratruoc() {
	        return cameratruoc;
	    }

	    public void setCameratruoc(String cameratruoc) {
	        this.cameratruoc = cameratruoc;
	    }

	    public int getThoigianbaohanh() {
	        return thoigianbaohanh;
	    }

	    public void setThoigianbaohanh(int thoigianbaohanh) {
	        this.thoigianbaohanh = thoigianbaohanh;
	    }

	    public int getThuonghieu() {
	        return thuonghieu;
	    }

	    public void setThuonghieu(int thuonghieu) {
	        this.thuonghieu = thuonghieu;
	    }

	    public int getKhuvuckho() {
	        return khuvuckho;
	    }

	    public void setKhuvuckho(int khuvuckho) {
	        this.khuvuckho = khuvuckho;
	    }

	    public int getSoluongton() {
	        return soluongton;
	    }

	    public void setSoluongton(int soluongton) {
	        this.soluongton = soluongton;
	    }
		public int getTrangthai() {
			return trangthai;
		}
		public void setTrangthai(int trangthai) {
			this.trangthai = trangthai;
		}
		public String getTenthuonghieu() {
			return tenthuonghieu;
		}
		public void setTenthuonghieu(String tenthuonghieu) {
			this.tenthuonghieu = tenthuonghieu;
		}
		public String getTenxuatxu() {
			return tenxuatxu;
		}
		public void setTenxuatxu(String tenxuatxu) {
			this.tenxuatxu = tenxuatxu;
		}
		public String getTenhedieuhanh() {
			return tenhedieuhanh;
		}
		public void setTenhedieuhanh(String tenhedieuhanh) {
			this.tenhedieuhanh = tenhedieuhanh;
		}
		public String getTenkhuvuc() {
			return tenkhuvuc;
		}
		public void setTenkhuvuc(String tenkhuvuc) {
			this.tenkhuvuc = tenkhuvuc;
		}
		@Override
		public String toString() {
			return "Sanpham [masp=" + masp + ", tensp=" + tensp + ", hinhanh=" + hinhanh + ", xuatxu=" + xuatxu
					+ ", dungluongpin=" + dungluongpin + ", kichthuocman=" + kichthuocman + ", hedieuhanh=" + hedieuhanh
					+ ", phienbanhdh=" + phienbanhdh + ", camerasau=" + camerasau + ", cameratruoc=" + cameratruoc
					+ ", thoigianbaohanh=" + thoigianbaohanh + ", thuonghieu=" + thuonghieu + ", khuvuckho=" + khuvuckho
					+ ", soluongton=" + soluongton + "]";
		}
		
	    
	}