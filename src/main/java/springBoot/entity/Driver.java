package springBoot.entity;

/**
 * @author li_hhui
 * @date:2020年3月3日
 * @version:
 */
public class Driver {
	private String name;
	private String address;
	private Car car;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	

}
