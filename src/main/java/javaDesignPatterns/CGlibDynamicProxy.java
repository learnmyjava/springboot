package javaDesignPatterns;

import java.lang.reflect.Method;

import oracle.jdbc.proxy.annotation.GetProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;


/**
 * @author li_hhui
 * @date:2020年3月27日
 * @version:
 */
public class CGlibDynamicProxy  implements MethodInterceptor{

	private Object object;
	public  CGlibDynamicProxy(Object object) {
		this.object = object;
	}
	@Override
	public Object intercept(Object obj, Method method, Object[] arg2,
			MethodProxy proxy) throws Throwable {
		System.out.println("Before invoke "+method.getName());
		method.invoke(object, arg2);
		System.out.println("After invoke "+method.getName());
		return null;
	}

	//获取被代理接口实例对象
	public <T> T GetProxy(){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(object.getClass());
		enhancer.setCallback(this);
		return (T) enhancer.create();
	}
	
}
