package com.alipaydaikou.until;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * @Written by : zhoujie
 * @Creation Date : Sep 21, 2015 6:18:05 PM
 * @version : v1.00
 * @Description : 读取xml数据工具类
 * 
 */
public class ReadXmlUtil
{
    
	private static final Logger log = LoggerFactory.getLogger(ReadXmlUtil.class);
    
    // java自带的DOM解析.
    public static Map<String, String> ReaderXmlForDOM(String protocolXML)
    {
        Map<String, String> map = new HashMap<String, String>();
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(protocolXML)));
            
            Element root = doc.getDocumentElement();
            NodeList books = root.getChildNodes();
            
            if (books != null)
            {
                for (int i = 0; i < books.getLength(); i++)
                {
                    Node book = books.item(i);
                    String value = book.getFirstChild().getNodeValue();
                    map.put(book.getNodeName(), value != null ? value.trim() : value);
                    System.out.println("节点=" + book.getNodeName() + "\ttext=" + book.getFirstChild().getNodeValue());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return map;
    }
    
    // java自带的SAX解析.
    public static Map<String, String> ReaderXmlForSAX(String protocolXML)
    {
        if (StringUtils.isEmpty(protocolXML))
            return new HashMap<String, String>();
        
        MySAX tsax = new MySAX();
        try
        {
            SAXParserFactory saxfac = SAXParserFactory.newInstance();
            SAXParser saxparser = saxfac.newSAXParser();
            saxparser.parse(new InputSource(new StringReader(protocolXML)), tsax);
            System.out.println("------ReaderXmlForSAX-------------");
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return tsax.getMap();
    }
    
    // DOM4j解析XML
    public static Map<String, String> ReaderXmlForDOM4J(String protocolXML)
    {
        Map<String, String> map = new HashMap<String, String>();
        try
        {
            org.dom4j.Document doc = (org.dom4j.Document)DocumentHelper.parseText(protocolXML);
            org.dom4j.Element books = doc.getRootElement();
            System.out.println("根节点" + books.getName());
            // Iterator users_subElements =
            // books.elementIterator("UID");//指定获取那个元素
            Iterator<?> Elements = books.elementIterator();
            while (Elements.hasNext())
            {
                org.dom4j.Element user = (org.dom4j.Element)Elements.next();
                log.debug("节点" + user.getName() + "\ttext=" + user.getText());
                map.put(user.getName(), user.getText());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return map;
    }
    
    public static Map<String, String> getSignMap(Map<String, String> paraMap)
    {
        
        if (paraMap == null)
        {
            return null;
        }
        
        paraMap.remove("request");
        paraMap.remove("alipay");
        paraMap.remove("response");
        paraMap.remove("param");
        
        Map<String, String> tmpMap = new HashMap<String, String>();
        for (String key : paraMap.keySet())
        {
            tmpMap.put(key, paraMap.get(key));
        }
        tmpMap.remove("sign_type");
        tmpMap.remove("is_success");
        return tmpMap;
    }
    
    public static void main(String[] args) {
		
	}
}
