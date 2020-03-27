package javaDesignPatterns;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.boot.devtools.remote.server.HandlerMapper;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

/**
 * @author li_hhui
 * @date:2020年3月24日
 * @version:
 * 结构类模式：适配器设计模式
 * 把一个接口转换成客户端期待的另一种接口，从而使因两个接口不匹配无法一起工作的两个类一起工作，适配器的作用就是解决接口不兼容性的问题。
 * 
* springmvc 中前端控制器dispatcher 将请求分发到具体的controller。其底层就是使用的适配器模式。
 * 在springmvc 中存在多种handlerMapping 和多种handler(即 Controller) ,通过适配器将两者对应起来，处理具体请求
 *  * 
 * spring Advice
 * Advice 的类型:BeforeAdvice AfterReturningAdvice  ThrowsAdvice
 * 有对应的的拦截器:MethodBeforeAdviceInterceptor、AfterReturningAdviceInterceptor、ThrowsAdviceInterceptor 
 * spring 需要将每个Advice 都封装成对应的拦截器，返回给容器，需要是用适配器来匹配
 * 
 */
public class AdapterModelTest {

	//springmvc  controller 适配器设计模式应用
	public void testSpringmvc(){
		//DispatcherServlet 
				//doDispatch() 主要方法
				//mappedHandler = getHandler(processedRequest); //根据请求获取对应的mappingHandler				
				//HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler()); //根据mappingHandler获取适配器
				//mv = ha.handle(processedRequest, response, mappedHandler.getHandler());,由适配器匹配到对应的handler

				
			//	HandlerMapping
			//	Controller
	}
	
	
	//spring AOP 中增强 advice 适配器设计模型应用
	public void testSpringAopAdvice(){
//		AdvisorAdapter
//		eg :AfterReturningAdviceAdapter
	/*	public boolean supportsAdvice(Advice advice) {
			return (advice instanceof AfterReturningAdvice);
		}

		public MethodInterceptor getInterceptor(Advisor advisor) {
			AfterReturningAdvice advice = (AfterReturningAdvice) advisor.getAdvice();
			return new AfterReturningAdviceInterceptor(advice);
		}*/
	}
	
	
	
}
