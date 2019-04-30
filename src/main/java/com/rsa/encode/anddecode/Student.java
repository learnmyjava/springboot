package com.rsa.encode.anddecode;

public class Student {
	private String id;
	private String name;
	private String adress;
	private int age;
	private String sex;
	private String tel;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", adress=" + adress
				+ ", age=" + age + ", sex=" + sex + ", tel=" + tel + "]";
	}
	
	
	
}
