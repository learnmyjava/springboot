package com.example.SpringBoot;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import springBoot.Application;


/**
 * 测试启动springboot
 * @Author lihh
 * @Time 2019年5月9日 上午10:01:42
 * @Version 1.0
 * Description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Junit4Test {
	private static final Logger LOG = LoggerFactory.getLogger(Junit4Test.class);

	@Autowired
//	private TransContractServiceImpl transContractServiceImpl;
	
	@Test
	public void test01TransContract() {
//		transContractServiceImpl.transContract();
	}
	/**
	 * trace < debug < info < warn < error
	 */
	@Test
	public void testLog(){
		
		LOG.info("---------info级别日志-------------");
		LOG.warn("---------warn级别日志--------------");
		LOG.error("----------error级别日志-----------");
	}
	
	
	public static String  formatUTC(String  datestr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat utcSdf  = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Calendar cal = Calendar.getInstance();
		// 取得时间偏移量：
		int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
		// 取得夏令时差：
		int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
		Date dateValue = sdf.parse(datestr);
		long longDate = dateValue.getTime();
		longDate = longDate - zoneOffset - dstOffset;
		Date UTCDate = new Date(longDate);
		return utcSdf.format(UTCDate);
	}
	
	public static String parseUTC(String utcDate) throws ParseException {
	    SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	    utcDate = utcDate.replace("Z", " UTC"); //注意UTC前有空格
	      Date date = utcFormat.parse(utcDate);
	      return sdf.format(date);
	}

public static void main(String[] args) throws ParseException {
//	String utcString =	formatUTC("20191112124312");
//	System.out.println(parseUTC(utcString));
	
	String string = "{dfasdf:dfsdf,dfadsfa=213r434,3253=lihonghui}";
	System.out.println(string.substring(0,string.length()-1)+",\"sss\":"+"\""+"hello"+"\"}");
	/*int s = (int) new Date().getTime();
	System.out.println(s);*/
}



}
