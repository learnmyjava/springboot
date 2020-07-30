package com.http.and.ssl.httpClient;


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

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Content-Type 默认application/x-www-form-urlencoded，key=value&...的请求参数被设置在请求头中,即请求头中param集中，后台使用@RequestParam接收，spring将请求参数转换成实体类（同request.getparam("key")）
 * @author li_hhui
 * @date:2019年7月14日
 * @version:
 */
public class TestHttpClientForRequestParamAnnotation {



	public static void main(String[] args) {
		String url ="http://60.208.85.140:9999/spdbTrans";//10.183.32.66
		SPDBConsumeRequest request = new SPDBConsumeRequest();
		String bank_abbr ="38";
		String partner_id ="854100007420608";
		String out_partner_id="777290058110097";
		String tid_no ="13201507";
		String certif_type="01";
		String certifId="341126197709218366";
		String customer_name="全渠道";
		String phoneNo="13552535506";
		String bankNo="6216261000000000018";
		String sign_type="MD5";
		String charset="UTF-8";
		String bg_notify_url="https://www.baidu.com/";
		
		
		String order_id="12810201906070095";//订单号
		String txnAmt="2";
		String tid_seq="5015";
		
		
		request.setBank_abbr(bank_abbr);
		request.setPartner_id(partner_id);
		request.setOut_partner_id(out_partner_id);
		request.setTid_no(tid_no);
		request.setOrder_id(order_id);
		request.setTxnAmt(txnAmt);
		request.setCertif_type(certif_type);
		request.setCertifId(certifId);
		request.setCustomer_name(customer_name);
		request.setPhoneNo(phoneNo);
		request.setBankNo(bankNo);
		request.setTid_seq(tid_seq);
		request.setSign_type(sign_type);
		request.setCharset(charset);
		request.setBg_notify_url(bg_notify_url);
						
		try {
			String sign=getSign(request, "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCRMzy5YPdTej7UKKAKiV5X02A4nmIwH8oNUKfxHbgnem97s99Xm0zRVq34dhNZY8NF4XIsU+BvC8C6a5SlAQEeEB+u8YA7rByGGk4pIanM8UyppT/ZwBpRzlwbpmc377kYLoOpr/wUTXHCw++GQz1qJg0FsDzl820cv4f04al03QIDAQAB");
			System.out.println("sign:"+sign);
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("bank_abbr", bank_abbr));
			params.add(new BasicNameValuePair("partner_id", partner_id));
			params.add(new BasicNameValuePair("out_partner_id",out_partner_id ));
			params.add(new BasicNameValuePair("tid_no", tid_no));
			params.add(new BasicNameValuePair("order_id", order_id));
			params.add(new BasicNameValuePair("txnAmt", txnAmt));
			params.add(new BasicNameValuePair("tid_seq",tid_seq ));
			params.add(new BasicNameValuePair("sign_type",sign_type ));
			params.add(new BasicNameValuePair("charset", charset));
			params.add(new BasicNameValuePair("bg_notify_url", bg_notify_url));
			params.add(new BasicNameValuePair("certif_type",certif_type ));
			params.add(new BasicNameValuePair("certifId", certifId));
			params.add(new BasicNameValuePair("customer_name", customer_name));
			params.add(new BasicNameValuePair("phoneNo", phoneNo));
			params.add(new BasicNameValuePair("bankNo",bankNo));
			
			params.add(new BasicNameValuePair("sign", sign));

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

	private static String getSign(SPDBConsumeRequest req, String string) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		
		/*Map<String, Object> map = objectToMap(req);
		String signStr = createLinkString(map)+string;
		String sign = MD5.MD5EncodeJh(signStr, "UTF-8");
		return sign;*/
		return  null;

	}
	
	
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
