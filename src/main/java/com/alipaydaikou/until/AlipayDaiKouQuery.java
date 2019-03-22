package com.alipaydaikou.until;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlipayDaiKouQuery {
	private static final Logger log = LoggerFactory
			.getLogger(AlipayDaiKouQuery.class);

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
		Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);

		String mysign = buildRequestMysign(sPara, privateKey, sign_type);
		sPara.put("sign", mysign);
		sPara.put("sign_type", sign_type);
		log.info("sign:" + mysign);
		return sPara;
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String buildRequestNoCert(Map<String, String> sParaTemp,
			String privateKey, String sign_type) throws Exception {
		Map<String, String> sPara = buildRequestPara(sParaTemp, privateKey,
				sign_type);

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

	/*public static void main(String[] args) {
		Map<String, String> txtParams = new HashMap<String, String>();
		txtParams.put("service", "alipay.acquire.query");
		txtParams.put("partner", "2088421490783646");
		txtParams.put("_input_charset", "GBK");
		txtParams.put("sign_type", "RSA");
		String out_trade_no = "ALDK20190315104017";
		txtParams.put("out_trade_no", out_trade_no);
		String response = null;
		String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJvTytEOUoPeC2e7FG5sh7rhmHSuwVW3D8MfOkrVOR+NojMKmCEQ756HmxXRDwaQ3Vc/0ybephxNnFRA2VGbvuvZNxTQqrDiLXg/p40E3tKwcvMD+22tynY21m1LqCSlNzCKPxwDPGIjIEOYRZ/LNVR8r4tMc1ekr+7m7ObLWp/FAgMBAAECgYBAR/cjlE/vu/pieSctgjHfos58W9a/YDvtVwV590mQPaIKgjVNdqoScIhGUK5tG/di1Z5qK3DEvjNeAj5jzubLyUohgY0Mt29QScPVxrhF6LfekkxIxZtdIGGt/nD79bwUMPNQehACTPVFilgjsv3/USU7v6k5jjbw/viGM625YQJBAMriP8hBrIUylqdLnp9LSL9Vnez3yS+WbU3VV5KMfpY2vMt8V2vMbSbBOHXGfbYuSZF/IRJ23GHsxV0wpvhI+FkCQQDEn7UFPgPMyFst1FFOjmQfNnhq/Q+P9OzRREsxzX7Zakfa6HEd7u+DT776QWmVNwxxIDbRtiuq/qLn2rXKObVNAkASIBpwqqVeFQHumkvmkVRDe8OrKwv3S2oifP/g4NpiUyuUxlwNDmtl/fcsVnper031SLVMr//KY9vSEomoPuSpAkBmv2pyBdkofx6ekU+3tFL/uJAuoHUsbfn+1ShSKyd5Z7+K1mKeGMkSnm6XCe2pqu2WhBYBeqfRIGVysWjD92oVAkBlarG64YzvllrPNtIt2XhuHlPk928lC1jJbAu1H+TXFdRUFgP+/D6mpgHQnc5Kh/IDHvnR2fOinOKFK9MPMqJO";
		try {
			response = AlipayDaiKouQuery.buildRequestNoCert(txtParams,
					privateKey, "RSA");

			System.out.println("支付宝代扣返回：" + response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
