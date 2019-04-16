package com.thread.pool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author li_hhui
 * @date:2019年4月15日
 * @version:
 */
public class RejectedExenHandler implements RejectedExecutionHandler{

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("线程池已经到达最大处理能力，无法处理新的请求....");
	}

}
