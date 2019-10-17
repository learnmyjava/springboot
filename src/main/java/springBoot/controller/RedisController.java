package springBoot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	/**
	 * string类型读取
	 * @param key
	 * @return
	 */
	@RequestMapping("/getData")
	private String getData(@RequestParam (name="key") String key){
		
		String name = (String) redisService.get(key);
		LOG.info("获取参数="+name);
		return name;
	}

	/**
	 * string类型存储
	 * @param key
	 * @param value
	 * @return
	 */
	@RequestMapping("/setData")
	private String setData(@RequestParam(name="key")String key,@RequestParam(name="value") String value){
		
		boolean result = redisService.set(key, value);
		LOG.info("存入数据="+result );
		return null;
	}
	/**
	 * 存取list
	 * @param key
	 */
	@RequestMapping("/setlist")
	private void setlist(@RequestParam(name="key") String key){
		redisService.setlist(key);
		
	}
	
	@RequestMapping("/setOper")
	private void setOper(){
		redisService.setOper();
	}
	
	@RequestMapping("/hashmap")
	private void sethashmap(){
		redisService.hashmapOper();
	}
	
	@RequestMapping("/setObject")
	private void setobject(){
		redisService.setObject();
	}
}
