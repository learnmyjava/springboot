package com.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author li_hhui
 * @date:2019年4月15日
 * @version:
 * 各类常用线程池
 */
public class ThreadPoolUtil {

	/**
	 * java util  newCachedThreadPool
	 */
	@Test
	public void testcachedThreadPool(){
		
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i <100; i++) {
			 int ss=100;
			cachedThreadPool.execute(new DoSaveJob(i));
		}
	}	
	
	
	
	/**
	 * Spring线程池ThreadPoolTaskExecutor
	 */
	@Test
	public void springThreadPoolTaskExecutor(){
		//使用spring容器创建
		
		ThreadPoolTaskExecutor poolThread = new ThreadPoolTaskExecutor();
		poolThread.setCorePoolSize(10);//维护最小线程数
		poolThread.setMaxPoolSize(50);//维护最大线程数
		poolThread.setQueueCapacity(100);//缓存队列
		poolThread.setKeepAliveSeconds(300);//允许线程空闲的时间
		poolThread.setRejectedExecutionHandler(new RejectedExenHandler());//对于拒绝task的处理策略
		
		for (int i = 0; i < 10; i++) {
			poolThread.execute(new DoSaveJob(i));
		}
		
	}
	/**
	 * 创建一个定长的线程池，而且支持定时的周期性的任务计划
	 */
	public static void main(String[] args) {
		 /*ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		  scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
		   public void run() {
		    System.out.println("delay 1 seconds, and excute every 3 seconds");
		   }
		  }, 1, 3, TimeUnit.SECONDS);*/  
		
		int i = 1;
		
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.scheduleAtFixedRate(new DoSaveJob(i), 1, 5, TimeUnit.MINUTES);//创建线程后延迟1秒 每5秒执行一次
		  
		  
	 }

	
}
