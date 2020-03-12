package com.example.SpringBoot;

/**
 * @author li_hhui
 * @date:2019年11月15日
 * @version:
 */
public class Person {

	private String name;
	private int age;
	private String address;
	private String getName() {
		return name;
	}
	
	public Person(){
		
	}
	private Person(int age){
		this.age=age;
	}
	
	public Person(String address){
		this.address=address;
		
	}
	private void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	private void privatemethod(){
		System.out.println("他是私有方法");
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", address=" + address
				+ "]";
	}
	
	
}
