package com.alipaydaikou.until;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MySAX extends DefaultHandler {
	private Map<String, String> map = new HashMap<String, String>();
	private StringBuffer buf;
	private static final Logger log = LoggerFactory.getLogger(MySAX.class);

	public MySAX() {
		super();
	}

	public void startDocument() throws SAXException {
		buf = new StringBuffer();
		log.debug("*******开始解析XML*******");
	}

	public void endDocument() throws SAXException {
		log.debug("*******XML解析结束*******");
	}

	public void endElement(String namespaceURI, String localName, String fullName) throws SAXException {
		log.debug("节点=" + fullName + "\tvalue=" + buf.toString().trim());
		map.put(fullName, buf.toString().trim());
		buf.delete(0, buf.length());
	}

	public void characters(char[] chars, int start, int length) throws SAXException {
		// 将元素内容累加到StringBuffer中
		buf.append(chars, start, length);
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}