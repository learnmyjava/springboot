package com.alipaydaikou.until;

import java.nio.charset.Charset;

public class HttpRequestConfig {
	private String contentType = "application/x-www-form-urlencoded;charset=UTF-8";
	private Charset charset;
	private boolean poolSwitch = true;
	private Integer poolingNum;
	private Integer socketTimeout;
	private Integer connectTimeout;
	private String proxyIp;
	private Integer proxyPort;

	public boolean isPoolSwitch() {
		return this.poolSwitch;
	}

	public void setPoolSwitch(boolean poolSwitch) {
		this.poolSwitch = poolSwitch;
	}

	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Charset getCharset() {
		return this.charset;
	}

	public void setCharset(Charset charset) {
		this.charset = charset;
	}

	public Integer getPoolingNum() {
		return this.poolingNum;
	}

	public void setPoolingNum(Integer poolingNum) {
		this.poolingNum = poolingNum;
	}

	public Integer getSocketTimeout() {
		return this.socketTimeout;
	}

	public void setSocketTimeout(Integer socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public Integer getConnectTimeout() {
		return this.connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public String getProxyIp() {
		return this.proxyIp;
	}

	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}

	public Integer getProxyPort() {
		return this.proxyPort;
	}

	public void setProxyPort(Integer proxyPort) {
		this.proxyPort = proxyPort;
	}
}
