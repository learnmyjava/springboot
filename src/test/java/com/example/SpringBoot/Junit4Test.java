package com.example.SpringBoot;
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
}
