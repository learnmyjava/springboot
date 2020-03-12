package com.java.callback.learn;

/**
 * @author li_hhui
 * @date:2020年3月12日
 * @version:
 */
public class Student {

	public void answer(CallBack callBack,String question){
		System.out.println("学生在思考"+question);
		try {
			Thread.sleep(2000);
			String answer="1+1=2";
			callBack.answerCallBack(answer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
