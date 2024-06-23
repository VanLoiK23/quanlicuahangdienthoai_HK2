package ModelUserPhone;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.fxml.Initializable;


@Entity
@Table(name = "sanpham")
public class modelsanpham implements Serializable {
	@Id
	@Column(name="masp")
	private int masp;
	@Column(name="tensp")
	private String Phonename;
	@Column(name="hinhanh")
	private String Img;
	@Transient
	private int xuatxu;
	@Transient
	private int dungluongpin;
	@Transient
	private Double kichthuocman;
	@Transient
	private int hedieuhanh;
	@Transient
	private int phienbanhdh;
	@Transient
	private String camerasau;
	@Transient
	private String CameraTruoc;
	@Transient 
	private int Thoigianbaohanh;
	@Transient
	private int thuonghieu;
	@Transient 
	private int Khuvuckho;
	private int mapbansp;
	@Column(name="soluongton")
	private int soluongton;
	private int giaxuat;
	
	public modelsanpham() {
		super();
	}
	public modelsanpham(int masp,String phonename, String img,int mapbsp, int soluongton, int giaxuat) {
		this.masp= masp;
		Phonename = phonename;
		Img = img;
		this.mapbansp = mapbsp;
		this.soluongton = soluongton;
		this.giaxuat = giaxuat;
	}
	
	public int getMapbansp() {
		return mapbansp;
	}
	public void setMapbansp(int mapbansp) {
		this.mapbansp = mapbansp;
	}
	public int getMasp() {
		return masp;
	}
	public void setMasp(int masp) {
		this.masp = masp;
	}
	public String getPhonename() {
		return Phonename;
	}
	public void setPhonename(String phonename) {
		Phonename = phonename;
	}
	public String getImg() {
		return Img;
	}
	public void setImg(String img) {
		Img = img;
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
	public Double getKichthuocman() {
		return kichthuocman;
	}
	public void setKichthuocman(Double kichthuocman) {
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
	public String getCameraTruoc() {
		return CameraTruoc;
	}
	public void setCameraTruoc(String cameraTruoc) {
		CameraTruoc = cameraTruoc;
	}
	public int getThoigianbaohanh() {
		return Thoigianbaohanh;
	}
	public void setThoigianbaohanh(int thoigianbaohanh) {
		Thoigianbaohanh = thoigianbaohanh;
	}
	public int getThuonghieu() {
		return thuonghieu;
	}
	public void setThuonghieu(int thuonghieu) {
		this.thuonghieu = thuonghieu;
	}
	public int getKhuvuckho() {
		return Khuvuckho;
	}
	public void setKhuvuckho(int khuvuckho) {
		Khuvuckho = khuvuckho;
	}
	public int getSoluongton() {
		return soluongton;
	}
	public void setSoluongton(int soluongton) {
		this.soluongton = soluongton;
	}
	public int getGiaxuat() {
		return giaxuat;
	}
	public void setGiaxuat(int giaxuat) {
		this.giaxuat = giaxuat;
	}
	@Override
	public String toString() {
		return "modelsanpham [masp=" + masp + ", Phonename=" + Phonename + ", Img=" + Img + ", xuatxu=" + xuatxu
				+ ", dungluongpin=" + dungluongpin + ", kichthuocman=" + kichthuocman + ", hedieuhanh=" + hedieuhanh
				+ ", phienbanhdh=" + phienbanhdh + ", camerasau=" + camerasau + ", CameraTruoc=" + CameraTruoc
				+ ", Thoigianbaohanh=" + Thoigianbaohanh + ", thuonghieu=" + thuonghieu + ", Khuvuckho=" + Khuvuckho
				+ ", mapbansp=" + mapbansp + ", soluongton=" + soluongton + ", giaxuat=" + giaxuat + "]";
	}
	
	

}
