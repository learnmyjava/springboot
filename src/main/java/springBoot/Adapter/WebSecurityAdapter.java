package springBoot.Adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class WebSecurityAdapter extends  WebMvcConfigurerAdapter{
		public final static String SESSION_USER_KEY="user";
		  @Bean
		    public SecurityInterceptor getSecurityInterceptor(){
		        return new SecurityInterceptor();
		    }
		  @Override
		public void addInterceptors(InterceptorRegistry registry) {
			  InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
			  addInterceptor.excludePathPatterns("/error");
			  addInterceptor.excludePathPatterns("/login**");
			  addInterceptor.addPathPatterns("/**");
			  
		}
	  private class SecurityInterceptor extends HandlerInterceptorAdapter {
		  @Override
		public boolean preHandle(HttpServletRequest request,
				HttpServletResponse response, Object handler) throws Exception {
			  HttpSession session = request.getSession();
			  if(session.getAttribute(SESSION_USER_KEY) !=null){
				  return true;
			  }
			  //登录权限拦截	
//			  String url="login";
//			  response.sendRedirect(url);
//			  return false;
			  return true;
		  }
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
