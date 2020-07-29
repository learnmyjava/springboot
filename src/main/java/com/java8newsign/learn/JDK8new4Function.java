package com.java8newsign.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

/**
 * jdk8 Lambda 需要函数型接口的支持(函数型接口-即只有一个抽象方法的接口)
 * jdk8 提供了四种接口类型:消费型 供给型 函数型 断言型  (简化程序员主动生成接口的操作)
 * @author Administrator
 *
 */
public class JDK8new4Function {
	
	
	//消费型
	@Test
	public void test1(){
		
		happy(1000l,m -> System.out.println("消费了"+m+"元"));//System.out.println("消费了"+m+"元") 具体实现方法体===》 将功能作为参数传给方法
	}
	
	//需求 消费金额  Consumer<T> void accept(T t)
	public void happy(double money ,Consumer<Double> con){
		//impl  实现accpt方法 其具体实现的方法体使用lambda 表达式实现
		con.accept(money);
	}
	//供给型
	@Test
	public void test2(){
		List<Integer> numlist = getList(10, () -> (int)(Math.random()*100));
		 System.out.println(numlist);
	}
	
	// 供给型 Supplier<T>  T get();
	//需求 获取指定大小的数组，数组元素是随机数
	public List<Integer> getList(int listsize,Supplier<Integer> sup){
		
		List<Integer> lists = new ArrayList<>();
		for (int i = 0; i < listsize; i++) {
			Integer num = sup.get();
			lists.add(num);
		}
		return lists;
		
	}
	
	
	//函数型
	@Test
	public void test3(){
		String newstr= operat("你好，我是李红慧",(str) -> str.substring(3, 8));
		System.out.println("截取字符串:"+newstr);
		
		int strlength = getlength("你好，我是李红慧", str -> str.length()	);
		System.out.println("获取字符长度:"+strlength);
	}
	
	//需求 操作字符串 Function<T, R>  R apply(T t);
	public String operat(String str, Function<String,String> fun){
		return fun.apply(str);
	}
	
	//需求 获取长度
	public int getlength(String str, Function<String,Integer> fun){
		return fun.apply(str);
	}
	
	
	
	@Test
	public void test4(){
		List<String> lists = Arrays.asList("hellojava","mina","socket","javascript");
		
		List<String> s = filertList(lists, str -> str.length()>9);
		System.out.println(s);
	}
	//断言型
	//需求 过滤数组 Predicate<T> boolean test(T t);
	public List<String> filertList(List<String> list,Predicate<String> pre ){
		List<String> newlist = new ArrayList<>();
		for(String str:list){
			if(pre.test(str)){
				newlist.add(str);
			}
		}
		
		return newlist;
	}
	
	
	
	
}
