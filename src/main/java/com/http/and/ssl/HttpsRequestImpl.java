package com.http.and.ssl;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alipaydaikou.until.AlipayDaiKouQuery;
import com.alipaydaikou.until.AlipayDaiKouSubmit;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class HttpsRequestImpl  {

	private static final Logger LOG = LoggerFactory
			.getLogger(HttpsRequestImpl.class);

	// 表示请求器是否已经做了初始化工作
	private boolean hasInit = false;

	// 连接超时时间，默认20秒
	private int socketTimeout = 60000;//20000

	// 传输超时时间，默认30秒
	private int connectTimeout = 30000;

	// 请求器的配置
	private RequestConfig requestConfig = null;

	// HTTP请求器
	private CloseableHttpClient httpClient = null;
	@Autowired
	private  MeterRegistry registry;
	public HttpsRequestImpl() throws UnrecoverableKeyException,
			KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, IOException {
		init();
	}

	private void init() throws IOException, KeyStoreException,
			UnrecoverableKeyException, NoSuchAlgorithmException,
			KeyManagementException {

		// KeyStore keyStore = KeyStore.getInstance("PKCS12");
		// FileInputStream instream = new
		// FileInputStream(Configure.certLocalPath);// 加载本地的证书进行https加密传输
		// try {
		// keyStore.load(instream, Configure.certPassword.toCharArray());//
		// 设置证书密码
		// } catch (CertificateException e) {
		// LOG.error("证书错误");
		// } catch (NoSuchAlgorithmException e) {
		// LOG.error("密钥错误");
		// } finally {
		// instream.close();
		// }

		// Trust own CA and all self-signed certs
		// SSLContext sslcontext = SSLContexts
		// .custom()
		// .loadKeyMaterial(keyStore, Configure.certPassword.toCharArray())
		// .build();
		// // Allow TLSv1 protocol only
		// SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
		// sslcontext, new String[] { "TLSv1" }, null,
		// SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(200);

		httpClient = HttpClients.custom().setConnectionManager(cm).build();

		// 根据默认超时限制初始化requestConfig
		requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout).build();

		hasInit = true;
	}

	/**
	 * 通过Https往API post xml数据
	 *
	 * @param url
	 *            API地址
	 * @param xmlObj
	 *            要提交的XML数据对象
	 * @return API回包的实际数据
	 * @throws IOException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	@SuppressWarnings("rawtypes")
	
	public String sendPost(String url, Object xmlObj, Class clazz)
			throws IOException, KeyStoreException, UnrecoverableKeyException,
			NoSuchAlgorithmException, KeyManagementException {
		if (!hasInit) {
			init();
		}

		String result = null;
		HttpPost httpPost = new HttpPost(url);
//		HttpGet
//		HttpDelete
//		HttpPatch

		// 解决XStream对出现双下划线的bug
		XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8",
				new XmlFriendlyNameCoder("-_", "_")));

		// 将要提交给API的数据对象转换成XML格式数据Post给API
		xStreamForRequestPostData.alias("xml", clazz);
		String postDataXML = xStreamForRequestPostData.toXML(xmlObj);
		LOG.info("API，POST过去的数据是：");
		LOG.info(postDataXML);

		// 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
		StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.setEntity(postEntity);

		// 设置请求器的配置
		httpPost.setConfig(requestConfig);

		LOG.info("executing request" + httpPost.getRequestLine());

		try {
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "UTF-8");
			} finally {
				response.close();
			}
		} catch (ConnectionPoolTimeoutException e) {
			LOG.error("http get throw ConnectionPoolTimeoutException(wait time out)");
		} catch (ConnectTimeoutException e) {
			LOG.error("http get throw ConnectTimeoutException");
		} catch (SocketTimeoutException e) {
			LOG.error("http get throw SocketTimeoutException");
		} catch (Exception e) {
			LOG.error("http get throw Exception");
		} finally {
			httpPost.abort();
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "finally" })
	
	public String sendPostXinlian(String url, Map<String, Object> map)
			throws ClientProtocolException, IOException,
			UnrecoverableKeyException, KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException {
		if (!hasInit) {
			init();
		}
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		// 设置请求器的配置
		httpPost.setConfig(requestConfig);
		try {
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator
						.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
			LOG.info("请求参数"+list.toString());
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "GBK");
			} finally {
				response.close();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			httpPost.abort();
			return result;
		}
	}
	
	
	/**
	 * 通过Https往API post xml数据
	 *
	 * @param url
	 *            API地址
	 * @param xmlObj
	 *            要提交的XML数据对象
	 * @return API回包的实际数据
	 * @throws IOException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	
	public String sendPostEtc(String url,  String jsonObject)
			throws IOException, KeyStoreException, UnrecoverableKeyException,
			NoSuchAlgorithmException, KeyManagementException,Exception {
		if (!hasInit) {
			init();
		}
		String result = null;
		String decryptResult = null;
		HttpPost httpPost = new HttpPost(url);
		// 设置请求器的配置
		httpPost.setConfig(requestConfig);
		try {
			LOG.info("请求数据："+jsonObject);
			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
			/*XlDesTools des = new XlDesTools(AppCode.XL_ETC_KEY);
		    String encryptString = "message="+des.encrypt(jsonObject);
		    LOG.info("请求加密数据："+encryptString);
		    StringEntity postEntity = new StringEntity(encryptString, "UTF-8");*/
			 StringEntity postEntity = new StringEntity(jsonObject, "UTF-8");
			httpPost.setEntity(postEntity);

			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "UTF-8");
			/*	decryptResult = des.decrypt(result);
				LOG.info("返回解密数据："+decryptResult);*/
			} finally {
				response.close();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			httpPost.abort();
			return decryptResult;
		}
	}
	
	
	
	public String dkreqest(Map<String, String> sParaTemp, String key,
			String sign_type) throws Exception {
		
		return  AlipayDaiKouSubmit.buildRequestNoCert(sParaTemp, key,sign_type);

	}

	
	public String dkQuryreq(Map<String, String> sParaTemp, String key,
			String sign_type) throws Exception {
		return AlipayDaiKouQuery.buildRequestNoCert(sParaTemp, key, sign_type);
	}

	/**
	 * 商户对账
	 * @param url
	 * @param jsonObject
	 * @return
	 * @throws IOException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws Exception
	 */
	public static  String sendMerduizhang(String url,  String jsonObject)
			throws IOException, KeyStoreException, UnrecoverableKeyException,
			NoSuchAlgorithmException, KeyManagementException,Exception {
		
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(200);

		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();

		// 根据默认超时限制初始化requestConfig
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000)
				.setConnectTimeout(30000).build();
		
		
		
		
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		// 设置请求器的配置
		httpPost.setConfig(requestConfig);
		try {
			LOG.info("请求数据："+jsonObject);
			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
			StringEntity postEntity = new StringEntity(jsonObject, "UTF-8");
			httpPost.setEntity(postEntity);

			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, "UTF-8");
				LOG.info("响应数据："+result);
			} finally {
				response.close();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			httpPost.abort();
			return result;
		}
	}
	
	/**
	 * httppost请求(用于key-value格式的参数) 请求银行 监控url的响应时间
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws KeyManagementException
	 * @throws UnrecoverableKeyException
	 */
	public  String PostMapMertics(String url, Map params) throws UnrecoverableKeyException, KeyManagementException,
			KeyStoreException, NoSuchAlgorithmException, IOException{
		if (!hasInit) {
			init();
		}
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		// 设置请求器的配置
		httpPost.setConfig(requestConfig);
		try {
			//设置参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Iterator iter = params.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String value = String.valueOf(params.get(name));
				nvps.add(new BasicNameValuePair(name, value));

			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
			Timer.Sample sample =null /*= Timer.start(registry)*/;
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				String statusCode = String.valueOf(response.getStatusLine().getStatusCode());
				sample.stop(registry.timer("http.client.requests","uri",url,"method", "POST", "status", statusCode));
				if(response.getStatusLine().getStatusCode() ==200 && entity !=null){
					result = EntityUtils.toString(entity, "UTF-8");
				}
			} finally {
				response.close();

			}
		}  catch (ParseException e) {
			e.printStackTrace();
		} finally {
			httpPost.abort();
			return result;
		}
	}

	public static void main(String[] args) {
		String url = "";
		String str = "";
		String sign = "";
		
		String requestStr = str+"&"+"sign="+sign;
		
		try {
			String responsestr = sendMerduizhang(url,requestStr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
