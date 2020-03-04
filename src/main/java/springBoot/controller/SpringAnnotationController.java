package springBoot.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springBoot.annotation.SpringComponentAnnotationUtil;
import springBoot.entity.User;

/**
 * 常用注解使用
 * 
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
	private User u;//============控制层依赖spring容器注入 获取容器中的bean 实例 =============
	
	@Resource(name="getuser")
	private User uR;
	
	/*@Resource
	private User uR1;*/ //作用和@Resource(name="getuser")相同
	
	/*@Resource(type=User.class)
	private User uT;*/

	
	
	@ResponseBody
	@RequestMapping("beantest")
	public String beantest(){
		System.out.println("==================controller===="+this);//BeanAnnotationController 在spring容器中 默认是单例
		
		User user11 = (User) SpringComponentAnnotationUtil.getBean("getuser");//==========通过上下文获取容器的中bean 实例=============
		System.out.println("==============="+user11);
		
		
		//方法报错
		/*
		User user2 = (User) SpringBeanAnnotationUtil.getBean(User.class);
		System.out.println("====================="+user2);*/
		
		User user21 = (User) SpringComponentAnnotationUtil.getBean("createUser2");
		User user22 = (User) SpringComponentAnnotationUtil.getBean("createUser2");
		User user23 = (User) SpringComponentAnnotationUtil.getBean("createUser2");
		
//		System.out.println("========="+user21+" "+ user22+" "+user23);//此处  user21 user22 user23 是同一个实例==>@Bean 默认是单例
		User user31 = new User();
		User user32 = new User();
		User user33 = new User();
//		System.out.println("========="+user31+" "+ user32+" "+user33);//user31 user32 user33 是3个不同实例
		
		User user41  = (User) SpringComponentAnnotationUtil.getBean("createUser3");
		User user42  = (User) SpringComponentAnnotationUtil.getBean("createUser3");
		User user43  = (User) SpringComponentAnnotationUtil.getBean("createUser3");
		
//		System.out.println("========="+user41+" "+ user42+" "+user43);//user41 user42 user43 是3个不同实例

		System.out.println("======="+u+"   "+u.getUsername());//user11 和 u 是同一个实例
		
		
		

		return "hello";
	}
	
	
	
}
