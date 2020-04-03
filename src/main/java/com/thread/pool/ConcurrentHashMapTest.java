package com.thread.pool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author li_hhui
 * @date:2020年3月31日
 * @version:
 * HashMap 不是线程安全
 * ConcurrentHashMap 线程安全https://blog.csdn.net/hulinku/article/details/79647015
 */
public class ConcurrentHashMapTest {

	public static void main(String[] args) {
		
		Map<Object, Object> hashMap = new HashMap<Object, Object>();
		for (int i = 0; i < 10000; i++) {
			
			new Thread(new Runnable() {
				public void run() {
					double a = Math.random();
					hashMap.put(a, a);
					hashMap.remove(a);
					
				}
			}).start();
		}
		
		try {
			Thread.sleep(15000);
			System.out.println(hashMap.size());
			Iterator it = hashMap.entrySet().iterator();
			System.out.println("===============================");
			
			Iterator entryIterator = hashMap.entrySet().iterator();
			while (entryIterator.hasNext()) {
				Map.Entry entry= (Entry) entryIterator.next();
				System.out.println(entry.getKey()+":"+entry.getValue());
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
