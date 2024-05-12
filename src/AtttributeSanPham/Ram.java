package AtttributeSanPham;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dungluongram")
public class Ram {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "madlram")
    private int madl;
	@Column(name = "kichthuocram")
    private int dungluongRam;
	
	public Ram() {
    }
public Ram(int madl, int dungluongRam) {
	super();
	this.madl = madl;
	this.dungluongRam = dungluongRam;
}
public Ram(int dungluongRam) {
	super();
	this.dungluongRam = dungluongRam;
}
public int getMadl() {
	return madl;
}
public void setMadl(int madl) {
	this.madl = madl;
}
public int getDungluongRam() {
	return dungluongRam;
}
public void setDungluongRam(int dungluongRam) {
	this.dungluongRam = dungluongRam;
}
@Override
public String toString() {
	return Integer.toString(dungluongRam);
}
  
}
