package com.alipaydaikou.until;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpService {
	private static final Logger log = LoggerFactory
			.getLogger(HttpService.class);

	public static int DEFAULT_POOLING_NUM = 100;

	public static CloseableHttpClient getHttpClientDefalut() {
		return HttpClients.createDefault();
	}

	public static CloseableHttpClient getPoolingHttpClient(Integer poolNum) {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(poolNum == null ? DEFAULT_POOLING_NUM : poolNum
				.intValue());
		return HttpClients.custom().setConnectionManager(cm).build();
	}

	public static String executeHttpRequest(HttpRequestBase httpRequestBase,
			HttpRequestConfig httpRequestConfig,
			ResponseHandler<String> responseHandler) {
		CloseableHttpClient httpClient = null;
		if (httpRequestConfig != null) {
			if (httpRequestConfig.isPoolSwitch()) {
				if (httpRequestConfig.getPoolingNum() != null)
					httpClient = getPoolingHttpClient(httpRequestConfig
							.getPoolingNum());
				else
					httpClient = getPoolingHttpClient(null);
			} else
				httpClient = getHttpClientDefalut();
		} else {
			httpClient = getPoolingHttpClient(null);
		}
		if (httpRequestConfig != null) {
			requestConfig(httpRequestBase, httpRequestConfig);
		}
		String responseBody = null;
		try {
			responseBody = (String) httpClient.execute(httpRequestBase,
					responseHandler);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				httpClient.close();
			} catch (IOException e1) {
				e1.printStackTrace();
				log.info("executeHttpRequest中发生异常", e1);
			}
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
				log.info("executeHttpRequest中发生异常", e);
			}
		}
		return responseBody;
	}

	public static String executePost(String url, Map<String, String> params,
			ResponseHandler<String> responseHandler) {
		return executePost(url, params, null, responseHandler);
	}

	public static String executePost(String url, Map<String, String> params,
			HttpRequestConfig httpRequestConfig,
			ResponseHandler<String> responseHandler) {
		if (url == null) {
			log.error("url为空");
			return null;
		}
		HttpPost httpPost = new HttpPost(url);
		Charset charset = Consts.UTF_8;
		if ((httpRequestConfig != null)
				&& (httpRequestConfig.getCharset() != null)) {
			charset = httpRequestConfig.getCharset();
		}
		httpPost.setEntity(wrapperRequestParams(params, charset));
		return executeHttpRequest(httpPost, httpRequestConfig, responseHandler);
	}

	public static String executeGet(String url, Map<String, String> params,
			ResponseHandler<String> responseHandler) {
		return executeGet(url, params, null, responseHandler);
	}

	public static String executeGet(String url, Map<String, String> params,
			HttpRequestConfig httpRequestConfig,
			ResponseHandler<String> responseHandler) {
		if (url == null) {
			log.error("url为空");
			return null;
		}
		HttpGet httpGet = new HttpGet();
		Charset charset = Consts.UTF_8;
		if ((httpRequestConfig != null)
				&& (httpRequestConfig.getCharset() != null)) {
			charset = httpRequestConfig.getCharset();
		}
		try {
			String strParams = EntityUtils.toString(
					wrapperRequestParams(params, charset), charset);
			String reqUrl = url + "?" + strParams;
			httpGet.setURI(new URI(reqUrl));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return executeHttpRequest(httpGet, httpRequestConfig, responseHandler);
	}

	public static HttpEntity wrapperRequestParams(Map<String, String> params,
			Charset charset) {
		List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
		if ((params != null) && (params.size() != 0)) {
			for (String key : params.keySet()) {
				String value = (String) params.get(key);
				nvps.add(new BasicNameValuePair(key, value));
			}
		}
		return new UrlEncodedFormEntity(nvps, charset == null ? Consts.UTF_8
				: charset);
	}

	public static void requestConfig(HttpRequestBase httpRequestBase,
			HttpRequestConfig httpRequestConfig) {
		log.info("执行requestConfig()......");
		RequestConfig.Builder builder = RequestConfig.custom();
		if (httpRequestConfig.getContentType() != null) {
			httpRequestBase.setHeader("Content-Type",
					httpRequestConfig.getContentType());
		}
		if ((httpRequestConfig.getProxyIp() != null)
				&& (httpRequestConfig.getProxyPort() != null)) {
			HttpHost proxy = new HttpHost(httpRequestConfig.getProxyIp(),
					httpRequestConfig.getProxyPort().intValue());
			builder.setProxy(proxy);
		}

		if (httpRequestConfig.getSocketTimeout() != null) {
			builder.setSocketTimeout(httpRequestConfig.getSocketTimeout()
					.intValue());
		}
		if (httpRequestConfig.getConnectTimeout() != null) {
			builder.setConnectTimeout(httpRequestConfig.getConnectTimeout()
					.intValue());
		}
		RequestConfig requestConfig = builder.build();
		httpRequestBase.setConfig(requestConfig);
	}
}
