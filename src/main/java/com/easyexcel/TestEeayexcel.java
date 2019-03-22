package com.easyexcel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.easyexcel.TerminalTransBean;


public class TestEeayexcel {

	@Test
	public void testExcel() {
		
		try {
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		OutputStream out = new FileOutputStream("D:/学习资料/"+df.format(new Date())+".xlsx");
		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX,true);
		Sheet sheet = new Sheet(1, 0, TerminalTransBean.class);
		sheet.setSheetName("sheet1");
		List<TerminalTransBean> data = new ArrayList();
		TerminalTransBean bean =new TerminalTransBean();
		bean.setAddress("济南历下");
		bean.setReturnCount(9);
		
		data.add(bean);
		TerminalTransBean bean1 =new TerminalTransBean();
		bean1.setAddress("济南高新区");
		bean1.setReturnCount(3);
		data.add(bean1);
		writer.write(data, sheet);
		writer.finish();
		out.close();
		
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
		
		
	}
		

	
}