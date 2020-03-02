package springBoot.annotation;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author li_hhui
 * @date:2020年3月2日
 * @version:
 * 本类用于学习@Bean注解的使用  是一个工具类，并托管
 * 
 * @Bean注解作用相当于spring 中
 * <bean id="" class ="">
 * 在spring容器启动时就创建此类
 * 
 * 
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

	
	
}
