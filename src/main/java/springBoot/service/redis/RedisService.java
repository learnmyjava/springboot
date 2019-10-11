package springBoot.service.redis;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @author li_hhui
 * @date:2019年10月11日
 * @version:
 */
@Service
public class RedisService {
	private static final Logger LOG = LoggerFactory.getLogger(RedisService.class);
	@Autowired
	private RedisTemplate redisTemplate;
	/**
	 * 写入缓存
	 * @param key
	 * @param value
	 */
	public boolean set(String key,Object value){
		boolean result = false;
		try {
			ValueOperations<String, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			LOG.error("redis写入缓存失败"+e.getMessage());
			e.printStackTrace();
			
		}
		return result;
	}
	
	
	public Object get(String key){
		Object resultObject = null;
		ValueOperations<String, Object> operations = redisTemplate.opsForValue();
		resultObject = operations.get(key);
		return resultObject;
	}
	
	
}
