package com.jsonstring.to.object;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import sun.misc.BASE64Decoder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;


/**
 * @author li_hhui
 * @date:2019年6月22日
 * @version:
 */
public class TestJson {
	/**
	 * 多属性且包含list json转换 java对象或者map
	 * {
"BODY":
{"DETAIL":[{"RETMSG":"�ɹ�","NOTE":"","CARNO":"³B11133","ACTAMT":"1","AMOUNT":"1","RETCODE":"002","SIGNNO":"1000000000000010156","ORDERNO":"19062016750319"},
{"RETMSG":"�ɹ�","NOTE":"","CARNO":"³A11144","ACTAMT":"2","AMOUNT":"2","RETCODE":"002","SIGNNO":"1000000000000010157","ORDERNO":"19062016750320"},{"RETMSG":"�ɹ�","NOTE":"","CARNO":"³A11177","ACTAMT":"3","AMOUNT":"3","RETCODE":"002","SIGNNO":"1000000000000020160","ORDERNO":"19062016750321"},
{"RETMSG":"�ɹ�","NOTE":"","CARNO":"³B11881","ACTAMT":"4","AMOUNT":"4","RETCODE":"002","SIGNNO":"1000000000000010159","ORDERNO":"19062016750322"}],
"TOTALNUM":"4","TOTALAMT":"10","SUCNUM":"4","SUCAMT":"10"},
"HEADER":{"REMARK":"","REQFLAG":"0","CORPNO":"00","TRCODE":"10001","TRDATE":"2019-06-21 19:39:50","REQNO":"ICBC-00-20190621-1"}
}
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@Test
	public void testhavelisttoobject() throws UnsupportedEncodingException, IOException{
		
		String reponseciphertext="{\"data\":\"eyJCT0RZIjp7IkRFVEFJTCI6W3siUkVUTVNHIjois8m5piIsIk5PVEUiOiIiLCJDQVJOTyI6IsKz\\nQjExMTMzIiwiQUNUQU1UIjoiMSIsIkFNT1VOVCI6IjEiLCJSRVRDT0RFIjoiMDAyIiwiU0lHTk5P\\nIjoiMTAwMDAwMDAwMDAwMDAxMDE1NiIsIk9SREVSTk8iOiIxOTA2MjAxNjc1MDMxOSJ9LHsiUkVU\\nTVNHIjois8m5piIsIk5PVEUiOiIiLCJDQVJOTyI6IsKzQTExMTQ0IiwiQUNUQU1UIjoiMiIsIkFN\\nT1VOVCI6IjIiLCJSRVRDT0RFIjoiMDAyIiwiU0lHTk5PIjoiMTAwMDAwMDAwMDAwMDAxMDE1NyIs\\nIk9SREVSTk8iOiIxOTA2MjAxNjc1MDMyMCJ9LHsiUkVUTVNHIjois8m5piIsIk5PVEUiOiIiLCJD\\nQVJOTyI6IsKzQTExMTc3IiwiQUNUQU1UIjoiMyIsIkFNT1VOVCI6IjMiLCJSRVRDT0RFIjoiMDAy\\nIiwiU0lHTk5PIjoiMTAwMDAwMDAwMDAwMDAyMDE2MCIsIk9SREVSTk8iOiIxOTA2MjAxNjc1MDMy\\nMSJ9LHsiUkVUTVNHIjois8m5piIsIk5PVEUiOiIiLCJDQVJOTyI6IsKzQjExODgxIiwiQUNUQU1U\\nIjoiNCIsIkFNT1VOVCI6IjQiLCJSRVRDT0RFIjoiMDAyIiwiU0lHTk5PIjoiMTAwMDAwMDAwMDAw\\nMDAxMDE1OSIsIk9SREVSTk8iOiIxOTA2MjAxNjc1MDMyMiJ9XSwiVE9UQUxOVU0iOiI0IiwiVE9U\\nQUxBTVQiOiIxMCIsIlNVQ05VTSI6IjQiLCJTVUNBTVQiOiIxMCJ9LCJIRUFERVIiOnsiUkVNQVJL\\nIjoiIiwiUkVRRkxBRyI6IjAiLCJDT1JQTk8iOiIwMCIsIlRSQ09ERSI6IjEwMDAxIiwiVFJEQVRF\\nIjoiMjAxOS0wNi0yMSAxOTozOTo1MCIsIlJFUU5PIjoiSUNCQy0wMC0yMDE5MDYyMS0xIn19\"}";
		BASE64Decoder decoder = new BASE64Decoder();
		net.sf.json.JSONObject returnObject = net.sf.json.JSONObject.fromObject(reponseciphertext);
		
		String str = returnObject.getString("data");
		String responseStr = new String(decoder.decodeBuffer(str),"UTF-8");//明文

		
		/*	BackICBCBatchDKRequest respVo = JSONObject.parseObject(responseStr, BackICBCBatchDKRequest.class);
		
		List<ICBCBatchDKDetail> detailList = new ArrayList<ICBCBatchDKDetail>();
		List<String> s= respVo.getBODY().getDETAIL();
    	for (int i = 0; i < s.size(); i++) {
			String string = s.get(i);
			ICBCBatchDKDetail detail = JSONObject.parseObject(string, ICBCBatchDKDetail.class);
			detailList.add(detail);//获取到list方法1
		}
    	*/
    	
