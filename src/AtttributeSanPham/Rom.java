package AtttributeSanPham;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dungluongrom")
public class Rom {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "madlrom")
    private int madl;
	@Column(name = "kichthuocrom")
	  private int dungluongRom;
	
	public Rom() {
    }
	public Rom(int madl, int dungluongRom) {
		super();
		this.madl = madl;
		this.dungluongRom = dungluongRom;
	}
	public Rom(int dungluongRom) {
		super();
		this.dungluongRom = dungluongRom;
	}
	public int getMadl() {
		return madl;
	}
	public void setMadl(int madl) {
		this.madl = madl;
	}
	public int getDungluongRom() {
		return dungluongRom;
	}
	public void setDungluongRom(int dungluongRom) {
		this.dungluongRom = dungluongRom;
	}
	@Override
	public String toString() {
		return Integer.toString(dungluongRom);
	}
	}

