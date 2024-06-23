package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thongke_tongquan")
public class Thongke_tongquan {
    @Column(name = "sanpham")
   private Integer sp;
    @Column(name = "khachhang")
   private Integer khachhang;
    @Id
    @Column(name = "nhanvien")    
   private Integer nv;
    @Column(name = "von")
   private Long sovon;
    @Column(name = "doanthu")
   private Long doanthu;
    @Column(name = "loinhuan")
   private Long loinhuan;
public Thongke_tongquan() {
	
}
public Thongke_tongquan(int sp, int khachhang, int nv, Long sovon, Long doanthu, Long loinhuan) {
	super();
	this.sp = sp;
	this.khachhang = khachhang;
	this.nv = nv;
	this.sovon = sovon;
	this.doanthu = doanthu;
	this.loinhuan = loinhuan;
}
public Integer getSp() {
	return sp;
}
public void setSp(Integer sp) {
	this.sp = sp;
}
public Integer getKhachhang() {
	return khachhang;
}
public void setKhachhang(Integer khachhang) {
	this.khachhang = khachhang;
}
public Integer getNv() {
	return nv;
}
public void setNv(Integer nv) {
	this.nv = nv;
}
public Long getSovon() {
	return sovon;
}
public void setSovon(Long sovon) {
	this.sovon = sovon;
}
public Long getDoanthu() {
	return doanthu;
}
public void setDoanthu(Long doanthu) {
	this.doanthu = doanthu;
}
public Long getLoinhuan() {
	return loinhuan;
}
public void setLoinhuan(Long loinhuan) {
	this.loinhuan = loinhuan;
}


}