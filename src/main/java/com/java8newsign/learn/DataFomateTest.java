package com.java8newsign.learn;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * 传统时间格式存在线程安全问题
 * java8 提供了更安全更规范日期处理 java.time 包下
 * @author Administrator
 *
 */
public class DataFomateTest {
	/**
	 * 8版本之前的线程不安全演示
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	
	@Test
	public void test1() {
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Callable<Date> task = new Callable<Date>(){
			@Override
			public Date call() throws Exception {
				return sdf.parse("20200227");
			}
			
		};
		
		ExecutorService pool = Executors.newFixedThreadPool(10);
		List<Future<Date>> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			list.add(pool.submit(task));
		}
		for (Future<Date> future : list) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		pool.shutdown();
	}
	
	
	/**
	 * 使用Java8提供的类
	 */
	@Test
	public void test2(){
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		Callable<LocalDate> task = new Callable<LocalDate>(){
			@Override
			public LocalDate call() throws Exception {
				return LocalDate.parse("20200227", dtf);
			}
		};
		
		ExecutorService pool = Executors.newFixedThreadPool(10);
		List<Future<LocalDate>> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			list.add(pool.submit(task));
		}
		for (Future<LocalDate> future : list) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		pool.shutdown();
	}
}
