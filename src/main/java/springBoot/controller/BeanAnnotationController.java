package springBoot.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import springBoot.annotation.SpringBeanAnnotationUtil;
import springBoot.entity.User;

/**
 * @author li_hhui
 * @date:2020年3月2日
 * @version:
 * 通过托管获取@Bean 
 */
@Controller
public class BeanAnnotationController {

	@ResponseBody
	@RequestMapping("beantest")
	public String beantest(){
		System.out.println(this);
		/*User user = (User) SpringBeanAnnotationUtil.getBean("createUser");
		System.out.println("==============="+user+" "+user.getUsername());
		User user1 = (User) SpringBeanAnnotationUtil.getBean("getuser");
		System.out.println("==============="+user1+"  " +user1.getUsername());
		*/
		
		//第三种方法报错
		/*
		User user2 = (User) SpringBeanAnnotationUtil.getBean(User.class);
		System.out.println("====================="+user2);*/
		
		return "hello";
	}
	
	@Bean
	public User createUser(){
		User user = new User(12,"lihonghui");
		return user;
		
	}
	
	@Bean(name="getuser")
	public User createUser1(){
		User user = new User(12,"lihonghui1");
		return user;
	}
}
