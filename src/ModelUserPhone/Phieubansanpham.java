package ModelUserPhone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "phienbansanpham")
public class Phieubansanpham {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maphienbansp")
	private int maphienbansp;
	@Column(name = "masp")
    private int masp;
	@Column(name = "ram")
    private int ram;
	@Column(name = "rom")
    private int rom;
	@Column(name = "mausac")
    private int mausac;
	@Column(name = "gianhap")
    private int gianhap;
	@Column(name = "giaxuat")
    private int giaxuat;
	@Column(name = "soluongton")
    private int soluongton;
	
	 @Transient
	 private int ktram;
     @Transient
	 private int ktrom;
     @Transient
	 private String color;
     @Transient
	 private String tensp;
     @Transient
	 private Long imei;

    public Phieubansanpham() {
    }

    
//    int maphienbansp = rs.getInt("maphienbansp");
//    int masp = rs.getInt("masp");
//    int ram = rs.getInt("ram");
//    int mausac = rs.getInt("mausac");
//    int rom = rs.getInt("rom");
//    int gianhap = rs.getInt("gianhap");
//    int giaxuat = rs.getInt("giaxuat");
//    int soluongton = rs.getInt("soluongton");
    public Phieubansanpham(int maphienbansp,int masp, int ram, int mausac, int rom, int gianhap, int giaxuat,int soluongton) {
        this.maphienbansp = maphienbansp;
        this.masp = masp;
        this.ram = ram;
        this.rom = rom;
        this.mausac = mausac;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
        this.soluongton=soluongton;
    }
    public Phieubansanpham(int maphienbansp,int masp, int ram, int rom, int mausac, int gianhap, int giaxuat) {
        this.maphienbansp = maphienbansp;
        this.masp = masp;
        this.ram = ram;
        this.rom = rom;
        this.mausac = mausac;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
    }
    public Phieubansanpham(int ram, int rom, String color) {
        this.ram = ram;
        this.rom = rom;
        this.color = color;
    }
    public Phieubansanpham(int maphienbansp,int soluongton) {
        this.maphienbansp = maphienbansp;
        this.soluongton = soluongton;
    }
    public Phieubansanpham(int maphienbansp, int ram, int rom, int mausac, int gianhap, int giaxuat) {
        this.maphienbansp = maphienbansp;
        this.ram = ram;
        this.rom = rom;
        this.mausac = mausac;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
    }
    public Phieubansanpham(int masp,int ram, int rom, int mausac, int gianhap, float giaxuat) {
        this.masp=masp;
    	this.ram = ram;
        this.rom = rom;
        this.mausac = mausac;
        this.gianhap = gianhap;
        this.giaxuat = (int)giaxuat;
    }
    public Phieubansanpham(int maphienbansp,int masp,String tensp, int ram, int rom, String  color, int gianhap, int soluongton) {
        this.maphienbansp=maphienbansp;
    	this.masp=masp;
        this.tensp=tensp;
    	this.ram = ram;
        this.rom = rom;
        this.color = color;
        this.gianhap = gianhap;
        this.soluongton=soluongton;
    }
    public Phieubansanpham(int maphienbansp,Long imei,int masp,String tensp, int ram, int rom, String  color, int gianhap, int soluongton) {
        this.maphienbansp=maphienbansp;
        this.imei=imei;
    	this.masp=masp;
        this.tensp=tensp;
    	this.ram = ram;
        this.rom = rom;
        this.color = color;
        this.gianhap = gianhap;
        this.soluongton=soluongton;
    }
    public Phieubansanpham(int maphienbansp, int ktram, int ktrom, String color, int gianhap, int giaxuat) {
        this.maphienbansp = maphienbansp;
        this.ktram = ktram;
        this.ktrom = ktrom;
        this.color = color;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
    }
    public Phieubansanpham( int ktram, int ktrom, String color, int gianhap, int giaxuat) {
        this.ktram = ktram;
        this.ktrom = ktrom;
        this.color = color;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
    } 
    public int getMaphienbansp() {
        return maphienbansp;
    }

    public void setMaphienbansp(int maphienbansp) {
        this.maphienbansp = maphienbansp;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getRom() {
        return rom;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }

    public int getMausac() {
        return mausac;
    }

    public void setMausac(int mausac) {
        this.mausac = mausac;
    }

    public int getGianhap() {
        return gianhap;
    }

    public void setGianhap(int gianhap) {
        this.gianhap = gianhap;
    }

    public int getGiaxuat() {
        return giaxuat;
    }

    public void setGiaxuat(int giaxuat) {
        this.giaxuat = giaxuat;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }
    
    
    public int getKtram() {
		return ktram;
	}

	public void setKtram(int ktram) {
		this.ktram = ktram;
	}

	public int getKtrom() {
		return ktrom;
	}

	public void setKtrom(int ktrom) {
		this.ktrom = ktrom;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	

	public String getTensp() {
		return tensp;
	}

	public void setTensp(String tensp) {
		this.tensp = tensp;
	}

	
	public Long getImei() {
		return imei;
	}

	public void setImei(Long imei) {
		this.imei = imei;
	}

	@Override
    public String toString() {
        return ram+"GB - "+rom+"GB - "+color;
    }

    
}
