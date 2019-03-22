package com.alipaydaikou.until;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/*******************************************************************************
 * xml通用工具类
 * @author www.zuidaima.com
 */
@SuppressWarnings("unchecked")
public class XMLUtils
{
	public static String xml = "";

	public static void init()
	{
		xml = "";
	}

	/***************************************************************************
	 * 得到指定名称节点下的所有文本内容,包括节点(逆归) <暂不考虑节点属性情况>
	 * 
	 * @param doc
	 *            xml文档对象
	 * @param e
	 *            要获取的节点对象
	 * @param exceptTag
	 *            要排除的节点名称
	 * @return
	 */

	public static String getChildAllText(Document doc, Element e)
	{
		if (e != null)
		{
			if (e.getChildren() != null)
			{
				List<Element> list = e.getChildren();
				xml += "<" + e.getName() + ">";
				for (Element el : list)
				{
					if(el.getChildren().size() > 0)
					{
						getChildAllText(doc, el);
					}
					else
					{
							xml += "<" + el.getName() + ">" + el.getTextTrim() + "</"
									+ el.getName() + ">";
					}
				}
				xml += "</" + e.getName() + ">" ;
			}
			else
			{
					xml += "<" + e.getName() + ">" + e.getTextTrim() + "</"
							+ e.getName() + ">";
			}
		}
		return xml;
	}

	public static void main(String[] args) throws FileNotFoundException,
			JDOMException, IOException
	{ // 如果有任何异常则抛出
		SAXBuilder sb = new SAXBuilder(); // 新建立构造器
		Document doc = null;
		doc = sb
				.build(new FileInputStream(
						"D:\\test.xml")); // 读入6.xml
		Element root = doc.getRootElement(); // 取得根节点
		// Element e = root.getChild("apptype1");

		// System.out.println(e);
		System.out.println(getChildAllText(doc, root));
	}

}