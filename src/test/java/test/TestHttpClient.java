package test;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


/**
 * @author li_hhui
 * @date:2019年7月14日
 * @version:
 */
public class TestHttpClient {



	public static void main(String[] args) {
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
        RequestEntity r = new RequestEntity();
        r.setColumns(columnInfoList);
        r.setTableName("T_USER");
        r.setTableComment("用户表");


        try {
			/*String sign=getSign(request, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCRMzy5YPdTej7UKKAKiV5X02A4nmIwH8oNUKfxHbgnem97s99Xm0zRVq34dhNZY8NF4XIsU+BvC8C6a5SlAQEeEB+u8YA7rByGGk4pIanM8UyppT/ZwBpRzlwbpmc377kYLoOpr/wUTXHCw++GQz1qJg0FsDzl820cv4f04al03QIDAQAB");
			System.out.println("sign:"+sign);*/
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("tableName", "T_USER"));
			params.add(new BasicNameValuePair("tableComment", "用户表"));
//			params.add(new BasicNameValuePair("columns",columnInfoList ));

			UrlEncodedFormEntity entity = null;
			try {
				entity = new UrlEncodedFormEntity(params, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			httpPost.setEntity(entity);
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
