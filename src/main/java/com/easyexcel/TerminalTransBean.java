package com.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class TerminalTransBean extends BaseRowModel{
	@ExcelProperty(index = 0)
	private String address;
	@ExcelProperty(index = 1)
	private int returnCount;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getReturnCount() {
		return returnCount;
	}
	public void setReturnCount(int returnCount) {
		this.returnCount = returnCount;
	}
	
	
}
