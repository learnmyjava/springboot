package com.staticlearn;

/**
 * @author li_hhui
 * @date:2020年1月8日
 * @version:
 */
public class LearnStaticTwo {
	private static final ParamUtil paramUtil = new ParamUtil();
	
	public void changeParamUtil(String address,String name){
		
		paramUtil.setAddress(address);
		paramUtil.setName(name);
		
	}


	public ParamUtil getParamUtil() {
		return paramUtil;
	}


/*	public void setParamUtil(ParamUtil paramUtil) {
		this.paramUtil = paramUtil;
	}*/


	
	
	
}
