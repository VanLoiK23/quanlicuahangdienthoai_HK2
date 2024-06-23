package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "thongke_sp")
public class Thongkesp {
	    @Id 
	    @Column(name = "tensanpham")
	    private String tensanpham;
	    @Column(name = "soluong")
	    private Integer soluong;
		public Thongkesp(String tensanpham, Integer soluong) {
			super();
			this.tensanpham = tensanpham;
			this.soluong = soluong;
		}
		public Thongkesp() {
			super();
		}
		public String getTensanpham() {
			return tensanpham;
		}
		public void setTensanpham(String tensanpham) {
			this.tensanpham = tensanpham;
		}
		public Integer getSoluong() {
			return soluong;
		}
		public void setSoluong(Integer soluong) {
			this.soluong = soluong;
		}
	    
	    
}
