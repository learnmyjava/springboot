package springBoot.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springBoot.Adapter.WebSecurityAdapter;
import springBoot.entity.User;

@Controller
public class LoginController {
	
	@RequestMapping("/login")
	public String login(){
		
		return "login/login";
	}
	
	@RequestMapping("/loginVerify")
	public String loginVerify(User user,Model model,HttpSession session){
		if((user.getUsername().equals("admin") && user.getPassword().equals("admin")) ||  user.getUsername().equals("tom") && user.getPassword().equals("tom")){
			session.setAttribute(WebSecurityAdapter.SESSION_USER_KEY, user);
			model.addAttribute("user", user);
			return "login/jspsuccess";
		}else{
			model.addAttribute("logintip", "用户名或密码错误");
			model.addAttribute("user", user);
			return "login/login";
		}
		
		
	}
	
	@RequestMapping("/logoff")
	public String logoff(HttpSession session){
		session.removeAttribute(WebSecurityAdapter.SESSION_USER_KEY);
		return "login/login";
	}
	
	@RequestMapping("/ajaxrequest")
	@ResponseBody
	public User ajaxrequest(@RequestBody User u,HttpSession session){
		session.setAttribute(WebSecurityAdapter.SESSION_USER_KEY, u);
		return u;
	}
	
	
	@RequestMapping("/showList")
	public String showList(Model model,HttpSession session){
		List list=new ArrayList<>();
		User u =(User) session.getAttribute(WebSecurityAdapter.SESSION_USER_KEY);
		if(u.getUsername().equals("admin")){
			User u1 =new User("tom","tom",12,"山东济南");
			User u2 =new User("marry","marry",32,"安徽合肥");
			User u3 =new User("jack","jack",25,"江苏南京");
			User u4 =new User("roles","roles",19,"上海");
			list.add(u1);
			list.add(u2);
			list.add(u3);
			list.add(u4);
			list.add(u);
		}
	
		
		model.addAttribute("list", list);
		return "login/showList";
		
	}
}
