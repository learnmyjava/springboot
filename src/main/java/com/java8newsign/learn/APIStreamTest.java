package com.java8newsign.learn;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

import org.junit.Test;

public class APIStreamTest { 
	
	/**
	 * 用for 循环计算求和
	 * 传统的串行在高并发情况下cup使用率比较低1千亿的加法 cup使用率37%
	 */
	@Test
	public void test1(){
		Instant starttime = Instant.now();
		long sum = 0;
		for (long i = 0; i <=100000000000L; i++) {
			sum+=i;
		}
		System.out.println(sum);
		Instant endtime = Instant.now();
		System.out.println("耗时:"+Duration.between(starttime, endtime).toMillis());
		//1亿-10亿-100亿-1000亿
		//32ms-2161-21369-214102
	}
	
	/**
	 * 使用Java8 并行流 parallel --对于高并发的操作 Java8  cup的使用率更高可以达到100%
	 * 并发量越大越能体现出Java8的优势 
	 * 串行流sequential()
	 */
	@Test
	public void test2(){
		Instant starttime = Instant.now();
		long sum = LongStream.range(0, 100000000000L)
					.parallel()
					.reduce(0,Long::sum);
		
		Instant endtime = Instant.now(); 
		System.out.println("耗时："+Duration.between(starttime, endtime).toMillis());
		//263-778-6049-60047
	}

}
