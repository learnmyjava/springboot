package com.java8newsign.learn;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;







import org.junit.Test;

import springBoot.entity.User;

/**
 * 1方法引用
 * 若lambda体 的内容已经有jdk提供的方法实现了，可以使用方法引用
 * @author Administrator
 *
 *主要有三种语法格式(前提条件:lambda方法体中的方法 参数格式类型 和返回值类型   与  函数型接口抽象方法的方法参数 参数个数 返回值类型 完全一致)
 *
 *实例名::方法名
 *类::静态方法名
 *
 *2 构造器引用
 *ClassName::new;
 *前提条件
 *需要调用的类的构造器参数的列表 要与函数式接口中抽象方法的参数列表要求一致
 *
 *3数组引用
 *Type:new;
 */
public class MethodRefTest {

	/**
	 * 实例名::方法名
	 */

	@Test
	public void test1(){
		User u = new User();
		u.setUsername("lihonghui");
		u.setAge(30);
		Supplier<String> sup = () -> u.getUsername();
		System.out.println(sup.get());
		
		Supplier<Integer> sup1 = () -> u.getAge();
		System.out.println(sup1.get());
		
//		Supplier<String> sup2 = () -> u::getUsername;
		PrintStream ps = System.out;
		Consumer<String> con = ps::println;
		con.accept("消费型函数");
		
	
	}
	/**
	 * 类::静态方法名
	 */
	@Test
	public void test2(){
		/*Comparator<Integer> com = (x,y) -> Integer.compare(x, y);
		int max=com.compare(12, 1);
		System.out.println(max);*/
		
		Comparator<Integer> com1 = Integer::compare;
		int max1=com1.compare(12, 1);
		System.out.println(max1);
	}

	/**
	 * 类::实例方法名
	 * 
	 * 实例名::方法名  当方法的调用者是该实例并且方法中有一个参数  可以使用lei::实例方法名
	 */
	@Test
	public void test3(){
		BiPredicate<String, String> pre = (x,y) -> x.equals(y);
		boolean f = pre.test("hello", "hello");
//		System.out.println(f);
		
		BiPredicate<String, String> pre1 = String::equals;
		boolean f1 = pre.test("hello", "hello");
		System.out.println(f1);
	}
	
	
	/**
	 * 构造器
	 */
	@Test
	public void test4(){
		//new 一个空对象
		Supplier<User> sup = () -> new User();
		User u = sup.get();
//		System.out.println(u.toString());
		
		Supplier<User> sup1 = User::new;
		System.out.println(sup1.get());
		
		
		
		//new 一个属性赋值的对象
		Function<Integer, User> fun = (x) -> new User(x);
		User user = fun.apply(12);
//		System.out.println(user.toString());
		
		Function<Integer,User> fun1	 =  User::new;
		User user1 = fun1.apply(12);
		System.out.println(user1.toString());
		
		//new 两个属性赋值的对象
		BiFunction<Integer, String, User> bif = (x,y) ->  new User(x,y);
		User user2 = bif.apply(12, "lihonghui");
		System.out.println(user2.toString());
		
	}
	
	/**
	 * 数组引用
	 * 
	 */
	@Test
	public void test(){
		Function<Integer, String[]> fun = x -> new String[x];
		String[] str = fun.apply(12);
		System.out.println(str.length);
		
		
		Function<Integer, String[]> fun1 = String[]::new;
		String[] str1 = fun.apply(13);
		System.out.println(str1.length);
	}
	
}
