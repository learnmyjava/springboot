package com.java.callback.learn;

import org.junit.Test;

/**
 * @author li_hhui
 * @date:2020年3月12日
 * @version:
 * java软件模块从调用方式上:同步调用，回调，异步调用
 * 同步调用:一种阻塞式的调用 ，调用方要等待被调用方执行完成并返回。是一种单向调用
 * 回调：一种双向调用的模式，被调用方在接口被调用时也会调用对方的接口
 * 异步调用:一种类似消息或事件的机制，不过它的调用方向刚好相反，接口的服务在收到某种讯息或发生某种事件时，会主动通知客户方（即调用客户方的接口）。 
 * 
 * 使用回调来实现异步消息的注册，通过异步调用来实现消息的通知
 * 
 * spring 中容器初始化之后，销毁之前使用回调机制
 * 
 * bean被初始化之后的3中回调
 * java 提供的@PostConstruct
 * 实现InitializingBean接口，实现afterPropertiesSet方法
 * xml方式，bean标签里配置init-method属性，指向类中的方法。
 */
public class TestCallBack {
	@Test
	public void test1(){
		Student student = new Student();
		Teacher teacher = new Teacher(student);
		teacher.question();
	}
	

}
