package com.http.and.ssl.httpClient;


import com.alibaba.fastjson.JSON;
import com.http.and.ssl.HttpsRequestImpl;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


/**
 * Content-Type application/json 编码类型。json格式的请求参数通过HttpEntity设置到请求体中，后台使用@RequestBody 接收，spring解析器可将json转化为实体类。GET请求中没有entity,故@RequesBody不知道get请求
 * @author li_hhui
 * @date:2019年7月14日
 * @version:
 */
public class TestHttpClientForRequestBodyAnnotation {


	public static void main(String[] args) throws Exception {
		String url ="http://localhost:80/lr/impl/create";
        ColumnInfo c1 = new ColumnInfo();
        c1.setColumnName("username");
        c1.setColumnComment("姓名");
        c1.setColumnType("varchar");
        c1.setIsPrimaryKey(false);
        c1.setIsNotNull(true);
        c1.setIsIncrement(false);

        ColumnInfo c2 = new ColumnInfo();
        c2.setColumnName("id");
        c2.setColumnComment("id");
        c2.setColumnType("number");
        c2.setIsPrimaryKey(true);
        c2.setIsNotNull(true);
        c2.setIsIncrement(false);


        ColumnInfo c3 = new ColumnInfo();
        c3.setColumnName("age");
        c3.setColumnComment("age");
        c3.setColumnType("number");
        c3.setIsPrimaryKey(false);
        c3.setIsNotNull(true);
        c3.setIsIncrement(false);
        List<ColumnInfo> columnInfoList = new ArrayList<>();
		columnInfoList.add(c1);
		columnInfoList.add(c2);
		columnInfoList.add(c3);

        RequestEntity r = new RequestEntity();
        r.setColumns(columnInfoList);
        r.setTableName("T");
        r.setTableComment("用户表");

		HttpsRequestImpl http = new HttpsRequestImpl();
		http.sendPostEtc(url,JSON.toJSONString(r));

        try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Content-Type", "application/json");
			StringEntity post = new StringEntity(JSON.toJSONString(r),"UTF-8");
			httpPost.setEntity(post);
			String response = "";
			try {
				HttpResponse httpResponse = httpClient.execute(httpPost);
				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					System.out.println("请求和响应成功");
					HttpEntity httpEntity = httpResponse.getEntity();
					response = EntityUtils.toString(httpEntity);
	                System.out.println("返回报文=="+response);
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}


/*	private static String getSign(CMBWithHoldConsumeRequest req, String string) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		
		Map<String, Object> map = objectToMap(req);
		String signStr = createLinkString(map)+string;
		String sign = MD5.MD5EncodeJh(signStr, "UTF-8");
		return sign;

	}
	*/
	
	  public static TreeMap<String, Object> objectToMap(Object obj) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {    
	        if(obj == null)  
	            return null;      
	  
	        TreeMap<String, Object> map = new TreeMap<String, Object>();   
	  
	        BeanInfo beanInfo;
			beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
	        for (PropertyDescriptor property : propertyDescriptors) {    
	            String key = property.getName();    
	            if (key.compareToIgnoreCase("class") == 0) {   
	                continue;  
	            }  
	            Method getter = property.getReadMethod();  
	            Object value =getter.invoke(obj);
	            if(null !=value ){
			         map.put(key, value);  
	            }
	        }    
	  
	        return map;  
	    } 
	  
	  
	  
	  public static String createLinkString(Map<String, Object> params){
	        
	        List<String> keys = new ArrayList<String>(params.keySet());
	        Collections.sort(keys);
	        String prestr = "";
	        for (int i = 0; i < keys.size(); i++){
	            String key = keys.get(i);
	            String value = (String) params.get(key);
	            if(key != null && value != null){
	            	if (i == keys.size() - 1){// 拼接时，不包括最后一�?&字符
	                    prestr = prestr + key + "=" + value;
	                }
	                else{
	                    prestr = prestr + key + "=" + value + "&";
	                }
	            }
	            
	        }
	        return prestr;
	    }
	  


}
