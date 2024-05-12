package AtttributeSanPham;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thuonghieu")
public class Thuonghieu {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mathuonghieu")
    private int math;
	@Column(name = "tenthuonghieu")
	private String tenthuonghieu;
	public Thuonghieu() {
		
	}
public Thuonghieu(int math, String tenthuonghieu) {
	super();
	this.math = math;
	this.tenthuonghieu = tenthuonghieu;
}
public Thuonghieu(String tenthuonghieu) {
	super();
	this.tenthuonghieu = tenthuonghieu;
}
public int getMath() {
	return math;
}
public void setMath(int math) {
	this.math = math;
}
public String getTenthuonghieu() {
	return tenthuonghieu;
}
public void setTenthuonghieu(String tenthuonghieu) {
	this.tenthuonghieu = tenthuonghieu;
}
@Override
public String toString() {
    return tenthuonghieu;
}
}
