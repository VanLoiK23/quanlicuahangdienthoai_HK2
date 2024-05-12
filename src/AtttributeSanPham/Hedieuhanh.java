package AtttributeSanPham;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hedieuhanh")
public class Hedieuhanh {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mahedieuhanh")
    private int madh;
	@Column(name = "tenhedieuhanh")
    private String tenhdh;
public Hedieuhanh() {
	
}
public Hedieuhanh(int madh, String tenhdh) {
	super();
	this.madh = madh;
	this.tenhdh = tenhdh;
}
public Hedieuhanh(String tenhdh) {
	super();
	this.tenhdh = tenhdh;
}
public int getMadh() {
	return madh;
}
public void setMadh(int madh) {
	this.madh = madh;
}
public String getTenhdh() {
	return tenhdh;
}
public void setTenhdh(String tenhdh) {
	this.tenhdh = tenhdh;
}
@Override
public String toString() {
    return tenhdh;
}
}
