package com.example.SpringBoot;

/**
 * @author li_hhui
 * @date:2019年11月15日
 * @version:
 */
public class TestVO {

	private String name;
	private String age;
	private String address;
	private String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
