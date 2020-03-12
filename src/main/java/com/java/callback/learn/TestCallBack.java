package com.java.callback.learn;

import org.junit.Test;

/**
 * @author li_hhui
 * @date:2020年3月12日
 * @version:
 */
public class TestCallBack {
	@Test
	public void test1(){
		Student student = new Student();
		Teacher teacher = new Teacher(student);
		teacher.question();
	}
	

}
