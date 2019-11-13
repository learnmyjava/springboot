package springBoot.service.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisCluster;
import springBoot.entity.User;


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
	@Autowired
	JedisCluster jedisCluster;
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
	
	//list有序可重复
	public List setlist(String key){
		//单个存入
		for (int i = 0; i <= 2; i++) {
			redisTemplate.opsForList().rightPush(key, String.valueOf(i+1)+"个");
			
		}
		List list = redisTemplate.opsForList().range(key, 0, -1);//全部读取
		
		
		List liststring = new ArrayList();
		liststring.add("b1");
		liststring.add("b2");
		liststring.add("b3");
		
		List liststring1 = new ArrayList();
		liststring1.add("a1");
		liststring1.add("a2");
		liststring1.add("a3");
		
		
		
		redisTemplate.opsForList().rightPush("rightlist", liststring1);
		redisTemplate.opsForList().rightPush("rightlist", liststring);
		
		
		redisTemplate.opsForList().leftPush("leftlist", liststring1);
		redisTemplate.opsForList().leftPush("leftlist", liststring);
		
		
		List rightlist = redisTemplate.opsForList().range("rightlist", 0, -1);//全部读取 ===》[[a1, a2, a3], [b1, b2, b3]]
		List leftlist = redisTemplate.opsForList().range("leftlist", 0, -1);//全部读取》[[b1, b2, b3],[a1, a2, a3] ]
		
		

		List list2 = (List) redisTemplate.opsForList().rightPop("rightlist");//全部读取并从缓存删除  list2=[b1, b2, b3] ,rightlist 还剩下[a1, a2, a3]
		List list3= (List) redisTemplate.opsForList().leftPop("leftlist") ;//全部读取并从缓存删除 list3=[b1, b2, b3] ,leftlist 还剩下[a1, a2, a3]
		
		
		return null;
	}
	
	//set 无序不可重复
	public Set<String> setOper(){
		Set<String> set = new HashSet<String>();
		set.add("aa");
		set.add("bb");
		set.add("cc");
		set.add("dd");
		redisTemplate.opsForSet().add("myset", set);
		Set<String> returnSet = redisTemplate.opsForSet().members("myset");
		return returnSet;
		
	}
	//hash
	public void hashmapOper(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "李红慧");
		map.put("id", "190090");
		map.put("address", "安徽合肥");
		map.put("email", "1499004754@qq.com");
		
		redisTemplate.opsForHash().put("namemap", "name", "tom");
		String name = (String) redisTemplate.opsForHash().get("namemap", "name");
		
		redisTemplate.opsForHash().putAll("map", map);
		
		Map<String, String> mapreturnMap = redisTemplate.opsForHash().entries("map");
		Set<String>  keyList =  redisTemplate.opsForHash().keys("map");
		List<String> valueList = redisTemplate.opsForHash().values("map");
		
	}
	
	//VOList
	public void setObject(){
		
		List<User> users = new ArrayList<User>();
		User u1 = new  User("lili","lilipass",45,"山东");
		User u2 = new  User("tom","tompass",23,"河南");
		User u3 = new  User("Jack","Jackpass",42,"安徽");
		User u4 = new  User("Marray","Marraypass",45,"上海");
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);
		
		redisTemplate.opsForValue().set("users", users);
		List<User> returnUsers = (List<User>) redisTemplate.opsForValue().get("users");
		
		
	}
	
	
	//VO
	/**
	 * 单个对象
	 */
	public void setsingleObject(){
		
		User u1 = new  User("lili","lilipass",45,"山东");
		redisTemplate.opsForValue().set("user1", u1);
		User returnUsers =  (User) redisTemplate.opsForValue().get("user1");
		
		
	}
	
	
	
	
}
