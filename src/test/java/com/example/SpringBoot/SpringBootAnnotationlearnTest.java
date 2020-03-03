package com.example.SpringBoot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import springBoot.Application;
import springBoot.entity.Car;
import springBoot.entity.Driver;
import springBoot.entity.User;

/**
 * @author li_hhui
 * @date:2020年3月3日
 * @version:
 * Springboot 集成测试类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class SpringBootAnnotationlearnTest {

	@Autowired
	@Qualifier(value="getUserFromCon")
	private User u;
	
	@Autowired
	@Qualifier(value="getUsernobeanname")
	private User u1;
	
	@Autowired
	private Driver driver;//调用的时候注意 实例名就是方法名
	
	@Autowired
	private Car car;
	
	/**
	 * 获取@Configuration 方式注入的bean 
	 */
	@Test
	public void testConfigurationUser(){
		System.out.println("============="+u+"  "+u.getUsername());
		System.out.println("============="+u1+" "+u1.getUsername());
		
	}
	
	
	/**
	 * 测试@Configuration 和@Component 的区别
	 */
	//true  //通过@Configuration 获取
	//false  通过@Component  方式获取
	
	//Component 修饰的配置类 不会为bean 生成CGLIB代理，所以在@Bean给容器注入car实例，在driver()方法中调用car()方法时，有新建了一个实例。
	//而在Configuration 中，有代理class 会对car()方法拦截，在driver()中调用car() 方法时，会遇到拦截，直接从BeanFactory中获取car实例
	@Test
	public void testConfiguration(){
		boolean  result = driver.getCar() == car;
		System.out.println("============"+result);
		
	}
	
	
	
}
