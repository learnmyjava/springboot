package com.md5.encode.anddecode;

import java.util.HashMap;
import java.util.Map;


/**
 * @author li_hhui
 * @date:2019年4月29日
 * @version:
 */
public class MainTest {

	
    public static void main(String args[]) { 
    	//加解密
       String s = new String("thisisoriadata中午");  //含有中文情况下 如下两个md5的结果不一样
       System.out.println("原始：" + s);  
       System.out.println("MD5后：" + MD5Util.string2MD5(s));  

       String decodeStr = MD5Util.convertMD5(s);
       System.out.println("加密的：" + decodeStr); 
       System.out.println("解密的：" + MD5Util.convertMD5(decodeStr));  

    //计算签名
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("name", "lilili");
    	map.put("address", "安徽合肥");
    	map.put("aadress", "山东");
    	map.put("tip", "主题");
    	String ss=MD5Util.getSign(map);
    	System.out.println("sign：" + MD5Util.MD5Encode(ss));  
    	

    }  
}
