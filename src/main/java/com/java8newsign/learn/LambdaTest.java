package com.java8newsign.learn;

import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * @author li_hhui
 * @date:2020年2月24日
 * @version:
 * 
 * java 8 版本之前，我们无法将功能(代码)作为参数传递给方法
 * 但是可以封装匿名类，传递给给方法
 * Lambda Expression 使得代码量更少，因此代码更简洁,灵活(适合 对复用要求不高的代码)
 */
public class LambdaTest {

	/**
	 * 8之前版本的str排序
	 */
	@Test
	public void testold(){
		List<String> name = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(name);
		/*Collections.sort(name, new Comparator<String>() {
		    @Override
		    public int compare(String a, String b) {
		        return b.compareTo(a);
		    }
		});*/
		
		System.out.println(name.toString());
		
		
	}
	
	/**
	 * 8版本的str排序 lambda
	 */
	@Test
	public void testlambda(){
		
		List<String> name = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(name,(a,b) -> a.compareTo(b));//java 编译器能够自动推到出参数的类型
		
		System.out.println(name.toString());
		
		
	}

	
	@Test
	public void testFor(){
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		//java8 之前版本
		/*for (String name:names) {
			System.out.println(name);
		}*/
		
		//使用
		names.forEach(n -> System.out.println(n));
	}
	
	/**
	 * 过滤
	 */
	@Test
	public void testFilter(){
		List<String> listStr = Arrays.asList("USA","Japan","China","Canada","France");
		
		List<String> list1 = listStr.stream().filter(x -> x.length()>4).collect(Collectors.toList()	);
		System.out.println("长度大于4的list:"+list1);
		
		List<String> list2 = listStr.stream().filter(x -> x.startsWith("C")).collect(Collectors.toList());
		System.out.println("以C开头的的list:"+list2);
		
		 List<String> list3 = listStr.stream().filter(x -> x.endsWith("a")).collect(Collectors.toList());
		System.out.println("以a结束的的list:"+list2);

		 List<String> list4 = listStr.stream().filter(x -> true).collect(Collectors.toList());
		System.out.println("打印所有的list:"+list4);
		
		 List<String> list5 = listStr.stream().filter(x -> false).collect(Collectors.toList());
			System.out.println("全部过滤掉:"+list5);

	}
	
	@Test
	public  void testSUM(){
		List<Integer> list = Arrays.asList(11,33,1,34,561,12,123,51,231);
		
		IntSummaryStatistics statistics = list.stream().mapToInt(x -> x).summaryStatistics();
		System.out.println("数组大小:"+statistics.getCount()+",最大"+statistics.getMax()+",最小:"+statistics.getMin()+",求和"+statistics.getSum()+",求平均"+statistics.getAverage());
	}
}
