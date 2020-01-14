package com.staticlearn;

/**
 * @author li_hhui
 * @date:2020年1月7日
 * @version: 本类旨在对于static的学习使用
 * 
 *           static 常用来修饰变量 方法 ，被static修饰的变量/方法 是不依附于对象实例，即访问他们时使用类名.就可以访问，
 *           无需创建实例 ，起到优化程序性能的作用，在系统启动时类被首次加载时 他们就被创建了。 static 修饰 的
 *           变量(静态变量或者静态代码块)是被同一个类的所有实例共享，在内存中只存在一个副本;非静态变量，同一个类
 *           在创建出多个实例时，非静态变量存在多个副本，不同实例之间互不影响
 *           非静态的变量是依附于对象实例的，在对象被实例化之后，通过实例是访问。所以静态方法中不用访问非静态方法和非静态变量
 *           正常情况下需要进行一次 初始化操作可以使用static
 * 
 * 
 */
public class LearnStaticTest {

	public static void main(String[] args) {

		/*
		 * System.out.println(ParamUtil.getStatic_string());
		 * 
		 * ParamUtil.setStatic_string("静态变量被修改");
		 * 
		 * System.out.println(ParamUtil.getStatic_string());
		 */

		/* LearnStaticTwo 定义 private ParamUtil paramUtil = new ParamUtil();如下 */

		/*
		 * com.staticlearn.ParamUtil@6d06d69c山东 济南
		 * com.staticlearn.ParamUtil@7852e922安徽 宿州 非静态的依附于对象 不同的实例 存在多个副本
		 */
		/*
		 * LearnStaticTwo two = new LearnStaticTwo();
		 * two.changeParamUtil("山东","济南");
		 * System.out.println(two.getParamUtil()+
		 * two.getParamUtil().getAddress()+" "+two.getParamUtil().getName());
		 * 
		 * LearnStaticTwo two1 = new LearnStaticTwo();
		 * two1.changeParamUtil("安徽","宿州");
		 * System.out.println(two1.getParamUtil(
		 * )+two1.getParamUtil().getAddress(
		 * )+" "+two1.getParamUtil().getName());
		 */

		// LearnStaticTwo 定义 private static final ParamUtil paramUtil = new
		// ParamUtil();

		/*
		 * com.staticlearn.ParamUtil@6d06d69c山东 济南
		 * com.staticlearn.ParamUtil@6d06d69c安徽 宿州
		 * 静态变量对于同一个类的不同实例，在内存中只存在一个副本(只有一个内存地址)
		 */
		LearnStaticTwo two = new LearnStaticTwo();
		two.changeParamUtil("山东", "济南");
		System.out.println(two.getParamUtil() + two.getParamUtil().getAddress()
				+ " " + two.getParamUtil().getName());

		LearnStaticTwo two1 = new LearnStaticTwo();
		two1.changeParamUtil("安徽", "宿州");
		System.out.println(two1.getParamUtil()
				+ two1.getParamUtil().getAddress() + " "
				+ two1.getParamUtil().getName());

		LearnStaticThree three = new LearnStaticThree();
		three.changeParamUtil("安徽", "合肥");
		System.out.println(three.getParamUtil()
				+ three.getParamUtil().getAddress() + " "
				+ three.getParamUtil().getName());

		// final 修饰的变量被赋值之后不可以改变
		System.out.println(ParamUtil.final_static_string);
		ParamUtil aParamUtil = new ParamUtil();
		System.out.println(aParamUtil.final_string);

	}
}
