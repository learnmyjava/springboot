package springBoot.entity;

/**
 * @author li_hhui
 * @date:2020年3月3日
 * @version:
 */
public class Car {

	private String color;
	private long price;
	private String productname;
	private String platenumber;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getPlatenumber() {
		return platenumber;
	}
	public void setPlatenumber(String platenumber) {
		this.platenumber = platenumber;
	}
	
	
}
