package ModelUserPhone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//2
@Entity
@Table(name="giohangsanpham")
public class ModelGiohang {
@Id
@Column(name="masp")
private int masp;
@Column(name="tensp")
private String tensp;
@Column(name="hinhanh")
private String hinhanh;
@Column(name="soluongton")
private int soluongton;
@Column(name="giatien")
private int giatien;
@Column(name="rom")
private String rom;
public ModelGiohang(int masp, String tensp, String hinhanh, int soluongton, int giatien, String rom) {
	super();
	this.masp = masp;
	this.tensp = tensp;
	this.hinhanh = hinhanh;
	this.soluongton = soluongton;
	this.giatien = giatien;
	this.rom = rom;
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
public Integer getSoluongton() {
	return soluongton;
}
public void setSoluongton(int soluongton) {
	this.soluongton = soluongton;
}
public Integer getGiatien() {
	return giatien;
}
public void setGiatien(int giatien) {
	this.giatien = giatien;
}
public String getRom() {
	return rom;
}
public void setRom(String rom) {
	this.rom = rom;
}
public ModelGiohang() {
	
}


	
	
	
}
