package springBoot.Adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
			  
//			  String url="login";
//			  response.sendRedirect(url);
//			  return false;
			  return true;
		  }
	  }
}
