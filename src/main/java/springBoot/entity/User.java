package springBoot.entity;

import java.io.Serializable;


public class User  implements Serializable{
	/**用户id*/
	private String id;
	/**登录名*/
	private String username;
	/**登录密码*/
	private String password;
	
	/**年龄*/
	private int age;
	/**家庭住址*/
	private String address;
	public User() {
		
	}
	
	public User(int age){
		this.age = age;
	}
	public User(int age,String username){
		this.age = age;
		this.username = username;
		
	}
	public User(String username,String password,int age,String address){
		this.username=username;
		this.password=password;
		this.age=age;
		this.address=address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
