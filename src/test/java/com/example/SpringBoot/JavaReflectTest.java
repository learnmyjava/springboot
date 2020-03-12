package com.example.SpringBoot;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;














import org.apache.commons.lang.StringUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alipaydaikou.until.ConvertTools;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import springBoot.Application;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class JavaReflectTest {
	private static final Logger LOG = LoggerFactory.getLogger(JavaReflectTest.class);
	
	/**
	 * JSONObject默认不序列null
	 */
	@Test
	public void testJSON() {
		/*Map map = new HashMap();
		map.put("plateNumber", "鲁A88888");
		map.put("refundAmount", 12);
		map.put("refundId", "12345677");
		
		String data = JSONObject.fromObject(map).toString();
		System.out.println(data);
		
		Map head = new HashMap();
		head.put("appId", "SDDJKD");
		head.put("timestamp", 1234567);
	
		head.put("data", data);
		System.out.println(JSONObject.fromObject(head).toString());*/
		
		/*TestVO vo = new TestVO();
		vo.setAddress(null);
		vo.setAge("12");
		vo.setName("");
		String strjson = JSONObject.toJSONString(vo);//默认不序列null
		System.out.println(strjson);
		Map map = JSONObject.parseObject(strjson);
		System.out.println(map.get("age"));*/
		Integer i =1234566;
		Integer a = 1234566;
		System.out.println(i.equals(a));
	}

	/**
	 * 在jvm运行时状态中，对于任意一个类，都能知道该类的所以属性和方法
	 * 对于任意一个对象，都可以调用其任意一个方法和属性
	 * 这种动态获取信息以及动态调用对象的方法的功能称为 java反射机制
	 * 
	 * 静态编译:在编译阶段就确定类型
	 * 动态编译:运行时确实类型 绑定对象，最大限度的发挥了java灵活性，体现了多态的应用。 降低了类之间的耦合性。
	 */
	@Test
	public void testreflact(){
		try {
			Class<?> testclazz = Class.forName("com.example.SpringBoot.Person");
			Person testVO = (Person) testclazz.newInstance();
			
			Constructor<?>[] publiccon=testclazz.getConstructors();//获取所有的公有构造方法
			for(Constructor c:publiccon){
//				System.out.println("public构造方法="+c);
			}
			Constructor<?>[] allConstructors = testclazz.getDeclaredConstructors();//获取所有的构造方法
			for(Constructor c:allConstructors){
//				System.out.println("所有的构造方法"+c);
			}
			
			Constructor<?> constructor=testclazz.getDeclaredConstructor(int.class);//获取指定参数的构造方法(公有或者私有)
			
//			System.out.println("指定参数匹配的构造方法"+constructor);
			
		/*	System.out.println(testVO);
			testVO.setAddress(12);
			System.out.println("testvo="+testVO.getAddress());
			testVO.setName("tom");//私有的没有权限更改
			System.out.println(testVO.getName());*/
			
			Field[] fields = testclazz.getFields();//公有属性
			for(Field f:fields){
//				System.out.println("===="+f);
			}
			
			Field[] fields2 =testclazz.getDeclaredFields();//所有的属性
			for(Field field :fields2){
//				System.out.println(field);
			}
			
			
			Field f = testclazz.getDeclaredField("name");
			f.setAccessible(true);//暴力发射 不推荐
			f.set(testVO, "jack");
//			System.out.println(testVO.toString());//暴力修改了Person的私有属性值
			
			Method[] methods = testclazz.getMethods();
			for (Method method :methods){
				
//				System.out.println(method);
			}
			
			Method[] methods2 = testclazz.getDeclaredMethods();
			for(Method method:methods2){
//				System.out.println(method);
			}
			
			Method m = testclazz.getDeclaredMethod("privatemethod");
			System.out.println(m);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * java反射机制中class.forName和classloader区别
	 * 类加载过程 表现为 加载 链接 初始化
	 *Class.forName() 除了执行了将类.class加载到jvm中，还对类就行了解释，即执行了类的static 代码块
	 * Classloader.getClassLoader()则不会执行static 代码块
	 * 
	 * 原理 由Class.forName 底层其实就是通过classloader 加载类，但是它去设置了类被加载后是否执行static代码块，默认设置成true
	 */
	@Test
	public void testloader(){
		
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		try {
			Class<?> loadervo = classLoader.loadClass("com.example.SpringBoot.Person");
			System.out.println("classLoader加载="+loadervo.getName());
			System.out.println("===============");
			Class<?> classfornamevo = Class.forName("com.example.SpringBoot.Person");
			System.out.println(classfornamevo.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
