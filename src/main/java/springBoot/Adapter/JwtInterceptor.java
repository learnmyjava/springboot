package springBoot.Adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import springBoot.JWT.util.JWTUtil;
import springBoot.entity.User;

/**
 * @author li_hhui
 * @date:2020年4月29日
 * @version:
 */
@Slf4j
@Configuration
public class JwtInterceptor extends HandlerInterceptorAdapter{
	public final static String SESSION_USER_KEY="user";

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		  HttpSession session = request.getSession();
		  User user =(User) session.getAttribute(SESSION_USER_KEY);
		  if(user !=null && user.getToken() !=null ){//已经登录过
			  
			  //获取token
			  String token = user.getToken();
			  String username = JWTUtil.getUsername(token);
			  log.info("当前登录用户:{},token{}:",username,token);
			  JWTUtil.parseJWT(token);//验证token 是否失效  无效则抛出异常，由全局异常处理返回对应信息
			  log.info("token验证通过");
			  return true;
		  }
		  
		  //登录权限拦截	
		  String url="/login";
		  response.sendRedirect(url);
		  return false;
	  

	}

}
