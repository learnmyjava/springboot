package springBoot.Adapter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebSecurityAdapter implements  WebMvcConfigurer{
		@Autowired
		private JwtInterceptor jwtInterceptor;
		public void addInterceptors(InterceptorRegistry registry) {
			  InterceptorRegistration addInterceptor = registry.addInterceptor(jwtInterceptor);
			  addInterceptor.excludePathPatterns("/error");//不拦截
			  addInterceptor.excludePathPatterns("/login**");//登录相关
			  addInterceptor.excludePathPatterns("/logoff");//注销
			  addInterceptor.addPathPatterns("/**");
			  
		}
	  
	  //swagger-ui.html相关的所有前端静态文件都在springfox-swagger-ui-2.4.0.jar里面
	  //Springboot 自动配置的时候不会把swagger-ui.html这个路径映射到对应的目录META-INF/resources/下面 
	  //需要加上这个映射
	  
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
		  
		  registry.addResourceHandler("swagger-ui.html")
          .addResourceLocations("classpath:/META-INF/resources/");

      registry.addResourceHandler("/webjars/")
          .addResourceLocations("classpath:/META-INF/resources/webjars/");
	  }
}
