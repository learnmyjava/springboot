package com.sftp;


import com.jcraft.jsch.ChannelSftp;
import org.junit.jupiter.api.Test;

/**
 * @author li_hhui
 * @date:2020年1月14日
 * @version:
 * 使用com.jcraft.jsch 进行SFTP下载文件
 */
public class TESTSftp {

	@Test
	public void downloadfromSftp(){
		ChannelSftp sftp1 = new ChannelSftp();
		
		String ftpUserName="sftp登录名/用户名";
		String ftpPassword="sftp登录密码";
		String ftpHost="sftp ip";
		String downloadFile="/home/abc/pub/lihonghui.txt";//sftp上 文件目录+文件名
		String localFile="/localhost/lihonghui.txt";//下载本地的  文件目录+文件名
		int ftpPort=333;
		try {
			System.out.println("创建sftp连接");
			sftp1 = JSchUtils.connect(ftpUserName, ftpPassword, ftpHost, ftpPort);
			System.out.println("开始下载文件");
			sftp1.get(downloadFile, localFile);
			
		} catch (Exception e) {
			System.out.println(e.toString());
			if (e.toString().equals("2: No such file")) {  
				System.out.println("--->>>downloadFile--ftp下载文件失败," + downloadFile+" 不存在!");
            }
		}finally{
			sftp1.disconnect();
			System.out.println("sftp连接关闭");
		}
		
				
	}
	
	
	
}
