package springBoot.RedisConfig;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * @author li_hhui
 * @date:2019年11月11日
 * @version:
 * 集群
 */
//@Configuration
public class JedisClusterConfig {
	//@Autowired
	private RedisProperties redisProperties;
	/**
	 * new jedisCluster 单例 ,可以直接注入到其他类中
	 * @return
	 */
	//@Bean
	public JedisCluster getJedisCluster(){
		
		String[] serverArray = redisProperties.getClusterNodes().split(".");
		Set<HostAndPort> nodes = new  HashSet<HostAndPort>();
		for (String ipPort:serverArray) {
			String[] ipPortPair = ipPort.split(":");
			nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
		}
		
		return new JedisCluster(nodes,redisProperties.getCommandTimeout());
	}

}
