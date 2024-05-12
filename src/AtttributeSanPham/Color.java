//package AtttributeSanPham;
package AtttributeSanPham;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mausac")
public class Color {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mamau")
   private int mamau;
	@Column(name = "tenmau")
   private String color;
public Color() {
	
}
public Color(int mamau, String color) {
	super();
	this.mamau = mamau;
	this.color = color;
}
public Color(String color) {
	super();
	this.color = color;
}
public int getMamau() {
	return mamau;
}
public void setMamau(int mamau) {
	this.mamau = mamau;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
@Override
public String toString() {
	return color;
}
}
