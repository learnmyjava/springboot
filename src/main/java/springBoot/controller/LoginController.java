package springBoot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springBoot.Adapter.JwtInterceptor;
import springBoot.JWT.util.JWTUtil;
import springBoot.constant.Constant;
import springBoot.entity.User;
import springBoot.service.IuserInfoService;

@Slf4j
@Controller
//@RestController
public class LoginController {
	@Autowired
	IuserInfoService userImpl;
	
	@Autowired
	Constant c;
	@RequestMapping("getstr")
	@ResponseBody
	public String getstr(){
		return "hellojava";
	}
	@RequestMapping("/login") 
	//跳转到指定页面时 @Controller 注解和视图解析器InternalResourceViewResolver 配合使用
	//使用@RestController注解，视图解析器InternalResourceViewResolver会失效，无法解析html、jsp页面进而跳转到指定的页面，而是返回return的内容
	//需要借助ModelAndView 返回页面
	public String login(){
		
		return "login";
	}
/*	public ModelAndView Login(){
		ModelAndView model = new ModelAndView();
		model.setViewName("login/login");
		return model;
	}*/
	
	@RequestMapping("/loginVerify")//@Controller返回页面
	public String loginVerify(User user,Model model,HttpSession session){
		if(user == null || user.getUsername()==null || user.getPassword() == null){
			model.addAttribute("logintip", "用户名或密码错误");
			model.addAttribute("user", user);
			return "login/login";
		}
		User u =userImpl.getUser(user);
		
		if( null != u){
			log.info("当前登录用  username="+u.getUsername());
			
			//设置token
			String token = JWTUtil.getToken(u.getId(), u.getUsername());
			log.info("创建JWT："+token);
			u.setToken(token);
			session.setAttribute(JwtInterceptor.SESSION_USER_KEY, u);
			model.addAttribute("user", u);
			
			
			log.info("#############"+c.getC1()+""+c.getC2());//测试常量
//			int i =9/0;//测试自定义异常
			
		
			return "login/jspsuccess";
		}else{
			model.addAttribute("logintip", "用户名或密码错误");
			model.addAttribute("user", user);
			return "login/login";
		}
		
		
	}
	@RequestMapping("/logoff")
	public String logoff(HttpSession session){
		session.removeAttribute(JwtInterceptor.SESSION_USER_KEY);
		return "login/login";
	}
	
	@RequestMapping("/ajaxrequest")
	@ResponseBody
	public User ajaxrequest(@RequestBody User u,HttpSession session){
		session.setAttribute(JwtInterceptor.SESSION_USER_KEY, u);
		return u;
	}
	
	
	@RequestMapping("/showList")
	public String showList(Model model,HttpSession session){
		List list=new ArrayList<>();
		User u =(User) session.getAttribute(JwtInterceptor.SESSION_USER_KEY);
		if(u.getUsername().equals("admin")){
			User u1 =new User();
			u1.setUsername("marray");
			u1.setAddress("山东");
			u1.setAge(12);
			
			
			list.add(u1);
			
			list.add(u);
		}
	
		
		model.addAttribute("list", list);
		return "login/showList";
		
	}
}
