package springBoot.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author li_hhui
 * @date:2020年3月5日
 * @version:
 * 通过成员变量 测试 springboot 容器默认创建的bean 是单例
 * 
 * Spring bean 有5个作用域
 * singleton ：单例模式，当spring创建applicationContext 容器的时，默认需要创建的bean是单例，并且可以重复使用
 * prototype：原型模式，每次通过容器的getBean获取prototype定义的bean都会产生一个新的实例 ，一般不建议使用，bean创建和销毁代价大
 * ==以下都是只有在web应用中使用 该作用域才有效
 * request:对于每次http请求，使用requst 定义的bean都将产生一个新的实例，
 * session:对于每次HTTP Session，使用session定义的Bean都将产生一个新实例
 * global session：每个全局的HTTP Session，使用session定义的Bean都将产生一个新实例。典型情况下，仅在使用portlet context的时候有效
 */
@Controller
@Scope("prototype")
public class SpringSingletonTestController {
	//定义一个全局变量
	//SpringSingletonTestController 默认是单例，所以oper1 和oper2 操作同一个变量，对于全局变量而言 单例是不安全的
	//设置controller 为多例的情况下  每次请求count的初始初始值都是0
	private int count=0;
	//不管controller 是不是单例 static 都是独有一份变量
	private static int  sum=0;

	
	@RequestMapping("/oper1")
	@ResponseBody
	public String oper1(){
		System.out.println(++count);
		return String.valueOf(count);
		
	}
	
	
	@RequestMapping("/oper2")
	@ResponseBody
	public String oper2(){
		
		System.out.println(++count);
		return String.valueOf(count);
		
	}
	
	@RequestMapping("/oper3")
	@ResponseBody
	public String  oper3(){
		System.out.println(++sum);
		return String.valueOf(sum);
	}
}
