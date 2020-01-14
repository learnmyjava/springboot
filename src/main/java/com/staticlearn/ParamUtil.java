package com.staticlearn;

import net.bytebuddy.asm.Advice.This;

import org.springframework.context.support.StaticApplicationContext;

/**
 * @author li_hhui
 * @date:2020年1月7日
 * @version:
 */
public class ParamUtil {
	private String name;
	private String address;

	
	private static String static_string ="静态变量";
	
	public   static final  String  final_static_string="这是final static 常量";

	public final String  final_string="这是final常量";
	public static String getStatic_string() {
		
		return static_string;
	}

	public static void setStatic_string(String static_string) {
		ParamUtil.static_string = static_string;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static String getFinalStaticString() {
		return final_static_string;
	}

	public String getFinal_string() {
		return final_string;
	}
	


}
