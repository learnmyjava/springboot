package com.example.SpringBoot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import springBoot.entity.User;

/**
 * @author li_hhui
 * @date:2020年3月30日
 * @version:
 * 	序列化
	就是将内存中的对象转换为字节流，方便持久化或网络传输。序列化后的字节流保存了java对象的状态 (-》"xuliugen", "123456", "male"）(不管对象的方法)以及相关的描述信息。
	反序列化 
	客户端从文件中或者网络上获取的字节流。通过反序列化重建对象。
	序列化的核心作用就是对对象状态的保存和重建。
	
	如何实现
	java.io.ObjectOutputStream  表示对象输出流,其writeObject(Object obj) 方法可以将指定对象进行序列化，并把得到的字节数组写到一个目标输出流中。
	
	java.io.ObjectInputStream 表示对象输入流，其 readObject() 方法 可以从输入流中读取字节数组，再把他们反序列化成为一个对象。
	
	实现前提条件
	只有实现了Serializable或Externalizable接口的类的对象才能被序列化，否则抛出异常！
 */
public class SerializableTest {

	@Test
	public void testSerializable(){
		
		User user = new User("tom", "12345678", 12, "安徽合肥");
		
		try {
			FileOutputStream outputStream = new FileOutputStream("F:\\object.out");//序列化磁盘文件
			ObjectOutputStream outStream = new ObjectOutputStream(outputStream);
			outStream.writeObject(user);
			outStream.flush();
			outputStream.close();
			//从磁盘获取序列化的字节流 反序列化为对象
			FileInputStream inputStream = new FileInputStream("F:\\object.out");
			ObjectInputStream inStream = new ObjectInputStream(inputStream);
			User u =(User) inStream.readObject();
			System.out.println(JSON.toJSONString(u));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
