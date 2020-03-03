package springBoot.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springBoot.entity.Car;
import springBoot.entity.Driver;
import springBoot.entity.User;

/**
 * @author li_hhui
 * @date:2020年3月3日
 * @version:
 * 
 * @Configuration 用于定义配置类，可替换xml配置文件，相当于spring xml配置文件中<beans ></beans> ,其内有一个或者多个@Bean 注解的方法，
 * 实现自动注册并装配到spring容器
 * 作用同@Component 类似 ，存在区别
 */
@Configuration
public class SpringConfigurationUtil {
	
	@Bean(name="getUserFromCon")
	public User getUserCon(){
		User user = new User();
		user.setUsername("通过beanname获取");
		return  user;
	}

	
	@Bean
	public User getUsernobeanname(){
		User user = new User();
		user.setUsername("通过方法名获取");
		return  user;
	}
	
	
/*	@Bean
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
	}*/
}
