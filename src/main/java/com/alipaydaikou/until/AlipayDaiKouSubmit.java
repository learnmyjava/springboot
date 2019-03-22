package com.alipaydaikou.until;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlipayDaiKouSubmit {
	private static final Logger log = LoggerFactory.getLogger(AlipayDaiKouSubmit.class);

	public static String buildRequestMysign(Map<String, String> sPara,
			String privateKey, String sign_type) {
		String prestr = AlipayCore.createLinkString(sPara);
		String mysign = "";
		if ("RSA".equals(sign_type)) {
			mysign = RSA.sign_rsa(prestr, privateKey, "GBK");
		}
		if ("RSA2".equals(sign_type)) {
			mysign = RSA.sign_rsa2(prestr, privateKey, "GBK");
		}
		return mysign;
	}

	public static Map<String, String> buildRequestPara(
			Map<String, String> sParaTemp, String privateKey, String sign_type) {
		Map<String, String> sPara = null;
		String mysign = null;
		try {
			sPara = AlipayCore.paraFilter(sParaTemp);
			mysign = buildRequestMysign(sPara, privateKey, sign_type);
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		sPara.put("sign", mysign);
		sPara.put("sign_type", sign_type);
		log.info("sign:" + mysign);
		return sPara;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String buildRequestNoCert(Map<String, String> sParaTemp,
			String privateKey, String sign_type) throws Exception {
		Map<String, String> sPara = buildRequestPara(sParaTemp, privateKey,sign_type);

		HttpRequest request = new HttpRequest(HttpResultType.BYTES);

		request.setCharset("GBK");
		request.setParameters(generatNameValuePair(sPara));
		String resultStr = null;
		try {
			resultStr = HttpService.executePost(
					"https://mapi.alipay.com/gateway.do?", sPara, null,
					new ResponseHandler() {
						public String handleResponse(
								HttpResponse paramHttpResponse)
								throws ClientProtocolException, IOException {
							HttpEntity httpEntity = paramHttpResponse
									.getEntity();
							String resultStr = httpEntity == null ? null
									: EntityUtils.toString(httpEntity);
							return resultStr;
						}
					});
		} catch (Exception e) {
			log.info("请求银行发生的异常  ", e);
		}
		return resultStr;
	}

	private static NameValuePair[] generatNameValuePair(
			Map<String, String> properties) {
		NameValuePair[] nameValuePair = new NameValuePair[properties.size()];
		int i = 0;
		for (Map.Entry entry : properties.entrySet()) {
			nameValuePair[(i++)] = new NameValuePair((String) entry.getKey(),
					(String) entry.getValue());
		}

		return nameValuePair;
	}
	
	/*public static void main(String[] args) {
		 Map<String, String> txtParams = new HashMap<String, String>();
		    txtParams.put("service", "alipay.acquire.createandpay");
		    txtParams.put("partner", "2088421490783646");//3646
		    txtParams.put("_input_charset", "UTF-8");
		    String out_ord_no = new SimpleDateFormat("yyyyMMddHHmmss")
		      .format(new Date());
		    txtParams.put("out_trade_no", "ALDK" + out_ord_no);
		    System.out.println("ALDK" + out_ord_no);
		    txtParams.put("subject", "AliDK");
		    txtParams.put("product_code", "GENERAL_WITHHOLDING");
//		    txtParams.put("auth_no", "123456789");
		    txtParams.put("total_fee", "0.01");
		    txtParams.put("buyer_id", "");
		    txtParams.put("body", "ALDK");
		   // txtParams.put("goods_detail", detailJson);
		    txtParams.put("sign_type", "RSA");
		    txtParams.put("notify_url", 
		      "http://www.baidu.com");
		    txtParams.put("agreement_info", "{\"agreement_no\":\"\"}");
//		    txtParams.put("extend_params", "{\"STORE_ID\":\"AM180601\",\"STORE_TYPE\":\"1\"}");

		    String privateKey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJvTytEOUoPeC2e7FG5sh7rhmHSuwVW3D8MfOkrVOR+NojMKmCEQ756HmxXRDwaQ3Vc/0ybephxNnFRA2VGbvuvZNxTQqrDiLXg/p40E3tKwcvMD+22tynY21m1LqCSlNzCKPxwDPGIjIEOYRZ/LNVR8r4tMc1ekr+7m7ObLWp/FAgMBAAECgYBAR/cjlE/vu/pieSctgjHfos58W9a/YDvtVwV590mQPaIKgjVNdqoScIhGUK5tG/di1Z5qK3DEvjNeAj5jzubLyUohgY0Mt29QScPVxrhF6LfekkxIxZtdIGGt/nD79bwUMPNQehACTPVFilgjsv3/USU7v6k5jjbw/viGM625YQJBAMriP8hBrIUylqdLnp9LSL9Vnez3yS+WbU3VV5KMfpY2vMt8V2vMbSbBOHXGfbYuSZF/IRJ23GHsxV0wpvhI+FkCQQDEn7UFPgPMyFst1FFOjmQfNnhq/Q+P9OzRREsxzX7Zakfa6HEd7u+DT776QWmVNwxxIDbRtiuq/qLn2rXKObVNAkASIBpwqqVeFQHumkvmkVRDe8OrKwv3S2oifP/g4NpiUyuUxlwNDmtl/fcsVnper031SLVMr//KY9vSEomoPuSpAkBmv2pyBdkofx6ekU+3tFL/uJAuoHUsbfn+1ShSKyd5Z7+K1mKeGMkSnm6XCe2pqu2WhBYBeqfRIGVysWjD92oVAkBlarG64YzvllrPNtIt2XhuHlPk928lC1jJbAu1H+TXFdRUFgP+/D6mpgHQnc5Kh/IDHvnR2fOinOKFK9MPMqJO";
		   try {//MII
			String response= AlipayDaiKouSubmit.buildRequestNoCert(txtParams, privateKey, "RSA");
			
			System.out.println("代扣响应   "+response);
			Map<String, String> map = ReadXmlUtil.ReaderXmlForSAX(response);
			System.out.println("first   "+map);
			Map<String, String> mapsign =ReadXmlUtil.getSignMap(map);
			  
			System.out.println("解析后"+ConvertTools.sortMapByKey(mapsign));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		    
	}*/
}
