package AtttributeSanPham;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "xuatxu")
public class Xuatxu {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maxuatxu")
    private int maxuatxu;
	@Column(name = "tenxuatxu")
    private String noi;
public Xuatxu() {
	
}
public Xuatxu(int maxuatxu, String noi) {
	super();
	this.maxuatxu = maxuatxu;
	this.noi = noi;
}
public Xuatxu(String noi) {
	this.noi=noi;
}
public int getMaxuatxu() {
	return maxuatxu;
}
public void setMaxuatxu(int maxuatxu) {
	this.maxuatxu = maxuatxu;
}
public String getNoi() {
	return noi != null ? noi : "";
}
public void setNoi(String noi) {
	this.noi = noi;
}
@Override
public String toString() {
    return noi;
}
}
