package springBoot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * https://blog.csdn.net/u014745069/article/details/83820511
 * springboot 自动配置原理
 * @SpringBootApplication 是一个复合注解，其中之一的注解EnableAutoConfiguration，这个注解的作用就是开启自动配置，这个注解也是复合注解，
 * 其中关键注解@Import，其导入的AutoConfigurationImportSelector 实现了接口ImportSelector 的selectImports()方法,方法中去读取
 * META-INF/spring.factories 这个文件，此文件以类全限定名的方式 配置所有的springboot需要自动装载的类，容器根据全限定名来创建类并
 * 
 * 与配置文件匹配的原理
 * 以AutoConfiguration结尾来命名的，实际上是个javaConfig 形势的spring容器配置类，此配置类通过@EnableConfigurationProperties 注解将XXXPropertiesde类加载到容器，
 * XXXPropertiesde 这个类又通过@ConfigurationProperties 与全局配置文件匹配。
 * 
 * 以application.properties 中修改服务端口server.port 为例
 * 在spring.factories 配置了ServletWebServerFactoryAutoConfiguration，
 * 通过注解将XxxxProperties 加载到容器，XxxxProperties 通过prefix = "server" 与全局配置文件匹配，由此修改了服务的端口
 * 服务有默认端口在 来自于org.apache.catalina.startup.Tomcat
 */

@EnableTransactionManagement(proxyTargetClass = true)//开启事物管理
@SpringBootApplication
@MapperScan("springBoot.mapper")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	
}

