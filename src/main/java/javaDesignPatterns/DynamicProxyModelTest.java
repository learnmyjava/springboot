package javaDesignPatterns;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

/**
 * @author li_hhui
 * @date:2020年3月25日
 * @version:
 * 结构类模式
 *  代理模式 为某个对象提供一个代理以控制对该对象的访问。 代理类主要负责为被代理类预处理消息，过滤消息，传递消息给被代理类，消息的后置处理，不实现具体业务逻辑
 *           
 * spring AOP 动态代理分两种:
 *   JDK动态代理 :代理类需要实现一个接口，核心类InvocationHandler,Proxy,JDK代理限制了只能为接口创建代理实例，对于没有通过接口定义的业务类，就无法使用JDK。所以它是面向接口的代理
 *   CGLIB动态代理：CGLIB可以为一个类创建一个子类，在子类中采用拦截的技术拦截所有父类方法的调用并顺序织入横切逻辑,若这个类被fianl 修饰，则无法实现代理，
 * 	 CGLIB代理创建的对象在运行时的性能比JDK代理高，在JDK8之后差不多
 * 	 CGLIB代理创建对象相对JDK慢
 */
public class DynamicProxyModelTest {

	public static void main(String[] args) {
//	    System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        HelloInterface hello = new Hello();
        ByeInterface bye = new Bye();
        
        InvocationHandler handler = new ProxyHandler(hello);
        InvocationHandler handler2 = new ProxyHandler(bye);
        
        //利用反射机制在运行时创建代理类proxyHello
        HelloInterface proxyHello = (HelloInterface) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), handler);
        ByeInterface proxyBye = (ByeInterface) Proxy.newProxyInstance(bye.getClass().getClassLoader(), bye.getClass().getInterfaces(), handler2);
        
        proxyHello.sayHello();
        proxyBye.sayBye();
        
        System.out.println("===========以下是CGLIB代理方式===============");
        
        Service service = new Service();//被代理类
        CGlibDynamicProxy proxyHandler = new CGlibDynamicProxy(service);
        Service proxy=proxyHandler.GetProxy();//代理类
        
        proxy.finalMethod();//私有方法不允许子类覆盖 
        proxy.publicMethod();
	}

}
