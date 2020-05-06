package springBoot.controller;

import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springBoot.entity.User;

import com.google.common.util.concurrent.RateLimiter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author li_hhui
 * @date:2020年5月6日
 * @version:
 * 令牌桶算法限流
 * 
 * google guava RateLimiter 速率器
 */

@Slf4j
@RestController
public class TokenBucketController {
	
	// 1秒内  40个线程并发数  速率器每秒产生5个 会出现获取不到令牌的情况  
	  private static final RateLimiter retelimter = RateLimiter.create(5);//创建 每秒产生X个令牌 的速率器


	    @RequestMapping("/limiter")
	    public TreeMap<String,String>  limiter(User req){
	        TreeMap treeMap = new TreeMap();
	        if(retelimter.tryAcquire(5, TimeUnit.SECONDS)){//获取令牌超时时间5S
	            log.info("获取到令牌，开始进行业务处理");
	            try {
	                Thread.sleep(10000);//处理业务需要10秒
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	            treeMap.put("respcode","00");
	            treeMap.put("respdesc","获取到令牌");


	        }else{
	            log.warn("请求量过大，获取不到令牌");
	            treeMap.put("respcode","99");
	            treeMap.put("respdesc","系统繁忙，请稍后在访问");
	        }

	        return treeMap;


	    }


}
