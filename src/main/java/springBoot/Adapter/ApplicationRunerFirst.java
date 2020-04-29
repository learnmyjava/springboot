package springBoot.Adapter;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author li_hhui
 * @date:2020年3月5日
 * @version: springboot 容器启动时就运行特定代码 两种方式实现APPlicationRunner 实现CommandLineRunner
 */
@Component
public class ApplicationRunerFirst implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("ApplicationRunerFirst==>此处可以实现springboot 启动的时候运行一些 特定方法");
	}

}
