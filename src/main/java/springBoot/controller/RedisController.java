package springBoot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springBoot.service.redis.RedisService;

/**
 * @author li_hhui
 * @date:2019年10月11日
 * @version:
 */
@RestController
public class RedisController {
	private static final Logger LOG = LoggerFactory.getLogger(RestController.class);
	
	@Autowired
	private RedisService redisService;
	@RequestMapping("/getData")
	private String getData(){
		
		String name = (String) redisService.get("name");
		LOG.info("获取参数="+name);
		return name;
	}

	
	@RequestMapping("/setData")
	private String setData(){
		
		boolean result = redisService.set("address", "安徽合肥");
		LOG.info("存入数据="+result );
		return null;
	}
}
