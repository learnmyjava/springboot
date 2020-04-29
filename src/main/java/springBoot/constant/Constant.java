package springBoot.constant;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author li_hhui
 * @date:2020年4月29日
 * @version:
 */
@Data
@ConfigurationProperties(prefix = "springboot")
@Component
public class Constant {
	
	public   String c1;
	public  int c2;

}
