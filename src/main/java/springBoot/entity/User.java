package springBoot.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
	
	private String token;
	
}
