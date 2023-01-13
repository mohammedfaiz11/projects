package carsdetails;
import javax.persistence.*;

@Entity
@Table
public class carsdetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int carid;
	private String carname;
	private String modelyear;
	private int carprice;
	
	
	public int getCarid() {
		return carid;
	}
	public void setCarid(int carid) {
		this.carid = carid;
	}
	public String getCarname() {
		return carname;
	}
	public void setCarname(String carname) {
		this.carname = carname;
	}
	public String getModelyear() {
		return modelyear;
	}
	public void setModelyear(String modelyear) {
		this.modelyear = modelyear;
	}
	public int getCarprice() {
		return carprice;
	}
	public void setCarprice(int carprice) {
		this.carprice = carprice;
	}
	
	
	public carsdetail(String carname, String modelyear, int carprice) {
		super();
		this.carname = carname;
		this.modelyear = modelyear;
		this.carprice = carprice;
	}
	
	
	@Override
	public String toString() {
		return "carsdetail [carid=" + carid + ", carname=" + carname + ", modelyear=" + modelyear + ", carprice="
				+ carprice + "]";
	}
}
