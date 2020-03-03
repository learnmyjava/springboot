package springBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springBoot.annotation.SpringBeanAnnotationUtil;
import springBoot.entity.User;

/**
 * @author li_hhui
 * @date:2020年3月2日
 * @version:
 * 通过托管获取@Bean 
 * @Controller =>控制层
 * @Service =>业务层(serviceInface serviceImpl)
 * @Repository => 持久层( 数据访问层 XXDao  XXXmapper)
 * 以上注解的作用都是将此类自动注册并装载入spring容器  只是根据功能细分
 * 
 * 
 */
@Controller
public class SpringAnnotationController {

	@Autowired
	@Qualifier(value="getuser")
	private User u;//============控制层过依赖注入获取 容器中的bean 实例 =============
	
	
	
	/**
	 * @Bean注解使用 引用注意 实例名和方法名相等
	 * @return
	 * 
	 * 本类用于学习
	 * @Bean注解   由@Bean创建的类默认是单例，@Bean注解作用相当于spring 配置文件中，<bean id="" class ="">，在spring容器启动时就创建此类 
	 * @Component 注解 的作用同@Bean类似，自动注册bean并装配到spring容器，但是bean比component自定义性更强更灵活，可以实现一些component 实现不了的自定义加载类
	 * 
	 */
	
	
	@ResponseBody
	@RequestMapping("beantest")
	public String beantest(){
		System.out.println("==================controller===="+this);//BeanAnnotationController 在spring容器中 默认是单例
		
		User user11 = (User) SpringBeanAnnotationUtil.getBean("getuser");//==========通过上下文获取容器的中bean 实例=============
		System.out.println("==============="+user11);
		
		
		//方法报错
		/*
		User user2 = (User) SpringBeanAnnotationUtil.getBean(User.class);
		System.out.println("====================="+user2);*/
		
		User user21 = (User) SpringBeanAnnotationUtil.getBean("createUser2");
		User user22 = (User) SpringBeanAnnotationUtil.getBean("createUser2");
		User user23 = (User) SpringBeanAnnotationUtil.getBean("createUser2");
		
//		System.out.println("========="+user21+" "+ user22+" "+user23);//此处  user21 user22 user23 是同一个实例==>@Bean 默认是单例
		User user31 = new User();
		User user32 = new User();
		User user33 = new User();
//		System.out.println("========="+user31+" "+ user32+" "+user33);//user31 user32 user33 是3个不同实例
		
		User user41  = (User) SpringBeanAnnotationUtil.getBean("createUser3");
		User user42  = (User) SpringBeanAnnotationUtil.getBean("createUser3");
		User user43  = (User) SpringBeanAnnotationUtil.getBean("createUser3");
		
//		System.out.println("========="+user41+" "+ user42+" "+user43);//user41 user42 user43 是3个不同实例

		System.out.println("======="+u+"   "+u.getUsername());//user11 和 u 是同一个实例
		
		
		

		return "hello";
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
		User user = new User(12,"lihonghui1");
		return user;
	}
	
	
}
