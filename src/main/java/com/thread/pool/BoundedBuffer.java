package com.thread.pool;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();//写条件
	final Condition notEmpty = lock.newCondition();//读条件

	final Object[] items = new Object[100];
	int putptr, takeptr, count;

	public void put(Object x) throws InterruptedException {
		System.out.println("put wait lock");
		lock.lock();
		System.out.println("put get lock");
		try {
			while (count == items.length) {
				System.out.println("buffer full, please wait");
				notFull.await();//写满了，写线程阻塞
			}

			items[putptr] = x;
			if (++putptr == items.length)
				putptr = 0;
			++count;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public Object take() throws InterruptedException {
		System.out.println("take wait lock");
		lock.lock();
		System.out.println("take get lock");
		try {
			while (count == 0) {
				System.out.println("no elements, please wait");
				notEmpty.await();//空时，读线程阻塞
			}
			Object x = items[takeptr];
			if (++takeptr == items.length)
				takeptr = 0;
			--count;
			notFull.signal();
			return x;
		} finally {
			lock.unlock();
		}
	}
}
