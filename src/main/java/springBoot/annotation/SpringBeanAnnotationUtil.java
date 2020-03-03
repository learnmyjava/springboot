package springBoot.annotation;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import springBoot.entity.Car;
import springBoot.entity.Driver;

/**
 * @author li_hhui
 * @date:2020年3月2日
 * @version:
 * 本来获取spring容器的上下文
 */
@Component
public class SpringBeanAnnotationUtil implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		//获取上下文
		if (SpringBeanAnnotationUtil.applicationContext == null ) {
			SpringBeanAnnotationUtil.applicationContext = applicationContext;
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
	
	
}
