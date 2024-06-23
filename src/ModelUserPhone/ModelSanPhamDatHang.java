package ModelUserPhone;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="danhsachdathang")
public class ModelSanPhamDatHang implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="ID")
private int id;
@Column(name="masp")
private int masp;
@Column(name="tensp")
private String name;
@Column(name="giatien")
private int giatien;
@Column(name="soluongmua")
private int soluong;
@Column(name="rom")
private String rom;
@Column(name="Images")
private String Images;
@Column(name="taikhoanuser")
private String taikhoanuser;
public ModelSanPhamDatHang() {
	
}


public ModelSanPhamDatHang(int id, int masp, String name, int giatien, int soluong, String rom, String images,String taikhoanuser) {
	
	this.id = id;
	this.masp = masp;
	this.name = name;
	this.giatien = giatien;
	this.soluong = soluong;
	this.rom = rom;
	Images = images;
	this.taikhoanuser=taikhoanuser;
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getGiatien() {
	return giatien;
}
public void setGiatien(int giatien) {
	this.giatien = giatien;
}
public int getSoluong() {
	return soluong;
}
public void setSoluong(int soluong) {
	this.soluong = soluong;
}
public String getRom() {
	return rom;
}
public void setRom(String rom) {
	this.rom = rom;
}
public String getImages() {
	return Images;
}
public void setImages(String images) {
	Images = images;
}



}
