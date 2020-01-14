package com.dflate.andbase64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author li_hhui
 * @date:2019年5月7日
 * @version:
 * dflate压缩 base64编码===base64解码 Inflater解压
 * 1.可用于文本流的传输
 * 
 */
public class CommonUtil {

	private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);
	
	/**
	 * 功能：将批量文件内容使用DEFLATE压缩算法压缩，Base64编码生成字符串并返回<br>
	 * 适用到的交易：批量<br>
	 * 
	 * @param filePath
	 *            批量文件-全路径文件名<br>
	 * @return
	 */
	public static String enCodeFileContent(String fileContent, String encoding) {
		String baseFileContent = "";
/*
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				log.info(e.getMessage(), e);
			}
		}*/
//		InputStream file1 = new ByteArrayInputStream(fileContent.toString().getBytes());

		InputStream in = null;
		try {
//			in = new FileInputStream(file);
			in = new ByteArrayInputStream(fileContent.toString().getBytes());
			int fl = in.available();
			if (null != in) {
				byte[] s = new byte[fl];
				in.read(s, 0, fl);
				// 压缩编码.
				baseFileContent = new String(base64Encode(s), encoding);
			}
		} catch (Exception e) {
			log.info(e.getMessage(), e);
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					log.info(e.getMessage(), e);
				}
			}
		}
		return baseFileContent;
	}

	  
	public static byte[] base64Encode(byte[] inputByte) throws IOException {
		int compressedDataLength = 0;
		Deflater compresser = new Deflater();
		compresser.setInput(inputByte);
		compresser.finish();
		ByteArrayOutputStream o = new ByteArrayOutputStream(inputByte.length);
		byte[] result = new byte[1024];
		try {
			while (!compresser.finished()) {
				compressedDataLength = compresser.deflate(result);
				o.write(result, 0, compressedDataLength);
			}
		} finally {
			o.close();
		}
		compresser.end();
		return Base64.encodeBase64(o.toByteArray());
	}
	
	
	/**
	 * 使用 DEFLATE 压缩算法压缩后， Base64 编码的方式传输经压缩编码的文件内容及解析
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		StringBuffer str = new StringBuffer();

		for (int i = 0; i < 1000; i++) {
			str.append("16613157|20190402|20190401112737|支付宝(订单创建)|1|13201508|失败|未冲正|未撤销").append("\n");
		}
		System.out.println("原文:");
		System.out.println(str.toString());
		String fileContent = CommonUtil.enCodeFileContent(str.toString(), "UTF-8");
		System.out.println("压缩编码后:"+fileContent);
		System.out.println("解压解码后:"+getFileContent(fileContent, "UTF-8"));
	}
	
	
	
	
	public static String getFileContent(String fileContent, String encoding) {
		String fc = "";
		try {
			fc = new String(inflater(base64Decode(fileContent.getBytes())), encoding);
		} catch (UnsupportedEncodingException e) {
			log.info(e.getMessage(), e);
		} catch (IOException e) {
			log.info(e.getMessage(), e);
		}
		return fc;
	}
	
	public static byte[] inflater(final byte[] inputByte) throws IOException {
		int compressedDataLength = 0;
		Inflater compresser = new Inflater(false);
		compresser.setInput(inputByte, 0, inputByte.length);
		ByteArrayOutputStream o = new ByteArrayOutputStream(inputByte.length);
		byte[] result = new byte[1024];
		try {
			while (!compresser.finished()) {
				compressedDataLength = compresser.inflate(result);
				if (compressedDataLength == 0) {
					break;
				}
				o.write(result, 0, compressedDataLength);
			}
		} catch (Exception ex) {
			log.info("Data format error!\n");
			ex.printStackTrace();
		} finally {
			o.close();
		}
		compresser.end();
		return o.toByteArray();
	}
	
	
	
	public static byte[] base64Decode(byte[] inputByte) throws IOException {
		return Base64.decodeBase64(inputByte);
	}
	
}
