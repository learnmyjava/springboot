package com.java.callback.learn;

import org.springframework.ui.context.Theme;


/**
 * @author li_hhui
 * @date:2020年3月12日
 * @version:
 */
public class Teacher implements CallBack{
	private Student student;
	public Teacher(Student student) {
		this.student=student;
	}
	
	/**
	 * 老师提问问题  学生思考问题  老师做别的事(提问问题交个线程处理)
	 * 学生回答问题
	 */
	public void question(){
		
		Runnable runnable= new Runnable() {
			@Override
			public void run() {
				String question="1+1=?";
				System.out.println("老师说:"+question);
				student.answer(Teacher.this, question);
				
			}
		};
		new Thread(runnable).start();
		try {
			
			Thread.sleep(5000);//模拟老师做其他事
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 老师收到学生的答案
	 */
	@Override
	public void answerCallBack(String answer) {
		
		System.out.println("学生说："+answer);
	}

}
