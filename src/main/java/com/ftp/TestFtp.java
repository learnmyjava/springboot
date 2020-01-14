package com.ftp;

import org.junit.Test;

/**
 * @author li_hhui
 * @date:2019年8月29日
 * @version:
 */
public class TestFtp {
	 /**
	  * 下载文件
	  */
	@Test
	public void testDownload(){
		
		String ftpip="";
    	String  ftpport="21";
    	String ftpuser="";
    	String ftppass="";
    	String ftppath="/pub/";//ftp文件位置
    	String path="F:/juhe/";//存放商户批扣文件和银行文件的本地位置
    	
    	
    	String ftpFilePath=ftppath+"req_379901_2019-08-29_0001.txt";//ftp上 文件名及路径
    	String localFilePath=path+"req_379901_2019-08-29_0001.txt";//要下载到本地的文件名及路径
    	
        FTPClientUtil ftpClient = new FTPClientUtil(ftpip,ftpport,ftpuser,ftppass);
        boolean downloadResult = ftpClient.getFile(ftpFilePath, localFilePath);

        if(downloadResult){
        	System.out.println("下载成功");
        }else{
        	System.out.println("下载失败");
        }

	}
	
	/**
	 * 上传文件
	 */
	@Test
	public void testupload(){
		
		String ftpip="";
    	String  ftpport="21";
    	String ftpuser="";
    	String ftppass="";
    	String ftppath="/pub/";//ftp文件位置
    	String path="F:/juhe/";//存放商户批扣文件和银行文件的本地位置
    	
    	
    	String ftpFilePath=ftppath+"publicKey.keystore";//ftp文件名及路径
    	String localFilePath=path+"publicKey.keystore";//上传的本地文件名及路径
        FTPClientUtil ftpClient = new FTPClientUtil(ftpip,ftpport,ftpuser,ftppass);

        boolean uploadResult = ftpClient.putFile(ftpFilePath, localFilePath);
        if(uploadResult){
        	System.out.println("上送成功");
        }else{
        	System.out.println("上送失败");

        }

	}
		
	
}
