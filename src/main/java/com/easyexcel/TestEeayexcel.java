package com.easyexcel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.easyexcel.TerminalTransBean;
import org.junit.jupiter.api.Test;

/**
 * 使用eazyexcel 导出excel
 * @author lihonghui
 *
 */
public class TestEeayexcel {

	@Test
	public void testExcel() {
		
		try {
			int i =0;
			int aa=100;
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		OutputStream out = new FileOutputStream("D:/学习资料/"+df.format(new Date())+".xlsx");
		ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX,true);
		Sheet sheet = new Sheet(1, 0, TerminalTransBean.class);
		sheet.setSheetName("sheet1");
		List<TerminalTransBean> data = new ArrayList();
		TerminalTransBean bean =new TerminalTransBean();
		bean.setAddress("中国安徽合肥");
		bean.setReturnCount(9);
		
		data.add(bean);
		TerminalTransBean bean1 =new TerminalTransBean();
		bean1.setAddress("济南高新区某某街道");
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
