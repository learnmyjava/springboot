package com.example.SpringBoot;

import javax.annotation.Resource;

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

	/**
	 * @Autowired
	 *  	是由spring提供的注解,按照类型注入，当一个类型存在多个实例时，会造成无法选择具体注入哪一个而报错，
	 * 		可以搭配@Qualifier(value="XX")使用，同@Resource 或者@Resource(name="XX") 相同
	 * @Resource 
	 * 		是用J2EE提供
	 * 		默认按照Byname 注入
	 * 		1.若指定了name 和type,则从spring上下文找到唯一匹配的bean进行装配，找不到则抛出异常
	 * 		2.如果指定name,从上下文中查找id 或者@Bean(value="xxx") 查找进行装配，找不到则抛出异常
	 * 		3.如果指定type,从上下文中查找类似匹配的唯一bean，找不到或者找到多个则抛出异常
	 * 		4.如果都没有指定，则自动按照byname方式查找，如果没有找到匹配，则回退为一个原始类型进行匹配，如果匹配则进行装载
	 */
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
	
	@Resource(name="getuser")
	private User uR;
	
	@Resource
	private User user;  // 引用的是方法名为user()注册的bean     SpringComponentAnnotationUtil 中user()
	
	
	
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
	 * 
	 * @Bean注解   由@Bean创建的类默认是单例，@Bean注解作用相当于spring 配置文件中，<bean id="" class ="">，在spring容器启动时就创建此类 
	 * @Bean注解使用  引用注意： 实例名和方法名相等
	 * @Component 注解 的作用同@Bean类似，自动注册bean并装配到spring容器，但是bean比component自定义性更强更灵活，可以实现一些component 实现不了的自定义加载类
	 * Component 修饰的配置类 不会为bean 生成CGLIB代理，所以在@Bean给容器注入car实例，在driver()方法中调用car()方法时，有新建了一个实例。
	 *  而在Configuration 中，有代理class 会对car()方法拦截，在driver()中调用car() 方法时，会遇到拦截，直接从BeanFactory中获取car实例
	 * 
	 */
	//true  //通过@Configuration 获取
	//false  通过@Component  方式获取
	
	
	@Test
	public void testConfiguration(){
		boolean  result = driver.getCar() == car;
		System.out.println("============"+result);
		
	}
	
	
	/**
	 * @Resource
	 */
	@Test
	public void testResource(){
		
		System.out.println("============="+uR+"  "+uR.getUsername());
		
		System.out.println("==========="+user+" "+user.getUsername());
	}
	
	
	
}
