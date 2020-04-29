package springBoot.JWT.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import springBoot.controller.LoginController;
import springBoot.exception.ResultCode;
import springBoot.exception.ServerException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;





import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT的工具类用于创建token并校验token的合法性
 * 参考博客
 * 
 * https://www.jianshu.com/p/9f5b09b3739a
 */
public class JWTUtil
{
	public static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	
    /**
     * 过期时间 30分钟
     */
    private static final long EXPIRE_TIME = 1 * 60 * 1000;
    
    /**
     * 加密密文 秘钥
     */
    private static final String TOKEN_SECRET = "e7a5aeaac86a4d579f26b7bb1e16bd9f";

    public static String getToken(String id, String username) {
        Date expTime = new Date( System.currentTimeMillis()+ EXPIRE_TIME);
        Date issuedAt = new Date(System.currentTimeMillis());
        return Jwts.builder()
                .setSubject(username)
                .setSubject(id)
                .claim("username", username)
                .claim("id", id)
                .setIssuedAt(issuedAt)
                .setExpiration(expTime)
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET)
                .compact();
    }

    /**
     * 先校验token是否失效
     * @param jwt
     * @return
     */
    public static boolean checkTokenInvalid(String jwt)
    {
        DecodedJWT decodedJWT = JWT.decode(jwt);
        Date expiresAt = decodedJWT.getExpiresAt();
        LOG.info("###token失效时间为：{}", expiresAt);
        Date thisDate = new Date(System.currentTimeMillis());
        int compareTo = expiresAt.compareTo(thisDate);
        if (compareTo < 1)
        {
            LOG.info("###token已失效");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 解析jwt(校验)  
     * 令牌超时,直接运行时异常,添加全局异常信息
     * @param jwt
     * @return
     */
    public static Claims parseJWT(String jwt){
    	try {
    		LOG.info("###验证JWT");
            Claims claims = Jwts.parser().setSigningKey(TOKEN_SECRET).parseClaimsJws(jwt).getBody();
            return claims;
		} catch (ExpiredJwtException  e1) {
			throw new ServerException(ResultCode.PERMISSION_TOKEN_EXPIRED);
		}catch (Exception e) {
			throw new ServerException(ResultCode.PERMISSION_TOKEN_INVALID);
		}
        
    }




    /**
     * 生成签名
     *
     * @param id
     * @param username
     * @param
     * @return
     */
    public static String sign(Integer id, String username)
    {
        LOG.info("###生成签名");
        // 设置过期时间
        Date expTime = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        // 私钥和加密算法，使用用户输入的密码
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        // 设置头信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("typ", "JWT");
        map.put("alg", "HS256");
        // 生成jwt的签发时间
        Date issuedAt = new Date(System.currentTimeMillis());
        String sign = JWT.create()
                .withHeader(map)
                .withClaim("id", id)
                .withClaim("username", username)
                .withIssuedAt(issuedAt)
                .withExpiresAt(expTime)
                .sign(algorithm);
        return sign;
    }

    /**
     * 校验token
     * 解密的另外一种写法
     * 这种写法如果令牌超时,直接运行时异常,无法做相关处理
     * 
     * @param token
     * @param username
     * @param passwoord
     * @return
     */
    public static boolean verify(String token, String username, String passwoord)
    {
        LOG.info("###校验token是否正确");
        try
        {
            Algorithm hmac256 = Algorithm.HMAC256(passwoord);
            JWTVerifier jwtVerifier = JWT.require(hmac256).withClaim("username", username).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            Map<String, Claim> claims = decodedJWT.getClaims();
            Claim id = claims.get("id");
            if (!StringUtils.isEmpty(id))
            {
                Integer integer = id.asInt();
                LOG.info("###用户Id:{}", integer);
                return true;
            }
        }
        catch (Exception e)
        {
            LOG.error("###token异常", e);
        }
        return false;
    }

    /**
     * 获取登录用户名
     * @param token
     * @return
     */
    public static String getUsername(String token)
    {
        LOG.info("###获取登录用户名");
        try
        {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        }
        catch (JWTDecodeException e)
        {
            LOG.error("###获取用户名异常");
            return null;
        }
    }
    
  
}