    	//to ICBCBatchDKBody.java  如此定义  private List<ICBCBatchDKDetail>  DETAIL;
    	/*BackICBCBatchDKRequest respVo = JSONObject.parseObject(responseStr, BackICBCBatchDKRequest.class);
    	net.sf.json.JSONObject r1 = net.sf.json.JSONObject.fromObject(responseStr);
		net.sf.json.JSONObject r2 = net.sf.json.JSONObject.fromObject(r1.getString("BODY"));
		List<ICBCBatchDKDetail> details = JSONObject.parseArray(r2.getString("DETAIL"), ICBCBatchDKDetail.class);
		respVo.getBODY().setDETAIL(details); */     //获取到list方法2
		
		//to javaObject
		Map responseMap = JSONObject.parseObject(responseStr);
		ICBCBatchDKRequest request = JSONObject.parseObject(responseStr, ICBCBatchDKRequest.class);
		ICBCBatchDKHeader header = request.getHEADER();
		ICBCBatchDKBody body = request.getBODY();
		List<ICBCBatchDKDetail>  DETAIL= body.getDETAIL();
		
		
		//List<ICBCBatchDKDetail>  DETAIL;
		Map headMap = (Map) responseMap.get("HEADER");
		Map bodyMap = (Map) responseMap.get("BODY");
		List<ICBCBatchDKDetail> details = JSONObject.parseArray(bodyMap.get("DETAIL").toString(), ICBCBatchDKDetail.class);
	}
	
	
	
	/**
	 * 使用fastjson  JSONObject.toJSONString 默认情况下不序列null值
	 * 把java 转成 jsonstr
	 * {
"BODY":
{"DETAIL":[{"RETMSG":"�ɹ�","NOTE":"","CARNO":"³B11133","ACTAMT":"1","AMOUNT":"1","RETCODE":"002","SIGNNO":"1000000000000010156","ORDERNO":"19062016750319"},
{"RETMSG":"�ɹ�","NOTE":"","CARNO":"³A11144","ACTAMT":"2","AMOUNT":"2","RETCODE":"002","SIGNNO":"1000000000000010157","ORDERNO":"19062016750320"},{"RETMSG":"�ɹ�","NOTE":"","CARNO":"³A11177","ACTAMT":"3","AMOUNT":"3","RETCODE":"002","SIGNNO":"1000000000000020160","ORDERNO":"19062016750321"},
{"RETMSG":"�ɹ�","NOTE":"","CARNO":"³B11881","ACTAMT":"4","AMOUNT":"4","RETCODE":"002","SIGNNO":"1000000000000010159","ORDERNO":"19062016750322"}],
"TOTALNUM":"4","TOTALAMT":"10","SUCNUM":"4","SUCAMT":"10"},
"HEADER":{"REMARK":"","REQFLAG":"0","CORPNO":"00","TRCODE":"10001","TRDATE":"2019-06-21 19:39:50","REQNO":"ICBC-00-20190621-1"}
}
	 */
	@Test
	public void testobjecttostring(){
		
		ICBCBatchDKRequest request = new ICBCBatchDKRequest();
		ICBCBatchDKHeader header = new ICBCBatchDKHeader();
		ICBCBatchDKBody body = new ICBCBatchDKBody();
		header.setREQFLAG("0");
		header.setTRCODE("10000");
		header.setTRDATE("201911191423");
		header.setREQNO("88889999");
		header.setCORPNO("01");
		
		body.setTOTALAMT("2");
		body.setTOTALNUM("2");
		List<ICBCBatchDKDetail> details = new ArrayList<ICBCBatchDKDetail>();
		
		ICBCBatchDKDetail detail = new ICBCBatchDKDetail();
		detail.setORDERNO("123");
		detail.setCARNO("adfa");
		detail.setAMOUNT("1");
		detail.setSIGNNO("adfefe");
		
		
		ICBCBatchDKDetail detail1 = new ICBCBatchDKDetail();
//		detail1.setORDERNO("123");
		detail1.setCARNO("adfa");
		detail1.setAMOUNT("1");
		detail1.setSIGNNO("adfefe");
		
		details.add(detail);
		details.add(detail1);
		
		body.setDETAIL(details);
		request.setHEADER(header);
		request.setBODY(body);
		System.out.println(JSONObject.toJSONString(request)); //默认情况下序列化null
		System.out.println(JSONObject.toJSONString(request,SerializerFeature.WriteMapNullValue)); 
	}
	
	
	

	
}
