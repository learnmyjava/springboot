package springBoot.annotation;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import springBoot.entity.Car;
import springBoot.entity.Driver;
import springBoot.entity.User;

/**
 * @author li_hhui
 * @date:2020年3月2日
 * @version:
 * 本来获取spring容器的上下文
 */
@Component
public class SpringComponentAnnotationUtil implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		//获取上下文
		if (SpringComponentAnnotationUtil.applicationContext == null ) {
			SpringComponentAnnotationUtil.applicationContext = applicationContext;
		}
	}
	
	
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static Object getBean(String name){
		return getApplicationContext().getBean(name);
		
	}
	
	
	 public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
	 }
	
	public static <T> T getBean(String name,Class<T> clazz){
		return getApplicationContext().getBean(name, clazz);
	}

	
	
	@Bean
	public Driver driver(){
		Driver driver = new Driver();
		driver.setName("tom");
		driver.setAddress("安徽");
		driver.setCar(car());
		return driver;
	}
	
	
	
	@Bean
	public Car car(){
		Car car = new Car();
		car.setColor("黑色");
		car.setPlatenumber("鲁Af2324");
		return car;
	}
	
	
	
	
	@Bean
	@Scope("prototype")//多例
	public User createUser3(){
		return new User();
	}
	
	@Bean
	public User createUser2(){
		return new User();
	}
	
	
	@Bean(name="getuser")
	public User createUser1(){
		User user = new User(null, null, null, 12,"lihonghui1", null);
		return user;
	}
	
	
	@Bean
	public User user(){
		User user = new User(null, null, null, 12,"lihonghui2222", null);
		return user;
	}
	
}
